package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * @author Rajesh
 * @Date : Feb, 2019
 *
 */

@Entity
@Table(name = "RE_LEAD_POLICY")
@SuppressWarnings("serial")
public class LeadPolicyTO implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "LeadPolicyTO")
	@TableGenerator(name = "LeadPolicyTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "EXPIRY_DAYS")
	private String expiryDays;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ACTION_KEY")
	private String excludedActionKeysIds;

	@Column(name = "ACTION_KEY_NAMES")
	private String excludedActionKeysNames;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "CAMPAIGNS")
	private String excludedCampaignIds;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "SUBSCRIBER_STATUS")
	private String subscriberStatus;

	@Column(name = "BONUS_TYPE")
	private String bonusType;

	@Transient
	private String createDateUI;

	@Transient
	private String startdateConvert;

	@Transient
	private String enddateConvert;

	@Transient
	private String excludedCampaignNames;

	@Transient
	private String auditRoleName;

	@Transient
	private String auditUserName;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public String getExpiryDays() {
		return expiryDays;
	}

	public void setExpiryDays(String expiryDays) {
		this.expiryDays = expiryDays;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getExcludedActionKeysIds() {
		return excludedActionKeysIds;
	}

	public void setExcludedActionKeysIds(String excludedActionKeysIds) {
		this.excludedActionKeysIds = excludedActionKeysIds;
	}

	public String getExcludedActionKeysNames() {
		return excludedActionKeysNames;
	}

	public void setExcludedActionKeysNames(String excludedActionKeysNames) {
		this.excludedActionKeysNames = excludedActionKeysNames;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getExcludedCampaignIds() {
		return excludedCampaignIds;
	}

	public void setExcludedCampaignIds(String excludedCampaignIds) {
		this.excludedCampaignIds = excludedCampaignIds;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public String getExcludedCampaignNames() {
		return excludedCampaignNames;
	}

	public void setExcludedCampaignNames(String excludedCampaignNames) {
		this.excludedCampaignNames = excludedCampaignNames;
	}

	public Object cloneObject(Object obj) {
		try {
			Object clone = obj.getClass().newInstance();
			for (Field field : obj.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				field.set(clone, field.get(obj));
			}
			return clone;
		} catch (Exception e) {
			return null;
		}
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

	public String getSubscriberStatus() {
		return subscriberStatus;
	}

	public void setSubscriberStatus(String subscriberStatus) {
		this.subscriberStatus = subscriberStatus;
	}

	public String getBonusType() {
		return bonusType;
	}

	public void setBonusType(String bonusType) {
		this.bonusType = bonusType;
	}

	public String getStartdateConvert() {
		return startdateConvert;
	}

	public void setStartdateConvert(String startdateConvert) {
		this.startdateConvert = startdateConvert;
	}

	public String getEnddateConvert() {
		return enddateConvert;
	}

	public void setEnddateConvert(String enddateConvert) {
		this.enddateConvert = enddateConvert;
	}

	public String getAuditRoleName() {
		return auditRoleName;
	}

	public void setAuditRoleName(String auditRoleName) {
		this.auditRoleName = auditRoleName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	@Override
	public String toString() {
		return "LeadPolicyTO [id=" + id + ", policyName=" + policyName + ", expiryDays=" + expiryDays + ", description="
				+ description + ", excludedActionKeysIds=" + excludedActionKeysIds + ", excludedActionKeysNames="
				+ excludedActionKeysNames + ", createDate=" + createDate + ", excludedCampaignIds="
				+ excludedCampaignIds + ", startDate=" + startDate + ", endDate=" + endDate + ", subscriberStatus="
				+ subscriberStatus + ", bonusType=" + bonusType + ", createDateUI=" + createDateUI
				+ ", startdateConvert=" + startdateConvert + ", enddateConvert=" + enddateConvert
				+ ", excludedCampaignNames=" + excludedCampaignNames + ", auditRoleName=" + auditRoleName
				+ ", auditUserName=" + auditUserName + "]";
	}

}
