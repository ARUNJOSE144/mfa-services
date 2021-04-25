package com.sixdee.magik.services.controller;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.adaptor.Cache;
import com.sixdee.magik.services.adaptor.FileDownlaodTO;
import com.sixdee.magik.services.adaptor.Response;
import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.service.ActionFileService;
import com.sixdee.magik.services.service.QuarantineRevampService;
import com.sixdee.magik.services.util.MagikServicePath;
import com.thoughtworks.xstream.XStreamException;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 *
 */

@RestController
public class ActionFileDownloadRestContoller {
	static final Logger logger = Logger.getLogger(ActionFileDownloadRestContoller.class);

	@Autowired
	ActionFileService actionFileDownloadService;
	public static ActionFileService actService = null;
	

	@SuppressWarnings("unused")
	@PostMapping(MagikServicePath.INSERT_FILE)
	public @ResponseBody void jsonFormation(HttpServletRequest req, HttpServletResponse resp) {

		logger.info("================== ActionFileDownloadRestContoller =====================");
		logger.info("Class : ActionFileDownloadRestContoller | Method : jsonFormation");
		System.out.println("inside ActionFileDownload ::: ");

		InputStream inputStream = null;
		DataOutputStream out = null;
		StringBuffer sb = null;
		HttpURLConnection connection = null;
		String requestJSON = null;
		String response = null;
		FileDownlaodTO responseTO = null;
		actService = actionFileDownloadService;

		try {
			inputStream = new BufferedInputStream(req.getInputStream(), 32 * 1024);
			sb = new StringBuffer();
			int character = inputStream.read();
			while (character != -1) {

				sb.append((char) character);
				character = inputStream.read();
			}
			System.out.println("ActionFileDownload Request Recieved  :: " + sb.toString());
			if (sb.toString() != null && !sb.toString().equals("")) {
				requestJSON = sb.toString();
				System.out.println("requestJSON  ::  " + requestJSON.toString());
			}
			if (requestJSON == null || requestJSON.trim().equals("")) {
				response = "Request JSON is Empty ::: ";
				System.out.println("response :::: " + response);
				try {
					throw new Exception("Request JSON is Empty ::: ");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			responseTO = validateRequestJSON(requestJSON);
			System.out.println("responseTO  :: " + responseTO.toString());
			if (responseTO != null) {
				Cache.workerPool.addTask(responseTO);
			}
		} catch (IOException e) {
			System.out.println("An Error Occured in jsonFormation  :: ");
			e.printStackTrace();
		}

	}

	private FileDownlaodTO validateRequestJSON(String requestJSON) {
		FileDownlaodTO responseTO = null;
		System.out.println("validateRequestJSON :: " + requestJSON.toString());
		try {
			responseTO = jsonToJaveObject(requestJSON);
			System.out.println("responseTO  Jave Object " + responseTO.toString());
				if (responseTO.getReqTxnId() == null || responseTO.getReqTxnId().trim().equals(""))
					throw new CommonException("No Transactionid in request");
				if (responseTO.getTimestamp() == null || responseTO.getTimestamp().equals(""))
					throw new CommonException("No Timestamp in request");
				if (responseTO.getData() == null)
					throw new CommonException("Parameters are missing");
			
		} catch (XStreamException e) {
			throw new CommonException("Invalid JSON " + requestJSON);
		}
		return responseTO;
	}

	private FileDownlaodTO jsonToJaveObject(String requestJSON) {
		FileDownlaodTO responseTO = null;
		System.out.println("jsonToJaveObject :: " + requestJSON.toString());
		Gson gson = new Gson();
		responseTO = gson.fromJson(requestJSON, FileDownlaodTO.class);
		System.out.println("jsonToJaveObject responseTO  ::  " + responseTO.toString());
		return responseTO;
	}

}
