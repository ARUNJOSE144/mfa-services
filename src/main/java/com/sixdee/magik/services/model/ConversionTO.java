package com.sixdee.magik.services.model;

import javax.persistence.OneToOne;

 
public class ConversionTO {
	
	
	
	private int autoId;
	private String points;
	private String dollar;
	private String rewardPointsCategory;
	
	
		
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getDollar() {
		return dollar;
	}
	public void setDollar(String dollar) {
		this.dollar = dollar;
	}
	public String getRewardPointsCategory() {
		return rewardPointsCategory;
	}
	public void setRewardPointsCategory(String rewardPointsCategory) {
		this.rewardPointsCategory = rewardPointsCategory;
	}
	
	
}
