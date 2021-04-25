package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Param {

	private String name;
	private String value;
	private List<MultiParam> multiParam;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<MultiParam> getMultiParam() {
		return multiParam;
	}
	public void setMultiParam(List<MultiParam> multiParam) {
		this.multiParam = multiParam;
	}
	
	
	
}
