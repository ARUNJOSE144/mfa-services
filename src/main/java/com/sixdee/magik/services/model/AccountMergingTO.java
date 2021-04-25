package com.sixdee.magik.services.model;


/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */
public class AccountMergingTO {

private int autoId;
	
	
	//If required to Float or Integer
	private String subscriberNumber1;
	private String subscriberNumber2;
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getSubscriberNumber1() {
		return subscriberNumber1;
	}
	public void setSubscriberNumber1(String subscriberNumber1) {
		this.subscriberNumber1 = subscriberNumber1;
	}
	public String getSubscriberNumber2() {
		return subscriberNumber2;
	}
	public void setSubscriberNumber2(String subscriberNumber2) {
		this.subscriberNumber2 = subscriberNumber2;
	}
	
	
}
