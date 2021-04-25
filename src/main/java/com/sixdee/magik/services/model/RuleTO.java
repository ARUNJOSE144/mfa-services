package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Rajesh
 * @Date : December, 2018
 *
 */
public class RuleTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int ruleId;
	private int savedRuleId;
	private int campaignId;
	private String ruleName;
	private String campaignName;
	private Date createDate;
	private String createDateUI;
	private int urlId;
	private String urlDetails;
	private int statusID;
	private String status;
	private int isSavedORScheduledRule;// -1 for saved rules ,1 for scheduled rules , 0 for all
	private String ruleJson;
	private String uiJson;

	// for Scheduled rules
	private Date expiryDate;
	private Date startDate;
	private int priority;
	private int approvalLevel;
	private int samplingId;
	private int scheduleTypeId;
	private String scheduleType;
	private int day;
	private int hour;
	private int minute;
	private int approvalStatus;
	private int contactPolicyId;
	private String leadPolicy;
	private String isABTesting;
	private String playPauseJson;

	// Group comparion
	private String groupComparisonJson;
	private String campaignNameGroup;
	private String isABTestingReport;
	private String isTrackerBoosterReport;
	private String isWeeklyReport;
	private String beforeTrakerReport;
	private String afterTrakerReport;
	private String weekTrakerReport;

	// Url
	private String ruleEngineUrlId;
	private String ruleEngineUrlName;
	private String ruleEngineUrl;

	// Online Trigger Url

	private String onlineTriggerUrlId;
	private String onlineTriggerUrl;
	private String onlineTriggerUrlName;
	private String onlineTriggerId;

	// userId
	private int userId;

	// Approval
	private String parentId;
	private String channelTypeName;
	private String designationName;
	private String approvalType;
	private String userName;

	// Audit
	private String userNameAudit;
	private String roleNameAudit;
	private String userIdAudit;

	public String getPlayPauseJson() {
		return playPauseJson;
	}

	public void setPlayPauseJson(String playPauseJson) {
		this.playPauseJson = playPauseJson;
	}

	public int getSavedRuleId() {
		return savedRuleId;
	}

	public void setSavedRuleId(int savedRuleId) {
		this.savedRuleId = savedRuleId;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
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

	public int getIsSavedORScheduledRule() {
		return isSavedORScheduledRule;
	}

	public void setIsSavedORScheduledRule(int isSavedORScheduledRule) {
		this.isSavedORScheduledRule = isSavedORScheduledRule;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public int getUrlId() {
		return urlId;
	}

	public void setUrlId(int urlId) {
		this.urlId = urlId;
	}

	public String getUrlDetails() {
		return urlDetails;
	}

	public void setUrlDetails(String urlDetails) {
		this.urlDetails = urlDetails;
	}

	public int getStatusID() {
		return statusID;
	}

	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int isSavedORScheduledRule() {
		return isSavedORScheduledRule;
	}

	public void setSavedORScheduledRule(int isSavedORScheduledRule) {
		this.isSavedORScheduledRule = isSavedORScheduledRule;
	}

	public String getRuleJson() {
		return ruleJson;
	}

	public void setRuleJson(String ruleJson) {
		this.ruleJson = ruleJson;
	}

	public String getUiJson() {
		return uiJson;
	}

	public void setUiJson(String uiJson) {
		this.uiJson = uiJson;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getApprovalLevel() {
		return approvalLevel;
	}

	public void setApprovalLevel(int approvalLevel) {
		this.approvalLevel = approvalLevel;
	}

	public int getSamplingId() {
		return samplingId;
	}

	public void setSamplingId(int samplingId) {
		this.samplingId = samplingId;
	}

	public int getScheduleTypeId() {
		return scheduleTypeId;
	}

	public void setScheduleTypeId(int scheduleTypeId) {
		this.scheduleTypeId = scheduleTypeId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getHour() {
		return hour;
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public int getMinute() {
		return minute;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}

	public String getScheduleType() {
		return scheduleType;
	}

	public void setScheduleType(String scheduleType) {
		this.scheduleType = scheduleType;
	}

	public int getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(int approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getContactPolicyId() {
		return contactPolicyId;
	}

	public void setContactPolicyId(int contactPolicyId) {
		this.contactPolicyId = contactPolicyId;
	}

	public String getLeadPolicy() {
		return leadPolicy;
	}

	public void setLeadPolicy(String leadPolicy) {
		this.leadPolicy = leadPolicy;
	}

	public String getIsABTesting() {
		return isABTesting;
	}

	public void setIsABTesting(String isABTesting) {
		this.isABTesting = isABTesting;
	}

	public String getGroupComparisonJson() {
		return groupComparisonJson;
	}

	public void setGroupComparisonJson(String groupComparisonJson) {
		this.groupComparisonJson = groupComparisonJson;
	}

	public String getCampaignNameGroup() {
		return campaignNameGroup;
	}

	public void setCampaignNameGroup(String campaignNameGroup) {
		this.campaignNameGroup = campaignNameGroup;
	}

	public String getIsABTestingReport() {
		return isABTestingReport;
	}

	public void setIsABTestingReport(String isABTestingReport) {
		this.isABTestingReport = isABTestingReport;
	}

	public String getIsTrackerBoosterReport() {
		return isTrackerBoosterReport;
	}

	public void setIsTrackerBoosterReport(String isTrackerBoosterReport) {
		this.isTrackerBoosterReport = isTrackerBoosterReport;
	}

	public String getIsWeeklyReport() {
		return isWeeklyReport;
	}

	public void setIsWeeklyReport(String isWeeklyReport) {
		this.isWeeklyReport = isWeeklyReport;
	}

	public String getBeforeTrakerReport() {
		return beforeTrakerReport;
	}

	public void setBeforeTrakerReport(String beforeTrakerReport) {
		this.beforeTrakerReport = beforeTrakerReport;
	}

	public String getAfterTrakerReport() {
		return afterTrakerReport;
	}

	public void setAfterTrakerReport(String afterTrakerReport) {
		this.afterTrakerReport = afterTrakerReport;
	}

	public String getWeekTrakerReport() {
		return weekTrakerReport;
	}

	public void setWeekTrakerReport(String weekTrakerReport) {
		this.weekTrakerReport = weekTrakerReport;
	}

	public String getRuleEngineUrlId() {
		return ruleEngineUrlId;
	}

	public void setRuleEngineUrlId(String ruleEngineUrlId) {
		this.ruleEngineUrlId = ruleEngineUrlId;
	}

	public String getRuleEngineUrlName() {
		return ruleEngineUrlName;
	}

	public void setRuleEngineUrlName(String ruleEngineUrlName) {
		this.ruleEngineUrlName = ruleEngineUrlName;
	}

	public String getRuleEngineUrl() {
		return ruleEngineUrl;
	}

	public void setRuleEngineUrl(String ruleEngineUrl) {
		this.ruleEngineUrl = ruleEngineUrl;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOnlineTriggerUrlId() {
		return onlineTriggerUrlId;
	}

	public void setOnlineTriggerUrlId(String onlineTriggerUrlId) {
		this.onlineTriggerUrlId = onlineTriggerUrlId;
	}

	public String getOnlineTriggerUrl() {
		return onlineTriggerUrl;
	}

	public void setOnlineTriggerUrl(String onlineTriggerUrl) {
		this.onlineTriggerUrl = onlineTriggerUrl;
	}

	public String getOnlineTriggerUrlName() {
		return onlineTriggerUrlName;
	}

	public void setOnlineTriggerUrlName(String onlineTriggerUrlName) {
		this.onlineTriggerUrlName = onlineTriggerUrlName;
	}

	public String getOnlineTriggerId() {
		return onlineTriggerId;
	}

	public void setOnlineTriggerId(String onlineTriggerId) {
		this.onlineTriggerId = onlineTriggerId;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getChannelTypeName() {
		return channelTypeName;
	}

	public void setChannelTypeName(String channelTypeName) {
		this.channelTypeName = channelTypeName;
	}

	public String getDesignationName() {
		return designationName;
	}

	public void setDesignationName(String designationName) {
		this.designationName = designationName;
	}

	public String getApprovalType() {
		return approvalType;
	}

	public void setApprovalType(String approvalType) {
		this.approvalType = approvalType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameAudit() {
		return userNameAudit;
	}

	public void setUserNameAudit(String userNameAudit) {
		this.userNameAudit = userNameAudit;
	}

	public String getRoleNameAudit() {
		return roleNameAudit;
	}

	public void setRoleNameAudit(String roleNameAudit) {
		this.roleNameAudit = roleNameAudit;
	}

	public String getUserIdAudit() {
		return userIdAudit;
	}

	public void setUserIdAudit(String userIdAudit) {
		this.userIdAudit = userIdAudit;
	}

	@Override
	public String toString() {
		return "RuleTO [ruleId=" + ruleId + ", savedRuleId=" + savedRuleId + ", campaignId=" + campaignId
				+ ", ruleName=" + ruleName + ", campaignName=" + campaignName + ", createDate=" + createDate
				+ ", createDateUI=" + createDateUI + ", urlId=" + urlId + ", urlDetails=" + urlDetails + ", statusID="
				+ statusID + ", status=" + status + ", isSavedORScheduledRule=" + isSavedORScheduledRule + ", ruleJson="
				+ ruleJson + ", uiJson=" + uiJson + ", expiryDate=" + expiryDate + ", startDate=" + startDate
				+ ", priority=" + priority + ", approvalLevel=" + approvalLevel + ", samplingId=" + samplingId
				+ ", scheduleTypeId=" + scheduleTypeId + ", scheduleType=" + scheduleType + ", day=" + day + ", hour="
				+ hour + ", minute=" + minute + ", approvalStatus=" + approvalStatus + ", contactPolicyId="
				+ contactPolicyId + ", leadPolicy=" + leadPolicy + ", isABTesting=" + isABTesting + ", playPauseJson="
				+ playPauseJson + ", groupComparisonJson=" + groupComparisonJson + ", campaignNameGroup="
				+ campaignNameGroup + ", isABTestingReport=" + isABTestingReport + ", isTrackerBoosterReport="
				+ isTrackerBoosterReport + ", isWeeklyReport=" + isWeeklyReport + ", beforeTrakerReport="
				+ beforeTrakerReport + ", afterTrakerReport=" + afterTrakerReport + ", weekTrakerReport="
				+ weekTrakerReport + ", ruleEngineUrlId=" + ruleEngineUrlId + ", ruleEngineUrlName=" + ruleEngineUrlName
				+ ", ruleEngineUrl=" + ruleEngineUrl + ", onlineTriggerUrlId=" + onlineTriggerUrlId
				+ ", onlineTriggerUrl=" + onlineTriggerUrl + ", onlineTriggerUrlName=" + onlineTriggerUrlName
				+ ", onlineTriggerId=" + onlineTriggerId + ", userId=" + userId + ", parentId=" + parentId
				+ ", channelTypeName=" + channelTypeName + ", designationName=" + designationName + ", approvalType="
				+ approvalType + ", userName=" + userName + ", userNameAudit=" + userNameAudit + ", roleNameAudit="
				+ roleNameAudit + ", userIdAudit=" + userIdAudit + "]";
	}

}
