package com.sixdee.magik.services.dao.impl;
/**
 * @author Amal A S
 * @category Target Audience for campaigns
 * @date 03/06/2020
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class CallThirdPartyUrl {

	public static final Logger logger = Logger.getLogger(CallThirdPartyUrl.class);

	public static String callGet(String thirdPartyUrl) throws Exception {

		HttpURLConnection conn = null;
		StringBuilder result = null;
		BufferedReader rd = null;
		String response = null;
		try {
			logger.info("Calling 3rd Party URL : " + thirdPartyUrl);
			result = new StringBuilder();
			URL urlToCall = new URL(thirdPartyUrl);
			conn = (HttpURLConnection) urlToCall.openConnection();
			conn.setRequestMethod("GET");
			System.out.println("Response code : " + conn.getResponseCode());
			if (conn.getResponseCode() == 400) {
				response = "FAILED";
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line;
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				response = result.toString();
				rd.close();

				System.out.println("Response from 3rd Party (" + thirdPartyUrl + ") : " + result.toString());

			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			response = "FAILED";
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
					conn = null;
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

			if (result != null) {
				try {
					result = null;
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

		}

		return response;
	}

	public static String callPost(String thirdPartyUrl, String postValue, String requestFormat) throws Exception {

		HttpURLConnection conn = null;
		StringBuilder result = null;
		BufferedReader rd = null;
		String response = null;
		try {
			logger.info("Calling 3rd Party POST URL : " + thirdPartyUrl);
			logger.info("Request Data : " + postValue);
			result = new StringBuilder();
			URL urlToCall = new URL(thirdPartyUrl);
			conn = (HttpURLConnection) urlToCall.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			if (requestFormat.equalsIgnoreCase("JSON")) {
				conn.setRequestProperty("Content-Type", "application/json; utf-8");
				conn.setRequestProperty("Accept", "application/json");
				conn.setRequestProperty("Tran-Id", String.valueOf(System.currentTimeMillis()));
				conn.setRequestProperty("Accepts-Version", "1.0");
				Map<String, String> headers = new HashMap<>();

				headers.put("Tran-Id", String.valueOf(System.currentTimeMillis()));
				headers.put("Accepts-Version", "1.0");

				for (String headerKey : headers.keySet()) {
				    conn.setRequestProperty(headerKey, headers.get(headerKey));
				}

			}
			try (OutputStream os = conn.getOutputStream()) {
				byte[] input = postValue.getBytes("utf-8");
				os.write(input, 0, input.length);
			}
			rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
			response = result.toString();
			rd.close();

			System.out.println("Response from 3rd Party (" + thirdPartyUrl + ") : " + result.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.disconnect();
					conn = null;
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

			if (result != null) {
				try {
					result = null;
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}

		}

		return response;
	}

	public static String callPostFile(String thirdPartyUrl, String fileName, String chatId, String mediaType, String caption)
			throws Exception {

		String charset = "UTF-8";
		File uploadFile = new File(fileName);
		String resp = null;
		

		try {
			logger.info("Calling 3rd Party POST FILE URL : " + thirdPartyUrl);
			logger.info("File Name  : " + fileName);
			logger.info("ChatId  : " + chatId);

			MultipartUtility multipart = new MultipartUtility(thirdPartyUrl, charset);

			multipart.addFormField("chat_id", chatId);
			if (caption!=null && !caption.equals("")) {
				multipart.addFormField("caption", caption);	
			}
			

			multipart.addFilePart(mediaType, uploadFile);

			List<String> response = multipart.finish();

			System.out.println("SERVER REPLIED:");

			for (String line : response) {
				System.out.println("Line : "+ line);
				resp = line;
			}
		} catch (IOException ex) {
			System.err.println(ex);
			resp = "FAILED";
		}

		return resp;
	}

}
