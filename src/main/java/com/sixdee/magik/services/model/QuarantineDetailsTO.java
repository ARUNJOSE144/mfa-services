package com.sixdee.magik.services.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author arun.jose
 * @Date : December, 2018
 *
 */

@Entity
@Table(name = "RE_QUARANTINE_DETAILS_OLD")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class QuarantineDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "QuarantineDetailsTO")
	@TableGenerator(name = "QuarantineDetailsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_ID")
	private int policyId;

	@Column(name = "NAME")
	private String name;
	
	@Column(name = "QUARANTINE_TYPE")
	private int quarantineType;

	@Transient
	private List<QuarantineSpecialDaysTO> specialDays;
	
	@Transient
	private List<QuarantineWeekDaysTO> weekDays;
	
	@Transient
	private List<QuarantineBlackListTO> blackList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPolicyId() {
		return policyId;
	}

	public void setPolicyId(int policyId) {
		this.policyId = policyId;
	}

	public List<QuarantineSpecialDaysTO> getSpecialDays() {
		return specialDays;
	}

	public void setSpecialDays(List<QuarantineSpecialDaysTO> specialDays) {
		this.specialDays = specialDays;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getQuarantineType() {
		return quarantineType;
	}

	public void setQuarantineType(int quarantineType) {
		this.quarantineType = quarantineType;
	}

	public List<QuarantineWeekDaysTO> getWeekDays() {
		return weekDays;
	}

	public void setWeekDays(List<QuarantineWeekDaysTO> weekDays) {
		this.weekDays = weekDays;
	}

	public List<QuarantineBlackListTO> getBlackList() {
		return blackList;
	}

	public void setBlackList(List<QuarantineBlackListTO> blackList) {
		this.blackList = blackList;
	}

	
	
	
}
