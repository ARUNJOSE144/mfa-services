package com.sixdee.magik.services.model;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
public class CreateUSerProfileTO {

	// Requesr
	private int id;
	private String msisdn;
	private String status;
	private String count;
	private String resposeCode;
	private String data;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "CreateUSerProfileTO [id=" + id + ", msisdn=" + msisdn + ", status=" + status + ", count=" + count
				+ ", resposeCode=" + resposeCode + ", data=" + data + ", description=" + description + "]";
	}

}
