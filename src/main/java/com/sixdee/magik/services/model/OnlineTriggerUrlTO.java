package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_ONLINE_RULES_TRIGGER")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class OnlineTriggerUrlTO {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OnlineTriggerUrlTO")
	@TableGenerator(name = "OnlineTriggerUrlTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "CAMPAIGN_ID")
	private int campId;

	@Column(name = "RULE_ID")
	private int ruleId;

	@Column(name = "RULE_URL")
	private String ruleURL;

	@Column(name = "RULE_URL_ID")
	private String ruleURLId;

	@Column(name = "TRIGGER_ID")
	private String triggerId;

	@Column(name = "USER_ID")
	private int userId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCampId() {
		return campId;
	}

	public void setCampId(int campId) {
		this.campId = campId;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleURL() {
		return ruleURL;
	}

	public void setRuleURL(String ruleURL) {
		this.ruleURL = ruleURL;
	}

	public String getRuleURLId() {
		return ruleURLId;
	}

	public void setRuleURLId(String ruleURLId) {
		this.ruleURLId = ruleURLId;
	}

	public String getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(String triggerId) {
		this.triggerId = triggerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "OnlineTriggerUrlTO [id=" + id + ", campId=" + campId + ", ruleId=" + ruleId + ", ruleURL=" + ruleURL
				+ ", ruleURLId=" + ruleURLId + ", triggerId=" + triggerId + ", userId=" + userId + "]";
	}

}
