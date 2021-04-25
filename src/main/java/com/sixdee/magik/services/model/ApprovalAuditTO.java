package com.sixdee.magik.services.model;

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
 * @category February 2021
 * 
 */
@Entity
@Table(name = "APPROVAL_AUDIT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ApprovalAuditTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ApprovalAuditTO")
	@TableGenerator(name = "ApprovalAuditTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "OPERATOR")
	private String operator;

	@Column(name = "DESIGNATION")
	private String designation;

	@Column(name = "USER_VALUE")
	private String user;

	@Column(name = "APPROVAL_TYPE")
	private String approvalType;

	@Column(name = "APPROVER")
	private int approver;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "RULE_ID")
	private int ruleId;

	@Column(name = "RULE_NAME")
	private String ruleName;

	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;

	@Column(name = "APPROVAL_DATE", nullable = false, updatable = false)
	@CreationTimestamp
	private Date approvalDate;

	@Transient
	private String createDateUI;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public int getApprover() {
		return approver;
	}

	public void setApprover(int approver) {
		this.approver = approver;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public Date getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(Date approvalDate) {
		this.approvalDate = approvalDate;
	}

	@Override
	public String toString() {
		return "ApprovalAuditTO [id=" + id + ", operator=" + operator + ", designation=" + designation + ", user="
				+ user + ", approvalType=" + approvalType + ", approver=" + approver + ", status=" + status
				+ ", ruleId=" + ruleId + ", ruleName=" + ruleName + ", campaignName=" + campaignName + ", approvalDate="
				+ approvalDate + ", createDateUI=" + createDateUI + "]";
	}

}
