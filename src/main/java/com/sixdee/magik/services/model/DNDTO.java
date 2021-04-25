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
@Table(name = "RE_QUARANTINE_DND")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DNDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DNDTO")
	@TableGenerator(name = "DNDTO", allocationSize = 1)

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
	private String startTime;

	@Transient
	private String endTime;

	@Transient
	private String marketingPlanId;

	@Transient
	private String marketingPlanName;

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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
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
		return "DNDTO [id=" + id + ", policyName=" + policyName + ", createUSerId=" + createUSerId + ", createDate="
				+ createDate + ", modifiedDate=" + modifiedDate + ", description=" + description + ", startTime="
				+ startTime + ", endTime=" + endTime + ", marketingPlanId=" + marketingPlanId + ", marketingPlanName="
				+ marketingPlanName + "]";
	}

}
