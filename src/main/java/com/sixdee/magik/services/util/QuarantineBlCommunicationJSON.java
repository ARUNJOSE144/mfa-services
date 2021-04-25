package com.sixdee.magik.services.util;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.SessionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import com.sixdee.magik.services.model.QuarantineBLCommunicationTO;
import com.sixdee.magik.services.model.RuleBuilderTO;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

public class QuarantineBlCommunicationJSON {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String BlJSON = "";
	@Autowired
	private Environment env;
	@Value("${BL_JSON_URL}")
	String BL_JSON_URL;
	SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");

	public String blJson(QuarantineBLCommunicationTO configurationTO) {
		System.out.println("BL_JSON_URL 111 ::  " + configurationTO.getUrlValue());
		String status = "SC0001";
		String urlAppender = "";

		JSONObject jsonReqObj = new JSONObject();
		InputStream inputStream = null;
		DataOutputStream out = null;
		HttpURLConnection connection = null;
		try {

			jsonReqObj.put("featureId", "QUARANTINE_POLICY_EXECUTION");
			jsonReqObj.put("appName", "CMS");
			jsonReqObj.put("username", "6D");
			jsonReqObj.put("password", "6D");
			jsonReqObj.put("reqTxnId", "100");
			jsonReqObj.put("msgOrigin", "ORI");
			jsonReqObj.put("msgDest", "DEST");
			jsonReqObj.put("timestamp", System.currentTimeMillis());
			JSONArray array = new JSONArray();

			if (configurationTO.getTypeChecker() != null && configurationTO.getTypeChecker().equals("BLACKLIST")) {
				System.out.println("Type Checker  :: " + configurationTO.getTypeChecker());
				JSONObject paramObj = new JSONObject();
				paramObj.put("name", "QuarantinePolicyId");
				paramObj.put("value", configurationTO.getPolicyId());
				array.put(paramObj);

				JSONObject paramObj1 = new JSONObject();
				paramObj1.put("name", "QuarantinePolicyName");
				paramObj1.put("value", configurationTO.getPolicyName());
				array.put(paramObj1);

				JSONObject paramObj2 = new JSONObject();
				paramObj2.put("name", "PolicyType");
				paramObj2.put("value", configurationTO.getPolicyType());
				array.put(paramObj2);

			}
			if (configurationTO.getTypeChecker() != null && configurationTO.getTypeChecker().equals("DND")) {
				System.out.println("Type Checker  :: " + configurationTO.getTypeChecker());
				JSONObject paramObj = new JSONObject();
				paramObj.put("name", "QuarantinePolicyId");
				paramObj.put("value", configurationTO.getPolicyId());
				array.put(paramObj);

				JSONObject paramObj1 = new JSONObject();
				paramObj1.put("name", "QuarantinePolicyName");
				paramObj1.put("value", configurationTO.getPolicyName());
				array.put(paramObj1);

				JSONObject paramObj2 = new JSONObject();
				paramObj2.put("name", "PolicyType");
				paramObj2.put("value", configurationTO.getPolicyType());
				array.put(paramObj2);

				JSONObject paramObj3 = new JSONObject();
				paramObj3.put("name", "Time");
				paramObj3.put("value", configurationTO.getDndStartTime() + "-" + configurationTO.getDndEndTime());
				array.put(paramObj3);

			}
			if (configurationTO.getTypeChecker() != null && configurationTO.getTypeChecker().equals("WEEKDAY")) {
				System.out.println("Type Checker  :: " + configurationTO.getTypeChecker());
				JSONObject paramObj = new JSONObject();
				paramObj.put("name", "QuarantinePolicyId");
				paramObj.put("value", configurationTO.getPolicyId());
				array.put(paramObj);

				JSONObject paramObj1 = new JSONObject();
				paramObj1.put("name", "QuarantinePolicyName");
				paramObj1.put("value", configurationTO.getPolicyName());
				array.put(paramObj1);

				JSONObject paramObj2 = new JSONObject();
				paramObj2.put("name", "PolicyType");
				paramObj2.put("value", configurationTO.getPolicyType());
				array.put(paramObj2);

				JSONObject paramObj3 = new JSONObject();
				paramObj3.put("name", "Days");
				paramObj3.put("value", configurationTO.getWeekDays());
				array.put(paramObj3);

			}

			if (configurationTO.getTypeChecker() != null && configurationTO.getTypeChecker().equals("SPECIALDATE")) {
				System.out.println("Type Checker  :: " + configurationTO.getTypeChecker());
				JSONObject paramObj = new JSONObject();
				paramObj.put("name", "QuarantinePolicyId");
				paramObj.put("value", configurationTO.getPolicyId());
				array.put(paramObj);

				JSONObject paramObj1 = new JSONObject();
				paramObj1.put("name", "QuarantinePolicyName");
				paramObj1.put("value", configurationTO.getPolicyName());
				array.put(paramObj1);

				JSONObject paramObj2 = new JSONObject();
				paramObj2.put("name", "PolicyType");
				paramObj2.put("value", configurationTO.getPolicyType());
				array.put(paramObj2);

				if (configurationTO.getStatusValueDate() == "0") {
					System.out.println("Inside Single Entry" + configurationTO.getSpecialDates());
					String finalDate = null;
					SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MMM-yyyy");
					Date date2 = formatter2.parse(configurationTO.getSpecialDates());
					SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
					finalDate = newFormat.format(date2);
					System.out.println("finalDate  :: " + finalDate);

					JSONObject paramObj3 = new JSONObject();
					paramObj3.put("name", "Dates");
					paramObj3.put("value", finalDate);
					array.put(paramObj3);
				} else {
					System.out.println("Inside File Entry" + configurationTO.getSpecialDates());
					String[] arrayDate = configurationTO.getSpecialDates().split(",");
					System.out.println("arrayDate  ::  " + arrayDate.toString());
					String finalDate = null;
					Date dateSet;
					String listDate = null;
					String removeDataFinal = null;
					ArrayList<String> list = new ArrayList<String>();
					for (int i = 0; i < arrayDate.length; i++) {
						DateFormat formatterDateNew = new SimpleDateFormat("yyyy-MM-dd");
						dateSet = (Date) formatterDateNew.parse(arrayDate[i]);
						SimpleDateFormat newFormat = new SimpleDateFormat("dd/MM/yyyy");
						finalDate = newFormat.format(dateSet);
						String[] words = finalDate.split(" ");
						for (int j = 0; j <= words.length - 1; j++) {
							list.add(words[j]);
						}
						listDate = list.toString();
						String removeData = listDate.replace("[", "");
						removeDataFinal = removeData.replace("]", "");
					}
					System.out.println("removeDataFinal  :: " + removeDataFinal);
					JSONObject paramObj3 = new JSONObject();
					paramObj3.put("name", "Dates");
					paramObj3.put("value", removeDataFinal);
					array.put(paramObj3);

				}

			}

			if (configurationTO.getMarketingPlanIds().split(",").length == 1
					&& configurationTO.getMarketingPlanIds().split(",")[0].trim().equalsIgnoreCase("-1")) {
				System.out.println("campaign if");
				JSONObject paramObj4 = new JSONObject();
				paramObj4.put("name", "ServiceDetails");
				paramObj4.put("value", "ALL");
				array.put(paramObj4);
			} else {
				System.out.println("campaign else  :  " + configurationTO.getMarketingPlanIds());
				JSONObject paramObj4 = new JSONObject();
				paramObj4.put("name", "ServiceDetails");
				paramObj4.put("value", configurationTO.getMarketingPlanIds());
				array.put(paramObj4);
			}

			JSONObject paramNameObj = new JSONObject();
			paramNameObj.put("param", array);
			JSONObject dataObj = new JSONObject();
			dataObj.put("detail", paramNameObj);
			jsonReqObj.put("data", dataObj);

			System.out.println("JSON:::::" + jsonReqObj.toString());

			System.out.println("policy url  : " + configurationTO.getUrlAppender());
			urlAppender = configurationTO.getUrlValue() + configurationTO.getUrlAppender();
			System.out.println("urlAppender fetch ::   " + urlAppender);

			URL url = new URL(urlAppender);
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
				String value = (String) jsonRespObj.get("respCode");
				System.out.println("Resp Code Bl :: " + value);
				System.out.println("Resp Deatils Bl :: " + jsonRespObj.toString());

				if (value.equalsIgnoreCase("SC0000")) {
					status = "SC0000";
				} else {
					status = "SC0001";
				}

			}

		} catch (Exception e) {
			System.out.println("Exception On Bl Side");
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

		return status;
	}

}
