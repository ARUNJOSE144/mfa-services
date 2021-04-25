package com.sixdee.magik.services.model;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

public class OfflineUploadSegmentTO {

	private String status;
	private String count;
	private String resposeCode;

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

	@Override
	public String toString() {
		return "OfflineUploadSegmentTO [status=" + status + ", count=" + count + ", resposeCode=" + resposeCode + "]";
	}

}
