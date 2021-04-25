package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */
@Entity
@Table(name = "ACTION_CONTROL_PROMOTION_DETAILS")
@SuppressWarnings("serial")
public class ActionPromotionDetailsTO {

	@Id
	// @GeneratedValue(strategy = GenerationType.TABLE, generator =
	// "ActionPromotionDetailsTO")
//	@TableGenerator(name = "ActionPromotionDetailsTO", allocationSize = 1)

//	@Column(name = "ID")
//	private int idDetails;
	@Column(name = "ACTION_KEY_ID")
	private int actionKeyDetailsId;

	@Column(name = "ACTION_KEY")
	private String actionKeyName;

	@Column(name = "ENGLISH_NAME")
	private String englishName;

	@Column(name = "CAMPAIGN_CHANNEL")
	private String campaignChannel;

	@Column(name = "SOURCE_TYPE")
	private String sourceType;

	@Column(name = "UPSELL_TARGET_LIST_ID")
	private String upsellTargetListid;

	@Column(name = "UPSELL_TARGET_LIST")
	private String upsellTargetLst;

	@Column(name = "CAMPAIGN_TYPE_ID")
	private String campaignTypeId;

	@Column(name = "CAMPAIGN_TYPE")
	private String campaignTypeName;

	@Column(name = "PROMOTED_TARGET_ID")
	private String promotedTargetId;

	@Column(name = "PROMOTED_TARGET_LIST")
	private String promotedTargetList;

	@Column(name = "TARGET_BAND")
	private String targetBand;

	@Column(name = "PACK_DESCRIPTION")
	private String packDescription;

	@Column(name = "MIN_TARGET_VALUE")
	private String minTargetValue;

	@Column(name = "VALID_DAYS")
	private String validDays;

	@Column(name = "ROI_TYPE")
	private String roiType;

	@Column(name = "MARKETING_PLAN_NAME")
	private String marKetingPlanNameDetails;

	@Column(name = "CREDIT_AMOUNT")
	private String creditAmount;

	@Column(name = "CREDIT_TYPE")
	private String creditType;

//	public int getIdDetails() {
//		return idDetails;
//	}
//
//	public void setIdDetails(int idDetails) {
//		this.idDetails = idDetails;
//	}

	public int getActionKeyDetailsId() {
		return actionKeyDetailsId;
	}

	public void setActionKeyDetailsId(int actionKeyDetails) {
		this.actionKeyDetailsId = actionKeyDetails;
	}

	public String getActionKeyName() {
		return actionKeyName;
	}

	public void setActionKeyName(String actionKeyName) {
		this.actionKeyName = actionKeyName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getCampaignChannel() {
		return campaignChannel;
	}

	public void setCampaignChannel(String campaignChannel) {
		this.campaignChannel = campaignChannel;
	}

	public String getSourceType() {
		return sourceType;
	}

	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	public String getUpsellTargetListid() {
		return upsellTargetListid;
	}

	public void setUpsellTargetListid(String upsellTargetListid) {
		this.upsellTargetListid = upsellTargetListid;
	}

	public String getUpsellTargetLst() {
		return upsellTargetLst;
	}

	public void setUpsellTargetLst(String upsellTargetLst) {
		this.upsellTargetLst = upsellTargetLst;
	}

	public String getCampaignTypeId() {
		return campaignTypeId;
	}

	public void setCampaignTypeId(String campaignTypeId) {
		this.campaignTypeId = campaignTypeId;
	}

	public String getCampaignTypeName() {
		return campaignTypeName;
	}

	public void setCampaignTypeName(String campaignTypeName) {
		this.campaignTypeName = campaignTypeName;
	}

	public String getPromotedTargetId() {
		return promotedTargetId;
	}

	public void setPromotedTargetId(String promotedTargetId) {
		this.promotedTargetId = promotedTargetId;
	}

	public String getPromotedTargetList() {
		return promotedTargetList;
	}

	public void setPromotedTargetList(String promotedTargetList) {
		this.promotedTargetList = promotedTargetList;
	}

	public String getTargetBand() {
		return targetBand;
	}

	public void setTargetBand(String targetBand) {
		this.targetBand = targetBand;
	}

	public String getPackDescription() {
		return packDescription;
	}

	public void setPackDescription(String packDescription) {
		this.packDescription = packDescription;
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

	public String getMarKetingPlanNameDetails() {
		return marKetingPlanNameDetails;
	}

	public void setMarKetingPlanNameDetails(String marKetingPlanNameDetails) {
		this.marKetingPlanNameDetails = marKetingPlanNameDetails;
	}

	public String getCreditAmount() {
		return creditAmount;
	}

	public void setCreditAmount(String creditAmount) {
		this.creditAmount = creditAmount;
	}

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	@Override
	public String toString() {
		return "ActionPromotionDetailsTO [actionKeyDetailsId=" + actionKeyDetailsId + ", actionKeyName=" + actionKeyName
				+ ", englishName=" + englishName + ", campaignChannel=" + campaignChannel + ", sourceType=" + sourceType
				+ ", upsellTargetListid=" + upsellTargetListid + ", upsellTargetLst=" + upsellTargetLst
				+ ", campaignTypeId=" + campaignTypeId + ", campaignTypeName=" + campaignTypeName
				+ ", promotedTargetId=" + promotedTargetId + ", promotedTargetList=" + promotedTargetList
				+ ", targetBand=" + targetBand + ", packDescription=" + packDescription + ", minTargetValue="
				+ minTargetValue + ", validDays=" + validDays + ", roiType=" + roiType + ", marKetingPlanNameDetails="
				+ marKetingPlanNameDetails + ", creditAmount=" + creditAmount + ", creditType=" + creditType + "]";
	}

}
