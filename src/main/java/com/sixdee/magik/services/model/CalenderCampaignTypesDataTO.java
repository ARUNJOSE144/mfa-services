package com.sixdee.magik.services.model;

public class CalenderCampaignTypesDataTO {

	int id;
	String camaignType;
	int all;
	int prepaid;
	int postPaid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCamaignType() {
		return camaignType;
	}

	public void setCamaignType(String camaignType) {
		this.camaignType = camaignType;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getPrepaid() {
		return prepaid;
	}

	public void setPrepaid(int prepaid) {
		this.prepaid = prepaid;
	}

	public int getPostPaid() {
		return postPaid;
	}

	public void setPostPaid(int postPaid) {
		this.postPaid = postPaid;
	}

}
