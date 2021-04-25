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
@Table(name = "LMS_CNFG_SUBSCRIPTION_TYPE")
public class SubscriberTypeLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SubscriberTypeLMSTO")
	@TableGenerator(name = "SubscriberTypeLMSTO", allocationSize = 1)
	@Column(name = "LINE_ID")
	private int lineId;
	
	@Column(name = "SUBSCRIPTION_NAME")
	private String subscrptionName;
	
	@CreationTimestamp
	@Column(name="CRAETE_TIME",nullable = false, updatable = false)
	private Date createdDate;

	public int getLineId() {
		return lineId;
	}

	public void setLineId(int lineId) {
		this.lineId = lineId;
	}

	public String getSubscrptionName() {
		return subscrptionName;
	}

	public void setSubscrptionName(String subscrptionName) {
		this.subscrptionName = subscrptionName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	

}
