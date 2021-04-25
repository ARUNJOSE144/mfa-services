package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.model.FacebookCampaignDetailsDTO;
import com.sixdee.magik.services.model.FacebookCampaignMasterDTO;
import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;

public interface FacebookCampaigner {

	FacebookCampaignDetailsDTO manageCampaigns(FacebookCampaignMasterDTO masterDto, TargetingAudienceMasterDTO audienceMasterDto);

	String updateSchedule(SocialMediaScheduleMasterDTO scMasterDto, FacebookCampaignDetailsDTO detailsDto,
			String requestData);

	String updateFacebookAdSetStatus(FacebookCampaignDetailsDTO fbDto, String string);

}
