package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 

@SuppressWarnings("serial")
@Entity
@Table(name = "LMS_CNFG_OFFER_TYPE")
public class ConfigureOfferTypeLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ConfigureOfferTypeLMSTO")
	@TableGenerator(name = "ConfigureOfferTypeLMSTO", allocationSize = 1)
	@Column(name = "OFFERTYPEID")
	private int offerTypeId;
	
	@Column(name = "OFFERTYPE")
	private String offertype;
	
	@CreationTimestamp
	@Column(name="CREATEDATE",nullable = false, updatable = false)
	private Date createdDate;

	public int getOfferTypeId() {
		return offerTypeId;
	}

	public void setOfferTypeId(int offerTypeId) {
		this.offerTypeId = offerTypeId;
	}

	public String getOffertype() {
		return offertype;
	}

	public void setOffertype(String offertype) {
		this.offertype = offertype;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

}
