package com.sixdee.magik.services.dao.impl;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.axis.wsdl.symbolTable.Undefined;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.TransactionHistoryDao;
import com.sixdee.magik.services.model.TransactionHistoryTO;

/**
 * @author Nakhil Kurian
 * @Date : February 2020
 *
 */

@Repository
public class TransactionHistoryDaoImpl implements TransactionHistoryDao {

	Logger logger = Logger.getLogger(TransactionHistoryDaoImpl.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String transHistoryUrl = "";
	String channel = "";
	String languageId = "";
	String noOfMonths = "";
	String offSet = "";
	String limit = "";

	String pattern = "dd-MM-YYYY";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Autowired
	private Environment env;

	private Session session = null;
	private String hql = null;

	@Override
	public TransactionHistoryTO getSearchTransactionHistory(TransactionHistoryTO transTO) {
		System.out.println("inside getSearchTransactionHistory " + transTO.toString());
		String status = "1";
		String urlAppender = "/GetTransactionDetailsAdapter";

		String urlName = env.getProperty("loyaltyCustomerCareUrl");
		transHistoryUrl = urlName + urlAppender;

		channel = env.getProperty("channelType");
		languageId = env.getProperty("languageId");
		noOfMonths = env.getProperty("noOfMonth");
		offSet = env.getProperty("offSet");
		limit = env.getProperty("limit");

		System.out.println("transHistoryUrl :  " + transHistoryUrl);
		System.out.println("channel :  " + channel);
		System.out.println("languageID :  " + languageId);
		System.out.println("noOfMonths :  " + noOfMonths);
		System.out.println("offSet :  " + offSet);
		System.out.println("limit :  " + limit);

		JSONObject jsonReqObj = new JSONObject();

		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		TransactionHistoryTO transHistoryTO = new TransactionHistoryTO();

		String timestamp = new SimpleDateFormat("ddMMyyyyHHmmss").format(new java.util.Date());
		System.out.println("timestamp  : " + timestamp);

		try {

			jsonReqObj.put("transactionId", System.currentTimeMillis());
			jsonReqObj.put("timeStamp", timestamp);
			jsonReqObj.put("channel", channel);
			jsonReqObj.put("msisdn", transTO.getSubscriberNumber());
			jsonReqObj.put("languageID", languageId);
			if (transTO.getStartDate() != null) {
				if (transTO.getEndDate() != null) {
					String startDate = simpleDateFormat.format(transTO.getStartDate());
					String endDate = simpleDateFormat.format(transTO.getEndDate());
					jsonReqObj.put("fromDate", startDate);
					jsonReqObj.put("toDate", endDate);
					jsonReqObj.put("noOfLastTransaction", 0);
				}
			}
			if (transTO.getNoOfTransaction() != null && transTO.getNoOfTransaction() != "") {
				jsonReqObj.put("noOfLastTransaction", Integer.parseInt(transTO.getNoOfTransaction()));
				jsonReqObj.put("fromDate", "");
				jsonReqObj.put("toDate", "");
			}
			jsonReqObj.put("noOfMonths", Integer.parseInt(noOfMonths));
			jsonReqObj.put("offSet", Integer.parseInt(offSet));
			jsonReqObj.put("limit", Integer.parseInt(limit));

			System.out.println("Json Request :: " + jsonReqObj.toString());

			URL url = new URL(transHistoryUrl);
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
					transHistoryTO.setData(jsonRespObj.toString());
					System.out.println("data    " + jsonRespObj.toString());
					transHistoryTO.setStatus(status);
					transHistoryTO.setResposeCode(responseCode);
					transHistoryTO.setMessage(statusDescription);
				} else {
					status = "1";
					transHistoryTO.setStatus(status);
					transHistoryTO.setResposeCode(responseCode);
					transHistoryTO.setMessage(statusDescription);
				}

			}

		} catch (Exception e) {
			status = "1";
			transHistoryTO.setStatus(status);
			transHistoryTO.setMessage("Application Process Error!.");
			System.out.println("Exception On Loyalty Transaction History Side  ::");
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

		return transHistoryTO;
	}

}
