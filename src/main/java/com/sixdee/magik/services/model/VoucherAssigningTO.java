package com.sixdee.magik.services.model;

import java.beans.Transient;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "VOUCHER_ASSIGN_LOY")
public class VoucherAssigningTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "VoucherAssigningTO")
	@TableGenerator(name = "VoucherAssigningTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	
	@Column(name = "PARTNER")
	private String partnerName;
	
	@OneToOne
	@JoinColumn(name="BATCH_NAME")
	private VoucherGenerationTO voucherGenerationTO;
	
	@Column(name = "OFFER_NAME")
	private String offerName;
	
	@Column(name = "ASSIGN_COUNT")
	private int assignCount;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	@Column(name = "EXPIRY_DATE")
	private Date expriyDate;
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	
	//For Demo..
	@javax.persistence.Transient
	private String batchNametransient;
	
	@javax.persistence.Transient
	private String fileName="VoucherDoc.txt";
	
	@javax.persistence.Transient
	private String encodedFile="data:@file/plain;base64,QU1aMTUwDQpQQVlUTTE1MA0KQUlSVEVMMTUwDQpORVcxMDANCkFNWjE1MA0K";
	

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}

	
	public VoucherGenerationTO getVoucherGenerationTO() {
		return voucherGenerationTO;
	}

	public void setVoucherGenerationTO(VoucherGenerationTO voucherGenerationTO) {
		this.voucherGenerationTO = voucherGenerationTO;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public int getAssignCount() {
		return assignCount;
	}

	public void setAssignCount(int assignCount) {
		this.assignCount = assignCount;
	}

	public Date getExpriyDate() {
		return expriyDate;
	}

	public void setExpriyDate(Date expriyDate) {
		this.expriyDate = expriyDate;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEncodedFile() {
		return encodedFile;
	}

	public void setEncodedFile(String encodedFile) {
		this.encodedFile = encodedFile;
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

	public String getBatchNametransient() {
		return batchNametransient;
	}

	public void setBatchNametransient(String batchNametransient) {
		this.batchNametransient = batchNametransient;
	}

	


}
