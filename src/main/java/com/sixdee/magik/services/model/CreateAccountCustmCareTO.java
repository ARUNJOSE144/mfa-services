package com.sixdee.magik.services.model;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
public class CreateAccountCustmCareTO {

	private int id;
	private String subscriberNumber;
	private String status;
	private String count;
	private String resposeCode;
	private String message;

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

	@Override
	public String toString() {
		return "CreateAccountCustmCareTO [id=" + id + ", subscriberNumber=" + subscriberNumber + ", status=" + status
				+ ", count=" + count + ", resposeCode=" + resposeCode + ", message=" + message + "]";
	}

}
