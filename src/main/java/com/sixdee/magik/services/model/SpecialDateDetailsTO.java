package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_QUARANTINE_SPECIAL_DAYS_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SpecialDateDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SpecialDateDetailsTO")
	@TableGenerator(name = "SpecialDateDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int Detailsid;

	@Column(name = "SPECIAL_DATE")
	private String specialDate;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "SPD_POLICY_ID")
	private int specialPolicyId;

	@Column(name = "START_TIME")
	private String specialDateStartTime;

	@Column(name = "END_TIME")
	private String specialDateEndTime;

	@Column(name = "MARKETING_PLAN")
	private int marketingPlanId;

	public int getDetailsid() {
		return Detailsid;
	}

	public void setDetailsid(int detailsid) {
		Detailsid = detailsid;
	}

	public String getSpecialDate() {
		return specialDate;
	}

	public void setSpecialDate(String specialDate) {
		this.specialDate = specialDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getSpecialPolicyId() {
		return specialPolicyId;
	}

	public void setSpecialPolicyId(int specialPolicyId) {
		this.specialPolicyId = specialPolicyId;
	}

	public String getSpecialDateStartTime() {
		return specialDateStartTime;
	}

	public void setSpecialDateStartTime(String specialDateStartTime) {
		this.specialDateStartTime = specialDateStartTime;
	}

	public String getSpecialDateEndTime() {
		return specialDateEndTime;
	}

	public void setSpecialDateEndTime(String specialDateEndTime) {
		this.specialDateEndTime = specialDateEndTime;
	}

	public int getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(int marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	@Override
	public String toString() {
		return "SpecialDateDetailsTO [Detailsid=" + Detailsid + ", specialDate=" + specialDate + ", description="
				+ description + ", specialPolicyId=" + specialPolicyId + ", specialDateStartTime="
				+ specialDateStartTime + ", specialDateEndTime=" + specialDateEndTime + ", marketingPlanId="
				+ marketingPlanId + "]";
	}

}
