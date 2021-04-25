package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Step {

	private String name;
	private String method;
	
	@JsonInclude(Include.NON_NULL)
	private String value;

	@JsonInclude(Include.NON_NULL)
	private String tile;

	@JsonInclude(Include.NON_NULL)
	private String sortType;

	@JsonInclude(Include.NON_NULL)
	private String orderBy;
	
	@JsonInclude(Include.NON_NULL)
	private String profiles;

	List<LogicalStep> logicalStep;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<LogicalStep> getLogicalStep() {
		return logicalStep;
	}

	public void setLogicalStep(List<LogicalStep> logicalStep) {
		this.logicalStep = logicalStep;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getTile() {
		return tile;
	}

	public void setTile(String tile) {
		this.tile = tile;
	}

	public String getProfiles() {
		return profiles;
	}

	public void setProfiles(String profiles) {
		this.profiles = profiles;
	}

}
