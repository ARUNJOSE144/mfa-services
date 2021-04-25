package com.sixdee.magik.services.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_QUARANTINE_WEEKDAY_DAYS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WeekDayTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WeekDayTO")
	@TableGenerator(name = "WeekDayTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "CREATE_USER_ID")
	private String createUserId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Transient
	private String day;

	@Transient
	private String description;

	@Transient
	private String weekDaysIds;

	@Transient
	private String weekDayStartTime;

	@Transient
	private String weekDayEndTime;

	@Transient
	private String marketingPlanName;

	@Transient
	private String marketingPlanId;

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

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getWeekDaysIds() {
		return weekDaysIds;
	}

	public void setWeekDaysIds(String weekDaysIds) {
		this.weekDaysIds = weekDaysIds;
	}

	public String getWeekDayStartTime() {
		return weekDayStartTime;
	}

	public void setWeekDayStartTime(String weekDayStartTime) {
		this.weekDayStartTime = weekDayStartTime;
	}

	public String getWeekDayEndTime() {
		return weekDayEndTime;
	}

	public void setWeekDayEndTime(String weekDayEndTime) {
		this.weekDayEndTime = weekDayEndTime;
	}

	public String getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(String marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	public String getMarketingPlanName() {
		return marketingPlanName;
	}

	public void setMarketingPlanName(String marketingPlanName) {
		this.marketingPlanName = marketingPlanName;
	}

	@Override
	public String toString() {
		return "WeekDayTO [id=" + id + ", policyName=" + policyName + ", createUserId=" + createUserId + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", day=" + day + ", description=" + description
				+ ", weekDaysIds=" + weekDaysIds + ", weekDayStartTime=" + weekDayStartTime + ", weekDayEndTime="
				+ weekDayEndTime + ", marketingPlanName=" + marketingPlanName + ", marketingPlanId=" + marketingPlanId
				+ "]";
	}

}
