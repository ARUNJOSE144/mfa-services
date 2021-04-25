package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * Rajesh
 */
@Entity
@Table(name = "RE_RULE_SCHEDULE_INFO")
@SuppressWarnings("serial")
public class ScheduleTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ScheduleTO")
	@TableGenerator(name = "ScheduleTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String ruleName;

	@Column(name = "JSON")
	private String ruleJson;

	@Column(name = "CAMPAIGN")
	private int campaignId;

	@Column(name = "RE_ADAPTER")
	private int ruleEngineURLId;

	@Column(name = "CREATED_DATE")
	private Date createDate;

	@Column(name = "CREATED_BY")
	private int userId;

	@Column(name = "UI_JSON")
	private String uiJson;

	@Column(name = "SCHEDULED_TYPE")
	private int typeId;

	@Column(name = "APPROVAL_LEVEL")
	private int approvalLevel;

	@Column(name = "PRIORITY")
	private int priority;

	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "RULE_STATUS")
	private int ruleStatus;

	@Column(name = "APPROVAL_STATUS")
	private int approvalStatus;

	@Column(name = "SAMPLING_ID")
	private int samplingId;

	@Column(name = "LEAD_POLICY")
	private String leadPolicy;

	@Column(name = "CONTACT_POLICY")
	private int contactPolicy;

	@Column(name = "IS_AB_TESTING")
	private String isABTesting;

	@Column(name = "RULE_ENGINE_URL")
	private String ruleEngineUrl;

	@Column(name = "APPROVER")
	private String approverType;

	@Transient
	public LinkedHashMap<Integer, String> samplingMap;
	@Transient
	public LinkedHashMap<Integer, String> campaignMap;
	@Transient
	public LinkedHashMap<Integer, String> scheduleTypeMap;
	@Transient
	public LinkedHashMap<Integer, String> leadPolicyMap;
	@Transient
	public LinkedHashMap<Integer, String> contactPolicyMap;
	@Transient
	public List<WeekdaysTO> weekdaysList;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleJson() {
		return ruleJson;
	}

	public void setRuleJson(String ruleJson) {
		this.ruleJson = ruleJson;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public int getRuleEngineURLId() {
		return ruleEngineURLId;
	}

	public void setRuleEngineURLId(int ruleEngineURLId) {
		this.ruleEngineURLId = ruleEngineURLId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public LinkedHashMap<Integer, String> getSamplingMap() {
		return samplingMap;
	}

	public void setSamplingMap(LinkedHashMap<Integer, String> samplingMap) {
		this.samplingMap = samplingMap;
	}

	public LinkedHashMap<Integer, String> getCampaignMap() {
		return campaignMap;
	}

	public void setCampaignMap(LinkedHashMap<Integer, String> campaignMap) {
		this.campaignMap = campaignMap;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUiJson() {
		return uiJson;
	}

	public void setUiJson(String uiJson) {
		this.uiJson = uiJson;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public int getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public LinkedHashMap<Integer, String> getScheduleTypeMap() {
		return scheduleTypeMap;
	}

	public void setScheduleTypeMap(LinkedHashMap<Integer, String> scheduleTypeMap) {
		this.scheduleTypeMap = scheduleTypeMap;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public LinkedHashMap<Integer, String> getLeadPolicyMap() {
		return leadPolicyMap;
	}

	public void setLeadPolicyMap(LinkedHashMap<Integer, String> leadPolicyMap) {
		this.leadPolicyMap = leadPolicyMap;
	}

	public LinkedHashMap<Integer, String> getContactPolicyMap() {
		return contactPolicyMap;
	}

	public void setContactPolicyMap(LinkedHashMap<Integer, String> contactPolicyMap) {
		this.contactPolicyMap = contactPolicyMap;
	}

	public int getRuleStatus() {
		return ruleStatus;
	}

	public void setRuleStatus(int ruleStatus) {
		this.ruleStatus = ruleStatus;
	}

	public List<WeekdaysTO> getWeekdaysList() {
		return weekdaysList;
	}

	public void setWeekdaysList(List<WeekdaysTO> weekdaysList) {
		this.weekdaysList = weekdaysList;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public int getSamplingId() {
		return samplingId;
	}

	public void setSamplingId(int samplingId) {
		this.samplingId = samplingId;
	}

	public String getLeadPolicy() {
		return leadPolicy;
	}

	public void setLeadPolicy(String leadPolicy) {
		this.leadPolicy = leadPolicy;
	}

	public int getContactPolicy() {
		return contactPolicy;
	}

	public void setContactPolicy(int contactPolicy) {
		this.contactPolicy = contactPolicy;
	}

	public String getIsABTesting() {
		return isABTesting;
	}

	public void setIsABTesting(String isABTesting) {
		this.isABTesting = isABTesting;
	}

	public String getRuleEngineUrl() {
		return ruleEngineUrl;
	}

	public void setRuleEngineUrl(String ruleEngineUrl) {
		this.ruleEngineUrl = ruleEngineUrl;
	}

	public String getApproverType() {
		return approverType;
	}

	public void setApproverType(String approverType) {
		this.approverType = approverType;
	}

	@Override
	public String toString() {
		return "ScheduleTO [Id=" + Id + ", ruleName=" + ruleName + ", ruleJson=" + ruleJson + ", campaignId="
				+ campaignId + ", ruleEngineURLId=" + ruleEngineURLId + ", createDate=" + createDate + ", userId="
				+ userId + ", uiJson=" + uiJson + ", typeId=" + typeId + ", approvalLevel=" + approvalLevel
				+ ", priority=" + priority + ", expiryDate=" + expiryDate + ", startDate=" + startDate + ", ruleStatus="
				+ ruleStatus + ", approvalStatus=" + approvalStatus + ", samplingId=" + samplingId + ", leadPolicy="
				+ leadPolicy + ", contactPolicy=" + contactPolicy + ", isABTesting=" + isABTesting + ", ruleEngineUrl="
				+ ruleEngineUrl + ", approverType=" + approverType + ", samplingMap=" + samplingMap + ", campaignMap="
				+ campaignMap + ", scheduleTypeMap=" + scheduleTypeMap + ", leadPolicyMap=" + leadPolicyMap
				+ ", contactPolicyMap=" + contactPolicyMap + ", weekdaysList=" + weekdaysList + "]";
	}

}
