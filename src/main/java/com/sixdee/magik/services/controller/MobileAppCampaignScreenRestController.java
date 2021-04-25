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

import com.sixdee.magik.services.model.MOBILE_APP_CategoryTO;
import com.sixdee.magik.services.model.MOBILE_APP_RequestBody;
import com.sixdee.magik.services.model.MOBILE_APP_TemplateTO;
import com.sixdee.magik.services.model.MobileAppMenusTO;
import com.sixdee.magik.services.service.MobileAppCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */
 
@RestController
public class MobileAppCampaignScreenRestController {

	
static final Logger logger = Logger.getLogger(MobileAppCampaignScreenRestController.class);
	
	@Autowired MobileAppCampaignScreenService serviceLayer;
	
	@GetMapping(MagikServicePath.GET_MOBILE_APP_CATEGORGYs_DATA)
	public @ResponseBody RestListInfo<MOBILE_APP_CategoryTO> getCategoryList(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : getCategoryList");
		RestListInfo<MOBILE_APP_CategoryTO> info = new RestListInfo<MOBILE_APP_CategoryTO>();
		try {
			  info.setData(serviceLayer.getCategoriesList(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : MobileAppCampaignScreenRestController  |  Method : getCategoryList " + e);
			e.printStackTrace();
		}
		logger.info("================== MobileAppCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.GET_SAVEUPDATE_MOBILE_APP_CATEGORGYs_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateCategory(HttpServletRequest httpServletRequest,@RequestBody MOBILE_APP_CategoryTO mobileAppcategoryto
							) throws IOException 
	{
		
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : SaveorUpdateCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
		try 
		{
			statuscode=serviceLayer.saveorUpdateCategoryData(mobileAppcategoryto);
			info.setOperationCode(0);
			info.setMessage("Success");
		} 
	    catch (Exception e) {
			logger.error("Class : MobileAppCampaignScreenRestController  |  Method : SaveorUpdateCategory " + e);
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
	     }
	    logger.info("================== MobileAppCampaignScreenRestController API End =====================");
        return info;
	}
	
	@GetMapping(MagikServicePath.GET_MOBILE_APP_TEMPLATE_MSG_DATA)
	public @ResponseBody RestListInfo<MOBILE_APP_TemplateTO> getMobileAppData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId,@RequestParam(value = "categoryId") int categoryId) 
	{
		
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : getMobileAppData");
		RestListInfo<MOBILE_APP_TemplateTO> info = new RestListInfo<MOBILE_APP_TemplateTO>();
		try {
			  info.setData(serviceLayer.getTemplateRecord(userId,categoryId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : MobileAppCampaignScreenRestController  |  Method : getMobileAppData " + e);
			e.printStackTrace();
		}
		logger.info("================== MobileAppCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	@GetMapping(MagikServicePath.GET_MOBILE_APP_SELECTED_DATA)
	public @ResponseBody RestListInfo<MOBILE_APP_TemplateTO> getMobileAppDetailsData(HttpServletRequest httpServletRequest,@RequestParam(value = "menuTemplateId") int msgId) 
	{
		
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : getMobileAppDetailsData");
		RestListInfo<MOBILE_APP_TemplateTO> info = new RestListInfo<MOBILE_APP_TemplateTO>();
		try {
			  info.setData(serviceLayer.getSelectedTemplate_Detials(msgId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : MobileAppCampaignScreenRestController  |  Method : getMobileAppDetailsData " + e);
			e.printStackTrace();
		}
		logger.info("================== MobileAppCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(MagikServicePath.DELETE_MOBILE_APP_CATEGORGY_DATA)
	public @ResponseBody RestListInfo<String> deleteCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "campId") int campId) 
	{
		
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : deleteCategory");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteCategory(campId);
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : MobileAppCampaignScreenRestController  |  Method : deleteCategory " + e);
			e.printStackTrace();
		}
		logger.info("================== MobileAppCampaignScreenRestController API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.DELETE_MOBILE_APP_MENU_DATA)
	public @ResponseBody RestListInfo<String> deleteMenu(HttpServletRequest httpServletRequest,@RequestParam(value = "menuId") String menuId) 
	{
		
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : deleteMenu");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteMenu(Integer.parseInt(menuId));
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : MobileAppCampaignScreenRestController  |  Method : deleteMenu " + e);
			e.printStackTrace();
		}
		logger.info("================== MobileAppCampaignScreenRestController API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.SAVEUPDATE_MOBILE_APP_DATA)
	public @ResponseBody RestInfo<String> saveorUpdateMessageDetails(@RequestBody MOBILE_APP_RequestBody mobile_app_requestbody) {
		logger.info("================== MobileAppCampaignScreenRestController API Start =====================");
		logger.info("Class : MobileAppCampaignScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateMsgDetailsData(mobile_app_requestbody);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : MobileAppCampaignScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== MobileAppCampaignScreenRestController API End =====================");
		return info;

	}
	
	
}
