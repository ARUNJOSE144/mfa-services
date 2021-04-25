package com.sixdee.magik.services.model;

import java.io.Serializable;

public class TierConfigDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tierName;
	private String tierDesc;
	private String maxValue;
	private String minValue;
	private String tierId;
	private int status;
	private int statusCode;
	private int welcomeRewardPts;
	private int welcomeStatusPts;

	public String getTierName() {
		return tierName;
	}

	public void setTierName(String tierName) {
		this.tierName = tierName;
	}

	public String getTierDesc() {
		return tierDesc;
	}

	public void setTierDesc(String tierDesc) {
		this.tierDesc = tierDesc;
	}

	public String getMaxValue() {
		return maxValue;
	}

	public void setMaxValue(String maxValue) {
		this.maxValue = maxValue;
	}

	public String getMinValue() {
		return minValue;
	}

	public void setMinValue(String minValue) {
		this.minValue = minValue;
	}

	public String getTierId() {
		return tierId;
	}

	public void setTierId(String tierId) {
		this.tierId = tierId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public int getWelcomeRewardPts() {
		return welcomeRewardPts;
	}

	public void setWelcomeRewardPts(int welcomeRewardPts) {
		this.welcomeRewardPts = welcomeRewardPts;
	}

	public int getWelcomeStatusPts() {
		return welcomeStatusPts;
	}

	public void setWelcomeStatusPts(int welcomeStatusPts) {
		this.welcomeStatusPts = welcomeStatusPts;
	}
}
