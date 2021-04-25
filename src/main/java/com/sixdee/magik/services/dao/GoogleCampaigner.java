package com.sixdee.magik.services.dao;
/**
 * @author Amal A S
 * @category Campaigner for Google Campaigns
 * @date 10/07/2020
 * 
 */

import com.sixdee.magik.services.model.GoogleAdsMaster;
import com.sixdee.magik.services.model.GoogleCampaignDetailsDTO;
import com.sixdee.magik.services.model.GoogleCampaignMasterDTO;
import com.sixdee.magik.services.model.TargetingAudienceMasterDTO;

public interface GoogleCampaigner {

	GoogleCampaignDetailsDTO manageCampaign(GoogleCampaignMasterDTO masterDto, GoogleAdsMaster adsMaster,
			TargetingAudienceMasterDTO targetMaster);

}
