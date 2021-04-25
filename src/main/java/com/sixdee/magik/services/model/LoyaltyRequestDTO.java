package com.sixdee.magik.services.model;

import java.util.List;

public class LoyaltyRequestDTO {

	private Integer id;
	private String subscriber1;
	private String subscriber2;
	private String points;
	private String status;
	private String returnCode;
	private String msisdn;
	private String fromDate;
	private String endDate;
	private String numberOfTransactions;
	private String transactionType;
	private String action;
	private String details;
	private String transactionDate;
	private String tier;
	private String bonus;
	private String total;
	private String transactoinId; 
	private String statusCode;
	private String statusDescription;
	private String accountLineNumber;
	
	private List<LoyaltyRequestDTO> transactionList;
	
	
	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(String returnCode) {
		this.returnCode = returnCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubscriber1() {
		return subscriber1;
	}

	public void setSubscriber1(String subscriber1) {
		this.subscriber1 = subscriber1;
	}

	public String getSubscriber2() {
		return subscriber2;
	}

	public void setSubscriber2(String subscriber2) {
		this.subscriber2 = subscriber2;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getNumberOfTransactions() {
		return numberOfTransactions;
	}

	public void setNumberOfTransactions(String numberOfTransactions) {
		this.numberOfTransactions = numberOfTransactions;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public List<LoyaltyRequestDTO> getTransactionList() {
		return transactionList;
	}

	public void setTransactionList(List<LoyaltyRequestDTO> transactionList) {
		this.transactionList = transactionList;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public String getTier() {
		return tier;
	}

	public void setTier(String tier) {
		this.tier = tier;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getTransactoinId() {
		return transactoinId;
	}

	public void setTransactoinId(String transactoinId) {
		this.transactoinId = transactoinId;
	}

	public String getStatusDescription() {
		return statusDescription;
	}

	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getAccountLineNumber() {
		return accountLineNumber;
	}

	public void setAccountLineNumber(String accountLineNumber) {
		this.accountLineNumber = accountLineNumber;
	}

	
	
}
