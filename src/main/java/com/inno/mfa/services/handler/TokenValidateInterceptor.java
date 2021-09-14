
package com.inno.mfa.services.handler;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.inno.mfa.services.dao.TokenDAO;
import com.inno.mfa.services.exception.DAOException;

/**
 * @author Arun Jose
 * @Date : March, 2021
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
	public static final int AUTH_TOKEN = 1;
	public static final ArrayList<String> EXCLUDE_URL_LIST = new ArrayList<String>(
			Arrays.asList("/v1/login", "/getDownloadFiles"));

	@Autowired
	TokenDAO tokenDao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String resourcePath = new UrlPathHelper().getPathWithinApplication(request);

		if (!request.getMethod().equals(OPTIONS)) {

			String transactionId = UUID.randomUUID().toString();
			long startTime = System.currentTimeMillis();
			request.setAttribute("startTime", startTime);
			request.setAttribute("requestId", transactionId);
			// MDC.put(MDCConstants.TRANSACTION_ID, transactionId);

			response.setHeader(XTRANSACTION_ID, transactionId);
			response.setHeader(XREQUEST_ID, request.getHeader(XREQUEST_ID));

			try {
				System.out.println("resourcePath : " + resourcePath);

				if (!EXCLUDE_URL_LIST.contains(resourcePath)) {
					String token = request.getHeader(XTOKEN);
					int userId = Integer.parseInt(request.getHeader(XUSER_ID));
					System.out.println("Token : " + token);
					System.out.println("userId : " + userId);

					if (!tokenDao.sessionExist(token, userId)) {
						throw new BadCredentialsException("Invalid Token.");
					}
				}
			} catch (InternalAuthenticationServiceException internalAuthenticationServiceException) {
				SecurityContextHolder.clearContext();
				logger.error("Internal authentication service exception", internalAuthenticationServiceException);
				response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			} catch (BadCredentialsException badCredentialsException) {
				logger.error("Inside the BadCredentialsException block");
				SecurityContextHolder.clearContext();
				response.setHeader("Access-Control-Allow-Origin", "/login");
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				return false;
			} catch (Exception e) {
				e.printStackTrace();
				logger.error(" exception" + e.getMessage());
				return false;
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
			/*
			 * long startTime = (Long) request.getAttribute("startTime"); long endTime =
			 * System.currentTimeMillis(); long executeTime = endTime - startTime;
			 * logger.info("Request Execution Time : " + executeTime + " ms");
			 */
		}
	}

	@SuppressWarnings("unused")
	private boolean postToAuthenticate(String resourcePath) {
		if (AUTHENTICATE_URL_LIST.contains(resourcePath))
			return true;
		return false;
	}

	@SuppressWarnings("unused")
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