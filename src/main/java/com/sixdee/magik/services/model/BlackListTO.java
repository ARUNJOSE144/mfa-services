package com.sixdee.magik.services.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_QUARANTINE_BLACKLIST")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BlackListTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BlackListTO")
	@TableGenerator(name = "BlackListTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "POLICY_NAME")
	private String policyName;

	@Column(name = "CREATE_USER_ID")
	private int createUserId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

	@Column(name = "FILE_UPLOAD_NAME")
	private String fileUploadName;

	@Transient
	private List<BlackListTO> msisdnList;

	@Transient
	private Set<BlackListDetailsTO> detailsTo;

	@Transient
	private String marketingPlanName;

	@Transient
	private String description;

	@Transient
	private String msisdn;

	@Transient
	private String policyId;

	@Transient
	private String marketingPlanId;

	@Transient
	private String expiryDate;

	public List<BlackListTO> getMsisdnList() {
		return msisdnList;
	}

	public void setMsisdnList(List<BlackListTO> msisdnList) {
		this.msisdnList = msisdnList;
	}

	public Set<BlackListDetailsTO> getDetailsTo() {
		return detailsTo;
	}

	public void setDetailsTo(Set<BlackListDetailsTO> detailsTo) {
		this.detailsTo = detailsTo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public int getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(int createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getFileUploadName() {
		return fileUploadName;
	}

	public void setFileUploadName(String fileUploadName) {
		this.fileUploadName = fileUploadName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	public String getMarketingPlanId() {
		return marketingPlanId;
	}

	public void setMarketingPlanId(String marketingPlanId) {
		this.marketingPlanId = marketingPlanId;
	}

	public String getMarketingPlanName() {
		return marketingPlanName;
	}

	public void setMarketingPlanName(String marketingPlanName) {
		this.marketingPlanName = marketingPlanName;
	}

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "BlackListTO [id=" + id + ", policyName=" + policyName + ", createUserId=" + createUserId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", fileUploadName=" + fileUploadName
				+ ", msisdnList=" + msisdnList + ", detailsTo=" + detailsTo + ", marketingPlanName=" + marketingPlanName
				+ ", description=" + description + ", msisdn=" + msisdn + ", policyId=" + policyId
				+ ", marketingPlanId=" + marketingPlanId + ", expiryDate=" + expiryDate + "]";
	}

}
