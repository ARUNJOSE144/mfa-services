package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_QUARANTINE_WEEK_DAYS_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WeekDayDetailsTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WeekDayDetailsTO")
	@TableGenerator(name = "WeekDayDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DAY_ID_DAILY")
	private String day;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "WEEK_DAY_ID")
	private String weekDaysIds;

	@Column(name = "START_TIME")
	private String weekDayStartTime;

	@Column(name = "END_TIME")
	private String weekDayEndTime;

	@Column(name = "WEEK_POLICY_ID")
	private int weekPolicyId;

	@Column(name = "MARKETING_PLAN")
	private String marketingPlanId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getWeekPolicyId() {
		return weekPolicyId;
	}

	public void setWeekPolicyId(int weekPolicyId) {
		this.weekPolicyId = weekPolicyId;
	}

	public String getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(String marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	@Override
	public String toString() {
		return "WeekDayDetailsTO [id=" + id + ", day=" + day + ", description=" + description + ", weekDaysIds="
				+ weekDaysIds + ", weekDayStartTime=" + weekDayStartTime + ", weekDayEndTime=" + weekDayEndTime
				+ ", weekPolicyId=" + weekPolicyId + ", marketingPlanId=" + marketingPlanId + "]";
	}

}