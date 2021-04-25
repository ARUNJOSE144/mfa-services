package com.sixdee.magik.services.model;

public class CustomerCareDTO {

	private String feature;
	private String msisdn;
	private boolean status;

	private String customerProfile;
	private String deviceProfile;
	private String loyaltyAccount;

	private String packagesInfo;

	private String redeemedPoints;

	private String usageSummary;
	private String loyaltySummary;
	private String campaignTransactions;
	private String subSegment;
	private String numberOfSubscribers;
	private String avAge;
	private String avgRevenuePerMonth;
	private String avgAccBalance;
	private String maxAgeInNetwork;
	private String minAgeInNetwork;
	private String avgIntCallPerDay;
	private String avgOutCallPerDay;
	private String avgVolPerDay;
	private String avgBalRecharge;
	private String avgMaxRefill;
	private String avgMinRefill;
	private String avgRechargeValuePerMon;
	private String avgRechargesPerMonth;
	private String avgTimeBwRecharges;
	private String avgOutSmsPerDay;
	private String segment;
	private String restSegment;
	private String avgRespRate;
	private String insightSmsCount;
	private String mmsCount;
	private String emailCount;
	private String ussdCount;
	private String avgChannelResponse;
	private String socialCount;
	private String avgLocalCallPerDay;

	/*
	 * private String myBestOffers; private String subscriberType; private String
	 * language; private String actDate; private String reagion; private String
	 * arpu; private Integer rechargeCount; private Integer rechargeAmount; private
	 * Integer voiceRevenu; private Integer totalCall; private Integer internalCall;
	 * private Integer mocCall; private Integer mtcCall; private Integer onNetCall;
	 * private Integer offNetCall; private Integer smsCount; private Integer
	 * smsRevenu; private Integer mosmsCount; private Integer dataRevenu; private
	 * Integer dataVolume; private List<EligibleCampaign> eligibleCampaign; private
	 * Map<String, EligibleCampaign> activeCamp = null;
	 * 
	 * //Customer Insights DTOs private String subSegment; private String
	 * numberOfSubscribers; private String avAge; private String avgRevenuePerMonth
	 * ; private String avgAccBalance; private String maxAgeInNetwork; private
	 * String minAgeInNetwork; private String avgIntCallPerDay; private String
	 * avgOutCallPerDay; private String avgVolPerDay; private String avgBalRecharge;
	 * private String avgMaxRefill; private String avgMinRefill; private String
	 * avgRechargeValuePerMon; private String avgRechargesPerMonth; private String
	 * avgTimeBwRecharges; private String avgOutSmsPerDay; private String segment;
	 * private String restSegment; private String avgRespRate; private String
	 * insightSmsCount; private String mmsCount; private String emailCount; private
	 * String ussdCount; private String avgChannelResponse; private String
	 * socialCount; private String avgLocalCallPerDay;
	 */

	/*
	 * public Map<String, EligibleCampaign> getActiveCamp() { return activeCamp; }
	 * 
	 * public void setActiveCamp(Map<String, EligibleCampaign> activeCamp) {
	 * this.activeCamp = activeCamp; }
	 * 
	 * public List<EligibleCampaign> getEligibleCampaign() { return
	 * eligibleCampaign; }
	 * 
	 * public void setEligibleCampaign(List<EligibleCampaign> eligibleCampaign) {
	 * this.eligibleCampaign = eligibleCampaign; }
	 */

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	/*
	 * public Integer getRechargeCount() { return rechargeCount; }
	 * 
	 * public void setRechargeCount(Integer rechargeCount) { this.rechargeCount =
	 * rechargeCount; }
	 * 
	 * public Integer getRechargeAmount() { return rechargeAmount; }
	 * 
	 * public void setRechargeAmount(Integer rechargeAmount) { this.rechargeAmount =
	 * rechargeAmount; }
	 * 
	 * public Integer getVoiceRevenu() { return voiceRevenu; }
	 * 
	 * public void setVoiceRevenu(Integer voiceRevenu) { this.voiceRevenu =
	 * voiceRevenu; }
	 * 
	 * public Integer getTotalCall() { return totalCall; }
	 * 
	 * public void setTotalCall(Integer totalCall) { this.totalCall = totalCall; }
	 * 
	 * public Integer getInternalCall() { return internalCall; }
	 * 
	 * public void setInternalCall(Integer internalCall) { this.internalCall =
	 * internalCall; }
	 * 
	 * public Integer getMocCall() { return mocCall; }
	 * 
	 * public void setMocCall(Integer mocCall) { this.mocCall = mocCall; }
	 * 
	 * public Integer getMtcCall() { return mtcCall; }
	 * 
	 * public void setMtcCall(Integer mtcCall) { this.mtcCall = mtcCall; }
	 * 
	 * public Integer getOnNetCall() { return onNetCall; }
	 * 
	 * public void setOnNetCall(Integer onNetCall) { this.onNetCall = onNetCall; }
	 * 
	 * public Integer getOffNetCall() { return offNetCall; }
	 * 
	 * public void setOffNetCall(Integer offNetCall) { this.offNetCall = offNetCall;
	 * }
	 * 
	 * public Integer getSmsCount() { return smsCount; }
	 * 
	 * public void setSmsCount(Integer smsCount) { this.smsCount = smsCount; }
	 * 
	 * public Integer getSmsRevenu() { return smsRevenu; }
	 * 
	 * public void setSmsRevenu(Integer smsRevenu) { this.smsRevenu = smsRevenu; }
	 * 
	 * public Integer getMosmsCount() { return mosmsCount; }
	 * 
	 * public void setMosmsCount(Integer mosmsCount) { this.mosmsCount = mosmsCount;
	 * }
	 * 
	 * public Integer getDataRevenu() { return dataRevenu; }
	 * 
	 * public void setDataRevenu(Integer dataRevenu) { this.dataRevenu = dataRevenu;
	 * }
	 * 
	 * public Integer getDataVolume() { return dataVolume; }
	 * 
	 * public void setDataVolume(Integer dataVolume) { this.dataVolume = dataVolume;
	 * }
	 * 
	 * public String getArpu() { return arpu; }
	 * 
	 * public void setArpu(String arpu) { this.arpu = arpu; }
	 * 
	 * public String getSubscriberType() { return subscriberType; }
	 * 
	 * public void setSubscriberType(String subscriberType) { this.subscriberType =
	 * subscriberType; }
	 * 
	 * public String getLanguage() { return language; }
	 * 
	 * public void setLanguage(String language) { this.language = language; }
	 * 
	 * public String getActDate() { return actDate; }
	 * 
	 * public void setActDate(String actDate) { this.actDate = actDate; }
	 * 
	 * public String getReagion() { return reagion; }
	 * 
	 * public void setReagion(String reagion) { this.reagion = reagion; }
	 * 
	 * public String getMyBestOffers() { return myBestOffers; }
	 * 
	 * public void setMyBestOffers(String myBestOffers) { this.myBestOffers =
	 * myBestOffers; }
	 */
	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	public void setCustomerProfile(String customerProfile) {
		this.customerProfile = customerProfile;
	}

	public void setDeviceProfile(String deviceProfile) {
		this.deviceProfile = deviceProfile;
	}

	public void setLoyaltyAccount(String loyaltyAccount) {
		this.loyaltyAccount = loyaltyAccount;
	}

	public void setPackagesInfo(String packagesInfo) {
		this.packagesInfo = packagesInfo;
	}

	public void setRedeemedPoints(String redeemedPoints) {
		this.redeemedPoints = redeemedPoints;
	}

	public void setUsageSummary(String usageSummary) {
		this.usageSummary = usageSummary;
	}

	public void setLoyaltySummary(String loyaltySummary) {
		this.loyaltySummary = loyaltySummary;
	}

	public void setCampaignTransactions(String campaignTransactions) {
		this.campaignTransactions = campaignTransactions;
	}

	public String getCustomerProfile() {
		return customerProfile;
	}

	public String getDeviceProfile() {
		return deviceProfile;
	}

	public String getLoyaltyAccount() {
		return loyaltyAccount;
	}

	public String getPackagesInfo() {
		return packagesInfo;
	}

	public String getRedeemedPoints() {
		return redeemedPoints;
	}

	public String getUsageSummary() {
		return usageSummary;
	}

	public String getLoyaltySummary() {
		return loyaltySummary;
	}

	public String getCampaignTransactions() {
		return campaignTransactions;
	}

	public String getSubSegment() {
		return subSegment;
	}

	public void setSubSegment(String subSegment) {
		this.subSegment = subSegment;
	}

	public String getNumberOfSubscribers() {
		return this.numberOfSubscribers;
	}

	public void setNumberOfSubscribers(String numberOfSubscribers) {
		this.numberOfSubscribers = numberOfSubscribers;
	}

	public String getAvAge() {
		return avAge;
	}

	public void setAvAge(String avAge) {
		this.avAge = avAge;
	}

	public String getAvgRevenuePerMonth() {
		return avgRevenuePerMonth;
	}

	public void setAvgRevenuePerMonth(String avgRevenuePerMonth) {
		this.avgRevenuePerMonth = avgRevenuePerMonth;
	}

	public String getAvgAccBalance() {
		return avgAccBalance;
	}

	public void setAvgAccBalance(String avgAccBalance) {
		this.avgAccBalance = avgAccBalance;
	}

	public String getMaxAgeInNetwork() {
		return maxAgeInNetwork;
	}

	public void setMaxAgeInNetwork(String maxAgeInNetwork) {
		this.maxAgeInNetwork = maxAgeInNetwork;
	}

	public String getMinAgeInNetwork() {
		return minAgeInNetwork;
	}

	public void setMinAgeInNetwork(String minAgeInNetwork) {
		this.minAgeInNetwork = minAgeInNetwork;
	}

	public String getAvgIntCallPerDay() {
		return avgIntCallPerDay;
	}

	public void setAvgIntCallPerDay(String avgIntCallPerDay) {
		this.avgIntCallPerDay = avgIntCallPerDay;
	}

	public String getAvgOutCallPerDay() {
		return avgOutCallPerDay;
	}

	public void setAvgOutCallPerDay(String avgOutCallPerDay) {
		this.avgOutCallPerDay = avgOutCallPerDay;
	}

	public String getAvgVolPerDay() {
		return avgVolPerDay;
	}

	public void setAvgVolPerDay(String avgVolPerDay) {
		this.avgVolPerDay = avgVolPerDay;
	}

	public String getAvgBalRecharge() {
		return avgBalRecharge;
	}

	public void setAvgBalRecharge(String avgBalRecharge) {
		this.avgBalRecharge = avgBalRecharge;
	}

	public String getAvgMaxRefill() {
		return avgMaxRefill;
	}

	public void setAvgMaxRefill(String avgMaxRefill) {
		this.avgMaxRefill = avgMaxRefill;
	}

	public String getAvgMinRefill() {
		return avgMinRefill;
	}

	public void setAvgMinRefill(String avgMinRefill) {
		this.avgMinRefill = avgMinRefill;
	}

	public String getAvgRechargeValuePerMon() {
		return avgRechargeValuePerMon;
	}

	public void setAvgRechargeValuePerMon(String avgRechargeValuePerMon) {
		this.avgRechargeValuePerMon = avgRechargeValuePerMon;
	}

	public String getAvgRechargesPerMonth() {
		return avgRechargesPerMonth;
	}

	public void setAvgRechargesPerMonth(String avgRechargesPerMonth) {
		this.avgRechargesPerMonth = avgRechargesPerMonth;
	}

	public String getAvgTimeBwRecharges() {
		return avgTimeBwRecharges;
	}

	public void setAvgTimeBwRecharges(String avgTimeBwRecharges) {
		this.avgTimeBwRecharges = avgTimeBwRecharges;
	}

	public String getAvgOutSmsPerDay() {
		return avgOutSmsPerDay;
	}

	public void setAvgOutSmsPerDay(String avgOutSmsPerDay) {
		this.avgOutSmsPerDay = avgOutSmsPerDay;
	}

	public String getSegment() {
		return segment;
	}

	public void setSegment(String segment) {
		this.segment = segment;
	}

	public String getRestSegment() {
		return restSegment;
	}

	public void setRestSegment(String restSegment) {
		this.restSegment = restSegment;
	}

	public String getAvgRespRate() {
		return avgRespRate;
	}

	public void setAvgRespRate(String avgRespRate) {
		this.avgRespRate = avgRespRate;
	}

	public String getInsightSmsCount() {
		return insightSmsCount;
	}

	public void setInsightSmsCount(String insightSmsCount) {
		this.insightSmsCount = insightSmsCount;
	}

	public String getMmsCount() {
		return mmsCount;
	}

	public void setMmsCount(String mmsCount) {
		this.mmsCount = mmsCount;
	}

	public String getEmailCount() {
		return emailCount;
	}

	public void setEmailCount(String emailCount) {
		this.emailCount = emailCount;
	}

	public String getUssdCount() {
		return ussdCount;
	}

	public void setUssdCount(String ussdCount) {
		this.ussdCount = ussdCount;
	}

	public String getAvgChannelResponse() {
		return avgChannelResponse;
	}

	public void setAvgChannelResponse(String avgChannelResponse) {
		this.avgChannelResponse = avgChannelResponse;
	}

	public String getSocialCount() {
		return socialCount;
	}

	public void setSocialCount(String socialCount) {
		this.socialCount = socialCount;
	}

	public String getAvgLocalCallPerDay() {
		return avgLocalCallPerDay;
	}

	public void setAvgLocalCallPerDay(String avgLocalCallPerDay) {
		this.avgLocalCallPerDay = avgLocalCallPerDay;
	}
	/*
	 * public String getCustomerProfiles() { return customerProfiles; }
	 * 
	 * public void setCustomerProfiles(String customerProfiles) {
	 * this.customerProfiles = customerProfiles; }
	 * 
	 * public String getPackagesInfo() { return packagesInfo; }
	 * 
	 * public void setPackagesInfo(String packagesInfo) { this.packagesInfo =
	 * packagesInfo; }
	 * 
	 * public String getTransactionsHistory() { return transactionsHistory; }
	 * 
	 * public void setTransactionsHistory(String transactionsHistory) {
	 * this.transactionsHistory = transactionsHistory; }
	 * 
	 * public String getCustomerProfile() { return customerProfile; }
	 * 
	 * public void setCustomerProfile(String customerProfile) { this.customerProfile
	 * = customerProfile; }
	 * 
	 * public String getDeviceProfile() { return deviceProfile; }
	 * 
	 * public void setDeviceProfile(String deviceProfile) { this.deviceProfile =
	 * deviceProfile; }
	 * 
	 * public String getLoyaltyAccount() { return loyaltyAccount; }
	 * 
	 * public void setLoyaltyAccount(String loyaltyAccount) { this.loyaltyAccount =
	 * loyaltyAccount; }
	 * 
	 * public String getUsageSummary() { return usageSummary; }
	 * 
	 * public void setUsageSummary(String usageSummary) { this.usageSummary =
	 * usageSummary; }
	 * 
	 * public String getLoyaltySummary() { return loyaltySummary; }
	 * 
	 * public void setLoyaltySummary(String loyaltySummary) { this.loyaltySummary =
	 * loyaltySummary; }
	 * 
	 * public String getCampaignTransactions() { return campaignTransactions; }
	 * 
	 * public void setCampaignTransactions(String campaignTransactions) {
	 * this.campaignTransactions = campaignTransactions; }
	 * 
	 */

}
