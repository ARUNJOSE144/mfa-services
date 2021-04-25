package com.sixdee.magik.services.dao.impl;
/**
 * @author Amal A S
 * @category Scheduler for facebook campaigns
 * @date 23/06/2020
 * 
 */

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.facebook.ads.sdk.APIContext;
import com.facebook.ads.sdk.APIException;
import com.facebook.ads.sdk.Ad;
import com.facebook.ads.sdk.AdAccount;
import com.facebook.ads.sdk.AdCreative;
import com.facebook.ads.sdk.AdCreativeLinkData;
import com.facebook.ads.sdk.AdCreativeObjectStorySpec;
import com.facebook.ads.sdk.AdImage;
import com.facebook.ads.sdk.AdSet;
import com.facebook.ads.sdk.Campaign;
import com.facebook.ads.sdk.Campaign.EnumObjective;
import com.facebook.ads.sdk.Campaign.EnumStatus;
import com.facebook.ads.sdk.IDName;
import com.facebook.ads.sdk.Targeting;
import com.facebook.ads.sdk.TargetingGeoLocation;
import com.facebook.ads.sdk.TargetingGeoLocationRegion;
import com.sixdee.magik.services.dao.FacebookCampaigner;
import com.sixdee.magik.services.model.FacebookCampaignDetailsDTO;
import com.sixdee.magik.services.model.FacebookCampaignMasterDTO;
import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TargetingAudienceDTO;
import com.sixdee.magik.services.model.TargetingAudienceMappingDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;

@Service
public class FacebookCampaignerImpl implements FacebookCampaigner {

	@Autowired
	private Environment env;

	private static final Logger logger = Logger.getLogger(FacebookCampaignerImpl.class);

	public FacebookCampaignDetailsDTO manageCampaigns(FacebookCampaignMasterDTO masterDto,
			TargetingAudienceMasterDTO audienceMasterDto) {

		String campaignId = null, adsetId = null, creativeAdId = null, adId = null, imageHash = null;
		FacebookCampaignDetailsDTO fbCampDetails = null;

		try {

			// Create Campaigns
			logger.info("Going for create Campaign");
			campaignId = createFacebookCampaign(masterDto, audienceMasterDto);

			// Create Adsets
			if (campaignId != null) {
				logger.info("Going for create Adsets");
				adsetId = createFacebookAdsets(masterDto, audienceMasterDto, campaignId);
			}

			if (masterDto.getMediaPath() != null && !masterDto.getMediaPath().equals("")) {
				imageHash = uploadImage(masterDto);
			}

			// Create CreativeAd
			if (masterDto.getFacebookPage() != null && !masterDto.getFacebookPage().equals("")
					&& masterDto.getPostId() != null && !masterDto.getPostId().equals("")) {

				logger.info("Going for create AdCreative");
				creativeAdId = createFacebookCreativeAd(masterDto);
			} else {

				creativeAdId = createFbInstaCreativeAd(masterDto, imageHash);
			}

			// Create Ad
			if (adsetId != null && creativeAdId != null) {
				adId = createFacebookAd(masterDto, adsetId, creativeAdId);
			}

			if (campaignId != null && adsetId != null) {
				fbCampDetails = new FacebookCampaignDetailsDTO();
				fbCampDetails.setFbCampaignId(campaignId);
				fbCampDetails.setAdSetId(adsetId);
				fbCampDetails.setAdCreativeId(creativeAdId);
				fbCampDetails.setAdId(adId);
				fbCampDetails.setMagikCampaignId(masterDto.getFacebookCampaignMaster().getId());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return fbCampDetails;

	}

	private String createFbInstaCreativeAd(FacebookCampaignMasterDTO masterDto, String imageHash) {

		String creativeAdId = null;
		APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);

		try {

			AdCreativeObjectStorySpec adCreativeObjectStorySpec = new AdCreativeObjectStorySpec();
			adCreativeObjectStorySpec.setFieldPageId(masterDto.getFacebookPage());
			adCreativeObjectStorySpec.setFieldLinkData(new AdCreativeLinkData()
					.setFieldLink(masterDto.getUrl())
					.setFieldMessage(masterDto.getTitleName()).setFieldImageHash(imageHash)
					.setFieldDescription(masterDto.getTitleDescription()));

			AdCreative adCreative = new AdAccount(Long.parseLong(env.getProperty("facebook.appcode")), context)
					.createAdCreative().setName(masterDto.getFacebookCampaignMaster().getCampaignName())
					.setImageHash(imageHash).setObjectStorySpec(adCreativeObjectStorySpec).execute();

			creativeAdId = adCreative.getId();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return creativeAdId;
	}

	private String uploadImage(FacebookCampaignMasterDTO masterDto) {

		String imageHashId = null;

		APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);

		try {
			AdImage adImage = new AdAccount(Long.parseLong(env.getProperty("facebook.appcode")), context)
					.createAdImage().addUploadFile(masterDto.getFacebookCampaignMaster().getCampaignName(),
							new File(env.getProperty("file.upload.path") + masterDto.getMediaPath()))
					.execute();

			imageHashId = adImage.getFieldHash();

		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (APIException e) {
			e.printStackTrace();
		}

		return imageHashId;
	}

	private String createFacebookCampaign(FacebookCampaignMasterDTO masterDto,
			TargetingAudienceMasterDTO audienceMasterDto) {

		String campaignId = null;
		EnumStatus status = null;
		EnumObjective objective = null;
		List<Campaign.EnumSpecialAdCategories> categories = null;

		try {

			categories = new ArrayList<>();
			if (masterDto.getFbCategroy() != null && !masterDto.getFbCategroy().equals("")) {

				if (masterDto.getFbCategroy().equals("NONE")) {
					categories.add(Campaign.EnumSpecialAdCategories.VALUE_NONE);
				} else if (masterDto.getFbCategroy().equals("CREDIT")) {
					categories.add(Campaign.EnumSpecialAdCategories.VALUE_CREDIT);
				} else if (masterDto.getFbCategroy().equals("EMPLOYMENT")) {
					categories.add(Campaign.EnumSpecialAdCategories.VALUE_EMPLOYMENT);
				} else if (masterDto.getFbCategroy().equals("HOUSING")) {
					categories.add(Campaign.EnumSpecialAdCategories.VALUE_HOUSING);
				}

			} else {
				categories.add(Campaign.EnumSpecialAdCategories.VALUE_NONE);
			}

			if (masterDto.getFacebookCampaignMaster().getPlayPauseStatus() == 0) {
				status = EnumStatus.VALUE_PAUSED;
			}
			if (masterDto.getFacebookCampaignMaster().getPlayPauseStatus() == 1) {
				status = EnumStatus.VALUE_ACTIVE;
			}

			if (masterDto.getCampObjective() != null && !masterDto.getCampObjective().equals("")) {
				if (masterDto.getCampObjective().equals("VALUE_LINK_CLICKS")) {
					objective = EnumObjective.VALUE_LINK_CLICKS;
				}
				if (masterDto.getCampObjective().equals("VALUE_POST_ENGAGEMENT")) {
					objective = EnumObjective.VALUE_POST_ENGAGEMENT;
				}
			} else {
				objective = EnumObjective.VALUE_POST_ENGAGEMENT;
			}

			APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);

			Campaign acc = new AdAccount(Long.parseLong(env.getProperty("facebook.appcode")), context).createCampaign()
					.setSpecialAdCategories(categories).setName(masterDto.getFacebookCampaignMaster().getCampaignName())
					.setObjective(objective).setStatus(status).execute();

			campaignId = acc.getId();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return campaignId;
	}

	private String createFacebookAdsets(FacebookCampaignMasterDTO masterDto,
			TargetingAudienceMasterDTO audienceMasterDto, String campaignId) {

		Long appCode = Long.parseLong(env.getProperty("facebook.appcode"));

		List<String> countriesList = new ArrayList<>();
		List<IDName> interestList = new ArrayList<>();
		List<Long> gender = new ArrayList<>();
		List<IDName> industriesList = new ArrayList<>();
		List<String> userOs = new ArrayList<>();
		List<TargetingGeoLocationRegion> regions = new ArrayList<>();

		TargetingAudienceDTO dto = new TargetingAudienceDTO();
		com.facebook.ads.sdk.AdSet.EnumStatus status = null;

		String adsetId = null;

		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

		try {

			for (TargetingAudienceMappingDTO mapDto : audienceMasterDto.getMappingDetails()) {

				if (mapDto.getAttributeType().equalsIgnoreCase("COUNTRY")) {
					dto.setCountry(mapDto.getAttributeValue());
				}
				if (mapDto.getAttributeType().equalsIgnoreCase("MIN_AGE")) {
					dto.setMinAge(mapDto.getAttributeValue());
				}
				if (mapDto.getAttributeType().equalsIgnoreCase("MAX_AGE")) {
					dto.setMaxAge(mapDto.getAttributeValue());
				}
				
				if (mapDto.getAttributeType().equalsIgnoreCase("ESTIMATED_VALUE")) {
					dto.setEstimatedSize(mapDto.getAttributeValue());
				}

				if (mapDto.getAttributeType().equalsIgnoreCase("INTERESTS")) {
					IDName idDto = new IDName();
					idDto.setFieldId(mapDto.getAttributeValue().split("_")[0]);
					idDto.setFieldName(mapDto.getAttributeValue().split("_")[1]);
					interestList.add(idDto);
				}
				if (mapDto.getAttributeType().equalsIgnoreCase("INDUSTRIES")) {
					IDName idDto = new IDName();
					idDto.setFieldId(mapDto.getAttributeValue().split("_")[0]);
					idDto.setFieldName(mapDto.getAttributeValue().split("_")[1]);
					industriesList.add(idDto);
				}

				if (mapDto.getAttributeType().equalsIgnoreCase("GENDER")) {
					gender.add(Long.parseLong(mapDto.getAttributeValue()));

				}
				if (mapDto.getAttributeType().equalsIgnoreCase("OS")) {
					userOs.add(mapDto.getAttributeValue());

				}
				if (mapDto.getAttributeType().equalsIgnoreCase("REGIONS")) {
					TargetingGeoLocationRegion idDto = new TargetingGeoLocationRegion();
					idDto.setFieldKey(mapDto.getAttributeValue());
					regions.add(idDto);
				}

			}

			for (String coun : dto.getCountry().split(",")) {
				countriesList.add(coun);
			}

			if (masterDto.getFacebookCampaignMaster().getPlayPauseStatus() == 0) {
				status = com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_PAUSED;
			}
			if (masterDto.getFacebookCampaignMaster().getPlayPauseStatus() == 1) {
				status = com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_ACTIVE;
			}

			APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);
			AdSet adSet = new AdAccount(appCode, context).createAdSet()
					.setName(masterDto.getFacebookCampaignMaster().getCampaignName() + "_ADSET")
					.setOptimizationGoal(AdSet.EnumOptimizationGoal.VALUE_REACH)
					.setBillingEvent(AdSet.EnumBillingEvent.VALUE_IMPRESSIONS)
					.setBidAmount(masterDto.getBid() != null ? masterDto.getBid() : null)
					.setDailyBudget(masterDto.getTotalBudget() != null ? masterDto.getTotalBudget() : "0")
					.setLifetimeBudget(masterDto.getLifeTimeBudget() != null ? masterDto.getLifeTimeBudget() : "0")
					.setCampaignId(campaignId)
					.setTargeting(new Targeting()
							.setFieldGeoLocations(new TargetingGeoLocation().setFieldCountries(
									(countriesList != null && countriesList.size() > 0) ? countriesList : null))
							.setFieldIndustries(
									(industriesList != null && industriesList.size() > 0) ? industriesList : null)
							.setFieldGenders((gender != null && gender.size() > 0) ? gender : null)
							.setFieldAgeMin(Long.parseLong(dto.getMinAge()))
							.setFieldAgeMax(Long.parseLong(dto.getMaxAge())).setFieldInterests(interestList)
							.setFieldPublisherPlatforms(Arrays.asList(audienceMasterDto.getMediaType()))
							.setFieldUserOs(userOs))
					.setStartTime(
							dateformat.format(masterDto.getFacebookCampaignMaster().getStartDate()) + "T00:00:00+0530")
					.setEndTime(
							dateformat.format(masterDto.getFacebookCampaignMaster().getEndDate()) + "T00:00:00+0530")
					.setStatus(status).execute();

			adsetId = adSet.getId();

		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return adsetId;
	}

	private String createFacebookCreativeAd(FacebookCampaignMasterDTO masterDto) {

		String adCreativeId = null;

		try {

			APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);

			AdCreative acc = new AdAccount(Long.parseLong(env.getProperty("facebook.appcode")), context)
					.createAdCreative()
					.setName(masterDto.getFacebookCampaignMaster().getCampaignName() + "_AD_CREATIVE")
					.setObjectStoryId(masterDto.getFacebookPage() + "_" + masterDto.getPostId()).execute();

			adCreativeId = acc.getId();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return adCreativeId;
	}

	private String createFacebookAd(FacebookCampaignMasterDTO masterDto, String adsetId, String creativeAdId) {

		String adId = null;

		try {

			APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);

			Ad ad = new AdAccount(Long.parseLong(env.getProperty("facebook.appcode")), context).createAd()
					.setName(masterDto.getFacebookCampaignMaster().getCampaignName() + "_Ad")
					.setAdsetId(Long.parseLong(adsetId)).setCreative(new AdCreative().setFieldId(creativeAdId))
					.setStatus(Ad.EnumStatus.VALUE_PAUSED).execute();

			adId = ad.getId();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return adId;
	}

	public String updateSchedule(SocialMediaScheduleMasterDTO scMasterDto, FacebookCampaignDetailsDTO detailsDto,
			String requestData) {

		String response = null;

		try {

			APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);
			AdSet resp = new AdSet(detailsDto.getAdSetId(), context).update().setPacingType("[\"day_parting\"]")
					.setAdsetSchedule(requestData).setStatus(com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_ACTIVE)
					.execute();

			if (resp.getRawResponse().equalsIgnoreCase("{\"success\":true}")) {
				response = "SC0000";
			} else {
				response = "SC0001";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return response;
	}

	public String updateFacebookAdSetStatus(FacebookCampaignDetailsDTO fbDto, String status) {

		String response = null;
		com.facebook.ads.sdk.AdSet.EnumStatus fbStatus = null;

		try {

			if (status != null) {

				if (status.equalsIgnoreCase("PAUSE")) {
					fbStatus = com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_PAUSED;
				} else if (status.equalsIgnoreCase("ACTIVE")) {
					fbStatus = com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_ACTIVE;
				} else if (status.equalsIgnoreCase("DELETE")) {
					fbStatus = com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_DELETED;
				}

			} else {
				fbStatus = com.facebook.ads.sdk.AdSet.EnumStatus.VALUE_PAUSED;
			}

			APIContext context = new APIContext(env.getProperty("facebook.accesstoken")).enableDebug(true);
			AdSet resp = new AdSet(fbDto.getAdSetId(), context).update().setStatus(fbStatus).execute();

			if (resp.getRawResponse().equalsIgnoreCase("{\"success\":true}")) {
				response = "SC0000";
			} else {
				response = "SC0001";
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return response;
	}

}
