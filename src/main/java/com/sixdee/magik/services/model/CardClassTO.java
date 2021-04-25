package com.sixdee.magik.services.model;

import javax.persistence.OneToOne;

 
public class CardClassTO {
	
	
	
	private int autoId;
	private String cardClass;
	private String tenant;
	
			
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	
	public String getCardClass() {
		return cardClass;
	}
	public void setCardClass(String cardClass) {
		this.cardClass = cardClass;
	}
	public String getTenant() {
		return tenant;
	}
	public void setTenant(String tenant) {
		this.tenant = tenant;
	}
	
	
	
}
