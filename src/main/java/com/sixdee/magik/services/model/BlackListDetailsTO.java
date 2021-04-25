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

@Entity
@Table(name = "RE_QUARANTINE_BLACKLIST_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BlackListDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BlackListDetailsTO")
	@TableGenerator(name = "BlackListDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int detailsId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "BLACKLIST_NUMBER")
	private String msisdn;

	@Column(name = "BLACKLIST_ID")
	private int blackPolicyId;

	@Column(name = "MARKETING_PLAN")
	private int marketingPlanId;

	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@Column(name = "CHANNEL_TYPE")
	private String channelType;

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public int getBlackPolicyId() {
		return blackPolicyId;
	}

	public void setBlackPolicyId(int blackPolicyId) {
		this.blackPolicyId = blackPolicyId;
	}

	public int getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(int marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	@Override
	public String toString() {
		return "BlackListDetailsTO [detailsId=" + detailsId + ", description=" + description + ", msisdn=" + msisdn
				+ ", blackPolicyId=" + blackPolicyId + ", marketingPlanId=" + marketingPlanId + ", expiryDate="
				+ expiryDate + ", channelType=" + channelType + "]";
	}

}
