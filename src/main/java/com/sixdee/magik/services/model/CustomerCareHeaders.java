package com.sixdee.magik.services.model;

public class CustomerCareHeaders {

	private CustomerProfileHeaders customerProfileHeaders;
	private TransactionHistoryHeaders transactionHistoryHeaders;
	private String msisdn;
	private String feature;

	public CustomerCareHeaders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerCareHeaders(CustomerProfileHeaders customerProfileHeaders, String msisdn, String feature,
			TransactionHistoryHeaders transactionHistoryHeaders) {
		super();
		this.customerProfileHeaders = customerProfileHeaders;
		this.msisdn = msisdn;
		this.feature = feature;
		this.transactionHistoryHeaders = transactionHistoryHeaders;
	}

	public CustomerProfileHeaders getCustomerProfileHeaders() {
		return customerProfileHeaders;
	}

	public void setCustomerProfileHeaders(CustomerProfileHeaders customerProfileHeaders) {
		this.customerProfileHeaders = customerProfileHeaders;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public TransactionHistoryHeaders getTransactionHistoryHeaders() {
		return transactionHistoryHeaders;
	}

	public void setTransactionHistoryHeaders(TransactionHistoryHeaders transactionHistoryHeaders) {
		this.transactionHistoryHeaders = transactionHistoryHeaders;
	}

}
