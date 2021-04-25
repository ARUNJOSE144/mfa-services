package com.sixdee.magik.services.model;

/**
 * @author Ramesh Babu Cheerla
 * @Date : August, 2020
 *
 */

public class QuarantineBLCommunicationTO {

	public int policyId;
	public String policyName;
	public String policyType;
	public String specialDates;
	public String weekDays;
	public String dndStartTime;
	public String dndEndTime;
	public String StatusValueDate;
	public String marketingPlanIds;
	public String urlValue;
	public String urlAppender;
	public String typeChecker;

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

	public String getPolicyType() {
		return policyType;
	}

	public void setPolicyType(String policyType) {
		this.policyType = policyType;
	}

	public String getSpecialDates() {
		return specialDates;
	}

	public void setSpecialDates(String specialDates) {
		this.specialDates = specialDates;
	}

	public String getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(String weekDays) {
		this.weekDays = weekDays;
	}

	public String getDndStartTime() {
		return dndStartTime;
	}

	public void setDndStartTime(String dndStartTime) {
		this.dndStartTime = dndStartTime;
	}

	public String getDndEndTime() {
		return dndEndTime;
	}

	public void setDndEndTime(String dndEndTime) {
		this.dndEndTime = dndEndTime;
	}

	public String getStatusValueDate() {
		return StatusValueDate;
	}

	public void setStatusValueDate(String statusValueDate) {
		StatusValueDate = statusValueDate;
	}

	public String getMarketingPlanIds() {
		return marketingPlanIds;
	}

	public void setMarketingPlanIds(String marketingPlanIds) {
		this.marketingPlanIds = marketingPlanIds;
	}

	public String getUrlValue() {
		return urlValue;
	}

	public void setUrlValue(String urlValue) {
		this.urlValue = urlValue;
	}

	public String getUrlAppender() {
		return urlAppender;
	}

	public void setUrlAppender(String urlAppender) {
		this.urlAppender = urlAppender;
	}

	public String getTypeChecker() {
		return typeChecker;
	}

	public void setTypeChecker(String typeChecker) {
		this.typeChecker = typeChecker;
	}

	@Override
	public String toString() {
		return "QuarantineBLCommunicationTO [policyId=" + policyId + ", policyName=" + policyName + ", policyType="
				+ policyType + ", specialDates=" + specialDates + ", weekDays=" + weekDays + ", dndStartTime="
				+ dndStartTime + ", dndEndTime=" + dndEndTime + ", StatusValueDate=" + StatusValueDate
				+ ", marketingPlanIds=" + marketingPlanIds + ", urlValue=" + urlValue + ", urlAppender=" + urlAppender
				+ ", typeChecker=" + typeChecker + "]";
	}

}
