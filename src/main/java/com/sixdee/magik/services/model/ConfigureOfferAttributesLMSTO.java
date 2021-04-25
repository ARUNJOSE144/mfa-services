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

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CSEG_OFFER_ATTRIBUTES")
public class ConfigureOfferAttributesLMSTO {
	
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferAttributesLMSTO")
	@TableGenerator(name = "ConfigureOfferAttributesLMSTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	
	@OneToOne
	@JoinColumn(name = "OFFER_ID")
	private ConfigureOfferMasterLMSTO configureOfferMasterLMSTO;
	
	@CreationTimestamp
	@Column(name="CREATE_DATE",nullable = false, updatable = false)
	private Date createdDate;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
	

}
