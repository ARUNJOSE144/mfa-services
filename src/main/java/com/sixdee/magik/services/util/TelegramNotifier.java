package com.sixdee.magik.services.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class TelegramNotifier {

	public String Notification(String token, String id, String apiUrl, String message) {

		System.out.println("Class => TelegramNotifier : Method => Notification");

		String response = "";

		String urlString = apiUrl;
		String apiToken = token;
		String chatId = id;
		String text = message;

		System.out.println("API Token    >>>>>>>>>>>>>>> : " + apiToken);
		System.out.println("Chat Id      >>>>>>>>>>>>>>> : " + chatId);
		System.out.println("Text         >>>>>>>>>>>>>>> : " + text);
		
		urlString = String.format(urlString, apiToken, chatId, text);
		
		System.out.println("Telegram URL with properties >>>>>>>>>>>>>>> : " + urlString);

		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			StringBuilder sb = new StringBuilder();
			InputStream is = new BufferedInputStream(conn.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String inputLine = "";

			while ((inputLine = br.readLine()) != null) {
				sb.append(inputLine);
			}

			response = sb.toString();

		} catch (Exception e) {
			response = "Failed.";
			System.out.println("Exception on sending notifictaion on Telegram Notifier.");
			e.printStackTrace();
		}

		return response;

	}
}
