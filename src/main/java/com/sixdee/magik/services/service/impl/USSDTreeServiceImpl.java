package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.USSDTreeDAO;
import com.sixdee.magik.services.model.USSDRequest;
import com.sixdee.magik.services.model.USSDResponse;
import com.sixdee.magik.services.service.USSDTreeService;


/**
 * @author davis.nayak
 * @Date : June, 2020
 *
 */


@Service
public class USSDTreeServiceImpl implements USSDTreeService {

	@Autowired
	USSDTreeDAO ussdTreeDao;

	
	  @Override public USSDResponse processRequest(USSDRequest request) {
	  
	  USSDResponse ussdResponse = null;
	  
	  if (true) {
	  
	  ussdResponse = new USSDResponse();
	  
	  if(request.getKeyword().equalsIgnoreCase("GetCamp")) {
	  
	  ussdResponse.setCampaigns(ussdTreeDao.GetCategories(request));
	  
	  return ussdResponse;
	  
	  }else if(request.getKeyword().equalsIgnoreCase("newCategory")) {
		  
	  return  ussdResponse = ussdTreeDao.CreateCamp(request);
	   
	  }else if(request.getKeyword().equalsIgnoreCase("modifyCategory")) {
		  
	  return  ussdResponse = ussdTreeDao.EditCamp(request);
	   
	  }else if(request.getKeyword().equalsIgnoreCase("deleteCategory")) {
		  
	  return  ussdResponse = ussdTreeDao.DeleteCamp(request);
	   
	  }else if (request.getKeyword().equalsIgnoreCase("GetMenuName")) {
	  
	  ussdResponse.setMenus(ussdTreeDao.GetSMSMenu(request));
	  
	  return ussdResponse;
	  
	  } else if (request.getKeyword().equalsIgnoreCase("CreateMessage")) {
		 
		   
	  ussdResponse = ussdTreeDao.CreateMessage(request);
	  
	  return ussdResponse;
	  
	  } else if (request.getKeyword().equalsIgnoreCase("GetMessage")) {
	  
	  return ussdTreeDao.GetMessage(request);
	  
	  } else if (request.getKeyword().equalsIgnoreCase("GetLang")) {
	  
	  return ussdTreeDao.GetLang(request);
	  
	  } else if (request.getKeyword().equalsIgnoreCase("DelMenu")) {
	  
	  ussdResponse = ussdTreeDao.DelMenu(request); return ussdResponse;
	  
	  } else if (request.getKeyword().equalsIgnoreCase("EditMessage")) {
	  
	  return ussdTreeDao.EditMessage(request);
	  
	  }
	  
	  } return ussdResponse;
	  
	  }
	 
	
	//---------------------------------------------------------------------------------------
	
		/*
		 * @Override public List<GetCampaigns> getCampaignDetails() { // TODO
		 * Auto-generated method stub return ussdTreeDao.GetCategories(); }
		 * 
		 * @Override public List<GetSMSMenusTO> GetSMSMenu(GetCampaigns getCampaigns) {
		 * // TODO Auto-generated method stub return
		 * ussdTreeDao.GetSMSMenu(getCampaigns); }
		 */
	
	
	
	
	
	
	
	
	
	
	
	
	
}