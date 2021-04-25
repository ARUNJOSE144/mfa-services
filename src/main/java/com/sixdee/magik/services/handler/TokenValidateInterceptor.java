/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UrlPathHelper;

import com.jcraft.jsch.UserAuth;
/*import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.redis.TokenMasterRedis;
import com.sixdee.magik.services.redis.TokenRepositoryRedisDAO;
import com.sixdee.magik.services.service.impl.UserAuthServiceImpl;
import com.sixdee.magik.services.util.MDCConstants;
import com.sixdee.magik.services.util.SystemProperties;*/
import com.sixdee.magik.services.exception.DAOException;

/**
 * @author Afthab
 * @version 1.0.0.0
 * @since 29-Nov-2017 : 1:58:54 PM
 */
@Component
public class TokenValidateInterceptor implements HandlerInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(TokenValidateInterceptor.class);
	public static final ArrayList<String> AUTHENTICATE_URL_LIST = new ArrayList<String>(
			Arrays.asList("/v1/login", "/ForgetPassword/v1/forgetPwd"));
	public static final String AUTHORIZATION = "Authorization";
	public static final String XREQUEST_ID = "X-RequestId";
	public static final String XTRANSACTION_ID = "X-TransctionId";
	public static final String XUSER_ID = "X-UserId";
	public static final String XTOKEN = "X-Auth-Token";
	public static final String GET = "GET";
	public static final String POST = "POST";
	public static final String DELETE = "DELETE";
	public static final String OPTIONS = "OPTIONS";
	public static final String BASIC = "Basic";
//	public static final ArrayList<String> EXCLUDE_URL_LIST = new ArrayList<String>(
//            Arrays.asList("/v1/doc/view", "/survey/v1/api/view", "/checkin/v1/api/submit/survey",
//                    "/privilege/v1/authorize", "/v1/relogin","/getlanguages","/cachemessages","/scheduleDetailsOfRule","/getCampaignDetails","/saveRule"
//                    )); 
	public static final int AUTH_TOKEN = 1;

	@Autowired
	// SystemProperties properties;
	public static ArrayList<String> EXCLUDE_URL_LIST = null;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		MDC.clear();

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		if (!request.getMethod().equals(OPTIONS)) {
			long startTime = (Long) request.getAttribute("startTime");
			long endTime = System.currentTimeMillis();
			long executeTime = endTime - startTime;
			logger.info("Request Execution Time : " + executeTime + " ms");
		}

	}

	private boolean postToAuthenticate(String resourcePath) {
		if (AUTHENTICATE_URL_LIST.contains(resourcePath))
			return true;
		return false;
	}

	private boolean processTokenAuthentication(HttpServletRequest request, String userId, String token)
			throws ClassNotFoundException {
		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(userId,
				token);
		return tryToAuthenticate(request, requestAuthentication);
	}

	private boolean tryToAuthenticate(HttpServletRequest request, Authentication requestAuthentication)
			throws ClassNotFoundException {
		// TokenMaster tokenMaster = null;
		try {
		} catch (DAOException e) {
			logger.error("Invalid Token.");

		}
		return false;

	}

	/*
	 * private String generateToken() { return UUID.randomUUID().toString(); }
	 * 
	 * private String generateRefreshToken() { return UUID.randomUUID().toString();
	 * }
	 */

}