package com.inno.mfa.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inno.mfa.services.dao.AuthenticationDAO;
import com.inno.mfa.services.model.LoginTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class AuthenticationRestController {

	static final Logger logger = Logger.getLogger(AuthenticationRestController.class);
	@Autowired
	AuthenticationDAO authenticationDAO;

	@PostMapping(value = "/v1/login")
	public @ResponseBody LoginTo getRolesList(HttpServletRequest httpServletRequest, @RequestBody LoginTo loginTo)
			throws IOException {
		try {
			logger.info("LoginTo in RestController: " + loginTo.toString());
			loginTo = authenticationDAO.login(loginTo);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginTo;
	}

	@PostMapping(value = "/v1/authorize")
	public @ResponseBody LoginTo authorize(@RequestHeader(value = "X-UserId") String userId,
			@RequestHeader(value = "X-Auth-Token") String token) throws IOException {
		LoginTo loginTo = null;
		try {
			loginTo = authenticationDAO.authorize(Integer.parseInt(userId));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginTo;
	}

}
