package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.CreateAccountCustmCareDAO;
import com.sixdee.magik.services.model.CreateAccountCustmCareTO;

/**
 * @author Nakhil Kurian
 * @Date : February 2020
 *
 */

@Repository
public class CreateAccountCustmCareDaoImpl implements CreateAccountCustmCareDAO {

	Logger logger = Logger.getLogger(CreateAccountCustmCareDaoImpl.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String createLoyaltyUrl = "";
	String deleteLoyaltyUrl = "";
	String channel = "";
	String languageId = "";
	String softHardDelete = "";
	@Autowired
	private Environment env;

	@Override
	public CreateAccountCustmCareTO CreateAccount(CreateAccountCustmCareTO createAccountTO) {
		System.out.println("inside CreateAccount " + createAccountTO.toString());
		String status = "1";
		String urlAppender = "/createAccount";

		String urlName = env.getProperty("loyaltyCustomerCareUrl");
		createLoyaltyUrl = urlName + urlAppender;
		channel = env.getProperty("channelType");
		languageId = env.getProperty("languageId");
		

		System.out.println("createLoyaltyUrl :  " + createLoyaltyUrl);
		System.out.println("channel :  " + channel);
		System.out.println("languageID :  " + languageId);

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		CreateAccountCustmCareTO CreateTO = new CreateAccountCustmCareTO();

		String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
		System.out.println("timestamp  : " + timestamp);

		try {

			jsonReqObj.put("requestId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);
			jsonReqObj.put("channel", channel);
			jsonReqObj.put("msisdn", createAccountTO.getSubscriberNumber());
			jsonReqObj.put("languageId", languageId);

			System.out.println("Json Request :: " + jsonReqObj.toString());

			URL url = new URL(createLoyaltyUrl);
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
					CreateTO.setStatus(status);
					CreateTO.setResposeCode(responseCode);
					CreateTO.setMessage(statusDescription);
				} else {
					status = "1";
					CreateTO.setStatus(status);
					CreateTO.setResposeCode(responseCode);
					CreateTO.setMessage(statusDescription);
				}

			}

		} catch (Exception e) {
			status = "2";
			CreateTO.setStatus(status);
			System.out.println("Exception On Loyalty Create Account Side  ::");
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

		return CreateTO;

	}

	@Override
	public CreateAccountCustmCareTO deleteAccount(CreateAccountCustmCareTO deleteAccountTO) {
		System.out.println("inside deleteAccountTO " + deleteAccountTO.toString());
		String status = "1";
		String urlAppender = "/deleteAccount";

		String urlName = env.getProperty("loyaltyCustomerCareUrl");
		deleteLoyaltyUrl = urlName + urlAppender;
		channel = env.getProperty("channelType");
		languageId = env.getProperty("languageId");
		softHardDelete = env.getProperty("softOrHardDelete");

		System.out.println("createLoyaltyUrl :  " + deleteLoyaltyUrl);
		System.out.println("channel :  " + channel);
		System.out.println("languageID :  " + languageId);
		System.out.println("softHardDelete :  " + softHardDelete);

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		CreateAccountCustmCareTO deleteTO = new CreateAccountCustmCareTO();

		String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
		System.out.println("timestamp  : " + timestamp);

		try {

			jsonReqObj.put("requestId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);
			jsonReqObj.put("channel", channel);
			jsonReqObj.put("msisdn", deleteAccountTO.getSubscriberNumber());
			jsonReqObj.put("languageId", languageId);

			JSONArray array = new JSONArray();
			JSONObject paramObj = new JSONObject();
			paramObj.put("name", "status");
			paramObj.put("value", softHardDelete);
			array.put(paramObj);
			jsonReqObj.put("data", array);

			System.out.println("Json Request :: " + jsonReqObj.toString());

			URL url = new URL(deleteLoyaltyUrl);
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
					deleteTO.setStatus(status);
					deleteTO.setResposeCode(responseCode);
					deleteTO.setMessage(statusDescription);
				} else {
					status = "1";
					deleteTO.setStatus(status);
					deleteTO.setResposeCode(responseCode);
					deleteTO.setMessage(statusDescription);
				}

			}

		} catch (Exception e) {
			status = "2";
			deleteTO.setStatus(status);
			System.out.println("Exception On Loyalty Delete Account Side  ::");
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

		return deleteTO;

	}

}
