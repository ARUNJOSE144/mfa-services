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
import com.sixdee.magik.services.model.WAPDetailsCategoryTO;
import com.sixdee.magik.services.model.WAPDetailsTemplateTO;
import com.sixdee.magik.services.service.WAPCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */
 
@RestController
public class WAPCampaignScreenRestController {
	
	static final Logger logger = Logger.getLogger(WAPCampaignScreenRestController.class);
	
	@Autowired WAPCampaignScreenService serviceLayer;
	
	@GetMapping(MagikServicePath.GET_WAP_CATEGORGYs_DATA)
	public @ResponseBody RestListInfo<WAPDetailsCategoryTO> getCategoryList(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : getCategoryList");
		RestListInfo<WAPDetailsCategoryTO> info = new RestListInfo<WAPDetailsCategoryTO>();
		try {
			  info.setData(serviceLayer.getCategoriesList(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WAPCampaignScreenRestController  |  Method : getCategoryList " + e);
			e.printStackTrace();
		}
		logger.info("================== WAPCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	@PostMapping(value=MagikServicePath.GET_SAVEUPDATE_WAP_CATEGORGYs_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateCategory(HttpServletRequest httpServletRequest,@RequestBody WAPDetailsCategoryTO wapDetailscategoryto
							) throws IOException 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : SaveorUpdateCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
		try 
		{
			statuscode=serviceLayer.saveorUpdateCategoryData(wapDetailscategoryto);
			info.setOperationCode(0);
			info.setMessage("Success");
		} 
	    catch (Exception e) {
			logger.error("Class : WAPCampaignScreenRestController  |  Method : SaveorUpdateCategory " + e);
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
	     }
	    logger.info("================== WAPCampaignScreenRestController API End =====================");
        return info;
	}
	
	
	@GetMapping(MagikServicePath.GET_WAP_TEMPLATE_MSG_DATA)
	public @ResponseBody RestListInfo<WAPDetailsTemplateTO> getWAPData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId,@RequestParam(value = "categoryId") int categoryId) 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : getWAPData");
		RestListInfo<WAPDetailsTemplateTO> info = new RestListInfo<WAPDetailsTemplateTO>();
		try {
			  info.setData(serviceLayer.getTemplateRecord(userId,categoryId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WAPCampaignScreenRestController  |  Method : getWAPData " + e);
			e.printStackTrace();
		}
		logger.info("================== WAPCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	@GetMapping(MagikServicePath.GET_WAP_SELECTED_DATA)
	public @ResponseBody RestListInfo<WAPDetailsTemplateTO> getWAPDetailsData(HttpServletRequest httpServletRequest,@RequestParam(value = "menuTemplateId") int msgId) 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : getWAPDetailsData");
		RestListInfo<WAPDetailsTemplateTO> info = new RestListInfo<WAPDetailsTemplateTO>();
		try {
			  info.setData(serviceLayer.getSelectedTemplate_Detials(msgId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WAPCampaignScreenRestController  |  Method : getWAPDetailsData " + e);
			e.printStackTrace();
		}
		logger.info("================== WAPCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.SAVEUPDATE_WAP_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO requestBodyTo
							) throws IOException 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateTemplateData(requestBodyTo);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : WAPCampaignScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== WAPCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	@PostMapping(MagikServicePath.DELETE_WAP_CATEGORGY_DATA)
	public @ResponseBody RestListInfo<String> deleteCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "campId") int campId) 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : deleteCategory");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteCategory(campId);
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WAPCampaignScreenRestController  |  Method : deleteCategory " + e);
			e.printStackTrace();
		}
		logger.info("================== WAPCampaignScreenRestController API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.DELETE_WAP_MENU_DATA)
	public @ResponseBody RestListInfo<String> deleteMenu(HttpServletRequest httpServletRequest,@RequestParam(value = "menuId") String menuId) 
	{
		
		logger.info("================== WAPCampaignScreenRestController API Start =====================");
		logger.info("Class : WAPCampaignScreenRestController | Method : deleteMenu");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteMenu(Integer.parseInt(menuId));
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : WAPCampaignScreenRestController  |  Method : deleteMenu " + e);
			e.printStackTrace();
		}
		logger.info("================== WAPCampaignScreenRestController API End =====================");
		return info;
	}
	
	
	
	

}
