package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.USSDRequest;
import com.sixdee.magik.services.model.USSDResponse;

public interface USSDTreeService {

	public USSDResponse processRequest(USSDRequest request);
	
	//-----------------------------------------------
	/*
	 * public List<GetCampaigns> getCampaignDetails();
	 * 
	 * public List<GetSMSMenusTO> GetSMSMenu(GetCampaigns getCampaigns);
	 */
}
