package com.sixdee.magik.services.dao;

import java.util.List;

/**
 * @author davis.nayak
 * @Date : June, 2020
 *
 */

import com.sixdee.magik.services.model.GetCampaigns;
import com.sixdee.magik.services.model.GetSMSMenusTO;
import com.sixdee.magik.services.model.USSDRequest;
import com.sixdee.magik.services.model.USSDResponse;

public interface USSDTreeDAO {

	
	  public List<GetCampaigns> GetCategories(USSDRequest request);
	  
	  public List<GetSMSMenusTO> GetSMSMenu(USSDRequest request);
	  
	  public USSDResponse CreateMessage(USSDRequest request);
	  
	  public USSDResponse DelMenu(USSDRequest request);
	  
	  public USSDResponse EditMessage(USSDRequest request);
	  
	  public USSDResponse GetLang(USSDRequest request);
	  
	  public USSDResponse GetMessage(USSDRequest request);
	  
	  public USSDResponse CreateCamp(USSDRequest request);
	  
	  public USSDResponse EditCamp(USSDRequest request);
	  
	  public USSDResponse DeleteCamp(USSDRequest request);
	 
}
