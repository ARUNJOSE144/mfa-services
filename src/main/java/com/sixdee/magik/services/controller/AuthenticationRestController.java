package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.controller.bean.LoginResponse;
import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.model.AuthenticationTO;
import com.sixdee.magik.services.service.AuthenticationService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@RestController
public class AuthenticationRestController {

	static final Logger logger = Logger.getLogger(AuthenticationRestController.class);

	@Autowired
	AuthenticationService authenticationService;

	/*
	 * Authenticate Credentials
	 */
	@PostMapping(MagikServicePath.AUTH_LOGIN_CREDENTIALS)
	public @ResponseBody RestInfo<AuthenticationTO> authLoginCredentails(HttpServletRequest httpServletRequest,
			@RequestBody AuthenticationTO authTO) {

		logger.info("================== Authentication Rest Controllar API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : authLoginCredentails");

		RestInfo<AuthenticationTO> info = new RestInfo<AuthenticationTO>();
		System.out.println("Username : " + authTO.getUserName());
		System.out.println("Password : " + authTO.getPassword());

		try {
			info.setData(authenticationService.authLoginCredentails(authTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : authLoginCredentails " + e);
			e.printStackTrace();
		}

		logger.info("================== Authentication Rest Controllar API End =====================");

		/*
		 * HttpSession session = httpServletRequest.getSession(); System.out.
		 * println("---------------------------- AUTH_LOGIN_CREDENTIALS -------------------------------------------"
		 * ); System.out.println("Session =============== > " + session +
		 * " | Id ======================= > " + session.getId());
		 */

		return info;
	}

	/*
	 * Reset password
	 */
	@PostMapping(MagikServicePath.RESET_PASSWORD)
	public @ResponseBody RestInfo<String> resetPassword(HttpServletRequest httpServletRequest,
			@RequestBody AuthenticationTO authTO) {

		logger.info("================== Authentication Rest Controllar Reset Password API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : resetPassword");

		RestInfo<String> info = new RestInfo<String>();
		int statusCode = 0;

		try {
			statusCode = authenticationService.resetPassword(authTO);
			info.setOperationCode(statusCode);
			info.setData(CacheDaoImpl.messages.get(statusCode));
			info.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : resetPassword " + e);
			e.printStackTrace();
			info.setMessage(CacheDaoImpl.messages.get(7));
		}

		logger.info("================== Authentication Rest Controllar Reset Password API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.CHANGE_PASSWORD)
	public @ResponseBody RestInfo<String> ChangePassword(HttpServletRequest httpServletRequest,
			@RequestBody AuthenticationTO authTO) {

		logger.info("================== Authentication Rest Controllar Reset Password API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : resetPassword");

		RestInfo<String> info = new RestInfo<String>();
		int statusCode = 0;

		System.out.println("----------------- session ----------------------------------");

		/*
		 * HttpSession session = httpServletRequest.getSession(); UserSessionTO user =
		 * (UserSessionTO) session.getAttribute("userSession");
		 * 
		 * System.out.println(session);
		 * System.out.println("Session id : "+user.getSessionId());
		 * System.out.println("User id : "+user.getUserId());
		 * System.out.println("User Name : "+user.getUserName());
		 */

		try {
			statusCode = authenticationService.ChangePassword(authTO);
			info.setOperationCode(statusCode);
			info.setData(CacheDaoImpl.messages.get(statusCode));
			info.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : resetPassword " + e);
			e.printStackTrace();
			info.setMessage(CacheDaoImpl.messages.get(7));
		}

		logger.info("================== Authentication Rest Controllar Reset Password API End =====================");

		return info;
	}

	@RequestMapping(value = "/v1/authorize", method = { RequestMethod.POST })
	public ResponseEntity<LoginResponse> authorize(@RequestHeader(value = "X-UserId") String userId,
			@RequestHeader(value = "X-Auth-Token") String token) {
		LoginResponse response = new LoginResponse();

		try {

			response = authenticationService.authorize(userId, token);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<LoginResponse>(response, HttpStatus.OK);
	}

}
