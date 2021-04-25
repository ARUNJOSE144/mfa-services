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
@Table(name = "RE_QUARANTINE_GOLBAL_FILTER_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class GlobalFilterDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GlobalFilterDetailsTO")
	@TableGenerator(name = "GlobalFilterDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "ACCOUNT_TYPE")
	private String accountType;

	@Column(name = "TARIFF_PLAN")
	private String tariffPlan;

	@Column(name = "EXCLUSION")
	private String exclusionTyp;

	@Column(name = "FILTER_POLICY_ID")
	private int filterPolicyId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getTariffPlan() {
		return tariffPlan;
	}

	public void setTariffPlan(String tariffPlan) {
		this.tariffPlan = tariffPlan;
	}


	public int getFilterPolicyId() {
		return filterPolicyId;
	}

	public void setFilterPolicyId(int filterPolicyId) {
		this.filterPolicyId = filterPolicyId;
	}

	public String getExclusionTyp() {
		return exclusionTyp;
	}

	public void setExclusionTyp(String exclusionTyp) {
		this.exclusionTyp = exclusionTyp;
	}

	@Override
	public String toString() {
		return "GlobalFilterDetailsTO [id=" + id + ", description=" + description + ", accountType=" + accountType
				+ ", tariffPlan=" + tariffPlan + ", exclusionTyp=" + exclusionTyp + ", filterPolicyId=" + filterPolicyId
				+ "]";
	}

	
	
}
