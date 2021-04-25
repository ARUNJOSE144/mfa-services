package com.sixdee.magik.services.adaptor;

/**
 * @author Nakhil Kurian
 * @Date : April, 2021
 *
 */
public class BlacklistApiResponseTO {

	public String statusCode;
	public String statusDescription;

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
		return "BlacklistApiResponseTO [statusCode=" + statusCode + ", statusDescription=" + statusDescription + "]";
	}

}
