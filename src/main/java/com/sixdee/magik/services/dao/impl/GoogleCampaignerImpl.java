package com.sixdee.magik.services.dao.impl;

import static com.google.api.ads.common.lib.utils.Builder.DEFAULT_CONFIGURATION_FILENAME;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.net.URLEncoder;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import com.google.api.ads.adwords.axis.factory.AdWordsServices;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroup;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupAd;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupAdOperation;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupAdReturnValue;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupAdRotationMode;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupAdServiceInterface;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupAdStatus;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupCriterion;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupCriterionOperation;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupOperation;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupReturnValue;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupServiceInterface;
import com.google.api.ads.adwords.axis.v201809.cm.AdGroupStatus;
import com.google.api.ads.adwords.axis.v201809.cm.AdRotationMode;
import com.google.api.ads.adwords.axis.v201809.cm.AdvertisingChannelType;
import com.google.api.ads.adwords.axis.v201809.cm.ApiError;
import com.google.api.ads.adwords.axis.v201809.cm.ApiException;
import com.google.api.ads.adwords.axis.v201809.cm.BiddableAdGroupCriterion;
import com.google.api.ads.adwords.axis.v201809.cm.BiddingStrategyConfiguration;
import com.google.api.ads.adwords.axis.v201809.cm.BiddingStrategyType;
import com.google.api.ads.adwords.axis.v201809.cm.Bids;
import com.google.api.ads.adwords.axis.v201809.cm.Budget;
import com.google.api.ads.adwords.axis.v201809.cm.BudgetBudgetDeliveryMethod;
import com.google.api.ads.adwords.axis.v201809.cm.BudgetOperation;
import com.google.api.ads.adwords.axis.v201809.cm.BudgetServiceInterface;
import com.google.api.ads.adwords.axis.v201809.cm.Campaign;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignCriterion;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignCriterionOperation;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignOperation;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignReturnValue;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignServiceInterface;
import com.google.api.ads.adwords.axis.v201809.cm.CampaignStatus;
import com.google.api.ads.adwords.axis.v201809.cm.CpcBid;
import com.google.api.ads.adwords.axis.v201809.cm.Criterion;
import com.google.api.ads.adwords.axis.v201809.cm.CriterionTypeGroup;
import com.google.api.ads.adwords.axis.v201809.cm.ExpandedTextAd;
import com.google.api.ads.adwords.axis.v201809.cm.FrequencyCap;
import com.google.api.ads.adwords.axis.v201809.cm.GeoTargetTypeSetting;
import com.google.api.ads.adwords.axis.v201809.cm.GeoTargetTypeSettingPositiveGeoTargetType;
import com.google.api.ads.adwords.axis.v201809.cm.Keyword;
import com.google.api.ads.adwords.axis.v201809.cm.KeywordMatchType;
import com.google.api.ads.adwords.axis.v201809.cm.Language;
import com.google.api.ads.adwords.axis.v201809.cm.Level;
import com.google.api.ads.adwords.axis.v201809.cm.Location;
import com.google.api.ads.adwords.axis.v201809.cm.ManualCpcBiddingScheme;
import com.google.api.ads.adwords.axis.v201809.cm.Money;
import com.google.api.ads.adwords.axis.v201809.cm.NetworkSetting;
import com.google.api.ads.adwords.axis.v201809.cm.Operator;
import com.google.api.ads.adwords.axis.v201809.cm.Setting;
import com.google.api.ads.adwords.axis.v201809.cm.TargetingSetting;
import com.google.api.ads.adwords.axis.v201809.cm.TargetingSettingDetail;
import com.google.api.ads.adwords.axis.v201809.cm.TimeUnit;
import com.google.api.ads.adwords.axis.v201809.cm.UrlList;
import com.google.api.ads.adwords.axis.v201809.cm.UserStatus;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServicesInterface;
import com.google.api.ads.common.lib.auth.OfflineCredentials;
import com.google.api.ads.common.lib.auth.OfflineCredentials.Api;
import com.google.api.ads.common.lib.conf.ConfigurationLoadException;
import com.google.api.ads.common.lib.exception.OAuthException;
import com.google.api.ads.common.lib.exception.ValidationException;
import com.google.api.client.auth.oauth2.Credential;
import com.sixdee.magik.services.dao.GoogleCampaigner;
import com.sixdee.magik.services.model.GoogleAdsKeywordMapping;
import com.sixdee.magik.services.model.GoogleAdsMaster;
import com.sixdee.magik.services.model.GoogleCampaignDetailsDTO;
import com.sixdee.magik.services.model.GoogleCampaignKeywordDetailsDTO;
import com.sixdee.magik.services.model.GoogleCampaignMasterDTO;
import com.sixdee.magik.services.model.TargetingAudienceMappingDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;

@Service
public class GoogleCampaignerImpl implements GoogleCampaigner {

	public GoogleCampaignDetailsDTO manageCampaign(GoogleCampaignMasterDTO masterDto, GoogleAdsMaster adsMaster,
			TargetingAudienceMasterDTO targetMaster) {

		AdWordsSession session = null;
		GoogleCampaignDetailsDTO detailsDto = null;

		try {
			// Generate a refreshable OAuth2 credential.
			Credential oAuth2Credential = new OfflineCredentials.Builder().forApi(Api.ADWORDS).fromFile().build()
					.generateCredential();

			// Construct an AdWordsSession.
			session = new AdWordsSession.Builder().fromFile().withOAuth2Credential(oAuth2Credential).build();
		} catch (ConfigurationLoadException cle) {
			System.err.printf("Failed to load configuration from the %s file. Exception: %s%n",
					DEFAULT_CONFIGURATION_FILENAME, cle);
		} catch (ValidationException ve) {
			System.err.printf("Invalid configuration in the %s file. Exception: %s%n", DEFAULT_CONFIGURATION_FILENAME,
					ve);
		} catch (OAuthException oe) {
			System.err.printf(
					"Failed to create OAuth credentials. Check OAuth settings in the %s file. " + "Exception: %s%n",
					DEFAULT_CONFIGURATION_FILENAME, oe);
		}

		AdWordsServicesInterface adWordsServices = AdWordsServices.getInstance();

		try {

			String adGroupdId = null, adId = null;
			detailsDto = new GoogleCampaignDetailsDTO();

			String campaignId = createCampaigns(adWordsServices, session, masterDto);

			if (campaignId != null) {

				detailsDto.setAdCampaignId(campaignId);

				adGroupdId = createadGroups(adWordsServices, session, masterDto, adsMaster, campaignId);

				createTargetingDetails(adWordsServices, session, masterDto, adsMaster, campaignId, targetMaster);

			}

			if (adGroupdId != null) {

				detailsDto.setAdGroupId(adGroupdId);

				adId = createAds(adWordsServices, session, masterDto, adsMaster, adGroupdId);
				System.out.println("Ad id : " + adId);

				detailsDto.setAdId(adId);

				detailsDto
						.setKeywordDetails(createAdKeyword(adWordsServices, session, masterDto, adsMaster, adGroupdId));
			}

		} catch (ApiException apiException) {

			System.err.println("Request failed due to ApiException. Underlying ApiErrors:");
			if (apiException.getErrors() != null) {
				int i = 0;
				for (ApiError apiError : apiException.getErrors()) {
					System.err.printf("  Error %d: %s%n", i++, apiError);
				}
			}
		} catch (RemoteException re) {
			System.err.printf("Request failed unexpectedly due to RemoteException: %s%n", re);
		}

		return detailsDto;

	}

	private void createTargetingDetails(AdWordsServicesInterface adWordsServices, AdWordsSession session,
			GoogleCampaignMasterDTO masterDto, GoogleAdsMaster adsMaster, String campaignId,
			TargetingAudienceMasterDTO targetMaster) throws RemoteException {

		// Get the CampaignService.
		CampaignCriterionServiceInterface campaignCriterionService = adWordsServices.get(session,
				CampaignCriterionServiceInterface.class);

		
		List<Criterion> criteria = new ArrayList<>();
		
		for (TargetingAudienceMappingDTO mapDto : targetMaster.getMappingDetails()) {
			
			if (mapDto.getAttributeType().equalsIgnoreCase("COUNTRY")) {
				Location location = new Location();
				location.setId(Long.parseLong(mapDto.getAttributeValue()));
				criteria.add(location);
			}
			
			if (mapDto.getAttributeType().equalsIgnoreCase("LANGUAGE")) {
				Language language = new Language();
				language.setId(Long.parseLong(mapDto.getAttributeValue()));
				criteria.add(language);
			}
			
			/*if (mapDto.getAttributeType().equalsIgnoreCase("GENDER")) {
				Gender gender = new Gender();
				gender.setId(Long.parseLong(mapDto.getAttributeValue()));
				criteria.add(gender);
			}
			
			if (mapDto.getAttributeType().equalsIgnoreCase("MIN_AGE")) {
				AgeRange ageRange = new AgeRange();
				ageRange.setId(Long.parseLong(mapDto.getAttributeValue()));
				criteria.add(ageRange);
			}*/
			
		}

		// Create operations to add each of the criteria above.
		List<CampaignCriterionOperation> operations = new ArrayList<>();
		for (Criterion criterion : criteria) {
			CampaignCriterionOperation operation = new CampaignCriterionOperation();
			CampaignCriterion campaignCriterion = new CampaignCriterion();
			campaignCriterion.setCampaignId(Long.parseLong(campaignId));
			campaignCriterion.setCriterion(criterion);
			operation.setOperand(campaignCriterion);
			operation.setOperator(Operator.ADD);
			operations.add(operation);
		}


		CampaignCriterionReturnValue result = campaignCriterionService
				.mutate(operations.toArray(new CampaignCriterionOperation[operations.size()]));

		// Display campaigns.
		for (CampaignCriterion campaignCriterion : result.getValue()) {
			System.out.printf(
					"Campaign criterion with campaign ID %d, criterion ID %d, " + "and type '%s' was added.%n",
					campaignCriterion.getCampaignId(), campaignCriterion.getCriterion().getId(),
					campaignCriterion.getCriterion().getCriterionType());
		}

	}

	private List<GoogleCampaignKeywordDetailsDTO> createAdKeyword(AdWordsServicesInterface adWordsServices,
			AdWordsSession session, GoogleCampaignMasterDTO masterDto, GoogleAdsMaster adsMaster, String adGroupdId)
			throws RemoteException {

		List<GoogleCampaignKeywordDetailsDTO> keywordList = null;

		try {

			keywordList = new ArrayList<>();

			// Get the AdGroupCriterionService.
			AdGroupCriterionServiceInterface adGroupCriterionService = adWordsServices.get(session,
					AdGroupCriterionServiceInterface.class);

			for (GoogleAdsKeywordMapping keyMap : adsMaster.getMappingDetails()) {

				GoogleCampaignKeywordDetailsDTO dto = new GoogleCampaignKeywordDetailsDTO();

				Keyword keyword = new Keyword();
				keyword.setText(keyMap.getKeyword());
				if (keyMap.getKeywordType().equals("EXACT")) {
					keyword.setMatchType(KeywordMatchType.EXACT);
				} else {
					keyword.setMatchType(KeywordMatchType.BROAD);
				}

				dto.setKeywordId(keyMap.getId());

				// Create biddable ad group criterion.
				BiddableAdGroupCriterion keywordBiddableAdGroupCriterion1 = new BiddableAdGroupCriterion();
				keywordBiddableAdGroupCriterion1.setAdGroupId(Long.parseLong(adGroupdId));
				keywordBiddableAdGroupCriterion1.setCriterion(keyword);

				// You can optionally provide these field(s).
				keywordBiddableAdGroupCriterion1.setUserStatus(UserStatus.PAUSED);

				String encodedFinalUrl = String.format(adsMaster.getUrl(),
						URLEncoder.encode(keyword.getText(), UTF_8.name()));
				keywordBiddableAdGroupCriterion1.setFinalUrls(new UrlList(new String[] { encodedFinalUrl }));

				BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
				CpcBid bid = new CpcBid();
				bid.setBid(new Money(null, Long.parseLong(adsMaster.getBidAmount())));
				biddingStrategyConfiguration.setBids(new Bids[] { bid });
				keywordBiddableAdGroupCriterion1.setBiddingStrategyConfiguration(biddingStrategyConfiguration);

				// Create operations.
				AdGroupCriterionOperation keywordAdGroupCriterionOperation1 = new AdGroupCriterionOperation();
				keywordAdGroupCriterionOperation1.setOperand(keywordBiddableAdGroupCriterion1);
				keywordAdGroupCriterionOperation1.setOperator(Operator.ADD);

				AdGroupCriterionOperation[] operations = new AdGroupCriterionOperation[] {
						keywordAdGroupCriterionOperation1 };

				// Add keywords.
				AdGroupCriterionReturnValue result = adGroupCriterionService.mutate(operations);

				// Display results.
				for (AdGroupCriterion adGroupCriterionResult : result.getValue()) {
					dto.setAdGroupId(adGroupdId);
					dto.setAdKeywordId(String.valueOf(adGroupCriterionResult.getCriterion().getId()));

				}

				keywordList.add(dto);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return keywordList;
	}

	private String createAds(AdWordsServicesInterface adWordsServices, AdWordsSession session,
			GoogleCampaignMasterDTO masterDto, GoogleAdsMaster adsMaster, String adGroupdId) throws RemoteException {

		String[] adId = new String[1];

		// Get the AdGroupAdService.
		AdGroupAdServiceInterface adGroupAdService = adWordsServices.get(session, AdGroupAdServiceInterface.class);

		List<AdGroupAdOperation> operations = new ArrayList<>();

		// Create expanded text ad.
		ExpandedTextAd expandedTextAd = new ExpandedTextAd();
		expandedTextAd.setHeadlinePart1(adsMaster.getHeadLine_1());
		expandedTextAd.setHeadlinePart2(adsMaster.getHeadLine_2());
		expandedTextAd.setHeadlinePart3(adsMaster.getHeadLine_3());
		expandedTextAd.setDescription(adsMaster.getDescription_1());
		expandedTextAd.setDescription2(adsMaster.getDescription_2());
		expandedTextAd.setFinalUrls(new String[] { adsMaster.getUrl() });

		// Create ad group ad.
		AdGroupAd expandedTextAdGroupAd = new AdGroupAd();
		expandedTextAdGroupAd.setAdGroupId(Long.parseLong(adGroupdId));
		expandedTextAdGroupAd.setAd(expandedTextAd);

		// Optional: set the status.
		expandedTextAdGroupAd.setStatus(AdGroupAdStatus.PAUSED);

		// Create the operation.
		AdGroupAdOperation adGroupAdOperation = new AdGroupAdOperation();
		adGroupAdOperation.setOperand(expandedTextAdGroupAd);
		adGroupAdOperation.setOperator(Operator.ADD);

		operations.add(adGroupAdOperation);

		// Add ads.
		AdGroupAdReturnValue result = adGroupAdService
				.mutate(operations.toArray(new AdGroupAdOperation[operations.size()]));

		// Display ads.
		Arrays.stream(result.getValue()).map(adGroupAdResult -> (ExpandedTextAd) adGroupAdResult.getAd())
				.forEach(newAd -> {
					adId[0] = String.valueOf(newAd.getId());
				});

		return adId[0];
	}

	private String createadGroups(AdWordsServicesInterface adWordsServices, AdWordsSession session,
			GoogleCampaignMasterDTO masterDto, GoogleAdsMaster adsMaster, String campaignId) throws RemoteException {

		String adGroupId = null;

		// Get the AdGroupService.
		AdGroupServiceInterface adGroupService = adWordsServices.get(session, AdGroupServiceInterface.class);

		// Create ad group.
		AdGroup adGroup = new AdGroup();
		adGroup.setName(
				masterDto.getGoogleCampaignMaster().getCampaignName() + "_AdGroup");
		adGroup.setStatus(AdGroupStatus.ENABLED);
		adGroup.setCampaignId(Long.parseLong(campaignId));

		// Optional settings.

		// Targeting restriction settings. Depending on the criterionTypeGroup
		// value, most TargetingSettingDetail only affect Display campaigns.
		// However, the USER_INTEREST_AND_LIST value works for RLSA campaigns -
		// Search campaigns targeting using a remarketing list.
		TargetingSetting targeting = new TargetingSetting();

		// Restricting to serve ads that match your ad group placements.
		// This is equivalent to choosing "Target and bid" in the UI.
		TargetingSettingDetail placements = new TargetingSettingDetail();
		placements.setCriterionTypeGroup(CriterionTypeGroup.PLACEMENT);
		placements.setTargetAll(Boolean.FALSE);

		// Using your ad group verticals only for bidding. This is equivalent
		// to choosing "Bid only" in the UI.
		TargetingSettingDetail verticals = new TargetingSettingDetail();
		verticals.setCriterionTypeGroup(CriterionTypeGroup.VERTICAL);
		verticals.setTargetAll(Boolean.TRUE);

		targeting.setDetails(new TargetingSettingDetail[] { placements, verticals });
		adGroup.setSettings(new Setting[] { targeting });

		// Set the rotation mode.
		AdGroupAdRotationMode rotationMode = new AdGroupAdRotationMode(AdRotationMode.OPTIMIZE);
		adGroup.setAdGroupAdRotationMode(rotationMode);

		// Create ad group bid.
		BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
		Money cpcBidMoney = new Money();
		cpcBidMoney.setMicroAmount(Long.parseLong(masterDto.getBudgetAmount()));
		CpcBid bid = new CpcBid();
		bid.setBid(cpcBidMoney);
		biddingStrategyConfiguration.setBids(new Bids[] { bid });
		adGroup.setBiddingStrategyConfiguration(biddingStrategyConfiguration);

		// Create operations.
		AdGroupOperation operation = new AdGroupOperation();
		operation.setOperand(adGroup);
		operation.setOperator(Operator.ADD);

		AdGroupOperation[] operations = new AdGroupOperation[] { operation/* , operation2 */ };

		// Add ad groups.
		AdGroupReturnValue result = adGroupService.mutate(operations);

		// Display new ad groups.
		for (AdGroup adGroupResult : result.getValue()) {
			System.out.printf("Ad group with name '%s' and ID %d was added.%n", adGroupResult.getName(),
					adGroupResult.getId());
			adGroupId = String.valueOf(adGroupResult.getId());
		}

		return adGroupId;
	}

	private String createCampaigns(AdWordsServicesInterface adWordsServices, AdWordsSession session,
			GoogleCampaignMasterDTO masterDto) throws RemoteException {

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
		String campaignId = null;

		// Get the BudgetService.
		BudgetServiceInterface budgetService = adWordsServices.get(session, BudgetServiceInterface.class);

		// Create a budget, which can be shared by multiple campaigns.
		Budget sharedBudget = new Budget();
		sharedBudget.setName(masterDto.getGoogleCampaignMaster().getCampaignName());
		Money budgetAmount = new Money();
		budgetAmount.setMicroAmount(Long.parseLong(masterDto.getBudgetAmount()));
		sharedBudget.setAmount(budgetAmount);
		sharedBudget.setDeliveryMethod(BudgetBudgetDeliveryMethod.STANDARD);

		BudgetOperation budgetOperation = new BudgetOperation();
		budgetOperation.setOperand(sharedBudget);
		budgetOperation.setOperator(Operator.ADD);

		// Add the budget
		Long budgetId = budgetService.mutate(new BudgetOperation[] { budgetOperation }).getValue(0).getBudgetId();

		// Get the CampaignService.
		CampaignServiceInterface campaignService = adWordsServices.get(session, CampaignServiceInterface.class);

		// Create campaign.
		Campaign campaign = new Campaign();
		campaign.setName(masterDto.getGoogleCampaignMaster().getCampaignName());
		campaign.setStatus(CampaignStatus.PAUSED);

		BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
		biddingStrategyConfiguration.setBiddingStrategyType(BiddingStrategyType.MANUAL_CPC);

		// You can optionally provide a bidding scheme in place of the type.
		ManualCpcBiddingScheme cpcBiddingScheme = new ManualCpcBiddingScheme();
		biddingStrategyConfiguration.setBiddingScheme(cpcBiddingScheme);

		campaign.setBiddingStrategyConfiguration(biddingStrategyConfiguration);

		// You can optionally provide these field(s).
		campaign.setStartDate(new DateTime().plusDays(1)
				.toString(dateformat.format(masterDto.getGoogleCampaignMaster().getStartDate())));
		campaign.setEndDate(new DateTime().plusDays(30)
				.toString(dateformat.format(masterDto.getGoogleCampaignMaster().getEndDate())));
		campaign.setFrequencyCap(new FrequencyCap(5L, TimeUnit.DAY, Level.ADGROUP));

		// Only the budgetId should be sent, all other fields will be ignored by
		// CampaignService.
		Budget budget = new Budget();
		budget.setBudgetId(budgetId);
		campaign.setBudget(budget);

		if (masterDto.getChannelType().equalsIgnoreCase("SEARCH")) {
			campaign.setAdvertisingChannelType(AdvertisingChannelType.SEARCH);
		} else if (masterDto.getChannelType().equalsIgnoreCase("DISPLAY")) {
			campaign.setAdvertisingChannelType(AdvertisingChannelType.DISPLAY);
		} else if (masterDto.getChannelType().equalsIgnoreCase("SHOPPING")) {
			campaign.setAdvertisingChannelType(AdvertisingChannelType.SHOPPING);
		}

		// Set the campaign network options to Search and Search Network.
		NetworkSetting networkSetting = new NetworkSetting();
		networkSetting.setTargetGoogleSearch(true);
		networkSetting.setTargetSearchNetwork(true);
		networkSetting.setTargetContentNetwork(false);
		networkSetting.setTargetPartnerSearchNetwork(false);
		campaign.setNetworkSetting(networkSetting);

		// Set options that are not required.
		GeoTargetTypeSetting geoTarget = new GeoTargetTypeSetting();
		geoTarget.setPositiveGeoTargetType(GeoTargetTypeSettingPositiveGeoTargetType.DONT_CARE);
		campaign.setSettings(new Setting[] { geoTarget });

		// Create operations.
		CampaignOperation operation = new CampaignOperation();
		operation.setOperand(campaign);
		operation.setOperator(Operator.ADD);

		CampaignOperation[] operations = new CampaignOperation[] { operation };

		// Add campaigns.
		CampaignReturnValue result = campaignService.mutate(operations);

		// Display campaigns.
		for (Campaign campaignResult : result.getValue()) {
			System.out.printf("Campaign with name '%s' and ID %d was added.%n", campaignResult.getName(),
					campaignResult.getId());
			campaignId = String.valueOf(campaignResult.getId());
		}

		return campaignId;
	}

}
