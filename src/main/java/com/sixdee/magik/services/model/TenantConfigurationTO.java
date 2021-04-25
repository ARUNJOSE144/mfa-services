package com.sixdee.magik.services.model;

import javax.persistence.OneToOne;

 
public class TenantConfigurationTO {
	
	
	
	private int autoId;
	private String countryName;
	private String tenant;
	private String pointMultiplier;
	private String customerCareNumber;
	private String customerCareEmail;
	private String tenantIdentifier;
	private String equivaltentDollarForPoint;
	private String loyaltyIdPattern;
	private String defaultLocation;
	private String callMethod;
	private String accumulationType;
			
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	public String getPointMultiplier() {
		return pointMultiplier;
	}
	public void setPointMultiplier(String pointMultiplier) {
		this.pointMultiplier = pointMultiplier;
	}
	public String getCustomerCareNumber() {
		return customerCareNumber;
	}
	public void setCustomerCareNumber(String customerCareNumber) {
		this.customerCareNumber = customerCareNumber;
	}
	public String getCustomerCareEmail() {
		return customerCareEmail;
	}
	public void setCustomerCareEmail(String customerCareEmail) {
		this.customerCareEmail = customerCareEmail;
	}
	public String getTenantIdentifier() {
		return tenantIdentifier;
	}
	public void setTenantIdentifier(String tenantIdentifier) {
		this.tenantIdentifier = tenantIdentifier;
	}
	public String getEquivaltentDollarForPoint() {
		return equivaltentDollarForPoint;
	}
	public void setEquivaltentDollarForPoint(String equivaltentDollarForPoint) {
		this.equivaltentDollarForPoint = equivaltentDollarForPoint;
	}
	public String getLoyaltyIdPattern() {
		return loyaltyIdPattern;
	}
	public void setLoyaltyIdPattern(String loyaltyIdPattern) {
		this.loyaltyIdPattern = loyaltyIdPattern;
	}
	public String getDefaultLocation() {
		return defaultLocation;
	}
	public void setDefaultLocation(String defaultLocation) {
		this.defaultLocation = defaultLocation;
	}
	public String getCallMethod() {
		return callMethod;
	}
	public void setCallMethod(String callMethod) {
		this.callMethod = callMethod;
	}
	public String getAccumulationType() {
		return accumulationType;
	}
	public void setAccumulationType(String accumulationType) {
		this.accumulationType = accumulationType;
	}
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
}
