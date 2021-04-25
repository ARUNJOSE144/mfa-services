package com.sixdee.magik.services.controller;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.OptBoolean;
import com.google.gson.Gson;
import com.sixdee.magik.services.adaptor.BlackListAPIReponseStatusTO;
import com.sixdee.magik.services.adaptor.BlackListApiTO;
import com.sixdee.magik.services.adaptor.BlacklistApiResponseTO;
import com.sixdee.magik.services.adaptor.Cache;
import com.sixdee.magik.services.adaptor.FileDownlaodTO;
import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.SpecialDateTO;
import com.sixdee.magik.services.service.BlackListApiService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.thoughtworks.xstream.XStreamException;

/**
 * @author Nakhil Kurian
 * @Date : April 2020
 *
 */

@SuppressWarnings("unused")
@RestController
public class BlackListApiRestController {

	static final Logger logger = Logger.getLogger(BlackListApiRestController.class);

	@Autowired
	BlackListApiService blackListApiService;
	String optIn = null;
	String optOut = null;
	@Autowired
	private Environment env;

	@PostMapping(MagikServicePath.BLACKLIST_API)
	public @ResponseBody BlacklistApiResponseTO updateOrDeleteBlacklist(HttpServletRequest req,
			HttpServletResponse resp) {

		logger.info("================== BlackListApiRestController =====================");
		logger.info("Class : BlackListApiRestController | Method : jsonFormation");
		System.out.println("inside jsonFormation ::: ");

		optIn = env.getProperty("optIn");
		optOut = env.getProperty("optOut");
		InputStream inputStream = null;
		DataOutputStream out = null;
		StringBuffer sb = null;
		HttpURLConnection connection = null;
		String requestJSON = null;
		String response = null;
		BlackListApiTO responseTO = null;
		BlacklistApiResponseTO info = new BlacklistApiResponseTO();

		try {
			inputStream = new BufferedInputStream(req.getInputStream(), 32 * 1024);
			sb = new StringBuffer();
			int character = inputStream.read();
			while (character != -1) {

				sb.append((char) character);
				character = inputStream.read();
			}
			System.out.println("Blacklist Request Recieved  :: " + sb.toString());
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
			String statusValue = "1";
			responseTO = validateRequestJSON(requestJSON, statusValue);
			System.out.println("responseTO  :: " + responseTO.toString());
			System.out.println("responseTO  :: " + responseTO.getStatus().toString());
			if (responseTO.getStatus() != null) {
				if (responseTO.getStatus().toString() == "SC0000") {
					try {
						BlackListApiTO result = blackListApiService.updateBlackListApi(responseTO);
						if (result.getStatus() == "SC0000") {
							if (Integer.parseInt(result.getAction()) == Integer.parseInt(optIn)) {
								info.setStatusCode("SC0000");
								info.setStatusDescription("Number Added To BlackList Policy Successfully.");
							}
							if (Integer.parseInt(result.getAction()) == Integer.parseInt(optOut)) {
								info.setStatusCode("SC0000");
								info.setStatusDescription("Number Removed From BlackList Policy Successfully.");
							}
						} else {
							info.setStatusCode("SC0003");
							info.setStatusDescription("Application Process Error!");
						}
					} catch (DataIntegrityViolationException e) {
						info.setStatusCode("SC0001");
						info.setStatusDescription("Number Already OptIn For DND!");
						e.printStackTrace();
					}

				} else {
					if (responseTO.getStatus().toString() == "SC0004") {
						info.setStatusCode("SC0002");
						info.setStatusDescription("No Input Number In BlackList Policy.");
					}
					if (responseTO.getStatus().toString() == "SC0005") {
						info.setStatusCode("SC0005");
						info.setStatusDescription("Action OptIn(0)/OptOut(1) Required For BlackList Policy");
					}
				}
			}
		} catch (IOException e) {
			System.out.println("An Error Occured in jsonFormation  :: ");
			info.setStatusCode("SC0003");
			info.setStatusDescription("Application Process Error!");
			e.printStackTrace();
		}
		return info;
	}

	@PostMapping(MagikServicePath.BLACKLIST_API_STATUS)
	public @ResponseBody BlackListAPIReponseStatusTO getBlackListStatus(HttpServletRequest req, HttpServletResponse resp) {
		InputStream inputStream = null;
		DataOutputStream out = null;
		StringBuffer sb = null;
		HttpURLConnection connection = null;
		String requestJSON = null;
		String response = null;
		BlackListApiTO responseTO = null;
		BlackListAPIReponseStatusTO info = new BlackListAPIReponseStatusTO();

		try {
			inputStream = new BufferedInputStream(req.getInputStream(), 32 * 1024);
			sb = new StringBuffer();
			int character = inputStream.read();
			while (character != -1) {

				sb.append((char) character);
				character = inputStream.read();
			}
			System.out.println("Blacklist Request Recieved  :: " + sb.toString());
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
			String status = "0";
			responseTO = validateRequestJSON(requestJSON, status);
			System.out.println("responseTO  :: " + responseTO.toString());
			System.out.println("responseTO  :: " + responseTO.getStatus().toString());
			if (responseTO.getStatus() != null) {
				if (responseTO.getStatus().toString() == "SC0000") {
					BlackListApiTO result = blackListApiService.getStatus(responseTO);
					if (result.getStatus() == "SC0000") {
						info.setStatusCode("SC0000");
						info.setStatusDescription("Enable OptOut Number Already Present In DND");
						info.setDndStatus("0");
						info.setBlackNumber(result.getMsisdn());
					} else {
						info.setStatusCode("SC0000");
						info.setStatusDescription("Enable OptIn Number Is Not Present In DND");
						info.setDndStatus("1");
						info.setBlackNumber(result.getMsisdn());
					}

				} else {
					if (responseTO.getStatus().toString() == "SC0004") {
						info.setStatusCode("SC0004");
						info.setStatusDescription("No Input Number In BlackList Policy");
					}
				}
			}
		} catch (IOException e) {
			System.out.println("An Error Occured in jsonFormation  :: ");
			info.setStatusCode("SC0003");
			info.setStatusDescription("Error In Processing!");
			e.printStackTrace();
		}
		return info;

	}

	private BlackListApiTO validateRequestJSON(String requestJSON, String statusValue) {
		BlackListApiTO responseTO = null;
		System.out.println("validateRequestJSON :: " + requestJSON.toString());
		try {
			responseTO = jsonToJaveObject(requestJSON);
			System.out.println("responseTO  Jave Object " + responseTO.toString());
			if (statusValue == "1") {// Update BlackList
				if (responseTO.getMsisdn() == null || responseTO.getMsisdn().trim().equals("")) {
					responseTO.setStatus("SC0004");
					responseTO.setStatusDescription("No MSISDN in Black List Policy");
				} else if (responseTO.getAction() == null || responseTO.getAction().equals("")) {
					responseTO.setStatus("SC0005");
					responseTO.setStatusDescription("No action in Black List Policy");
				} else {
					responseTO.setStatus("SC0000");
				}
			}
			if (statusValue == "0") {//Get Status
				if (responseTO.getMsisdn() == null || responseTO.getMsisdn().trim().equals("")) {
					responseTO.setStatus("SC0004");
					responseTO.setStatusDescription("No MSISDN in Black List Policy");
				} else {
					responseTO.setStatus("SC0000");
				}
			}

		} catch (XStreamException e) {
			throw new CommonException("Invalid JSON " + requestJSON);
		}
		return responseTO;
	}

	private BlackListApiTO jsonToJaveObject(String requestJSON) {
		BlackListApiTO responseTO = null;
		System.out.println("jsonToJaveObject :: " + requestJSON.toString());
		Gson gson = new Gson();
		responseTO = gson.fromJson(requestJSON, BlackListApiTO.class);
		System.out.println("jsonToJaveObject responseTO  ::  " + responseTO.toString());
		return responseTO;
	}
}
