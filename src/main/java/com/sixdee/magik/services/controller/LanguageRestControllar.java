package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LanguageTO;
import com.sixdee.magik.services.service.LanguageService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@RestController
public class LanguageRestControllar {

	static final Logger logger = Logger.getLogger(LanguageRestControllar.class);

	@Autowired
	LanguageService languageService;

	/*
	 * Get Languages
	 */
	@GetMapping(MagikServicePath.GET_LANGUAGES)
	public @ResponseBody RestListInfo<LanguageTO> getLanguages(HttpServletRequest httpServletRequest) {

		/*HttpSession session = httpServletRequest.getSession();	
		System.out.println("------------------------------- GET_LANGUAGES ------------------------------------");
		System.out.println("Session 3 =============== > " + session + " | Id ======================= > " + session.getId());*/
	
		logger.info("================== Language Rest Controllar API Start =====================");
		logger.info("Class : LanguageRestControllar | Method : getLanguages");
		RestListInfo<LanguageTO> info = new RestListInfo<LanguageTO>();
		try {
			info.setData(languageService.getLanguages());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	}

}
