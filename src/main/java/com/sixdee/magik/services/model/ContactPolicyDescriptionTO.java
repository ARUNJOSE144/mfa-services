package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "NCP_DESCRIPTION")
@SuppressWarnings("serial")
public class ContactPolicyDescriptionTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ContactPolicyDescriptionTO")
	@TableGenerator(name = "ContactPolicyDescriptionTO", allocationSize = 1)
	@Column(name = "DESC_ID")
	private int descId;

	@Column(name = "POLICY_ID")
	private int policyId;

	@Column(name = "CATEGORY_VALUE")
	private String categoryValue;

	@Column(name = "FREQUENCY")
	private String frequency;

	@Column(name = "CYCLIC_DAYS")
	private String cyclicDays;

	@Column(name = "THRESHOLD")
	private String threshold;

	@Column(name = "ACTION_KEY_ID")
	private String actionKeyId;

	@Transient
	private String daily;

	@Transient
	private String weekly;

	@Transient
	private String monthly;

	@Transient
	private String cyclicCount;

	@Transient
	private String id;

	public int getDescId() {
		return descId;
	}

	public void setDescId(int descId) {
		this.descId = descId;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public String getCategoryValue() {
		return categoryValue;
	}

	public void setCategoryValue(String categoryValue) {
		this.categoryValue = categoryValue;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCyclicDays() {
		return cyclicDays;
	}

	public void setCyclicDays(String cyclicDays) {
		this.cyclicDays = cyclicDays;
	}

	public String getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		this.threshold = threshold;
	}

	public String getDaily() {
		return daily;
	}

	public void setDaily(String daily) {
		this.daily = daily;
	}

	public String getWeekly() {
		return weekly;
	}

	public void setWeekly(String weekly) {
		this.weekly = weekly;
	}

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getCyclicCount() {
		return cyclicCount;
	}

	public void setCyclicCount(String cyclicCount) {
		this.cyclicCount = cyclicCount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionKeyId() {
		return actionKeyId;
	}

	public void setActionKeyId(String actionKeyId) {
		this.actionKeyId = actionKeyId;
	}

	@Override
	public String toString() {
		return "ContactPolicyDescriptionTO [descId=" + descId + ", policyId=" + policyId + ", categoryValue="
				+ categoryValue + ", frequency=" + frequency + ", cyclicDays=" + cyclicDays + ", threshold=" + threshold
				+ ", actionKeyId=" + actionKeyId + ", daily=" + daily + ", weekly=" + weekly + ", monthly=" + monthly
				+ ", cyclicCount=" + cyclicCount + ", id=" + id + "]";
	}

}
