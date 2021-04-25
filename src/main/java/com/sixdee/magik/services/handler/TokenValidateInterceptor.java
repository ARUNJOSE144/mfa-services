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

import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.model.UserAuth;
import com.sixdee.magik.services.redis.TokenMasterRedis;
import com.sixdee.magik.services.redis.TokenRepositoryRedisDAO;
import com.sixdee.magik.services.service.impl.UserAuthServiceImpl;
import com.sixdee.magik.services.util.MDCConstants;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author Afthab
 * @version 1.0.0.0
 * @since 29-Nov-2017 : 1:58:54 PM
 */
@Component
public class TokenValidateInterceptor implements HandlerInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(TokenValidateInterceptor.class);
	public static final ArrayList<String>  AUTHENTICATE_URL_LIST		= new ArrayList<String>(
			Arrays.asList( "/v1/login","/ForgetPassword/v1/forgetPwd"));
	public static final String AUTHORIZATION 		= "Authorization";
	public static final String XREQUEST_ID 			= "X-RequestId";
	public static final String XTRANSACTION_ID		= "X-TransctionId";
	public static final String XUSER_ID				= "X-UserId";
	public static final String XTOKEN 				= "X-Auth-Token";
	public static final String GET 					= "GET";
	public static final String POST 				= "POST";
	public static final String DELETE				= "DELETE";
	public static final String OPTIONS 				= "OPTIONS";
	public static final String BASIC 				= "Basic";
//	public static final ArrayList<String> EXCLUDE_URL_LIST = new ArrayList<String>(
//            Arrays.asList("/v1/doc/view", "/survey/v1/api/view", "/checkin/v1/api/submit/survey",
//                    "/privilege/v1/authorize", "/v1/relogin","/getlanguages","/cachemessages","/scheduleDetailsOfRule","/getCampaignDetails","/saveRule"
//                    )); 
	public static final int AUTH_TOKEN  = 1;


	@Autowired
	SystemProperties properties;
	public static ArrayList<String> EXCLUDE_URL_LIST =null;
	
	@Autowired
	TokenRepositoryRedisDAO tokenRepoRedis;
	
	@Autowired
	UserAuthServiceImpl userAuthServiceImpl;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		System.out.println("CAme to Token Validator interceptor");
	
			
		String resourcePath = new UrlPathHelper().getPathWithinApplication(request);
		System.out.println("Method====================>"+request.getMethod());
		System.out.println("EXCLUDE_URL_LIST " + properties.getEXCLUDE_URL_LIST().toString());
		
		System.out.println("Resource path::::"+resourcePath);

		if (!request.getMethod().equals(OPTIONS)) {

			String transactionId = UUID.randomUUID().toString();
			long startTime = System.currentTimeMillis();
			request.setAttribute("startTime", startTime);
			request.setAttribute("requestId", transactionId);
			MDC.put(MDCConstants.TRANSACTION_ID, transactionId);
			
			if(! properties.getEXCLUDE_URL_LIST().contains(resourcePath)){

				
				response.setHeader(XTRANSACTION_ID, transactionId);
				response.setHeader(XREQUEST_ID, request.getHeader(XREQUEST_ID));

				try {
					if(properties.isSessionCheckRequired()) {

						
						UserAuth userAuth = userAuthServiceImpl
								.findByAuthorization(request.getHeader(AUTHORIZATION).split(BASIC)[1].trim());
						/*UserAuth userAuth = userAuthServiceImpl
								.findByAuthorization("aW50ZXJmYWNlX3dlYl91c2VyOjk4OHNkc2RAdHU=");*/
						
						MDC.put(MDCConstants.CHANNEL_NAME, userAuth.getChannelName());
						MDC.put(MDCConstants.CHANNEL_ID, String.valueOf(userAuth.getChannelId()));
						/*MDC.put(MDCConstants.CHANNEL_NAME, "WEB");
						MDC.put(MDCConstants.CHANNEL_ID,"1");*/

						if((userAuth.getAuthReq() == AUTH_TOKEN)) {
							if (!postToAuthenticate(resourcePath) && (request.getMethod().equals(GET)
									|| request.getMethod().equals(DELETE) || request.getMethod().equals(POST))) {
								
								logger.info("---------------------------------------------------------");
								Enumeration<String> headers = request.getHeaderNames(); 
								while(headers.hasMoreElements()) {
									String header = headers.nextElement();
									logger.info(header + " : "+ request.getHeader(header));
								}
								logger.info("---------------------------------------------------------");
								
								String userId = new String(request.getHeader(XUSER_ID) != null ? request.getHeader(XUSER_ID) : "");
								//String userId="1";
								logger.info("XUSER_ID : {}", userId);
								String token = request.getHeader(XTOKEN);
								/*String token = "5597b45b-d46f-4139-ac6b-2422e07e4c65";*/
								logger.info("Trying to authenticate user by X-Auth-Token method. Token: {}", token);
								if (!processTokenAuthentication(request, userId, token)) {
									throw new BadCredentialsException("Invalid Token.");
								}
							} else {
								request.setAttribute("Token-Auth", "true");
							}
						} else {
							request.setAttribute("UserId", request.getHeader(XUSER_ID));
							logger.info("XUSER_ID : {}", request.getHeader(XUSER_ID));
							if (postToAuthenticate(resourcePath))
								request.setAttribute("Token-Auth", "false");

						}

						logger.debug("AuthenticationFilter is passing request down the filter chain");
					
					}else {
						System.out.println("Ignoring Session Check================================");
					}
				} catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
					SecurityContextHolder.clearContext();
					logger.error("Internal authentication service exception", internalAuthenticationServiceException);
					response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
				} catch (BadCredentialsException badCredentialsException) {
					logger.error("Bad Credentials Exception");
					SecurityContextHolder.clearContext();
					response.setHeader("Access-Control-Allow-Origin", properties.getUiCrossUrl());
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return false;
				} catch (Exception e) {
					e.printStackTrace();
					logger.error(" exception" + e.getMessage());
					return false;
				}
			}
		}
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
			logger.info("Request Execution Time : "+ executeTime +" ms");
		}

	}

	private boolean postToAuthenticate(String resourcePath) {
		if(AUTHENTICATE_URL_LIST.contains(resourcePath))
			return  true;
		return false;
	}



	private boolean processTokenAuthentication(HttpServletRequest request, String userId, String token) throws ClassNotFoundException {
		UsernamePasswordAuthenticationToken requestAuthentication = new UsernamePasswordAuthenticationToken(userId,
				token);
		return tryToAuthenticate(request, requestAuthentication);
	}

	private boolean tryToAuthenticate(HttpServletRequest request, Authentication requestAuthentication) throws ClassNotFoundException {
		//TokenMaster tokenMaster = null;
		try {
			
			TokenMasterRedis redisObj = null;
			logger.info("MULTI-SESSION-ENABLED - "+properties.getEnableMultipleSessions());
			
			/*TokenMasterRedis tokenMaster = new TokenMasterRedis("5597b45b-d46f-4139-ac6b-2422e07e4c65", "admin", "1", "5597b45b-d46f-4139-ac6b-2422e07e4c65");
			tokenRepoRedis.addToken(tokenMaster.getToken(), tokenMaster.getUserId(), tokenMaster);*/
			
			
			
			logger.info( requestAuthentication.getCredentials()+"::::::1234::::::::::"+requestAuthentication.getPrincipal());
			if(properties.getEnableMultipleSessions()) {
				System.out.println("type=========================>"+ tokenRepoRedis.getToken((String) requestAuthentication.getCredentials(), (String) requestAuthentication.getPrincipal()));
				redisObj = tokenRepoRedis.getToken((String) requestAuthentication.getCredentials(), (String) requestAuthentication.getPrincipal());
			}
			else
				redisObj = tokenRepoRedis.getToken((String) requestAuthentication.getPrincipal(),(String) requestAuthentication.getCredentials());
			//TokenMasterRedis redisObj = tokenRepoRedis.getToken((String) requestAuthentication.getCredentials(),(String) requestAuthentication.getPrincipal());
			if(redisObj == null){
				logger.error("TokenMasterRedis is null Invalid Token.");
				return false;
			}
			/*else {
				logger.info("Redis object===================>"+redisObj);
			}*/
			logger.info("USER AND TOKEN EXISTS ? " + tokenRepoRedis.hasKey((String)requestAuthentication.getPrincipal()));
			if(tokenRepoRedis.hasKey((String)requestAuthentication.getPrincipal())) {
				tokenRepoRedis.deleteUserId((String)requestAuthentication.getPrincipal());
			logger.info("DELETING TOKEN - " + requestAuthentication.getPrincipal() + ":" + requestAuthentication.getCredentials());
			}
			tokenRepoRedis.addToken((String) requestAuthentication.getPrincipal(),(String) requestAuthentication.getCredentials(), redisObj);
			logger.debug("User successfully authenticated");
			System.out.println("User successfully authenticated123");
			// requestAuthentication.setAuthenticated(true);
			request.setAttribute("UserId", (String) requestAuthentication.getPrincipal());
			SecurityContextHolder.getContext().setAuthentication(requestAuthentication);
			return true;

		} catch (DAOException e) {
			logger.error("Invalid Token.");

		}
		return false;

	}
	
	/*private String generateToken() {
		return UUID.randomUUID().toString();
	}

	private String generateRefreshToken() {
		return UUID.randomUUID().toString();
	}*/
	

}