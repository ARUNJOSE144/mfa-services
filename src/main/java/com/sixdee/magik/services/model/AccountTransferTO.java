package com.sixdee.magik.services.model;



/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */
public class AccountTransferTO {
	
private int autoId;
	
	
	//If required to Float or Integer
	private String subscriberNumber;
	private String DestinationNumber;
	private String points;
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getSubscriberNumber() {
		return subscriberNumber;
	}
	public void setSubscriberNumber(String subscriberNumber) {
		this.subscriberNumber = subscriberNumber;
	}
	public String getDestinationNumber() {
		return DestinationNumber;
	}
	public void setDestinationNumber(String destinationNumber) {
		DestinationNumber = destinationNumber;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}

	
	

}
