package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Entity
@Table(name = "RE_RULE_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class RuleBuilderTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RuleBuilderTO")
	@TableGenerator(name = "RuleBuilderTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "JSON")
	private String ruleJson;

	@Column(name = "CREATED_BY")
	private int userId;

	@Column(name = "CREATED_DATE")
	private Date createDate;

	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name = "CAMPAIGN_ID")
	private int campaignId;
	@Transient
	private int ruleId;

	@Transient
	private String roleName;

	@Transient
	private String createdBy;

	@Transient
	private String userIdAuit;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRuleJson() {
		return ruleJson;
	}

	public void setRuleJson(String ruleJson) {
		this.ruleJson = ruleJson;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public int getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(int campaignId) {
		this.campaignId = campaignId;
	}

	public int getRuleId() {
		return ruleId;
	}

	public void setRuleId(int ruleId) {
		this.ruleId = ruleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUserIdAuit() {
		return userIdAuit;
	}

	public void setUserIdAuit(String userIdAuit) {
		this.userIdAuit = userIdAuit;
	}

	@Override
	public String toString() {
		return "RuleBuilderTO [Id=" + Id + ", name=" + name + ", ruleJson=" + ruleJson + ", userId=" + userId
				+ ", createDate=" + createDate + ", lastUpdatedDate=" + lastUpdatedDate + ", campaignId=" + campaignId
				+ ", ruleId=" + ruleId + ", roleName=" + roleName + ", createdBy=" + createdBy + ", userIdAuit="
				+ userIdAuit + "]";
	}

}
