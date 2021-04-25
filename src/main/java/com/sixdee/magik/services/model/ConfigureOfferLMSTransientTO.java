package com.sixdee.magik.services.model;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

public class ConfigureOfferLMSTransientTO {
	
	
	
	private Double offerId;
	private int offerTypeId;
	private Date offerStartDate;
	private Date offerEndDate;
	private Double offerExpDay;
	private Double offerExpHrs;
	private Double offerExpMin;
	private Double cost;
	private String vendorName;
	private BigInteger physicalGift;
	private List<ChannelDetailsLMSTO> channelList;
	private List<TierConfigurationLMSTO> loyaltyList;
	private List<AccountTypeLMSTO> subscrptionList;
	private List<UserCategoryLMSTO> userCatList;
	private Double priority;
	private Double status;
	private String shortCode;
	private Double offerPts;
	private Double topOffer;
	private int partner;
	private String offerName;
	private String offerDesc;
	private String offerTc;
	private String rectangluarImg;
	private String squareImg;
	private String iconImage;
	private int interfaceTypeId;
	public Double getOfferId() {
		return offerId;
	}
	public void setOfferId(Double offerId) {
		this.offerId = offerId;
	}
	
	public int getOfferTypeId() {
		return offerTypeId;
	}
	public void setOfferTypeId(int offerTypeId) {
		this.offerTypeId = offerTypeId;
	}
	public Date getOfferStartDate() {
		return offerStartDate;
	}
	public void setOfferStartDate(Date offerStartDate) {
		this.offerStartDate = offerStartDate;
	}
	public Date getOfferEndDate() {
		return offerEndDate;
	}
	public void setOfferEndDate(Date offerEndDate) {
		this.offerEndDate = offerEndDate;
	}
	public Double getOfferExpDay() {
		return offerExpDay;
	}
	public void setOfferExpDay(Double offerExpDay) {
		this.offerExpDay = offerExpDay;
	}
	public Double getOfferExpHrs() {
		return offerExpHrs;
	}
	public void setOfferExpHrs(Double offerExpHrs) {
		this.offerExpHrs = offerExpHrs;
	}
	public Double getOfferExpMin() {
		return offerExpMin;
	}
	public void setOfferExpMin(Double offerExpMin) {
		this.offerExpMin = offerExpMin;
	}
	public Double getCost() {
		return cost;
	}
	public void setCost(Double cost) {
		this.cost = cost;
	}
	public String getVendorName() {
		return vendorName;
	}
	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
	public BigInteger getPhysicalGift() {
		return physicalGift;
	}
	public void setPhysicalGift(BigInteger physicalGift) {
		this.physicalGift = physicalGift;
	}
	public List<ChannelDetailsLMSTO> getChannelList() {
		return channelList;
	}
	public void setChannelList(List<ChannelDetailsLMSTO> channelList) {
		this.channelList = channelList;
	}
	public List<TierConfigurationLMSTO> getLoyaltyList() {
		return loyaltyList;
	}
	public void setLoyaltyList(List<TierConfigurationLMSTO> loyaltyList) {
		this.loyaltyList = loyaltyList;
	}
	public List<AccountTypeLMSTO> getSubscrptionList() {
		return subscrptionList;
	}
	public void setSubscrptionList(List<AccountTypeLMSTO> subscrptionList) {
		this.subscrptionList = subscrptionList;
	}
	public List<UserCategoryLMSTO> getUserCatList() {
		return userCatList;
	}
	public void setUserCatList(List<UserCategoryLMSTO> userCatList) {
		this.userCatList = userCatList;
	}
	public Double getPriority() {
		return priority;
	}
	public void setPriority(Double priority) {
		this.priority = priority;
	}
	public Double getStatus() {
		return status;
	}
	public void setStatus(Double status) {
		this.status = status;
	}
	public String getShortCode() {
		return shortCode;
	}
	public void setShortCode(String shortCode) {
		this.shortCode = shortCode;
	}
	public Double getOfferPts() {
		return offerPts;
	}
	public void setOfferPts(Double offerPts) {
		this.offerPts = offerPts;
	}
	public Double getTopOffer() {
		return topOffer;
	}
	public void setTopOffer(Double topOffer) {
		this.topOffer = topOffer;
	}
	public int getPartner() {
		return partner;
	}
	public void setPartner(int partner) {
		this.partner = partner;
	}
	public String getRectangluarImg() {
		return rectangluarImg;
	}
	public void setRectangluarImg(String rectangluarImg) {
		this.rectangluarImg = rectangluarImg;
	}
	public String getSquareImg() {
		return squareImg;
	}
	public void setSquareImg(String squareImg) {
		this.squareImg = squareImg;
	}
	public String getIconImage() {
		return iconImage;
	}
	public void setIconImage(String iconImage) {
		this.iconImage = iconImage;
	}
	public int getInterfaceTypeId() {
		return interfaceTypeId;
	}
	public void setInterfaceTypeId(int interfaceTypeId) {
		this.interfaceTypeId = interfaceTypeId;
	}
	public String getOfferName() {
		return offerName;
	}
	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}
	public String getOfferDesc() {
		return offerDesc;
	}
	public void setOfferDesc(String offerDesc) {
		this.offerDesc = offerDesc;
	}
	public String getOfferTc() {
		return offerTc;
	}
	public void setOfferTc(String offerTc) {
		this.offerTc = offerTc;
	}

}
