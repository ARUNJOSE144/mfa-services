package com.sixdee.magik.services.model;

import java.util.List;


public class LeadPolicy {
	
	private List<Param> param;
	private Exclude exclude;
	

	public List<Param> getParams() {
		return param;
	}

	public void setParams(List<Param> leadPolicy) {
		this.param = leadPolicy;
	}

	public Exclude getExclude() {
		return exclude;
	}

	public void setExclude(Exclude exclude) {
		this.exclude = exclude;
	}
	
	

	
	
}
