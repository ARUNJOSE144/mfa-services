package com.sixdee.magik.services.controller.bean;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.sixdee.magik.services.model.TokenMaster;

/**
 * @author Rajesh
 * @version 1.0.0.0
 * @since Sep 22, 2020 : 1:16:19 PM
 */

public class LoginResponse extends CommonResponse {
	private String token;
	private String refreshToken;
	private Date tokenExpiry;

	private String userName;
	private String msisdn;
	private String userId;
	private String fullName;
	private int channelType;
	private String entityId;
	private boolean isForceChangePassword;
	private long designationId;

	// Approval details
	private String isApprovalRequired;
	private String approvalTypeFlag;
	private String parentId;
	private String channelTypeIdName;
	private String designationName;

	// audit details
	private String roleName;

	// Session Name
	private List<TokenMaster> sessionName;

	private Set<Integer> privilages;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getTokenExpiry() {
		return tokenExpiry;
	}

	public void setTokenExpiry(Date tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public Set<Integer> getPrivilages() {
		return privilages;
	}

	public void setPrivilages(Set<Integer> privilages) {
		this.privilages = privilages;
	}

	public boolean isForceChangePassword() {
		return isForceChangePassword;
	}

	public void setForceChangePassword(boolean isForceChangePassword) {
		this.isForceChangePassword = isForceChangePassword;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public String getIsApprovalRequired() {
		return isApprovalRequired;
	}

	public void setIsApprovalRequired(String isApprovalRequired) {
		this.isApprovalRequired = isApprovalRequired;
	}

	public String getApprovalTypeFlag() {
		return approvalTypeFlag;
	}

	public void setApprovalTypeFlag(String approvalTypeFlag) {
		this.approvalTypeFlag = approvalTypeFlag;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getChannelTypeIdName() {
		return channelTypeIdName;
	}

	public void setChannelTypeIdName(String channelTypeIdName) {
		this.channelTypeIdName = channelTypeIdName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<TokenMaster> getSessionName() {
		return sessionName;
	}

	public void setSessionName(List<TokenMaster> sessionName) {
		this.sessionName = sessionName;
	}

	@Override
	public String toString() {
		return "LoginResponse [token=" + token + ", refreshToken=" + refreshToken + ", tokenExpiry=" + tokenExpiry
				+ ", userName=" + userName + ", msisdn=" + msisdn + ", userId=" + userId + ", fullName=" + fullName
				+ ", channelType=" + channelType + ", entityId=" + entityId + ", isForceChangePassword="
				+ isForceChangePassword + ", designationId=" + designationId + ", isApprovalRequired="
				+ isApprovalRequired + ", approvalTypeFlag=" + approvalTypeFlag + ", parentId=" + parentId
				+ ", channelTypeIdName=" + channelTypeIdName + ", designationName=" + designationName + ", roleName="
				+ roleName + ", sessionName=" + sessionName + ", privilages=" + privilages + "]";
	}

}
