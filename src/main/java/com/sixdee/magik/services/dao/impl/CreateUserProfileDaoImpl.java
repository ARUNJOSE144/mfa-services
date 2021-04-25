package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.CreateUserProfileDao;
import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.CreateAccountCustmCareTO;
import com.sixdee.magik.services.model.CreateUSerProfileTO;

/**
 * @author Nakhil Kurian
 * @Date : February 2020
 *
 */

@Repository
public class CreateUserProfileDaoImpl implements CreateUserProfileDao {

	Logger logger = Logger.getLogger(CreateUserProfileDaoImpl.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String userProfileUrl = "";
	String channel = "";
	String languageId = "";
	@Autowired
	private Environment env;

	private Session session = null;
	private String hql = null;

	@Override
	public CreateUSerProfileTO getUserProfiles(CreateUSerProfileTO profileTO) {
		System.out.println("inside getUserProfiles " + profileTO.toString());
		String status = "1";
		String urlAppender = "/GetUserProfileAdapter";

		String urlName = env.getProperty("loyaltyCustomerCareUrl");
		userProfileUrl = urlName + urlAppender;

		channel = env.getProperty("channelType");
		languageId = env.getProperty("languageId");

		System.out.println("userProfileUrl :  " + userProfileUrl);
		System.out.println("channel :  " + channel);
		System.out.println("languageID :  " + languageId);

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		CreateUSerProfileTO userProfileTO = new CreateUSerProfileTO();

		String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
		System.out.println("timestamp  : " + timestamp);

		try {

			jsonReqObj.put("transactionId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);
			jsonReqObj.put("channel", channel);
			jsonReqObj.put("msisdn", profileTO.getMsisdn());
			jsonReqObj.put("languageID", languageId);

			System.out.println("Json Request :: " + jsonReqObj.toString());

			URL url = new URL(userProfileUrl);
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
				System.out.println("statusDescription ::::" + statusDescription);
				System.out.println("jsonRespObj ::::" + jsonRespObj.toString());
				if (responseCode.equals("SC0000")) {
					status = "0";
					userProfileTO.setData(jsonRespObj.toString());
					System.out.println("data    " + jsonRespObj.toString());
					userProfileTO.setStatus(status);
					userProfileTO.setDescription(statusDescription);
				} else {
					status = "1";
					userProfileTO.setStatus(status);
					userProfileTO.setDescription(statusDescription);
				}

			}

		} catch (Exception e) {
			status = "2";
			userProfileTO.setStatus(status);
			userProfileTO.setDescription("Application Process Error!.");
			System.out.println("Exception On Loyalty User Profile Side  ::");
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

		return userProfileTO;
	}

}
