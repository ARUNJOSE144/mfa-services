package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "RPT_DAYWISE_CAMPCONV")
public class DaywiseCampaignConversionTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "DaywiseCampaignConversionTO")
	@TableGenerator(name = "DaywiseCampaignConversionTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;

	@CsvBindByName(column = "Promotion Date	", required = true)
	@CsvBindByPosition(position = 0)
	@Column(name = "PROMOTION_DATE")
	private String promotionDate;

	@CsvBindByName(column = "Campaign Name", required = true)
	@CsvBindByPosition(position = 1)
	@Column(name = "CAMPAIGN_NAME")
	private String campaignName;

	@CsvBindByName(column = "Segment Name", required = true)
	@CsvBindByPosition(position = 2)
	@Column(name = "SEGMENT_NAME")
	private String segmentName;

	@CsvBindByName(column = "TG Pushed", required = true)
	@CsvBindByPosition(position = 3)
	@Column(name = "TG_PUSHED")
	private int tgPushed;

	@CsvBindByName(column = "TG Delivered", required = true)
	@CsvBindByPosition(position = 4)
	@Column(name = "TG_DELIVERED")
	private int tgDelivered;

	@CsvBindByName(column = "TG Converted", required = true)
	@CsvBindByPosition(position = 5)
	@Column(name = "TG_CONVERTED")
	private int tgConverted;

	@CsvBindByName(column = "TG Conversion Per", required = true)
	@CsvBindByPosition(position = 6)
	@Column(name = "TG_CONVERSION_PER")
	private Double tgConversionPer;

	@CsvBindByName(column = "CG Base", required = true)
	@CsvBindByPosition(position = 7)
	@Column(name = "CG_BASE")
	private int cgBase;

	@CsvBindByName(column = "CG Conversion", required = true)
	@CsvBindByPosition(position = 8)
	@Column(name = "CG_CONVERSION")
	private String cgConversion;

	@CsvBindByName(column = "CG Conversion Per", required = true)
	@CsvBindByPosition(position = 9)
	@Column(name = "CG_CONVERSION_PER")
	private Double cgConversionPer;

	@CsvBindByName(column = "NRR", required = true)
	@CsvBindByPosition(position = 10)
	@Column(name = "NRR")
	private Double nrr;

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

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}

	public int getTgPushed() {
		return tgPushed;
	}

	public void setTgPushed(int tgPushed) {
		this.tgPushed = tgPushed;
	}

	public int getTgDelivered() {
		return tgDelivered;
	}

	public void setTgDelivered(int tgDelivered) {
		this.tgDelivered = tgDelivered;
	}

	public int getTgConverted() {
		return tgConverted;
	}

	public void setTgConverted(int tgConverted) {
		this.tgConverted = tgConverted;
	}

	public Double getTgConversionPer() {
		return tgConversionPer;
	}

	public void setTgConversionPer(Double tgConversionPer) {
		this.tgConversionPer = tgConversionPer;
	}

	public int getCgBase() {
		return cgBase;
	}

	public void setCgBase(int cgBase) {
		this.cgBase = cgBase;
	}

	public Double getCgConversionPer() {
		return cgConversionPer;
	}

	public void setCgConversionPer(Double cgConversionPer) {
		this.cgConversionPer = cgConversionPer;
	}

	public Double getNrr() {
		return nrr;
	}

	public void setNrr(Double nrr) {
		this.nrr = nrr;
	}

	public String getCgConversion() {
		return cgConversion;
	}

	public void setCgConversion(String cgConversion) {
		this.cgConversion = cgConversion;
	}

	public String getPromotionDate() {
		return promotionDate;
	}

	public void setPromotionDate(String promotionDate) {
		this.promotionDate = promotionDate;
	}

}
