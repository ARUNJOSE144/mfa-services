package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.service.CacheService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@RestController
public class CacheRestControllar {

	static final Logger logger = Logger.getLogger(CacheRestControllar.class);

	@Autowired
	CacheService cacheService;

	/*
	 * Messages
	 */
	@GetMapping(MagikServicePath.CACHE_MESSAGES)
	public @ResponseBody void cacheMessages(HttpServletRequest httpServletRequest) {

		logger.info("================== Cache API Start =====================");
		logger.info("Class : CacheRestControllar | Method : cacheMessages");

		/*HttpSession session = httpServletRequest.getSession();	
		System.out.println("---------------------------- CACHE_MESSAGES -------------------------------------------");
		System.out.println("Session 2 =============== > " + session + " | Id ======================= > " + session.getId());*/
	
		RestInfo<GenericTO> info = new RestInfo<GenericTO>();

		try {
			cacheService.cacheMessages();
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CacheRestControllar  |  Method : cacheMessages ");
			e.printStackTrace();
		}

		logger.info("================== Cache API End =====================");

	}

	/*
	 * User session
	 */
	@PostMapping(MagikServicePath.USER_SESSION)
	public void userSession(HttpServletRequest httpServletRequest, @RequestBody UserSessionTO to) {

		logger.info("================== Cache API Start =====================");
		logger.info("Class : CacheRestControllar | Method : userSession");

		
		HttpSession session = httpServletRequest.getSession();	
		System.out.println("---------------------------- USER_SESSION -------------------------------------------");
		System.out.println("Session =============== > " + session + " | Id ======================= > " + session.getId());

		
		/*UserSessionTO user = new UserSessionTO();
		user.setUserId(to.getUserId());
		user.setUserName(to.getUserName());
		user.setSessionId(to.getSessionId());
		
		System.out.println("Session id ========== : "+user.getSessionId());
		System.out.println("User id ============ : "+user.getUserId());
		System.out.println("User Name =========== : "+user.getUserName());

		HttpSession session = httpServletRequest.getSession();
		session.setAttribute("userSession", user);
		
		HttpSession session1 = httpServletRequest.getSession();
		UserSessionTO user1 = (UserSessionTO) session1.getAttribute("userSession");
		
		System.out.println(session);
		System.out.println(user1.getSessionId());*/

		logger.info("================== Cache API End =====================");

	}

}
