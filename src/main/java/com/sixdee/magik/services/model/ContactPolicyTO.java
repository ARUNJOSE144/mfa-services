package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
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

@Entity
@Table(name = "NCP_DETAIL")
@SuppressWarnings("serial")
public class ContactPolicyTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ContactPolicyTO")
	@TableGenerator(name = "ContactPolicyTO", allocationSize = 1)
	@Column(name = "POLICY_ID")
	private int policyId;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "CATEGORY_FIELD")
	private int categoryField;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "POLICY_JSON")
	private String policyJson;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "NO_OF_DAYS")
	private String noOfDays;

	@Column(name = "CATEGORY_TYPE")
	private int categoryType;

	@Column(name = "ACTION_KEY")
	private String actionkeyName;

	@Column(name = "ACTION_KEY_ID")
	private String actionkeyId;

	@Column(name = "ACTION_DAILY")
	private String actionDaily;

	@Column(name = "ACTION_WEEKLY")
	private String actionWeekly;

	@Column(name = "ACTION_MONTHLY")
	private String actionMonthly;

	@Column(name = "ACTION_CYCLIC")
	private String actionCyclic;

	@Transient
	private String policyType;

	@Transient
	private ArrayList<ContactPolicyValues> counts;

	@Transient
	public LinkedHashMap<Integer, String> contactPolicyProfiles;
	@Transient
	public LinkedHashMap<Integer, List<String>> profileTargetBandMap;
	@Transient
	public List<String> actionKeys;

	@Transient
	public ArrayList<ContactPolicyDescriptionTO> childrens;

	@Transient
	public String id;

	@Transient
	public String cyclicDays;

	@Transient
	public String strtDate;

	@Transient
	public String endDatee;

	@Transient
	private String startdateConvert;

	@Transient
	private String enddateConvert;

	@Transient
	private String auditRoleName;

	@Transient
	private String auditUserName;

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getCategoryField() {
		return categoryField;
	}

	public void setCategoryField(int categoryField) {
		this.categoryField = categoryField;
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

	public String getPolicyJson() {
		return policyJson;
	}

	public void setPolicyJson(String policyJson) {
		this.policyJson = policyJson;
	}

	public LinkedHashMap<Integer, String> getContactPolicyProfiles() {
		return contactPolicyProfiles;
	}

	public void setContactPolicyProfiles(LinkedHashMap<Integer, String> contactPolicyProfiles) {
		this.contactPolicyProfiles = contactPolicyProfiles;
	}

	public LinkedHashMap<Integer, List<String>> getProfileTargetBandMap() {
		return profileTargetBandMap;
	}

	public void setProfileTargetBandMap(LinkedHashMap<Integer, List<String>> profileTargetBandMap) {
		this.profileTargetBandMap = profileTargetBandMap;
	}

	public List<String> getActionKeys() {
		return actionKeys;
	}

	public void setActionKeys(List<String> actionKeys) {
		this.actionKeys = actionKeys;
	}

	public ArrayList<ContactPolicyDescriptionTO> getChildrens() {
		return childrens;
	}

	public void setChildrens(ArrayList<ContactPolicyDescriptionTO> childrens) {
		this.childrens = childrens;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCyclicDays() {
		return cyclicDays;
	}

	public void setCyclicDays(String cyclicDays) {
		this.cyclicDays = cyclicDays;
	}

	public String getStrtDate() {
		return strtDate;
	}

	public void setStrtDate(String strtDate) {
		this.strtDate = strtDate;
	}

	public String getEndDatee() {
		return endDatee;
	}

	public void setEndDatee(String endDatee) {
		this.endDatee = endDatee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNoOfDays() {
		return noOfDays;
	}

	public void setNoOfDays(String noOfDays) {
		this.noOfDays = noOfDays;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public ArrayList<ContactPolicyValues> getCounts() {
		return counts;
	}

	public void setCounts(ArrayList<ContactPolicyValues> counts) {
		this.counts = counts;
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

	public String getActionkeyName() {
		return actionkeyName;
	}

	public void setActionkeyName(String actionkeyName) {
		this.actionkeyName = actionkeyName;
	}

	public String getActionkeyId() {
		return actionkeyId;
	}

	public void setActionkeyId(String actionkeyId) {
		this.actionkeyId = actionkeyId;
	}

	public String getActionDaily() {
		return actionDaily;
	}

	public void setActionDaily(String actionDaily) {
		this.actionDaily = actionDaily;
	}

	public String getActionWeekly() {
		return actionWeekly;
	}

	public void setActionWeekly(String actionWeekly) {
		this.actionWeekly = actionWeekly;
	}

	public String getActionMonthly() {
		return actionMonthly;
	}

	public void setActionMonthly(String actionMonthly) {
		this.actionMonthly = actionMonthly;
	}

	public String getActionCyclic() {
		return actionCyclic;
	}

	public void setActionCyclic(String actionCyclic) {
		this.actionCyclic = actionCyclic;
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
		return "ContactPolicyTO [policyId=" + policyId + ", policyName=" + policyName + ", categoryField="
				+ categoryField + ", startDate=" + startDate + ", endDate=" + endDate + ", policyJson=" + policyJson
				+ ", description=" + description + ", noOfDays=" + noOfDays + ", categoryType=" + categoryType
				+ ", actionkeyName=" + actionkeyName + ", actionkeyId=" + actionkeyId + ", actionDaily=" + actionDaily
				+ ", actionWeekly=" + actionWeekly + ", actionMonthly=" + actionMonthly + ", actionCyclic="
				+ actionCyclic + ", policyType=" + policyType + ", counts=" + counts + ", contactPolicyProfiles="
				+ contactPolicyProfiles + ", profileTargetBandMap=" + profileTargetBandMap + ", actionKeys="
				+ actionKeys + ", childrens=" + childrens + ", id=" + id + ", cyclicDays=" + cyclicDays + ", strtDate="
				+ strtDate + ", endDatee=" + endDatee + ", startdateConvert=" + startdateConvert + ", enddateConvert="
				+ enddateConvert + ", auditRoleName=" + auditRoleName + ", auditUserName=" + auditUserName + "]";
	}

}
