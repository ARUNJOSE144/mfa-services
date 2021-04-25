package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author arun.jose
 * @Date : December, 2018
 *
 */

@Entity
@Table(name = "RE_QUARANTINE_WEEK_DAYS_OLD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuarantineWeekDaysTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuarantineWeekDaysTO")
	@TableGenerator(name = "QuarantineWeekDaysTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "DAY")
	private int dayId;

	@Column(name = "WEEKS")
	private String weeks;

	@Column(name = "START_TIME")
	private String weekDayStartTime;

	@Column(name = "END_TIME")
	private String weekDayEndTime;

	@Column(name = "POLICY_DETAILS_ID")
	private int weekDayPolicyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getWeekDayPolicyId() {
		return weekDayPolicyId;
	}

	public void setWeekDayPolicyId(int weekDayPolicyId) {
		this.weekDayPolicyId = weekDayPolicyId;
	}

	public int getDayId() {
		return dayId;
	}

	public void setDayId(int dayId) {
		this.dayId = dayId;
	}

	public String getWeeks() {
		return weeks;
	}

	public void setWeeks(String weeks) {
		this.weeks = weeks;
	}

	@Override
	public String toString() {
		return "QuarantineWeekDaysTO [id=" + id + ", dayId=" + dayId + ", weeks=" + weeks + ", weekDayStartTime="
				+ weekDayStartTime + ", weekDayEndTime=" + weekDayEndTime + ", weekDayPolicyId=" + weekDayPolicyId
				+ "]";
	}

}
