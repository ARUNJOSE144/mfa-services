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
@Table(name = "LMS_CNFG_ACCOUNT_TYPE")
public class AccountTypeLMSTO {
	
	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountTypeLMSTO")
	@TableGenerator(name = "AccountTypeLMSTO", allocationSize = 1)
	@Column(name = "TYPE_ID")
	private int typeId;
	
	@Column(name = "TYPE_NAME")
	private String typeName;
	
	@CreationTimestamp
	@Column(name="CREATE_TIME",nullable = false, updatable = false)
	private Date createdDate;

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

}
