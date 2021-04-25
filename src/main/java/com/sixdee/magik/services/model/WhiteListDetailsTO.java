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
@Table(name = "RE_QUARANTINE_WHITELIST_DETAILS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class WhiteListDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WhiteListDetailsTO")
	@TableGenerator(name = "WhiteListDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int detailsId;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "MSISDN")
	private String msisdn;

	@Column(name = "EXPIRY_DATE")
	private String expiryDate;

	@Column(name = "WHITE_POLICY_ID")
	private int whitePolicyId;

	public int getDetailsId() {
		return detailsId;
	}

	public void setDetailsId(int detailsId) {
		this.detailsId = detailsId;
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

	public int getWhitePolicyId() {
		return whitePolicyId;
	}

	public void setWhitePolicyId(int whitePolicyId) {
		this.whitePolicyId = whitePolicyId;
	}

	@Override
	public String toString() {
		return "WhiteListDetailsTO [detailsId=" + detailsId + ", description=" + description + ", msisdn=" + msisdn
				+ ", expiryDate=" + expiryDate + ", whitePolicyId=" + whitePolicyId + "]";
	}

}
