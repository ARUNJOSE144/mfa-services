package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.model.UserMaster;
import com.sixdee.magik.services.service.ApprovalService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

@RestController
public class ApprovalRestController {

	static final Logger logger = Logger.getLogger(ApprovalRestController.class);
	@Autowired
	ApprovalService approvalService;

	@GetMapping(MagikServicePath.Get_DESIGNATION_DETAILS)
	public @ResponseBody RestListInfo<DesignationHierarchy> getDesignationDetails(
			HttpServletRequest httpServletRequest) {
		String designationID = httpServletRequest.getParameter("designationID");
		RestListInfo<DesignationHierarchy> info = new RestListInfo<DesignationHierarchy>();
		try {
			info.setData(approvalService.getDesignationDetails(designationID));
			info.setOperationCode(0);

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.Get_USER_DETAILS)
	public @ResponseBody RestListInfo<UserMaster> getUserDetails(HttpServletRequest httpServletRequest) {
		String parentId = httpServletRequest.getParameter("parentId");
		String channelType = httpServletRequest.getParameter("channelType");
		RestListInfo<UserMaster> info = new RestListInfo<UserMaster>();
		try {
			info.setData(approvalService.getUserDetails(parentId, channelType));
			info.setOperationCode(0);

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.VIEW_USER_DETAILS)
	public @ResponseBody RestListInfo<UserMaster> viewUserDetails(HttpServletRequest httpServletRequest) {
		String parentId = httpServletRequest.getParameter("parentId");
		RestListInfo<UserMaster> info = new RestListInfo<UserMaster>();
		try {
			info.setData(approvalService.viewUserDetails(parentId));
			info.setOperationCode(0);

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@PostMapping(MagikServicePath.UPDATE_USER_DETAILS)
	public @ResponseBody RestInfo<String> updateUserDetails(HttpServletRequest httpServletRequest,
			@RequestBody UserDetails userTO) {

		RestInfo<String> info = new RestInfo<String>();
		try {
			String status = approvalService.updateUserDetails(userTO);
			if (status != null && status.trim().equalsIgnoreCase("0"))
				info.setOperationCode(0);
			else
				info.setOperationCode(2);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.GET_APPROVAL_AUDIT_INFO)
	public @ResponseBody RestListInfo<ApprovalAuditTO> getApprovalAuditInfo(HttpServletRequest httpServletRequest) {
		RestListInfo<ApprovalAuditTO> info = new RestListInfo<ApprovalAuditTO>();
		try {
			info.setData(approvalService.getApprovalAuditInfo());
			info.setOperationCode(0);

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@GetMapping(MagikServicePath.SEARCH_APPROVAL_AUDIT_INFO)
	public @ResponseBody RestListInfo<ApprovalAuditTO> searchApprovalAuditInfo(HttpServletRequest httpServletRequest) {
		RestListInfo<ApprovalAuditTO> info = new RestListInfo<ApprovalAuditTO>();
		try {
			info.setData(approvalService.searchApprovalAuditInfo());
			info.setOperationCode(0);

		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}

	@PostMapping(MagikServicePath.GET_SEARCH_APPROVAL_AUDIT_INFO)
	public @ResponseBody RestListInfo<ApprovalAuditTO> getSearchApprovalAuditInfo(HttpServletRequest httpServletRequest,
			@RequestBody ApprovalAuditTO auditInfoTO) {
		RestListInfo<ApprovalAuditTO> info = new RestListInfo<ApprovalAuditTO>();
		try {
			info.setData(approvalService.getSearchApprovalAuditInfo(auditInfoTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}
		return info;
	}
}
