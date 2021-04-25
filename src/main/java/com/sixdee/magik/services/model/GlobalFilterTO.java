package com.sixdee.magik.services.model;

import java.util.Date;

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
@Table(name = "RE_QUARANTINE_GOLBAL_FILTER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class GlobalFilterTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "GlobalFilterTO")
	@TableGenerator(name = "GlobalFilterTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "CREATE_USER_ID")
	private int createUSerId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Transient
	private String description;

	@Transient
	private String accountType;

	@Transient
	private String tariffPlan;

	@Transient
	private String exclusionTyp;

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

	public int getCreateUSerId() {
		return createUSerId;
	}

	public void setCreateUSerId(int createUSerId) {
		this.createUSerId = createUSerId;
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

	public String getExclusionTyp() {
		return exclusionTyp;
	}

	public void setExclusionTyp(String exclusionTyp) {
		this.exclusionTyp = exclusionTyp;
	}

	@Override
	public String toString() {
		return "GlobalFilterTO [id=" + id + ", policyName=" + policyName + ", createUSerId=" + createUSerId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", description=" + description
				+ ", accountType=" + accountType + ", tariffPlan=" + tariffPlan + ", exclusionTyp=" + exclusionTyp
				+ "]";
	}

}
