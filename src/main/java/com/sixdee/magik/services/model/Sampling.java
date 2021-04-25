package com.sixdee.magik.services.model;

import java.util.List;



public class Sampling {
	
	private String name;
	List<Param> param;
	List<Step> step;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Param> getParam() {
		return param;
	}
	public void setParam(List<Param> param) {
		this.param = param;
	}
	public List<Step> getStep() {
		return step;
	}
	public void setStep(List<Step> step) {
		this.step = step;
	}

}
