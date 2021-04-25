package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RedeemPointDao;
import com.sixdee.magik.services.model.CreateAccountCustmCareTO;
import com.sixdee.magik.services.model.RedeemPointTO;

/**
 * @author Nakhil Kurian
 * @Date : March 2020
 *
 */

@Repository
public class RedeemPointDaoImpl implements RedeemPointDao {

	Logger logger = Logger.getLogger(RedeemPointDaoImpl.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String getPackageUrl = "";
	String getRedeemPointUrl = "";
	String channel = "";
	String languageId = "";
	@Autowired
	private Environment env;

	@Override
	public RedeemPointTO getPackage(RedeemPointTO redeemPointTO) {
		System.out.println("inside getPackage " + redeemPointTO.toString());
		String status = "1";
		String urlAppender = "/GetPackagesAdapter";

		String urlName = env.getProperty("loyaltyCustomerCareUrl");
		getPackageUrl = urlName + urlAppender;
		channel = env.getProperty("channelType");
		languageId = env.getProperty("languageId");

		System.out.println("getPackageUrl :  " + getPackageUrl);
		System.out.println("channel :  " + channel);
		System.out.println("languageID :  " + languageId);

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		RedeemPointTO pointsTO = new RedeemPointTO();

		String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
		System.out.println("timestamp  : " + timestamp);

		try {

			jsonReqObj.put("transactionId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);
			jsonReqObj.put("channel", channel);
			jsonReqObj.put("msisdn", redeemPointTO.getSubscriberNumber());
			jsonReqObj.put("languageID", languageId);
			jsonReqObj.put("offerType", "-1");

			System.out.println("Json Request :: " + jsonReqObj.toString());

			URL url = new URL(getPackageUrl);
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
				String statusDescription = (String) jsonRespObj.get("statusDescription");
				System.out.println("responseCode ::::" + responseCode);
				System.out.println("jsonRespObj ::::" + jsonRespObj.toString());
				if (responseCode.equals("SC0000")) {
					status = "0";
					pointsTO.setStatus(status);
					pointsTO.setResposeCode(responseCode);
					pointsTO.setMessage(statusDescription);
					pointsTO.setOutputData(jsonRespObj.toString());
				} else {
					status = "1";
					pointsTO.setStatus(status);
					pointsTO.setResposeCode(responseCode);
					pointsTO.setMessage(statusDescription);
				}

			}

		} catch (Exception e) {
			status = "2";
			pointsTO.setStatus(status);
			pointsTO.setMessage("Application Process Error!.");
			System.out.println("Exception On Loyalty  Get Package Side  ::");
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

		System.out.println("status  :  " + status.toString());

		return pointsTO;
	}

	@Override
	public RedeemPointTO getRedeempoint(RedeemPointTO redeemPointTO) {
		System.out.println("inside getRedeempoint " + redeemPointTO.toString());
		String status = "1";
		String urlAppender = "/RedeemPointAdapter";

		String urlName = env.getProperty("loyaltyCustomerCareUrl");
		getRedeemPointUrl = urlName + urlAppender;
		channel = env.getProperty("channelType");
		languageId = env.getProperty("languageId");

		System.out.println("getRedeemPointUrl :  " + getRedeemPointUrl);
		System.out.println("channel :  " + channel);
		System.out.println("languageID :  " + languageId);

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		RedeemPointTO pointsTO = new RedeemPointTO();

		String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
		System.out.println("timestamp  : " + timestamp);

		try {

			jsonReqObj.put("transactionId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);
			jsonReqObj.put("channel", channel);
			jsonReqObj.put("msisdn", redeemPointTO.getSubscriberNumber());
			jsonReqObj.put("packId", redeemPointTO.getPackageId());
			jsonReqObj.put("languageID", languageId);

			System.out.println("Json Request :: " + jsonReqObj.toString());

			URL url = new URL(getRedeemPointUrl);
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
				String statusDescription = (String) jsonRespObj.get("statusDescription");
				System.out.println("responseCode ::::" + responseCode);
				System.out.println("jsonRespObj ::::" + jsonRespObj.toString());
				if (responseCode.equals("SC0000")) {
					status = "0";
					pointsTO.setStatus(status);
					pointsTO.setResposeCode(responseCode);
					pointsTO.setMessage(statusDescription);
				} else {
					status = "1";
					pointsTO.setStatus(status);
					pointsTO.setResposeCode(responseCode);
					pointsTO.setMessage(statusDescription);
				}

			}

		} catch (Exception e) {
			status = "2";
			pointsTO.setStatus(status);
			System.out.println("Exception On Loyalty  Get RedeemPoint Side  ::");
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

		System.out.println("status  :  " + status.toString());

		return pointsTO;
	}

}
