package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;

/**
 * @author Ramesh Babu Cheerla
 * @Date : August, 2020
 *
 */

public class TaskProfileTO {

	private int campaignId;
	private String startDate;
	private String endDate;
	private String status;
	private int userId;

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TaskProfileTO [campaignId=" + campaignId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", userId=" + userId + "]";
	}

}
