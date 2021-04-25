package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.model.CalenderCampaignTypesDataTO;
import com.sixdee.magik.services.model.CalenderDataTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.service.CampaignCalenderService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Ramesh Bbau Cheerla
 * @Date : April, 2019
 *
 */

@RestController
public class CampaignCalenderRestController {

	static final Logger logger = Logger.getLogger(CampaignCalenderRestController.class);

	@Autowired
	CampaignCalenderService campaignCalenderService;

	@GetMapping(MagikServicePath.GET_CALENDER_DATA)
	public @ResponseBody RestListInfo<CalenderDataTO> getCalenderData(HttpServletRequest httpServletRequest) {
		
		logger.info("================== Campaign Calendar Rest Controllar Calendar data API Start =====================");
		logger.info("Class : CampaignCalenderRestController | Method : getCalenderData");

		String userId = "";
		String startDate = httpServletRequest.getParameter("startDate");
		String endDate = httpServletRequest.getParameter("endDate");
		
		RestListInfo<CalenderDataTO> listInfo = new RestListInfo<CalenderDataTO>();
		int statusCode = 0;
		try {
			listInfo.setOperationCode(statusCode);
			listInfo.setData(campaignCalenderService.getCalenderData(startDate, endDate, userId));
			listInfo.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignCalenderRestController  |  Method : getCalenderData " + e);
			e.printStackTrace();
			listInfo.setMessage(CacheDaoImpl.messages.get(7));
		}
		
		logger.info("================== Campaign Calendar Rest Controllar Calendar data API End =====================");
		
		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGN_TYPE_INFO)
	public @ResponseBody RestListInfo<CampaignTypeTO> getCampaignTypeInfo(HttpServletRequest httpServletRequest) {
		
		logger.info("================== Campaign Calendar Rest Controllar Campaign Type Info API Start =====================");
		logger.info("Class : CampaignCalenderRestController | Method : getCampaignTypeInfo");

		RestListInfo<CampaignTypeTO> listInfo = new RestListInfo<CampaignTypeTO>();
		int statusCode = 0;
		try {
			listInfo.setOperationCode(statusCode);
			listInfo.setData(campaignCalenderService.getCampaignTypeInfo());
			listInfo.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignCalenderRestController  |  Method : getCampaignTypeInfo " + e);
			e.printStackTrace();
			listInfo.setMessage(CacheDaoImpl.messages.get(7));
		}
		
		logger.info("================== Campaign Calendar Rest Controllar Campaign Type Info API End =====================");
		
		return listInfo;
	}
	
	@GetMapping(MagikServicePath.GET_CAMPAIGN_INFO)
	public @ResponseBody RestListInfo<CampaignMasterTO> getCampaignInfo(HttpServletRequest httpServletRequest) {
		logger.info("================== Authentication Rest Controllar Reset Password API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : resetPassword");

		String date = httpServletRequest.getParameter("date");
		System.out.println("Date------------------>"+date);

		RestListInfo<CampaignMasterTO> listInfo = new RestListInfo<CampaignMasterTO>();
		int statusCode = 0;
		try {
			listInfo.setOperationCode(statusCode);
			listInfo.setData(campaignCalenderService.getCampaignInfo(date));
			listInfo.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : AuthenticationRestControllar  |  Method : resetPassword " + e);
			e.printStackTrace();
			listInfo.setMessage(CacheDaoImpl.messages.get(7));
		}
		logger.info("================== Authentication Rest Controllar Reset Password API End =====================");
		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGN_CALENDER_TYPES)
	public @ResponseBody RestListInfo<CalenderCampaignTypesDataTO> getCampaignTypesData(HttpServletRequest httpServletRequest) {
		logger.info("================== Authentication Rest Controllar Reset Password API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : resetPassword");
		String date=httpServletRequest.getParameter("date");
		RestListInfo<CalenderCampaignTypesDataTO> listInfo = new RestListInfo<CalenderCampaignTypesDataTO>();
		int statusCode = 0;
		try {
			listInfo.setOperationCode(statusCode);
			listInfo.setData(campaignCalenderService.getCampaignTypesData(date));
			listInfo.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : AuthenticationRestControllar  |  Method : resetPassword " + e);
			e.printStackTrace();
			listInfo.setMessage(CacheDaoImpl.messages.get(7));
		}
		logger.info("================== Authentication Rest Controllar Reset Password API End =====================");
		return listInfo;
	}

	@GetMapping(MagikServicePath.GET_ALL_CAMPAIGNS)
	public @ResponseBody RestListInfo<CalenderCampaignTypesDataTO> getAllCampaign(HttpServletRequest httpServletRequest) {
		logger.info("================== Authentication Rest Controllar Reset Password API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : resetPassword");
		RestListInfo<CalenderCampaignTypesDataTO> listInfo = new RestListInfo<CalenderCampaignTypesDataTO>();
		int statusCode = 0;
		try {
			listInfo.setOperationCode(statusCode);
			listInfo.setData(campaignCalenderService.getAllCampaign());
			listInfo.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : AuthenticationRestControllar  |  Method : resetPassword " + e);
			e.printStackTrace();
			listInfo.setMessage(CacheDaoImpl.messages.get(7));
		}
		logger.info("================== Authentication Rest Controllar Reset Password API End =====================");
		return listInfo;
	}

	@GetMapping(MagikServicePath.VIEW_PROMOTION)
	public @ResponseBody RestListInfo<CalenderCampaignTypesDataTO> viewPromotion(HttpServletRequest httpServletRequest) {
		logger.info("================== Authentication Rest Controllar Reset Password API Start =====================");
		logger.info("Class : AuthenticationRestControllar | Method : resetPassword");
		RestListInfo<CalenderCampaignTypesDataTO> listInfo = new RestListInfo<CalenderCampaignTypesDataTO>();
		int statusCode = 0;
		try {
			listInfo.setOperationCode(statusCode);
			// listInfo.setData(campaignCalenderService.viewPromotion());
			listInfo.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : AuthenticationRestControllar  |  Method : resetPassword " + e);
			e.printStackTrace();
			listInfo.setMessage(CacheDaoImpl.messages.get(7));
		}
		logger.info("================== Authentication Rest Controllar Reset Password API End =====================");
		return listInfo;
	}
	
	
	
	
}
