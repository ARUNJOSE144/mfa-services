package com.sixdee.magik.services.model;

import java.util.List;

public class Action {
	
	private List<Field> field;
	private Request request;
	private Sampling sampling;
	private List<LeadPolicy> leadPolicyList;
	
	private int samplingId;
	private int actionId;
	private String leadPolicyIds;
	
	
	
	
	public List<Field> getField() {
		return field;
	}
	public void setField(List<Field> field) {
		this.field = field;
	}
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	public Sampling getSampling() {
		return sampling;
	}
	public void setSampling(Sampling sampling) {
		this.sampling = sampling;
	}
	public List<LeadPolicy> getLeadPolicyList() {
		return leadPolicyList;
	}
	public void setLeadPolicyList(List<LeadPolicy> leadPolicyList) {
		this.leadPolicyList = leadPolicyList;
	}
	public int getSamplingId() {
		return samplingId;
	}
	public void setSamplingId(int samplingId) {
		this.samplingId = samplingId;
	}
	public int getActionId() {
		return actionId;
	}
	public void setActionId(int actionId) {
		this.actionId = actionId;
	}
	public String getLeadPolicyIds() {
		return leadPolicyIds;
	}
	public void setLeadPolicyIds(String leadPolicyIds) {
		this.leadPolicyIds = leadPolicyIds;
	}
	
	
	
	

}
