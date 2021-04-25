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
@Table(name = "RE_QUARANTINE_WHITELIST")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WhiteListTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WhiteListTO")
	@TableGenerator(name = "WhiteListTO", allocationSize = 1)

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
	private List<WhiteListTO> msisdnList;

	@Transient
	private Set<WhiteListDetailsTO> detailsTo;

	@Transient
	private String description;

	@Transient
	private String msisdn;

	@Transient
	private String expiryDate;

	@Transient
	private String policyId;

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

	public List<WhiteListTO> getMsisdnList() {
		return msisdnList;
	}

	public void setMsisdnList(List<WhiteListTO> msisdnList) {
		this.msisdnList = msisdnList;
	}

	public Set<WhiteListDetailsTO> getDetailsTo() {
		return detailsTo;
	}

	public void setDetailsTo(Set<WhiteListDetailsTO> detailsTo) {
		this.detailsTo = detailsTo;
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

	public String getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getPolicyId() {
		return policyId;
	}

	public void setPolicyId(String policyId) {
		this.policyId = policyId;
	}

	@Override
	public String toString() {
		return "WhiteListTO [id=" + id + ", policyName=" + policyName + ", createUserId=" + createUserId
				+ ", createDate=" + createDate + ", modifiedDate=" + modifiedDate + ", fileUploadName=" + fileUploadName
				+ ", msisdnList=" + msisdnList + ", detailsTo=" + detailsTo + ", description=" + description
				+ ", msisdn=" + msisdn + ", expiryDate=" + expiryDate + ", policyId=" + policyId + "]";
	}

}
