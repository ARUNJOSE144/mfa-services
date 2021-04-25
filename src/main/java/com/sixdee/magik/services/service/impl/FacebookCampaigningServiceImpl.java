package com.sixdee.magik.services.service.impl;
/**
 * @author Amal A S
 * @category Scheduler for facebook campaigns
 * @date 23/06/2020
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.FacebookCampaigningDAO;
import com.sixdee.magik.services.service.FacebookCampaigningService;

@Service
@Transactional
public class FacebookCampaigningServiceImpl implements FacebookCampaigningService {

	@Autowired
	FacebookCampaigningDAO facebookCampaigningDao;

	public void manageCampaigns() {

		facebookCampaigningDao.manageFacebookCampaigns();
	}

	public void scheduleCampaigns() {
		facebookCampaigningDao.scheduleFacebookCampaigns();
	}

}
