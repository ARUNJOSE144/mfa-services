package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ContactPolicyFieldDescriptionTO;
import com.sixdee.magik.services.model.ContactPolicyFieldTO;
import com.sixdee.magik.services.model.ContactPolicyTO;
import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.service.ContactPolicyService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

@RestController
public class ContactPolicyRestController {

	static final Logger logger = Logger.getLogger(ContactPolicyRestController.class);

	@Autowired
	ContactPolicyService contactPolicyService;

	@PostMapping(MagikServicePath.CREATE_CONTACT_POLICY)
	public @ResponseBody RestInfo<String> createContactPolicy(HttpServletRequest httpServletRequest,
			@RequestBody ContactPolicyTO contactPolicyTO) {

		logger.info("================== ContactPolicyRestController  API Start =====================");
		logger.info("Class : ContactPolicyRestController | Method : createContactPolicy");
		System.out.println("contactPolicyTO===========>>>>" + contactPolicyTO);

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(contactPolicyService.createContactPolicy(contactPolicyTO));
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

	@GetMapping("getContactPolicies")
	public @ResponseBody RestListInfo<ContactPolicyTO> getContactPolicyDetails(HttpServletRequest httpServletRequest) {
		logger.info(
				"================== ContactPolicyRestController  : getContactPolicyDetailsS  API Start =====================");
		System.out.println("ContactPolicyRestController>>>>>>>>");
		RestListInfo<ContactPolicyTO> listInfo = new RestListInfo<ContactPolicyTO>();

		try {
			listInfo.setData(contactPolicyService.getContactPolicies());

		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : ActionsRestController  |  Method : getActionGroupList \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.CONTACT_POLICY_GET_SEGMENT_TYPES)
	public @ResponseBody RestListInfo<ContactPolicyFieldTO> getSegmentTypes(HttpServletRequest httpServletRequest) {

		logger.info("================== ContactPolicyRestController  API Start =====================");
		logger.info("Class : ContactPolicyRestController | Method : createContactPolicy");

		int id = Integer.parseInt(httpServletRequest.getParameter("id"));
		RestListInfo<ContactPolicyFieldTO> info = new RestListInfo<ContactPolicyFieldTO>();

		try {
			info.setData(contactPolicyService.getSegmentTypes(id));
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

	@GetMapping(MagikServicePath.CONTACT_POLICY_GET_SEGMENT_CATEGORY_TYPES)
	public @ResponseBody RestListInfo<ContactPolicyFieldDescriptionTO> getSegmentCategoryTypes(
			HttpServletRequest httpServletRequest) {
		logger.info("================== ContactPolicyRestController  API Start =====================");
		logger.info("Class : ContactPolicyRestController | Method : createContactPolicy");
		int categoryField = Integer.parseInt(httpServletRequest.getParameter("categoryField"));
		RestListInfo<ContactPolicyFieldDescriptionTO> info = new RestListInfo<ContactPolicyFieldDescriptionTO>();
		try {
			info.setData(contactPolicyService.getSegmentCategoryTypes(categoryField));
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

	@GetMapping(MagikServicePath.GET_CONTACT_POLICY_DETAILS)
	public @ResponseBody RestInfo<ContactPolicyTO> getContactPolicyInfo(HttpServletRequest httpServletRequest) {
		logger.info("================== ContactPolicyRestController  API Start =====================");
		logger.info("Class : ContactPolicyRestController | Method : createContactPolicy");
		int policyId = Integer.parseInt(httpServletRequest.getParameter("policyId"));
		RestInfo<ContactPolicyTO> info = new RestInfo<ContactPolicyTO>();
		try {
			info.setData(contactPolicyService.getContactPolicyInfo(policyId));
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
	
	
	
	@PostMapping(MagikServicePath.DELETE_CONTACT_POLICY)
	public @ResponseBody RestInfo<String> deleteContactPolicy(HttpServletRequest httpServletRequest,
			@RequestBody ContactPolicyTO contactPolicyTO) {

		logger.info("================== ContactPolicyRestController  API Start =====================");
		logger.info("Class : ContactPolicyRestController | Method : createContactPolicy");
		System.out.println("contactPolicyTO===========>>>>" + contactPolicyTO);

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(contactPolicyService.deleteContactPolicy(contactPolicyTO));
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

}
