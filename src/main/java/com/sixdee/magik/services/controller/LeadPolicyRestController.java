package com.sixdee.magik.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.WhatIfBasicTO;
import com.sixdee.magik.services.service.LeadPolicyService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @Date : Feb, 2019
 *
 */

@RestController
public class LeadPolicyRestController {
	static final Logger logger = Logger.getLogger(LeadPolicyRestController.class);
	@Autowired
	LeadPolicyService leadPolicyService;

	@PostMapping(MagikServicePath.CREATE_LEAD_POLICY)
	public @ResponseBody RestInfo<String> createLeadPolicy(HttpServletRequest httpServletRequest,
			@RequestBody LeadPolicyTO leadPolicyTO) {

		logger.info("================== LeadPolicyRestController  API Start =====================");
		logger.info("Class : LeadPolicy Rest Controller | Method : createLeadPolicy");

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(leadPolicyService.createLeadPolicy(leadPolicyTO));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : LeadPolicyRestController  |  Method : createLeadPolicy " + e);
			e.printStackTrace();
		}

		logger.info("================== LeadPolicy Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_LEAD_POLICY_LIST)
	public @ResponseBody RestListInfo<LeadPolicyTO> getLeadPolicyList(HttpServletRequest httpServletRequest) {

		logger.info("================== LeadPolicyRestController  API Start =====================");
		logger.info("Class : LeadPolicy Rest Controller | Method : getLeadPolicyList");

		RestListInfo<LeadPolicyTO> info = new RestListInfo<LeadPolicyTO>();
		int userId = 0;

		try {
			info.setData(leadPolicyService.getLeadPolicyList(userId));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : LeadPolicyRestController  |  Method : getLeadPolicyList " + e);
			e.printStackTrace();
		}

		logger.info("================== LeadPolicy Rest Controllar API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.DELETE_LEAD_POLICY)
	public @ResponseBody RestInfo<String> deleteLeadPolicy(HttpServletRequest httpServletRequest,
			@RequestBody LeadPolicyTO leadPolicyTO) {
		logger.info("================== LeadPolicyRestController  API Start =====================");
		logger.info("Class : LeadPolicy Rest Controller | Method : deleteLeadPolicy");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(leadPolicyService.deleteLeadPolicy(leadPolicyTO));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : LeadPolicyRestController  |  Method : deleteLeadPolicy " + e);
			e.printStackTrace();
		}
		logger.info("================== LeadPolicy Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_WHAT_IF_ANALYSIS_BASIC_DETAILS)
	public @ResponseBody RestInfo<List<WhatIfBasicTO>> getWhatIfAnalysisBasicDetails(
			HttpServletRequest httpServletRequest) {
		logger.info("================== LeadPolicyRestController  API Start =====================");
		logger.info("Class : LeadPolicy Rest Controller | Method : deleteLeadPolicy");
		RestInfo<List<WhatIfBasicTO>> info = new RestInfo<List<WhatIfBasicTO>>();
		try {
			info.setData(leadPolicyService.getWhatIfAnalysisBasicDetails());
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : LeadPolicyRestController  |  Method : deleteLeadPolicy " + e);
			e.printStackTrace();
		}
		logger.info("================== LeadPolicy Rest Controllar API End =====================");
		return info;
	}
}
