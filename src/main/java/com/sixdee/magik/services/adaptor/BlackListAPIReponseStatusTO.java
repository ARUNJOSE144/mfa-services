package com.sixdee.magik.services.adaptor;

/**
 * @author Nakhil Kurian
 * @Date : April, 2021
 *
 */
public class BlackListAPIReponseStatusTO {

	public String statusCode;
	public String statusDescription;
	public String dndStatus;
	public String blackNumber;

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

	public String getDndStatus() {
		return dndStatus;
	}

	public void setDndStatus(String dndStatus) {
		this.dndStatus = dndStatus;
	}

	public String getBlackNumber() {
		return blackNumber;
	}

	public void setBlackNumber(String blackNumber) {
		this.blackNumber = blackNumber;
	}

	@Override
	public String toString() {
		return "BlackListAPIReponseStatusTO [statusCode=" + statusCode + ", statusDescription=" + statusDescription
				+ ", dndStatus=" + dndStatus + ", blackNumber=" + blackNumber + "]";
	}

}
