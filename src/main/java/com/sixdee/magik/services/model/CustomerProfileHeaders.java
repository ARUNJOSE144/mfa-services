package com.sixdee.magik.services.model;

public class CustomerProfileHeaders {
	public CustomerProfileHeaders() {
		super();
	}

	public CustomerProfileHeaders(String requestId, String keyType, String source, String key, String keyword, String isLoyaltyDetails,
			String isCustomerDetails, String isDeviceDetails) {
		this.requestId = requestId;
		this.key = key;
		this.source = source;
		this.keyword = keyword;
		this.isLoyaltyDetails = isLoyaltyDetails;
		this.isCustomerDetails = isCustomerDetails;
		this.isDeviceDetails = isDeviceDetails;
		this.keyType = keyType;
	}

	private String requestId;
	//private String CustomerCare;
	private String key;
	private String source;
	private String keyword;
	private String isLoyaltyDetails;
	private String isCustomerDetails;
	private String isDeviceDetails;
    private String keyType;
    
	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

/*	public String getCustomerCare() {
		return CustomerCare;
	}

	public void setCustomerCare(String customerCare) {
		CustomerCare = customerCare;
	}*/

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String mSISDN) {
		source = mSISDN;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getIsLoyaltyDetails() {
		return isLoyaltyDetails;
	}

	public void setIsLoyaltyDetails(String isLoyaltyDetails) {
		this.isLoyaltyDetails = isLoyaltyDetails;
	}

	public String getIsCustomerDetails() {
		return isCustomerDetails;
	}

	public void setIsCustomerDetails(String isCustomerDetails) {
		this.isCustomerDetails = isCustomerDetails;
	}

	public String getIsDeviceDetails() {
		return isDeviceDetails;
	}

	public void setIsDeviceDetails(String isDeviceDetails) {
		this.isDeviceDetails = isDeviceDetails;
	}

	public String getKeyType() {
		return keyType;
	}

	public void setKeyType(String keyType) {
		this.keyType = keyType;
	}
}
