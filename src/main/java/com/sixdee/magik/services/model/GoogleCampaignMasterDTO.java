package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="GOOGLE_CAMPAIGN_MASTER")
@JsonInclude(Include.NON_NULL)
public class GoogleCampaignMasterDTO {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="GoogleCampaignMasterDTO")
	@TableGenerator(name="GoogleCampaignMasterDTO")
	@Column(name="ID")
	private int id;
	
	@Column(name="TARGETING_ID")
	private int targetingId;
	
	@Column(name="AD_ID")
	private int adId;
	
	@Column(name="BUDGET_NAME")
	private String budgetName;
	
	@Column(name="BUDGET_AMOUNT")
	private String budgetAmount;
	
	@Column(name="CHANNEL_TYPE")
	private String channelType;
	
	@Column(name="STATUS")
	private int status;
	
	@ManyToOne
	@JoinColumn(name="CAMPAIGN_ID", nullable=false)
	private CampaignMasterTO googleCampaignMaster;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTargetingId() {
		return targetingId;
	}

	public void setTargetingId(int targetingId) {
		this.targetingId = targetingId;
	}

	public int getAdId() {
		return adId;
	}

	public void setAdId(int adId) {
		this.adId = adId;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public String getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(String budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public CampaignMasterTO getGoogleCampaignMaster() {
		return googleCampaignMaster;
	}

	public void setGoogleCampaignMaster(CampaignMasterTO googleCampaignMaster) {
		this.googleCampaignMaster = googleCampaignMaster;
	}

	@Override
	public String toString() {
		return "GoogleCampaignMasterDTO [id=" + id + ", targetingId=" + targetingId + ", adId=" + adId + ", budgetName="
				+ budgetName + ", budgetAmount=" + budgetAmount + ", channelType=" + channelType + ", status=" + status
				+ ", googleCampaignMaster=" + googleCampaignMaster + "]";
	}
	
	
	
	
}
