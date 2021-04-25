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
@Table(name = "LMS_CSEG_OFFER_TIER_MAPPING")
public class ConfigureOfferTierMappingLMSTO {
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferTierMappingLMSTO")
	@TableGenerator(name = "ConfigureOfferTierMappingLMSTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;

	@OneToOne
	@JoinColumn(name = "OFFERID")
	private ConfigureOfferMasterLMSTO configureOfferMasterLMSTO;
	
	
	@OneToOne
	@JoinColumn(name = "TIERID")
	private TierConfigurationLMSTO tierConfigurationLMSTO;
	
	
	@Column(name = "AVAILABLE")
	private Double available;
	
	@CreationTimestamp
	@Column(name="CREATEDATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="UPDATEDATE")
	private Date updateDate;
	
	@Column(name = "DISCOUNT")
	private Double discount;

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

	public TierConfigurationLMSTO getTierConfigurationLMSTO() {
		return tierConfigurationLMSTO;
	}

	public void setTierConfigurationLMSTO(TierConfigurationLMSTO tierConfigurationLMSTO) {
		this.tierConfigurationLMSTO = tierConfigurationLMSTO;
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

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	
	
	
	

}
