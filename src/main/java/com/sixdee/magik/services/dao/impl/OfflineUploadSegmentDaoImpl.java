package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.OfflineUploadSegmentDAO;
import com.sixdee.magik.services.model.OfflineUploadSegmentTO;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class OfflineUploadSegmentDaoImpl implements OfflineUploadSegmentDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String offlineNifiUrl = "";
	@Autowired
	private Environment env;

	@Override
	public String saveOfflineSegment(String campaignOperationType, String userId) {
		// TODO Auto-generated method stub
		String status = "0";
		System.out.println("status Daoimpl" + status.toString());
		return status;
	}

	@SuppressWarnings("unused")
	@Override
	public OfflineUploadSegmentTO insertOrDeleteSingle(String campaignOperationType, String userId, String mobileNumber,
			String microSegment) {
		// TODO Auto-generated method stub
		System.out.println("inside saveeOfflineSegmentSingle  : ");
		String status = "1";
		String urlAppender = "";
		int serviceId = 4;
		offlineNifiUrl = env.getProperty("Offline.single.url");

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		OfflineUploadSegmentTO offlineTO = new OfflineUploadSegmentTO();

		String timestamp = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());

		try {

			jsonReqObj.put("requestId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);

			JSONArray array = new JSONArray();

			JSONObject paramObj = new JSONObject();
			paramObj.put("id", "SERVICE_ID");
			paramObj.put("value", serviceId);
			array.put(paramObj);

			JSONObject paramObj2 = new JSONObject();
			paramObj2.put("id", "SEGMENT_MICROSEGMENT");
			paramObj2.put("value", microSegment);
			array.put(paramObj2);

			JSONObject paramObj3 = new JSONObject();
			paramObj3.put("id", "SubscriberNumber");
			paramObj3.put("value", mobileNumber);
			array.put(paramObj3);

			JSONObject paramObj4 = new JSONObject();
			paramObj4.put("id", "OPERATION");
			paramObj4.put("value", campaignOperationType);
			array.put(paramObj4);

			jsonReqObj.put("dataSet", array);

			System.out.println("array  :: " + array.toString());
			System.out.println("jsonReqObj :: " + jsonReqObj.toString());

			System.out.println("offlineNifiUrl  ::" + offlineNifiUrl);
			URL url = new URL(offlineNifiUrl);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");

			connection.setConnectTimeout(15000);
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);

			connection.setRequestProperty("Content-Type", "application/json; utf-8");
			connection.setRequestProperty("charset", "utf-8");
			connection.setRequestProperty("Accept", "application/json");

			out = new DataOutputStream(connection.getOutputStream());
			out.writeBytes(jsonReqObj.toString());
			out.flush();
			out.close();

			inputStream = new BufferedInputStream(connection.getInputStream(), 32 * 1024);
			StringBuffer sb = new StringBuffer();
			int character = inputStream.read();

			while (character != -1) {
				sb.append((char) character);
				character = inputStream.read();
			}
			System.out.println("sb.toString() ::::" + sb.toString());
			if (sb.toString() != null && !sb.toString().equals("")) {
				JSONObject jsonRespObj = new JSONObject(sb.toString());
				String responseCode = (String) jsonRespObj.get("statusCode");
				String count = (String) jsonRespObj.get("count");
				System.out.println("responseCode ::::" + responseCode);
				System.out.println("count ::::" + count);
				System.out.println("jsonRespObj ::::" + jsonRespObj.toString());
				if (responseCode.equals("SC0000")) {
					status = "0";
					offlineTO.setStatus(status);
					offlineTO.setResposeCode(responseCode);
					offlineTO.setCount(count);
				} else {
					status = "1";
					offlineTO.setStatus(status);
					offlineTO.setResposeCode(responseCode);
				}

			}

		} catch (Exception e) {
			status = "1";
			offlineTO.setStatus(status);
			System.out.println("Exception On NIFI  Side :::::");
			e.printStackTrace();
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
					inputStream = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (out != null) {
					out.close();
					out = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

			try {
				if (connection != null)
					connection.disconnect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return offlineTO;

	}

}
