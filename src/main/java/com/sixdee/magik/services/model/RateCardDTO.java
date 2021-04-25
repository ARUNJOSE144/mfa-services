package com.sixdee.magik.services.model;

import java.io.Serializable;

public class RateCardDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String rateCardName;
	private int id;
	public String status;
	public String validityDays;
	private String rateCardDesc;
	private int denomination;
	private int statusCode;
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRateCardName() {
		return rateCardName;
	}

	public void setRateCardName(String rateCardName) {
		this.rateCardName = rateCardName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getDenomination() {
		return denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}

	public String getRateCardDesc() {
		return rateCardDesc;
	}

	public void setRateCardDesc(String rateCardDesc) {
		this.rateCardDesc = rateCardDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValidityDays() {
		return validityDays;
	}

	public void setValidityDays(String validityDays) {
		this.validityDays = validityDays;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
