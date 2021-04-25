package com.sixdee.magik.services.model;


/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */
public class LoyaltyStatusTO {
	
private int autoId;
	
	
	//If required to Float or Integer
	private String languageName;
	private String statusDescription;
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getLanguageName() {
		return languageName;
	}
	public void setLanguageName(String languageName) {
		this.languageName = languageName;
	}
	public String getStatusDescription() {
		return statusDescription;
	}
	public void setStatusDescription(String statusDescription) {
		this.statusDescription = statusDescription;
	}
	
	
}
