package com.sixdee.magik.services.model;

import java.util.Date;

/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */
 
public class VoucherDetailsTO {

	
private int autoId;
	
	
	//If required to Float or Integer
	private String voucherName;
	private String voucherType;
	private String voucherDescription;
	private String voucherSynonym;
	private String rateCardName;
	private String quantity;
	private String pointsPerQuantity;
	private String generateVoucherId;
	private Date startDate;
	private Date expiryDate;
	private String orderReferenceNumber;
	private Date status;
	private Date from;
	private String to;
	
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public Date getStatus() {
		return status;
	}
	public void setStatus(Date status) {
		this.status = status;
	}
	public Date getFrom() {
		return from;
	}
	public void setFrom(Date from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public int getAutoId() {
		return autoId;
	}
	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}
	public String getVoucherName() {
		return voucherName;
	}
	public void setVoucherName(String voucherName) {
		this.voucherName = voucherName;
	}
	public String getVoucherType() {
		return voucherType;
	}
	public void setVoucherType(String voucherType) {
		this.voucherType = voucherType;
	}
	public String getVoucherDescription() {
		return voucherDescription;
	}
	public void setVoucherDescription(String voucherDescription) {
		this.voucherDescription = voucherDescription;
	}
	public String getVoucherSynonym() {
		return voucherSynonym;
	}
	public void setVoucherSynonym(String voucherSynonym) {
		this.voucherSynonym = voucherSynonym;
	}
	public String getRateCardName() {
		return rateCardName;
	}
	public void setRateCardName(String rateCardName) {
		this.rateCardName = rateCardName;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getPointsPerQuantity() {
		return pointsPerQuantity;
	}
	public void setPointsPerQuantity(String pointsPerQuantity) {
		this.pointsPerQuantity = pointsPerQuantity;
	}
	public String getGenerateVoucherId() {
		return generateVoucherId;
	}
	public void setGenerateVoucherId(String generateVoucherId) {
		this.generateVoucherId = generateVoucherId;
	}
	public String getOrderReferenceNumber() {
		return orderReferenceNumber;
	}
	public void setOrderReferenceNumber(String orderReferenceNumber) {
		this.orderReferenceNumber = orderReferenceNumber;
	}
	
	
}
