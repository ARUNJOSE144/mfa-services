package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignSegmentTypeTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.model.RuleTO;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */
public interface CampaignService {

	public List<CampaignTypeTO> getCampaignTypes();

	public CampaignMasterTO createCampaign(CampaignMasterTO masterTO);

	public CampaignMasterTO createTaskProfile(CampaignMasterTO masterTO);

	public List<CampaignMasterTO> getCampaignsList(int userId);

	public String deleteCampaign(int campaignId);

	public String copyCampaign(int campaignId);

	public CampaignMasterTO getCampaignDetails(int campaignId, int userId);

	public List<CampaignSegmentTypeTO> getCampaignSegmentTypes();

	public List<RuleTO> getRulesOfACampaign(int campaignId, int userId);

	public void copyCampaignWithSpecificRules(List<RuleTO> rulesList);

	public String updatePlayPauseStatus(int campaignId, int status);

	public List<CampaignMasterTO> getCampaignsWrtType(int typeId, String campaignName);
}
