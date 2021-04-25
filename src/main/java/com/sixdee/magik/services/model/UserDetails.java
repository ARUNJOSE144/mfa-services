package com.sixdee.magik.services.model;

import javax.validation.constraints.NotNull;

import com.sixdee.magik.services.util.ValidationHelper.CreateGroup;
import com.sixdee.magik.services.util.ValidationHelper.DeleteGroup;
import com.sixdee.magik.services.util.ValidationHelper.EditGroup;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jan 10, 2018 : 3:18:09 PM
 */
public class UserDetails {
	@NotNull(message = "userId can't be Empty!", groups = { EditGroup.class, DeleteGroup.class })
	private Long userId;

	@NotNull(message = "userName can't be Empty!", groups = { CreateGroup.class })
	private String userName;

	@NotNull(message = "firstName can't be Empty!", groups = { CreateGroup.class })
	private String firstName;

	private String lastName;

	private String empCode;

	@NotNull(message = "msisdn can't be Empty!", groups = { CreateGroup.class })
	private String msisdn;

	private String email;

	@NotNull(message = "designationId can't be Empty!", groups = { CreateGroup.class })
	private long designationId;
	private String designation;

	@NotNull(message = "reportingMgr can't be Empty!", groups = { CreateGroup.class })
	private String parentId;
	private String parent;

	@NotNull(message = "channelType can't be Empty!", groups = { CreateGroup.class })
	private int channelType;
	private String channelTypeName;
	private int cpType;
	private boolean ldapUser;

	private String isApprovalRequired;
	private String approvalTypeFlag;
	private String channelTypeIdName;
	private String designationName;

	/*
	 * private String teritoryType;
	 */

	/*
	 * @NotNull(message = "teritory can't be Empty!", groups = { CreateGroup.class
	 * }) private int teritoryId; private String teritoryName;
	 */

	private int statusId;
	private String statusName;

	@NotNull(message = "address can't be Empty!", groups = { CreateGroup.class })
	private Address address;

	private int teritoryTypeId;

	private String entityId;
	private String entity;

	/** ----------------------------------------. **/

	public UserDetails() {

	}

	public UserDetails(Long userId, String firstName, String lastName, String msisdn, String designation,
			String statusName, int channelType, boolean ldapUser) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.msisdn = msisdn;
		this.designation = designation;
		this.statusName = statusName;
		this.channelType = channelType;
		this.ldapUser = ldapUser;

	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getDesignationId() {
		return designationId;
	}

	public void setDesignationId(long designationId) {
		this.designationId = designationId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public int getTeritoryTypeId() {
		return teritoryTypeId;
	}

	public void setTeritoryTypeId(int teritoryTypeId) {
		this.teritoryTypeId = teritoryTypeId;
	}

	/*
	 * public int getTeritoryId() { return teritoryId; }
	 * 
	 * public void setTeritoryId(int teritoryId) { this.teritoryId = teritoryId; }
	 */

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*
	 * public String getTeritoryType() { return teritoryType; }
	 * 
	 * public void setTeritoryType(String teritoryType) { this.teritoryType =
	 * teritoryType; }
	 */

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	/*
	 * public String getTeritoryName() { return teritoryName; }
	 * 
	 * public void setTeritoryName(String teritoryName) { this.teritoryName =
	 * teritoryName; }
	 */

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getEntity() {
		return entity;
	}

	public void setEntity(String entity) {
		this.entity = entity;
	}

	public int getCpType() {
		return cpType;
	}

	public void setCpType(int cpType) {
		this.cpType = cpType;
	}

	public boolean isLdapUser() {
		return ldapUser;
	}

	public void setLdapUser(boolean ldapUser) {
		this.ldapUser = ldapUser;
	}

	public String getChannelTypeName() {
		return channelTypeName;
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

	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
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

}
