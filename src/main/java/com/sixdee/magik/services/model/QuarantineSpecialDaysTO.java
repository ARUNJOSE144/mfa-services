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

/**
 * @author arun.jose
 * @Date : December, 2018
 *
 */

@Entity
@Table(name = "RE_QUARANTINE_SPECIAL_DAYS_OLD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuarantineSpecialDaysTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuarantineSpecialDaysTO")
	@TableGenerator(name = "QuarantineSpecialDaysTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "SPECIAL_DATE")
	private String specialDate;

	@Column(name = "START_TIME")
	private String specialDateStartTime;

	@Column(name = "END_TIME")
	private String specialDateEndTime;

	@Column(name = "POLICY_DETAILS_ID")
	private int specialDatePolicyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getSpecialDatePolicyId() {
		return specialDatePolicyId;
	}

	public void setSpecialDatePolicyId(int specialDatePolicyId) {
		this.specialDatePolicyId = specialDatePolicyId;
	}

	public String getSpecialDate() {
		return specialDate;
	}

	public void setSpecialDate(String specialDate) {
		this.specialDate = specialDate;
	}

	@Override
	public String toString() {
		return "QuarantineSpecialDaysTO [id=" + id + ", specialDate=" + specialDate + ", specialDateStartTime="
				+ specialDateStartTime + ", specialDateEndTime=" + specialDateEndTime + ", specialDatePolicyId="
				+ specialDatePolicyId + "]";
	}

}
