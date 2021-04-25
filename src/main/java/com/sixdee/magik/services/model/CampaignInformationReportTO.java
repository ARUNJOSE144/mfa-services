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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */

@Entity
@Table(name = "RPT_CAMPAIGN_INFORMATION")
public class CampaignInformationReportTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "CampaignInformationReportTO")
	@TableGenerator(name = "CampaignInformationReportTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;

//	@OneToOne
//	@JoinColumn(name="CAMP_ID")
//	private CampaignDefMasterTO campMasterTO;

	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;
	
	@Column(name = "CAMPAIGN_TYPE")
	private String campaginType;

	//@DateTimeFormat(pattern = "dd-MM-yyyy")
	//@Temporal(TemporalType.DATE)
	@Column(name = "WEEK")
	private String week;

//	@Column(name = "PLAN_NAME")
//	private String planName;

	@Column(name = "SEGMENT_NAME")
	private String segmentName;

	@Column(name = "TYPE")
	private String type;

	@Column(name = "PRODUCT_PROMOTED")
	private int productPromoted;

	@Column(name = "CREDIT_TYPE")
	private String creditType;

	@Column(name = "CREDIT_AMOUNT")
	private int creditAmount;

	@Column(name = "TG_PUSHED")
	private int tgPushed;

	@Column(name = "TG_DELIVERY")
	private int tgDelivery;

	@Column(name = "DELIVERY_PER")
	private Double deliveryPer;

	@Column(name = "TG_RESPONSE")
	private int tgResponse;

	@Column(name = "TG_RESPONSE_PER")
	private Double tgResponsePer;

	@Column(name = "CG")
	private int cg;

	@Column(name = "CG_RESPONSE")
	private int cgResponse;

	@Column(name = "CG_RESPONSE_PER")
	private Double cgResponsePer;

	@Column(name = "NRR_PER")
	private Double nrrPer;

	@Column(name = "TG_GROSS_REVENUE")
	private int tgGrossRevenue;

	@Column(name = "TG_GROSS_RECHARGE")
	private int tgGrossRecharge;

	@Column(name = "CG_GROSS_REVENUE")
	private Double cgGrossRevenue;

	@Column(name = "CG_GROSS_RECHARGE")
	private int cgGrossRecharge;

	@Column(name = "INCREMENTAL_REVENUE")
	private int incrementalRevenue;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	
	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public String getCampaginType() {
		return campaginType;
	}

	public void setCampaginType(String campaginType) {
		this.campaginType = campaginType;
	}

	
	public String getWeek() {
		return week;
	}

	public void setWeek(String week) {
		this.week = week;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getProductPromoted() {
		return productPromoted;
	}

	public void setProductPromoted(int productPromoted) {
		this.productPromoted = productPromoted;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public int getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(int creditAmount) {
		this.creditAmount = creditAmount;
	}

	public int getTgPushed() {
		return tgPushed;
	}

	public void setTgPushed(int tgPushed) {
		this.tgPushed = tgPushed;
	}

	public int getTgDelivery() {
		return tgDelivery;
	}

	public void setTgDelivery(int tgDelivery) {
		this.tgDelivery = tgDelivery;
	}

	public Double getDeliveryPer() {
		return deliveryPer;
	}

	public void setDeliveryPer(Double deliveryPer) {
		this.deliveryPer = deliveryPer;
	}

	public int getTgResponse() {
		return tgResponse;
	}

	public void setTgResponse(int tgResponse) {
		this.tgResponse = tgResponse;
	}

	public Double getTgResponsePer() {
		return tgResponsePer;
	}

	public void setTgResponsePer(Double tgResponsePer) {
		this.tgResponsePer = tgResponsePer;
	}

	public int getCg() {
		return cg;
	}

	public void setCg(int cg) {
		this.cg = cg;
	}

	public int getCgResponse() {
		return cgResponse;
	}

	public void setCgResponse(int cgResponse) {
		this.cgResponse = cgResponse;
	}

	public Double getCgResponsePer() {
		return cgResponsePer;
	}

	public void setCgResponsePer(Double cgResponsePer) {
		this.cgResponsePer = cgResponsePer;
	}

	public Double getNrrPer() {
		return nrrPer;
	}

	public void setNrrPer(Double nrrPer) {
		this.nrrPer = nrrPer;
	}

	public int getTgGrossRevenue() {
		return tgGrossRevenue;
	}

	public void setTgGrossRevenue(int tgGrossRevenue) {
		this.tgGrossRevenue = tgGrossRevenue;
	}

	public int getTgGrossRecharge() {
		return tgGrossRecharge;
	}

	public void setTgGrossRecharge(int tgGrossRecharge) {
		this.tgGrossRecharge = tgGrossRecharge;
	}

	public Double getCgGrossRevenue() {
		return cgGrossRevenue;
	}

	public void setCgGrossRevenue(Double cgGrossRevenue) {
		this.cgGrossRevenue = cgGrossRevenue;
	}

	public int getCgGrossRecharge() {
		return cgGrossRecharge;
	}

	public void setCgGrossRecharge(int cgGrossRecharge) {
		this.cgGrossRecharge = cgGrossRecharge;
	}

	public int getIncrementalRevenue() {
		return incrementalRevenue;
	}

	public void setIncrementalRevenue(int incrementalRevenue) {
		this.incrementalRevenue = incrementalRevenue;
	}

	

}
