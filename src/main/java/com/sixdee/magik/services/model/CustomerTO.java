package com.sixdee.magik.services.model;

import javax.persistence.OneToOne;

 
public class CustomerTO {
	
	
	
	private int autoId;
	private String customerClass;
	private String tenant;
	
			
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getCustomerClass() {
		return customerClass;
	}
	public void setCustomerClass(String customerClass) {
		this.customerClass = customerClass;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	
	
	
}
