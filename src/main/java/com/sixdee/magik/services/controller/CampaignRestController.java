package com.sixdee.magik.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignSegmentTypeTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.service.CampaignService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */
@RestController
public class CampaignRestController {
	static final Logger logger = Logger.getLogger(CampaignRestController.class);

	@Autowired
	CampaignService campaignService;

	@GetMapping(MagikServicePath.GET_CAMPAIGN_TYPES)
	public @ResponseBody RestListInfo<CampaignTypeTO> getCampaignTypes(HttpServletRequest httpServletRequest) {

		logger.info("================== Campaign Rest Controllar API Start =====================");
		logger.info("Class : CampaignRestController | Method : getCampaignTypes");
		RestListInfo<CampaignTypeTO> info = new RestListInfo<CampaignTypeTO>();
		try {
			info.setData(campaignService.getCampaignTypes());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : getCampaignTypes " + e);
			e.printStackTrace();
		}
		logger.info("================== Campaign Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.CREATE_CAMAPAIGN)
	public @ResponseBody RestInfo<CampaignMasterTO> createCampaign(HttpServletRequest httpServletRequest,
			@RequestBody CampaignMasterTO campaignTO) {

		logger.info("================== CampaignRestController  API Start =====================");
		logger.info("Class : CampaignRestController | Method : createCampaign" + campaignTO);

		RestInfo<CampaignMasterTO> info = new RestInfo<CampaignMasterTO>();

		try {
			info.setData(campaignService.createCampaign(campaignTO));
			info.setOperationCode(0);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Unique key exception.........");
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(2);
			info.setMessage("Campaign " + campaignTO.getCampaignName() + " Already Exists ");
			logger.error("Class : CampaignRestController  |  Method : createCampaign " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Common exception.........");
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Try Again");
			logger.error("Class : CampaignRestController  |  Method : createCampaign " + e.getMessage());
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.CREATE_TASK_PROFILE)
	public @ResponseBody RestInfo<CampaignMasterTO> createTaskProfile(HttpServletRequest httpServletRequest,
			@RequestBody CampaignMasterTO campaignTO) {

		logger.info("================== CampaignRestController  API Start =====================");
		logger.info("Class : CampaignRestController | Method : createTaskProfile" + campaignTO);

		RestInfo<CampaignMasterTO> info = new RestInfo<CampaignMasterTO>();

		try {
			info.setData(campaignService.createTaskProfile(campaignTO));
			info.setOperationCode(0);
		} catch (DataIntegrityViolationException e) {
			System.out.println("Unique key exception.........");
			info.setOperationCode(2);
			info.setMessage("Campaign " + campaignTO.getCampaignName() + " Already Exists ");
			logger.error("Class : CampaignRestController  |  Method : createTaskProfile " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Common exception.........");
			ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Try Again");
			logger.error("Class : CampaignRestController  |  Method : createTaskProfile " + e.getMessage());
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGNS_LIST)
	public @ResponseBody RestListInfo<CampaignMasterTO> getCampaignsList(HttpServletRequest httpServletRequest) {

		logger.info("================== Campaign Rest Controllar API Start =====================");
		logger.info("Class : CampaignRestController | Method : getCampaignsList");
		RestListInfo<CampaignMasterTO> info = new RestListInfo<CampaignMasterTO>();
		int userId = 1;
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").equalsIgnoreCase("")) {
			userId = (Integer.parseInt(httpServletRequest.getParameter("userId")));
			System.out.println("userId  :  " + userId);
		}
		try {
			info.setData(campaignService.getCampaignsList(userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : getCampaignsList " + e);
			e.printStackTrace();
		}
		logger.info("================== Campaign Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.DELETE_CAMAPAIGN)
	public @ResponseBody RestInfo<String> deleteCampaign(HttpServletRequest httpServletRequest) {

		logger.info("================== Campaign Rest Controllar API Start =====================");
		logger.info("Class : CampaignRestController | Method : deleteCampaign");
		int campaignId = Integer.parseInt(httpServletRequest.getParameter("campaignId"));

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(campaignService.deleteCampaign(campaignId));
			info.setOperationCode(0);
		} catch (Exception e) {
			e.printStackTrace();
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : deleteCampaign " + e);
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.COPY_CAMAPAIGN)
	public @ResponseBody RestInfo<String> copyCampaign(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignRestController  API Start =====================");
		logger.info("Class : CampaignRestController | Method : copyCampaign");

		RestInfo<String> info = new RestInfo<String>();
		int campaignId = 0;
		if (httpServletRequest.getParameter("campaignId") != null
				&& !httpServletRequest.getParameter("campaignId").trim().equalsIgnoreCase(""))
			campaignId = Integer.parseInt(httpServletRequest.getParameter("campaignId"));

		try {
			info.setData(campaignService.copyCampaign(campaignId));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : copyCampaign " + e);
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGN_DETAILS)
	public @ResponseBody RestInfo<CampaignMasterTO> getCampaignDetails(HttpServletRequest httpServletRequest) {

		logger.info("================== Campaign Rest Controllar API Start =====================");
		logger.info("Class : CampaignRestController | Method : getCampaignDetails");
		RestInfo<CampaignMasterTO> info = new RestInfo<CampaignMasterTO>();
		int campaignId = 0;
		int userId=1;
		if (httpServletRequest.getParameter("campaignId") != null
				&& !httpServletRequest.getParameter("campaignId").trim().equalsIgnoreCase("")) {
			campaignId = Integer.parseInt(httpServletRequest.getParameter("campaignId"));
		}
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").trim().equalsIgnoreCase("")) {
			userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		}
		try {
			info.setData(campaignService.getCampaignDetails(campaignId,userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : getCampaignsList " + e);
			e.printStackTrace();
		}
		logger.info("================== Campaign Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGN_SEGMENT_TYPES)
	public @ResponseBody RestListInfo<CampaignSegmentTypeTO> getCampaignSegmentTypes(
			HttpServletRequest httpServletRequest) {

		logger.info("================== Campaign Rest Controllar API Start =====================");
		logger.info("Class : CampaignRestController | Method : getCampaignSegmentTypes");
		RestListInfo<CampaignSegmentTypeTO> info = new RestListInfo<CampaignSegmentTypeTO>();
		try {
			info.setData(campaignService.getCampaignSegmentTypes());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : getCampaignSegmentTypes " + e);
			e.printStackTrace();
		}
		logger.info("================== Campaign Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_RULES_OF_A_CAMPAIGN)
	public @ResponseBody RestListInfo<RuleTO> getRulesOfACampaign(HttpServletRequest httpServletRequest) {

		logger.info("================== Campaign Rest Controllar API Start =====================");
		logger.info("Class : CampaignRestController | Method : getRulesOfACampaign");
		RestListInfo<RuleTO> info = new RestListInfo<RuleTO>();
		int campaignId = 0;
		int userId = 1;
		if (httpServletRequest.getParameter("campaignId") != null
				&& !httpServletRequest.getParameter("campaignId").trim().equalsIgnoreCase("")) {
			campaignId = Integer.parseInt(httpServletRequest.getParameter("campaignId"));
		}
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").equalsIgnoreCase("")) {
			userId = (Integer.parseInt(httpServletRequest.getParameter("userId")));
			System.out.println("userId  :  " +userId);
		}
		try {
			info.setData(campaignService.getRulesOfACampaign(campaignId,userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : getRulesOfACampaign " + e);
			e.printStackTrace();
		}
		logger.info("================== Campaign Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.ATTACH_RULES_TO_A_CAMPAIGN_WHILE_COPY)
	public @ResponseBody RestInfo<String> attachRulesToACampaignWhileCopy(HttpServletRequest httpServletRequest,
			@RequestBody List<RuleTO> rulesList) {

		logger.info("================== CampaignRestController  API Start =====================");
		logger.info("Class : CampaignRestController | Method : attachRulesToACampaignWhileCopy");

		RestInfo<String> info = new RestInfo<String>();

		try {
			campaignService.copyCampaignWithSpecificRules(rulesList);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : attachRulesToACampaignWhileCopy " + e);
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.UPDATE_PLAY_PAUSE_STATUS)
	public @ResponseBody RestInfo<String> updatePlayPauseStatus(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignRestController  API Start =====================");
		logger.info("Class : CampaignRestController | Method : updatePlayPauseStatus");

		RestInfo<String> info = new RestInfo<String>();
		int campaignId = 0;
		int status = 0;
		if (httpServletRequest.getParameter("campaignId") != null
				&& !httpServletRequest.getParameter("campaignId").trim().equalsIgnoreCase(""))
			campaignId = Integer.parseInt(httpServletRequest.getParameter("campaignId"));

		if (httpServletRequest.getParameter("status") != null
				&& !httpServletRequest.getParameter("status").trim().equalsIgnoreCase(""))
			status = Integer.parseInt(httpServletRequest.getParameter("status"));

		try {
			info.setData(campaignService.updatePlayPauseStatus(campaignId, status));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : updatePlayPauseStatus " + e);
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_CAMPAIGNS_WRT_TYPE)
	public @ResponseBody RestListInfo<CampaignMasterTO> getCampaignsWrtType(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignRestController  API Start =====================");
		logger.info("Class : CampaignRestController | Method : getCampaignsWrtType");

		RestListInfo<CampaignMasterTO> info = new RestListInfo<CampaignMasterTO>();
		int typeId = 0;
		String campaignName = "";
		if (httpServletRequest.getParameter("typeId") != null
				&& !httpServletRequest.getParameter("typeId").trim().equalsIgnoreCase(""))
			typeId = Integer.parseInt(httpServletRequest.getParameter("typeId"));
		if (httpServletRequest.getParameter("campaignName") != null
				&& !httpServletRequest.getParameter("campaignName").trim().equalsIgnoreCase(""))
			campaignName = httpServletRequest.getParameter("campaignName");

		try {
			info.setData(campaignService.getCampaignsWrtType(typeId, campaignName));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignRestController  |  Method : getCampaignsWrtType " + e);
			e.printStackTrace();
		}

		logger.info("================== Campaign Rest Controllar API End =====================");

		return info;
	}

}
