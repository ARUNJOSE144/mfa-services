package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ApprovalDao;
import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.model.UserMaster;
import com.sixdee.magik.services.service.ApprovalService;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
@Service
@Transactional
public class ApprovalServiceImpl implements ApprovalService {

	@Autowired
	ApprovalDao approvalDao;

	@Override
	public List<DesignationHierarchy> getDesignationDetails(String designationID) {
		return approvalDao.getDesignationDetails(designationID);
	}

	@Override
	public List<UserMaster> getUserDetails(String parentId, String channelType) {
		return approvalDao.getUserDetails(parentId,channelType);
	}

	@Override
	public List<UserMaster> viewUserDetails(String parentId) {
		return approvalDao.viewUserDetails(parentId);
	}

	@Override
	public String updateUserDetails(UserDetails userTO) {
		// TODO Auto-generated method stub
		return approvalDao.updateUserDetails(userTO);
	}

	@Override
	public List<ApprovalAuditTO> getApprovalAuditInfo() {
		// TODO Auto-generated method stub
		return approvalDao.getApprovalAuditInfo();
	}

	@Override
	public List<ApprovalAuditTO> searchApprovalAuditInfo() {
		// TODO Auto-generated method stub
		return approvalDao.searchApprovalAuditInfo();
	}

	@Override
	public List<ApprovalAuditTO> getSearchApprovalAuditInfo(ApprovalAuditTO auditInfoTO) {
		return approvalDao.getSearchApprovalAuditInfo(auditInfoTO);
	}

}
