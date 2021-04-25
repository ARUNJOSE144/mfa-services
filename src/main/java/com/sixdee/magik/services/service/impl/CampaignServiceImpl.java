package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CampaignDAO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignSegmentTypeTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.model.RuleTO;
import com.sixdee.magik.services.service.CampaignService;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class CampaignServiceImpl implements CampaignService {

	@Autowired
	CampaignDAO campaignDAO;

	@Override
	public List<CampaignTypeTO> getCampaignTypes() {
		// TODO Auto-generated method stub
		return campaignDAO.getCampaignTypes();
	}

	@Override
	public CampaignMasterTO createCampaign(CampaignMasterTO masterTO) {
		// TODO Auto-generated method stub
		return campaignDAO.createCampaign(masterTO);
	}

	@Override
	public CampaignMasterTO createTaskProfile(CampaignMasterTO masterTO) {
		// TODO Auto-generated method stub
		return campaignDAO.createTaskProfile(masterTO);
	}

	@Override
	public List<CampaignMasterTO> getCampaignsList(int userId) {
		// TODO Auto-generated method stub
		return campaignDAO.getCampaignsList(userId);
	}

	@Override
	public String deleteCampaign(int campaignId) {
		return campaignDAO.deleteCampaign(campaignId);

	}

	@Override
	public String copyCampaign(int campaignId) {
		return campaignDAO.copyCampaign(campaignId);

	}

	@Override
	public CampaignMasterTO getCampaignDetails(int campaignId,int userId) {
		// TODO Auto-generated method stub
		return campaignDAO.getCampaignDetails(campaignId,userId);
	}

	@Override
	public List<CampaignSegmentTypeTO> getCampaignSegmentTypes() {
		// TODO Auto-generated method stub
		return campaignDAO.getCampaignSegmentTypes();
	}

	@Override
	public List<RuleTO> getRulesOfACampaign(int campaignId, int userId) {
		// TODO Auto-generated method stub
		return campaignDAO.getRulesOfACampaign(campaignId, userId);
	}

	@Override
	public void copyCampaignWithSpecificRules(List<RuleTO> rulesList) {
		// TODO Auto-generated method stub
		campaignDAO.copyCampaignWithSpecificRules(rulesList);

	}

	@Override
	public String updatePlayPauseStatus(int campaignId, int status) {
		// TODO Auto-generated method stub
		return campaignDAO.updatePlayPauseStatus(campaignId, status);
	}

	@Override
	public List<CampaignMasterTO> getCampaignsWrtType(int typeId, String campaignName) {
		// TODO Auto-generated method stub
		return campaignDAO.getCampaignsWrtType(typeId, campaignName);
	}

}
