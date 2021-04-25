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
import org.hibernate.annotations.UpdateTimestamp;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "VOUCHER_GEN_LOY")
public class VoucherGenerationTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "VoucherGenerationTO")
	@TableGenerator(name = "VoucherGenerationTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@Column(name = "BATCH_NAME")
	private String batchName;
	
	@Column(name = "OFFER_NAME")
	private String offerName;
	
	@Column(name = "STATUS")
	private String status;
	
	
	@Column(name = "VOUCHER_COUNT")
	private int voucherCount;
	
	
	@Column(name = "VOUCHER_LENGTH")
	private int voucherlength;
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getBatchName() {
		return batchName;
	}

	public void setBatchName(String batchName) {
		this.batchName = batchName;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public int getVoucherCount() {
		return voucherCount;
	}

	public void setVoucherCount(int voucherCount) {
		this.voucherCount = voucherCount;
	}

	public int getVoucherlength() {
		return voucherlength;
	}

	public void setVoucherlength(int voucherlength) {
		this.voucherlength = voucherlength;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	

}
