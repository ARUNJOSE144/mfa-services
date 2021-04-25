package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sixdee.magik.services.util.CustomJsonDateDeserializer;

/**
 * @author Nakhil Kurian
 * @category January 2021
 * 
 */
@Entity
@Table(name = "RE_ACTION_CONFIG_GROUP_NEW")
@SuppressWarnings("serial")
public class ActionControlGroupTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionControlGroupTO")
	@TableGenerator(name = "ActionControlGroupTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String actionKeyName;

	@Column(name = "PARENT_ID")
	private String parentId;

	@Column(name = "CHILD_EXIST")
	private int childExist;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	@Column(name = "Type")
	private String type;

	@Column(name = "No_OF_TIMES")
	private String noOfTimes;

	@Column(name = "VALID_FOR")
	private String validFor;

	@Column(name = "VALIDITY_TYPE")
	private String validityType;

	@Column(name = "From_DATE")
	private String fromDate;

	@Column(name = "To_DATE")
	private String toDate;

	@Column(name = "REPEAT_STATUS")
	private String repeatStatus;

	@Column(name = "EVERY_DAY_STATUS")
	private String everyDay;

	@Column(name = "PROMOTION_STATUS")
	private String ispromotion;

	@Column(name = "MARKETING_PLAN")
	private String marketingPlan;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "UPSELL_BUNDLE_TYPES")
	private String upsellBundleType;

	@Column(name = "UPSELL_BUNDLE_PRODUCTS")
	private String upsellBundleProducts;

	@Column(name = "PROMOTED_BUNDLE_TYPE")
	private String promotedBundleType;

	@Column(name = "PROMOTED_BUNDLE_PRODUCTS")
	private String promotedBundleProducts;

	@Column(name = "Marketing_Plan_Name")
	private String marketingPlanName;

	@Transient
	private String marketingPlanIdDetails;

	@Transient
	private String actionKeyIdDetails;

	@Transient
	private String actionKeyNameDetails;

	@Transient
	private String englishNameDetails;

	@Transient
	private String packDescriptionDetails;

	@Transient
	private String upsellTargetListDetails;

	@Transient
	private String upsellTargetListIDDetails;

	@Transient
	private String campaignTypeNameDetails;

	@Transient
	private String campaignTypeIdDetails;

	@Transient
	private String trgetBandDetails;

	@Transient
	private String minTargetValueDetails;

	@Transient
	private String validDaysDetails;

	@Transient
	private String promotedTargetIDDetails;

	@Transient
	private String promotedTargetListDetails;

	@Transient
	private String marketingPlanNameDetails;

	@Transient
	private String roiTypeDetails;

	@Transient
	private String sourceDateDetails;

	@Transient
	private String campaignChannelDetails;

	@Transient
	private String startDateUi;

	@Transient
	private String endDateUI;

	@Transient
	private List<ActionKeyBundleTariffRateTO> bundleTariffList;

	@Transient
	private List<CampaignTypeTO> campaignTypeList;

	@Transient
	private List<CampaignChannelTO> campaignChannelList;

	@Transient
	private List<IsPromotionDetailsTO> isPromotionDetails;

	@Transient
	private String auditRoleName;

	@Transient
	private String auditUserName;

	@Transient
	private String creditAmount;

	@Transient
	private String creditType;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionKeyName() {
		return actionKeyName;
	}

	public void setActionKeyName(String actionKeyName) {
		this.actionKeyName = actionKeyName;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public int getChildExist() {
		return childExist;
	}

	public void setChildExist(int childExist) {
		this.childExist = childExist;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNoOfTimes() {
		return noOfTimes;
	}

	public void setNoOfTimes(String noOfTimes) {
		this.noOfTimes = noOfTimes;
	}

	public String getValidFor() {
		return validFor;
	}

	public void setValidFor(String validFor) {
		this.validFor = validFor;
	}

	public String getValidityType() {
		return validityType;
	}

	public void setValidityType(String validityType) {
		this.validityType = validityType;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getToDate() {
		return toDate;
	}

	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getRepeatStatus() {
		return repeatStatus;
	}

	public void setRepeatStatus(String repeatStatus) {
		this.repeatStatus = repeatStatus;
	}

	public String getEveryDay() {
		return everyDay;
	}

	public void setEveryDay(String everyDay) {
		this.everyDay = everyDay;
	}

	public String getIspromotion() {
		return ispromotion;
	}

	public void setIspromotion(String ispromotion) {
		this.ispromotion = ispromotion;
	}

	public String getMarketingPlan() {
		return marketingPlan;
	}

	public void setMarketingPlan(String marketingPlan) {
		this.marketingPlan = marketingPlan;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUpsellBundleType() {
		return upsellBundleType;
	}

	public void setUpsellBundleType(String upsellBundleType) {
		this.upsellBundleType = upsellBundleType;
	}

	public String getUpsellBundleProducts() {
		return upsellBundleProducts;
	}

	public void setUpsellBundleProducts(String upsellBundleProducts) {
		this.upsellBundleProducts = upsellBundleProducts;
	}

	public String getPromotedBundleType() {
		return promotedBundleType;
	}

	public void setPromotedBundleType(String promotedBundleType) {
		this.promotedBundleType = promotedBundleType;
	}

	public String getPromotedBundleProducts() {
		return promotedBundleProducts;
	}

	public void setPromotedBundleProducts(String promotedBundleProducts) {
		this.promotedBundleProducts = promotedBundleProducts;
	}

	public String getMarketingPlanName() {
		return marketingPlanName;
	}

	public void setMarketingPlanName(String marketingPlanName) {
		this.marketingPlanName = marketingPlanName;
	}

	public String getMarketingPlanIdDetails() {
		return marketingPlanIdDetails;
	}

	public void setMarketingPlanIdDetails(String marketingPlanIdDetails) {
		this.marketingPlanIdDetails = marketingPlanIdDetails;
	}

	public String getActionKeyIdDetails() {
		return actionKeyIdDetails;
	}

	public void setActionKeyIdDetails(String actionKeyIdDetails) {
		this.actionKeyIdDetails = actionKeyIdDetails;
	}

	public String getActionKeyNameDetails() {
		return actionKeyNameDetails;
	}

	public void setActionKeyNameDetails(String actionKeyNameDetails) {
		this.actionKeyNameDetails = actionKeyNameDetails;
	}

	public String getEnglishNameDetails() {
		return englishNameDetails;
	}

	public void setEnglishNameDetails(String englishNameDetails) {
		this.englishNameDetails = englishNameDetails;
	}

	public String getPackDescriptionDetails() {
		return packDescriptionDetails;
	}

	public void setPackDescriptionDetails(String packDescriptionDetails) {
		this.packDescriptionDetails = packDescriptionDetails;
	}

	public String getUpsellTargetListDetails() {
		return upsellTargetListDetails;
	}

	public void setUpsellTargetListDetails(String upsellTargetListDetails) {
		this.upsellTargetListDetails = upsellTargetListDetails;
	}

	public String getUpsellTargetListIDDetails() {
		return upsellTargetListIDDetails;
	}

	public void setUpsellTargetListIDDetails(String upsellTargetListIDDetails) {
		this.upsellTargetListIDDetails = upsellTargetListIDDetails;
	}

	public String getCampaignTypeNameDetails() {
		return campaignTypeNameDetails;
	}

	public void setCampaignTypeNameDetails(String campaignTypeNameDetails) {
		this.campaignTypeNameDetails = campaignTypeNameDetails;
	}

	public String getCampaignTypeIdDetails() {
		return campaignTypeIdDetails;
	}

	public void setCampaignTypeIdDetails(String campaignTypeIdDetails) {
		this.campaignTypeIdDetails = campaignTypeIdDetails;
	}

	public String getTrgetBandDetails() {
		return trgetBandDetails;
	}

	public void setTrgetBandDetails(String trgetBandDetails) {
		this.trgetBandDetails = trgetBandDetails;
	}

	public String getMinTargetValueDetails() {
		return minTargetValueDetails;
	}

	public void setMinTargetValueDetails(String minTargetValueDetails) {
		this.minTargetValueDetails = minTargetValueDetails;
	}

	public String getValidDaysDetails() {
		return validDaysDetails;
	}

	public void setValidDaysDetails(String validDaysDetails) {
		this.validDaysDetails = validDaysDetails;
	}

	public String getPromotedTargetIDDetails() {
		return promotedTargetIDDetails;
	}

	public void setPromotedTargetIDDetails(String promotedTargetIDDetails) {
		this.promotedTargetIDDetails = promotedTargetIDDetails;
	}

	public String getPromotedTargetListDetails() {
		return promotedTargetListDetails;
	}

	public void setPromotedTargetListDetails(String promotedTargetListDetails) {
		this.promotedTargetListDetails = promotedTargetListDetails;
	}

	public String getMarketingPlanNameDetails() {
		return marketingPlanNameDetails;
	}

	public void setMarketingPlanNameDetails(String marketingPlanNameDetails) {
		this.marketingPlanNameDetails = marketingPlanNameDetails;
	}

	public String getRoiTypeDetails() {
		return roiTypeDetails;
	}

	public void setRoiTypeDetails(String roiTypeDetails) {
		this.roiTypeDetails = roiTypeDetails;
	}

	public String getSourceDateDetails() {
		return sourceDateDetails;
	}

	public void setSourceDateDetails(String sourceDateDetails) {
		this.sourceDateDetails = sourceDateDetails;
	}

	public String getCampaignChannelDetails() {
		return campaignChannelDetails;
	}

	public void setCampaignChannelDetails(String campaignChannelDetails) {
		this.campaignChannelDetails = campaignChannelDetails;
	}

	public String getStartDateUi() {
		return startDateUi;
	}

	public void setStartDateUi(String startDateUi) {
		this.startDateUi = startDateUi;
	}

	public String getEndDateUI() {
		return endDateUI;
	}

	public void setEndDateUI(String endDateUI) {
		this.endDateUI = endDateUI;
	}

	public List<ActionKeyBundleTariffRateTO> getBundleTariffList() {
		return bundleTariffList;
	}

	public void setBundleTariffList(List<ActionKeyBundleTariffRateTO> bundleTariffList) {
		this.bundleTariffList = bundleTariffList;
	}

	public List<CampaignTypeTO> getCampaignTypeList() {
		return campaignTypeList;
	}

	public void setCampaignTypeList(List<CampaignTypeTO> campaignTypeList) {
		this.campaignTypeList = campaignTypeList;
	}

	public List<CampaignChannelTO> getCampaignChannelList() {
		return campaignChannelList;
	}

	public void setCampaignChannelList(List<CampaignChannelTO> campaignChannelList) {
		this.campaignChannelList = campaignChannelList;
	}

	public List<IsPromotionDetailsTO> getIsPromotionDetails() {
		return isPromotionDetails;
	}

	public void setIsPromotionDetails(List<IsPromotionDetailsTO> isPromotionDetails) {
		this.isPromotionDetails = isPromotionDetails;
	}

	public String getAuditRoleName() {
		return auditRoleName;
	}

	public void setAuditRoleName(String auditRoleName) {
		this.auditRoleName = auditRoleName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
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
		return "ActionControlGroupTO [id=" + id + ", actionKeyName=" + actionKeyName + ", parentId=" + parentId
				+ ", childExist=" + childExist + ", startDate=" + startDate + ", endDate=" + endDate + ", type=" + type
				+ ", noOfTimes=" + noOfTimes + ", validFor=" + validFor + ", validityType=" + validityType
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", repeatStatus=" + repeatStatus + ", everyDay="
				+ everyDay + ", ispromotion=" + ispromotion + ", marketingPlan=" + marketingPlan + ", userId=" + userId
				+ ", upsellBundleType=" + upsellBundleType + ", upsellBundleProducts=" + upsellBundleProducts
				+ ", promotedBundleType=" + promotedBundleType + ", promotedBundleProducts=" + promotedBundleProducts
				+ ", marketingPlanName=" + marketingPlanName + ", marketingPlanIdDetails=" + marketingPlanIdDetails
				+ ", actionKeyIdDetails=" + actionKeyIdDetails + ", actionKeyNameDetails=" + actionKeyNameDetails
				+ ", englishNameDetails=" + englishNameDetails + ", packDescriptionDetails=" + packDescriptionDetails
				+ ", upsellTargetListDetails=" + upsellTargetListDetails + ", upsellTargetListIDDetails="
				+ upsellTargetListIDDetails + ", campaignTypeNameDetails=" + campaignTypeNameDetails
				+ ", campaignTypeIdDetails=" + campaignTypeIdDetails + ", trgetBandDetails=" + trgetBandDetails
				+ ", minTargetValueDetails=" + minTargetValueDetails + ", validDaysDetails=" + validDaysDetails
				+ ", promotedTargetIDDetails=" + promotedTargetIDDetails + ", promotedTargetListDetails="
				+ promotedTargetListDetails + ", marketingPlanNameDetails=" + marketingPlanNameDetails
				+ ", roiTypeDetails=" + roiTypeDetails + ", sourceDateDetails=" + sourceDateDetails
				+ ", campaignChannelDetails=" + campaignChannelDetails + ", startDateUi=" + startDateUi + ", endDateUI="
				+ endDateUI + ", bundleTariffList=" + bundleTariffList + ", campaignTypeList=" + campaignTypeList
				+ ", campaignChannelList=" + campaignChannelList + ", isPromotionDetails=" + isPromotionDetails
				+ ", auditRoleName=" + auditRoleName + ", auditUserName=" + auditUserName + ", creditAmount="
				+ creditAmount + ", creditType=" + creditType + "]";
	}

}
