package com.sixdee.magik.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.USSDDetailsCategoryTO;
import com.sixdee.magik.services.model.USSDDetailsTemplateTO;
import com.sixdee.magik.services.service.USSDCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */
 
@RestController
public class USSDCampaignScreenRestController {
	
		static final Logger logger = Logger.getLogger(USSDCampaignScreenRestController.class);
			
			@Autowired USSDCampaignScreenService serviceLayer;
			
	@GetMapping(MagikServicePath.GET_USSD_CATEGORGYs_DATA)
	public @ResponseBody RestListInfo<USSDDetailsCategoryTO> getCategoryList(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : getCategoryList");
		RestListInfo<USSDDetailsCategoryTO> info = new RestListInfo<USSDDetailsCategoryTO>();
		try {
			  info.setData(serviceLayer.getCategoriesList(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : USSDCampaignScreenRestController  |  Method : getCategoryList " + e);
			e.printStackTrace();
		}
		logger.info("================== USSDCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.GET_SAVEUPDATE_USSD_CATEGORGYs_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateCategory(HttpServletRequest httpServletRequest,@RequestBody USSDDetailsCategoryTO ussdDetailscategoryto
							) throws IOException 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : SaveorUpdateCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
		try 
		{
			statuscode=serviceLayer.saveorUpdateCategoryData(ussdDetailscategoryto);
			info.setOperationCode(0);
			info.setMessage("Success");
		} 
	    catch (Exception e) {
			logger.error("Class : USSDCampaignScreenRestController  |  Method : SaveorUpdateCategory " + e);
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
	     }
	    logger.info("================== USSDCampaignScreenRestController API End =====================");
        return info;
	}
	
	
	@GetMapping(MagikServicePath.GET_USSD_TEMPLATE_MSG_DATA)
	public @ResponseBody RestListInfo<USSDDetailsTemplateTO> getUSSDData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId,@RequestParam(value = "categoryId") int categoryId) 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : getUSSDData");
		RestListInfo<USSDDetailsTemplateTO> info = new RestListInfo<USSDDetailsTemplateTO>();
		try {
			  info.setData(serviceLayer.getTemplateRecord(userId,categoryId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : USSDCampaignScreenRestController  |  Method : getUSSDData " + e);
			e.printStackTrace();
		}
		logger.info("================== USSDCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@GetMapping(MagikServicePath.GET_USSD_SELECTED_DATA)
	public @ResponseBody RestListInfo<USSDDetailsTemplateTO> getUSSDDetailsData(HttpServletRequest httpServletRequest,@RequestParam(value = "menuTemplateId") int msgId) 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : getUSSDDetailsData");
		RestListInfo<USSDDetailsTemplateTO> info = new RestListInfo<USSDDetailsTemplateTO>();
		try {
			  info.setData(serviceLayer.getSelectedTemplate_Detials(msgId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : USSDCampaignScreenRestController  |  Method : getUSSDDetailsData " + e);
			e.printStackTrace();
		}
		logger.info("================== USSDCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.SAVEUPDATE_USSD_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO requestBodyTo
							) throws IOException 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateTemplateData(requestBodyTo);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : USSDCampaignScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== USSDCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(MagikServicePath.DELETE_USSD_CATEGORGY_DATA)
	public @ResponseBody RestListInfo<String> deleteCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "campId") int campId) 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : deleteCategory");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteCategory(campId);
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : USSDCampaignScreenRestController  |  Method : deleteCategory " + e);
			e.printStackTrace();
		}
		logger.info("================== USSDCampaignScreenRestController API End =====================");
		return info;
	}
	
	
	@PostMapping(MagikServicePath.DELETE_USSD_MENU_DATA)
	public @ResponseBody RestListInfo<String> deleteMenu(HttpServletRequest httpServletRequest,@RequestParam(value = "menuId") String menuId) 
	{
		
		logger.info("================== USSDCampaignScreenRestController API Start =====================");
		logger.info("Class : USSDCampaignScreenRestController | Method : deleteMenu");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteMenu(Integer.parseInt(menuId));
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : USSDCampaignScreenRestController  |  Method : deleteMenu " + e);
			e.printStackTrace();
		}
		logger.info("================== USSDCampaignScreenRestController API End =====================");
		return info;
	}
	
	

}
