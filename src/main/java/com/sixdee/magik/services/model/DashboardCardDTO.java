package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * The persistent class for the RPT_SUBMIT_DELIVERY_FACT database table.
 * 
 */
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name="RPT_SUBMIT_DELIVERY_FACT")
public class DashboardCardDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="SERIAL_NO")
	private Integer  serialNo;
	
	@Column(name="BOUNS_CREDITED",nullable = true)
	private Integer  bounsCredited;

	@Column(name="CAMPAIGN_CIRCLE_ID",nullable = true)
	private Integer  campaignCircleId;

	@Column(name="CDR_DATE")
	private String cdrDate;

	@Column(name="CHANNEL_ID",nullable = true)
	private String channelId;
	
	@Column(name="CAMPAIGN_ID",nullable = true)
	private String campId;

	@Column(name="CIRCLE_ID",nullable = true)
	private Integer  circleId;

	@Column(name="CONTROL_GROUP_ID",nullable = true)
	private Integer  controlGroupId;

	@Column(name="CONTROL_GROUP_REVENUE",nullable = true)
	private Integer  controlGroupRevenue;

	@Column(name="CREATE_TIME",nullable = true)
	private Timestamp createTime;

	@Column(name="DELIVERY_FAILURE_COUNT",nullable = true)
	private Integer  deliveryFailureCount;

	@Column(name="DELIVERY_SUCCESS_COUNT",nullable = true)
	private Integer  deliverySuccessCount;

	@Column(name="H_HOUR",nullable = true)
	private Integer  hHour;

	@Column(name="INTERFACE_ID",nullable = true)
	private Integer  interfaceId;

	@Column(name="MSG_ID",nullable = true)
	private Integer  msgId;

	@Column(name="OPERATION_TYPE",nullable = true)
	private String operationType;

	@Column(name="PRODUCT_ID",nullable = true)
	private Integer  productId;

	@Column(name="RECHARGE_AMOUNT",nullable = true)
	private Integer  rechargeAmount;

	@Column(name="SCHEDULE_ID",nullable = true)
	private Integer  scheduleId;


	@Column(name="SHORT_CODE",nullable = true)
	private String shortCode;

	@Column(name="SMS_REVENUE",nullable = true)
	private Integer  smsRevenue;

	@Column(name="SMSC_ID",nullable = true)
	private Integer  smscId;

	@Column(name="STATUS_CODE",nullable = true)
	private Integer  statusCode;

	@Column(name="SUBMIT_FAILED_COUNT",nullable = true)
	private Integer  submitFailedCount;

	@Column(name="SUBMIT_SUCCESS_COUNT",nullable = true)
	private Integer  submitSuccessCount;

	@Column(name="TARGET_COUNT",nullable = true)
	private Integer  targetCount;

	@Column(name="TARGET_GROUP_ID",nullable = true)
	private Integer  targetGroupId;

	@Column(name="TARGET_GROUP_REVENUE",nullable = true)
	private Integer  targetGroupRevenue;

	@Column(name="TASK_ID",nullable = true)
	private Integer  taskId;

	@Column(name="TOTAL_COUNT",nullable = true)
	private Integer  totalCount;

	@Column(name="TYPE_OF_ACTION",nullable = true)
	private String typeOfAction;

	@Column(name="USER_ID",nullable = true)
	private Integer  userId;

	@Column(name="VOICE_REVENUE",nullable = true)
	private Integer  voiceRevenue;
	
	@Column(name="CAMPAIGN_NAME",nullable = true)
	private String  campName;
	
	@Column(name="CAMPAIGN_TYPE",nullable = true)
	private String  campaignType;
	
	@Column(name="RESPONSE_COUNT",nullable = true)
	private Integer  responseCount;
	
	@Transient
	private String endDate;
	
	@Transient
	private String startDate;
	
	@Transient
	private boolean defaultDash;
	
	@Transient
	private String feature;
	
	@Column(name="TG_RESPOND",nullable = true)
	private Integer tgRespond;
	
	@Column(name="CG_RESPOND",nullable = true)
	private Integer cgRespond;
	
	@Column(name="PRODUCT_NAME",nullable = true)
	private String productName;
	
	@Column(name="ACCOUNT_TYPE",nullable = true)
	private String accountType;
	
	@Transient
	private List<String> campaignTypeList;
	
	@Transient
	private List<String> accountTypeList;
	
	@Transient
	private String circleName;
	
	@Transient
	private Integer limit;
	
	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public String getCircleName() {
		return circleName;
	}

	public void setCircleName(String circleName) {
		this.circleName = circleName;
	}

	public List<String> getCampaignTypeList() {
		return campaignTypeList;
	}

	public void setCampaignTypeList(List<String> campaignTypeList) {
		this.campaignTypeList = campaignTypeList;
	}

	public List<String> getAccountTypeList() {
		return accountTypeList;
	}

	public void setAccountTypeList(List<String> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}

	private boolean customDate;

	private Integer days;
	
	public boolean isCustomDate() {
		return customDate;
	}

	public void setCustomDate(boolean customDate) {
		this.customDate = customDate;
	}
	
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getTgRespond() {
		return tgRespond;
	}

	public void setTgRespond(Integer tgRespond) {
		this.tgRespond = tgRespond;
	}

	public Integer getCgRespond() {
		return cgRespond;
	}

	public void setCgRespond(Integer cgRespond) {
		this.cgRespond = cgRespond;
	}

	public boolean isDefaultDash() {
		return defaultDash;
	}

	public void setDefaultDash(boolean defaultDash) {
		this.defaultDash = defaultDash;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDtae) {
		this.endDate = endDtae;
	}

	public Integer getResponseCount() {
		return responseCount;
	}

	public void setResponseCount(Integer responseCount) {
		this.responseCount = responseCount;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campType) {
		this.campaignType = campType;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public DashboardCardDTO() {
	}

	public Integer  getBounsCredited() {
		return this.bounsCredited;
	}

	public void setBounsCredited(Integer  bounsCredited) {
		this.bounsCredited = bounsCredited;
	}

	public Integer  getCampaignCircleId() {
		return this.campaignCircleId;
	}

	public void setCampaignCircleId(Integer  campaignCircleId) {
		this.campaignCircleId = campaignCircleId;
	}

	public String getCdrDate() {
		return this.cdrDate;
	}

	public void setCdrDate(String cdrDate) {
		this.cdrDate = cdrDate;
	}

	public String getChannelId() {
		return this.channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public Integer getCircleId() {
		return this.circleId;
	}

	public void setCircleId(Integer  circleId) {
		this.circleId = circleId;
	}

	public Integer  getControlGroupId() {
		return this.controlGroupId;
	}

	public void setControlGroupId(Integer  controlGroupId) {
		this.controlGroupId = controlGroupId;
	}

	public Integer  getControlGroupRevenue() {
		return this.controlGroupRevenue;
	}

	public void setControlGroupRevenue(Integer  controlGroupRevenue) {
		this.controlGroupRevenue = controlGroupRevenue;
	}

	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer  getDeliveryFailureCount() {
		return this.deliveryFailureCount;
	}

	public void setDeliveryFailureCount(Integer  deliveryFailureCount) {
		this.deliveryFailureCount = deliveryFailureCount;
	}

	public Integer  getDeliverySuccessCount() {
		return this.deliverySuccessCount;
	}

	public void setDeliverySuccessCount(Integer deliverySuccessCount) {
		this.deliverySuccessCount = deliverySuccessCount;
	}

	public Integer  getHHour() {
		return this.hHour;
	}

	public void setHHour(Integer  hHour) {
		this.hHour = hHour;
	}

	public Integer  getInterfaceId() {
		return this.interfaceId;
	}

	public void setInterfaceId(Integer  interfaceId) {
		this.interfaceId = interfaceId;
	}

	public Integer  getMsgId() {
		return this.msgId;
	}

	public void setMsgId(Integer  msgId) {
		this.msgId = msgId;
	}

	public String getOperationType() {
		return this.operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Integer  getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer  getRechargeAmount() {
		return this.rechargeAmount;
	}

	public void setRechargeAmount(Integer  rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public Integer  getScheduleId() {
		return this.scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Integer  getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(Integer  serialNo) {
		this.serialNo = serialNo;
	}

	public String getShortCode() {
		return this.shortCode;
	}

	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}

	public Integer  getSmsRevenue() {
		return this.smsRevenue;
	}

	public void setSmsRevenue(Integer  smsRevenue) {
		this.smsRevenue = smsRevenue;
	}

	public Integer  getSmscId() {
		return this.smscId;
	}

	public void setSmscId(Integer smscId) {
		this.smscId = smscId;
	}

	public Integer  getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(Integer  statusCode) {
		this.statusCode = statusCode;
	}

	public Integer  getSubmitFailedCount() {
		return this.submitFailedCount;
	}

	public void setSubmitFailedCount(Integer  submitFailedCount) {
		this.submitFailedCount = submitFailedCount;
	}

	public Integer  getSubmitSuccessCount() {
		return this.submitSuccessCount;
	}

	public void setSubmitSuccessCount(Integer  submitSuccessCount) {
		this.submitSuccessCount = submitSuccessCount;
	}

	public Integer  getTargetCount() {
		return this.targetCount;
	}

	public void setTargetCount(Integer  targetCount) {
		this.targetCount = targetCount;
	}

	public Integer  getTargetGroupId() {
		return this.targetGroupId;
	}

	public void setTargetGroupId(Integer  targetGroupId) {
		this.targetGroupId = targetGroupId;
	}

	public Integer  getTargetGroupRevenue() {
		return this.targetGroupRevenue;
	}

	public void setTargetGroupRevenue(Integer  targetGroupRevenue) {
		this.targetGroupRevenue = targetGroupRevenue;
	}

	public Integer  getTaskId() {
		return this.taskId;
	}

	public void setTaskId(Integer  taskId) {
		this.taskId = taskId;
	}

	public Integer  getTotalCount() {
		return this.totalCount;
	}

	public void setTotalCount(Integer  totalCount) {
		this.totalCount = totalCount;
	}

	public String getTypeOfAction() {
		return this.typeOfAction;
	}

	public void setTypeOfAction(String typeOfAction) {
		this.typeOfAction = typeOfAction;
	}

	public Integer  getUserId() {
		return this.userId;
	}

	public void setUserId(Integer  userId) {
		this.userId = userId;
	}

	public Integer  getVoiceRevenue() {
		return this.voiceRevenue;
	}

	public void setVoiceRevenue(Integer  voiceRevenue) {
		this.voiceRevenue = voiceRevenue;
	}

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}
}