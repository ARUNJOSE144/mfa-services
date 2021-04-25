package com.sixdee.magik.services.model;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */
public class DemoExternalModuleTO {

	private String status;
	private String count;
	private String resposeCode;
	private String outputData;

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

	public String getOutputData() {
		return outputData;
	}

	public void setOutputData(String outputData) {
		this.outputData = outputData;
	}

	@Override
	public String toString() {
		return "ActiveOfferTO [status=" + status + ", count=" + count + ", resposeCode=" + resposeCode + ", outputData="
				+ outputData + "]";
	}

}
