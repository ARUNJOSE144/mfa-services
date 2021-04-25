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
@Table(name = "RE_QUARANTINE_DND_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DNDDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DNDDetailsTO")
	@TableGenerator(name = "DNDDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "START_TIME")
	private String startTime;

	@Column(name = "END_TIME")
	private String endTime;

	@Column(name = "DND_POLICY_ID")
	private int dndPolicyId;

	@Column(name = "MARKETING_PLAN")
	private int marketingPlanId;

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

	public int getDndPolicyId() {
		return dndPolicyId;
	}

	public void setDndPolicyId(int dndPolicyId) {
		this.dndPolicyId = dndPolicyId;
	}

	public int getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(int marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	@Override
	public String toString() {
		return "DNDDetailsTO [id=" + id + ", description=" + description + ", startTime=" + startTime + ", endTime="
				+ endTime + ", dndPolicyId=" + dndPolicyId + ", marketingPlanId=" + marketingPlanId + "]";
	}

}
