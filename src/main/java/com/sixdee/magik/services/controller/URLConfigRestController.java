package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.UrlConfigTO;
import com.sixdee.magik.services.service.URLConfigService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author arun.jose
 * @Date : January, 2019
 *
 */

@RestController
public class URLConfigRestController {

	static final Logger logger = Logger.getLogger(URLConfigRestController.class);

	@Autowired
	 URLConfigService urlConfigService;
	
	
	@GetMapping(MagikServicePath.GET_URL_LIST)
	public @ResponseBody RestListInfo<UrlConfigTO> getUrlList(HttpServletRequest httpServletRequest) {

	
		logger.info("==================  getUrlList Controllar API Start =====================");
		logger.info("Class : getUrlList | Method : getApplicationProperties");
		RestListInfo<UrlConfigTO> info = new RestListInfo<UrlConfigTO>();
		try {
			info.setData(urlConfigService.getUrlList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : getUrlList  |  Method : getApplicationProperties " + e);
			e.printStackTrace();
		}
		logger.info("================== getUrlList Rest Controllar API End =====================");
		return info;
	}
	
	
	@PostMapping(MagikServicePath.ADD_URL)
	public @ResponseBody RestInfo<String> addUrl(HttpServletRequest httpServletRequest,@RequestBody UrlConfigTO configTO) {

	
		logger.info("==================  getUrlList Controllar API Start =====================");
		logger.info("Class : getUrlList | Method : getApplicationProperties");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(urlConfigService.addUrl(configTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : getUrlList  |  Method : getApplicationProperties " + e);
			e.printStackTrace();
		}
		logger.info("================== getUrlList Rest Controllar API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.DELETE_URL)
	public @ResponseBody RestInfo<String> deleteUrl(HttpServletRequest httpServletRequest,@RequestBody UrlConfigTO configTO) {

	
		logger.info("==================  getUrlList Controllar API Start =====================");
		logger.info("Class : getUrlList | Method : getApplicationProperties");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(urlConfigService.deleteUrl(configTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : getUrlList  |  Method : getApplicationProperties " + e);
			e.printStackTrace();
		}
		logger.info("================== getUrlList Rest Controllar API End =====================");
		return info;
	}
	
	
}
