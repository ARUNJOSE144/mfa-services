package com.sixdee.magik.services.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;

public class MySessionUtil {

	public static Map<String, HttpSession> sessionMap = null;
	public static Map<String, String> userSessionMap = null;

	static {
		sessionMap = new HashMap<String, HttpSession>();
		userSessionMap = new LinkedHashMap<String, String>();
	}
}
