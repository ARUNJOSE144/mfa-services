package com.sixdee.magik.services.model;

import java.io.Serializable;

public class ContactPolicyValues implements Serializable {

	private String fieldType;
	private int dailyCount;
	private int weeklyCount;
	private int monthlyCount;
	
	
	
	
	public String getFieldType() {
		return fieldType;
	}
	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}
	public int getDailyCount() {
		return dailyCount;
	}
	public void setDailyCount(int dailyCount) {
		this.dailyCount = dailyCount;
	}
	public int getWeeklyCount() {
		return weeklyCount;
	}
	public void setWeeklyCount(int weeklyCount) {
		this.weeklyCount = weeklyCount;
	}
	public int getMonthlyCount() {
		return monthlyCount;
	}
	public void setMonthlyCount(int monthlyCount) {
		this.monthlyCount = monthlyCount;
	}
	
	
	@Override
	public String toString() {
		return "ContactPolicyValues [fieldType=" + fieldType + ", dailyCount=" + dailyCount + ", weeklyCount="
				+ weeklyCount + ", monthlyCount=" + monthlyCount + "]";
	}
	
	
}
