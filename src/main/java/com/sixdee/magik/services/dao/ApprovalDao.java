package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.model.UserMaster;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
public interface ApprovalDao {

	List<DesignationHierarchy> getDesignationDetails(String designationID);

	List<UserMaster> getUserDetails(String parentId, String channelType);

	List<UserMaster> viewUserDetails(String parentId);

	String updateUserDetails(UserDetails userTO);

	List<ApprovalAuditTO> getApprovalAuditInfo();

	List<ApprovalAuditTO> searchApprovalAuditInfo();

	List<ApprovalAuditTO> getSearchApprovalAuditInfo(ApprovalAuditTO auditInfoTO);

}
