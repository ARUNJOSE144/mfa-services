package com.sixdee.magik.services.model;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */
public class RedeemPointTO {

	private int id;
	private String subscriberNumber;
	private String status;
	private String count;
	private String resposeCode;
	private String message;
	private String packageId;
	private String outputData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubscriberNumber() {
		return subscriberNumber;
	}

	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getResposeCode() {
		return resposeCode;
	}

	public void setResposeCode(String resposeCode) {
		this.resposeCode = resposeCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getOutputData() {
		return outputData;
	}

	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}

	@Override
	public String toString() {
		return "RedeemPointTO [id=" + id + ", subscriberNumber=" + subscriberNumber + ", status=" + status + ", count="
				+ count + ", resposeCode=" + resposeCode + ", message=" + message + ", packageId=" + packageId
				+ ", outputData=" + outputData + "]";
	}

}
