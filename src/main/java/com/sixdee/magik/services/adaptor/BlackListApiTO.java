package com.sixdee.magik.services.adaptor;

/**
 * @author Nakhil Kurian
 * @Date : April, 2021
 *
 */
public class BlackListApiTO {

	public String msisdn;
	public String action;
	public String channelType;
	public String policyId;
	public String marketingPlan;
	public String expiryDate;
	public String status;
	public String statusCode;
	public String statusDescription;

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getMarketingPlan() {
		return marketingPlan;
	}

	public void setMarketingPlan(String marketingPlan) {
		this.marketingPlan = marketingPlan;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	@Override
	public String toString() {
		return "BlackListApiTO [msisdn=" + msisdn + ", action=" + action + ", channelType=" + channelType
				+ ", policyId=" + policyId + ", marketingPlan=" + marketingPlan + ", expiryDate=" + expiryDate
				+ ", status=" + status + ", statusCode=" + statusCode + ", statusDescription=" + statusDescription
				+ "]";
	}

}
