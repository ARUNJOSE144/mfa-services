package com.sixdee.magik.services.model;

import java.util.Date;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

public class TransactionHistoryTO {

	private int id;
	private String subscriberNumber;
	private Date startDate;
	private Date endDate;
	private String noOfTransaction;
	private String status;
	private String count;
	private String resposeCode;
	private String data;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getNoOfTransaction() {
		return noOfTransaction;
	}

	public void setNoOfTransaction(String noOfTransaction) {
		this.noOfTransaction = noOfTransaction;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TransactionHistoryTO [id=" + id + ", subscriberNumber=" + subscriberNumber + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", noOfTransaction=" + noOfTransaction + ", status=" + status + ", count="
				+ count + ", resposeCode=" + resposeCode + ", data=" + data + ", message=" + message + "]";
	}

}
