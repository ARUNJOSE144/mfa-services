package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "IS_PROMOTION_DETAILS")
@SuppressWarnings("serial")
public class IsPromotionDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "IsPromotionDetailsTO")
	@TableGenerator(name = "IsPromotionDetailsTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "ENGLISH_NAME")
	private String englishName;

	@Column(name = "TARGET_LIST")
	private String targetList;

	@Column(name = "CAMPAGIN_CHANNEL")
	private String campaignChannel;

	@Column(name = "CAMPAGIN_TYPE")
	private String campaignType;

	@Column(name = "PACK_DESCRIPTION")
	private String packDescription;

	@Column(name = "TARGET_BAND")
	private String targetBand;

	@Column(name = "MINIMUM_TARGET_VALUE")
	private String minTargetValue;

	@Column(name = "VALID_DAYS")
	private String validDays;

	@Column(name = "ROI_TYPE")
	private String roiType;

	@Column(name = "SOURCE_TYPE")
	private String sourceType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getTargetList() {
		return targetList;
	}

	public void setTargetList(String targetList) {
		this.targetList = targetList;
	}

	public String getCampaignChannel() {
		return campaignChannel;
	}

	public void setCampaignChannel(String campaignChannel) {
		this.campaignChannel = campaignChannel;
	}

	public String getCampaignType() {
		return campaignType;
	}

	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}

	public String getPackDescription() {
		return packDescription;
	}

	public void setPackDescription(String packDescription) {
		this.packDescription = packDescription;
	}

	public String getTargetBand() {
		return targetBand;
	}

	public void setTargetBand(String targetBand) {
		this.targetBand = targetBand;
	}

	public String getMinTargetValue() {
		return minTargetValue;
	}

	public void setMinTargetValue(String minTargetValue) {
		this.minTargetValue = minTargetValue;
	}

	public String getValidDays() {
		return validDays;
	}

	public void setValidDays(String validDays) {
		this.validDays = validDays;
	}

	public String getRoiType() {
		return roiType;
	}

	public void setRoiType(String roiType) {
		this.roiType = roiType;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	@Override
	public String toString() {
		return "IsPromotionDetailsTO [id=" + id + ", englishName=" + englishName + ", targetList=" + targetList
				+ ", campaignChannel=" + campaignChannel + ", campaignType=" + campaignType + ", packDescription="
				+ packDescription + ", targetBand=" + targetBand + ", minTargetValue=" + minTargetValue + ", validDays="
				+ validDays + ", roiType=" + roiType + ", sourceType=" + sourceType + "]";
	}

	
	
	

}
