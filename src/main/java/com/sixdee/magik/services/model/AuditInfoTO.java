package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Nakhil Kurian
 * @Date : March, 2020
 *
 */
@Entity
@Table(name = "AUDIT_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AuditInfoTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AuditInfoTO")
	@TableGenerator(name = "AuditInfoTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "DESCRIPTION")
	private String desc;

	@Column(name = "CREATE_DATE", nullable = false, updatable = false)
	@CreationTimestamp
	private Date createDate;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "NAME")
	private String addedName;

	@Column(name = "FEATURE")
	private String featureName;

	@Column(name = "ACTION_TYPE")
	private String actionType;

	@Column(name = "ATTRIBUTE")
	private String attribute;

	@Column(name = "PREVIOUS_VALUE")
	private String previousValue;

	@Column(name = "NEW_VALUE")
	private String newValue;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "ROLE_NAME")
	private String roleName;

	@Transient
	private Date startDate;

	@Transient
	private Date endDate;

	@Transient
	private String createDateUI;

	@Transient
	private String ruleName;

	@Transient
	private String auditDetails;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public String getAddedName() {
		return addedName;
	}

	public void setAddedName(String addedName) {
		this.addedName = addedName;
	}

	public String getFeatureName() {
		return featureName;
	}

	public void setFeatureName(String featureName) {
		this.featureName = featureName;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public String getPreviousValue() {
		return previousValue;
	}

	public void setPreviousValue(String previousValue) {
		this.previousValue = previousValue;
	}

	public String getNewValue() {
		return newValue;
	}

	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getAuditDetails() {
		return auditDetails;
	}

	public void setAuditDetails(String auditDetails) {
		this.auditDetails = auditDetails;
	}

	@Override
	public String toString() {
		return "AuditInfoTO [Id=" + Id + ", desc=" + desc + ", createDate=" + createDate + ", userId=" + userId
				+ ", addedName=" + addedName + ", featureName=" + featureName + ", actionType=" + actionType
				+ ", attribute=" + attribute + ", previousValue=" + previousValue + ", newValue=" + newValue
				+ ", createdBy=" + createdBy + ", roleName=" + roleName + ", startDate=" + startDate + ", endDate="
				+ endDate + ", createDateUI=" + createDateUI + ", ruleName=" + ruleName + ", auditDetails="
				+ auditDetails + "]";
	}

}
