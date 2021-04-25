package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CSEG_OFFER_ACCOUNT_MAPPING")
public class ConfigureOfferAccountMappingLMSTO {
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferAccountMappingLMSTO")
	@TableGenerator(name = "ConfigureOfferAccountMappingLMSTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	
	@OneToOne
	@JoinColumn(name = "OFFERID")
	private ConfigureOfferMasterLMSTO configureOfferMasterLMSTO;
	
	@OneToOne
	@JoinColumn(name = "ACCOUNTTYPEID")
	private AccountTypeLMSTO accounttypeid;
	
	@Column(name = "AVAILABLE")
	private Double available;
	
	@CreationTimestamp
	@Column(name="CREATEDATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="UPDATEDATE")
	private Date updateDate;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public ConfigureOfferMasterLMSTO getConfigureOfferMasterLMSTO() {
		return configureOfferMasterLMSTO;
	}

	public void setConfigureOfferMasterLMSTO(ConfigureOfferMasterLMSTO configureOfferMasterLMSTO) {
		this.configureOfferMasterLMSTO = configureOfferMasterLMSTO;
	}


	public AccountTypeLMSTO getAccounttypeid() {
		return accounttypeid;
	}

	public void setAccounttypeid(AccountTypeLMSTO accounttypeid) {
		this.accounttypeid = accounttypeid;
	}

	public Double getAvailable() {
		return available;
	}

	public void setAvailable(Double available) {
		this.available = available;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

}
