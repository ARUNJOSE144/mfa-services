package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.GoogleCampaigningDAO;
import com.sixdee.magik.services.service.GoogleCampaigningService;

@Service
@Transactional
public class GoogleCampaigningServiceImpl implements GoogleCampaigningService {
	
	@Autowired
	GoogleCampaigningDAO googleCampaigningDAO;
	
	public void manageCampaigns() {
		
		googleCampaigningDAO.manageCampaigns();
		
	}

}
