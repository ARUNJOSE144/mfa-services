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
import com.sixdee.magik.services.model.SMSDetailsCategoryTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateDtlsTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateTO;
import com.sixdee.magik.services.model.SMSLanguagesTO;
import com.sixdee.magik.services.service.SMSCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */
 
@RestController
public class SMSCampaginScreenRestController {
	
	static final Logger logger = Logger.getLogger(SMSCampaginScreenRestController.class);
	
	@Autowired SMSCampaignScreenService serviceLayer;
	
	@GetMapping(MagikServicePath.GET_CAMP_LANGUAGEs_DATA)
	public @ResponseBody RestListInfo<SMSLanguagesTO> getLanguages(HttpServletRequest httpServletRequest) {

		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : getLanguages");
		RestListInfo<SMSLanguagesTO> info = new RestListInfo<SMSLanguagesTO>();
		try {
			info.setData(serviceLayer.getLanguages());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SMSCampaginScreenRestController  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== SMSCampaginScreenRestController API End =====================");
	
		return info;

    }
	
	@GetMapping(MagikServicePath.GET_SMS_CATEGORGYs_DATA)
	public @ResponseBody RestListInfo<SMSDetailsCategoryTO> getCategoryList(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : getCategoryList");
		RestListInfo<SMSDetailsCategoryTO> info = new RestListInfo<SMSDetailsCategoryTO>();
		try {
			  info.setData(serviceLayer.getCategoriesList(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SMSCampaginScreenRestController  |  Method : getCategoryList " + e);
			e.printStackTrace();
		}
		logger.info("================== SMSCampaginScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.GET_SAVEUPDATE_CATEGORGYs_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateCategory(HttpServletRequest httpServletRequest,@RequestBody SMSDetailsCategoryTO smsdetailscategoryto
							) throws IOException 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : SaveorUpdateCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
		try 
		{
			statuscode=serviceLayer.saveorUpdateCategoryData(smsdetailscategoryto);
			info.setOperationCode(0);
			info.setMessage("Success");
		} 
	    catch (Exception e) {
			logger.error("Class : SMSCampaginScreenRestController  |  Method : SaveorUpdateCategory " + e);
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
	     }
	    logger.info("================== SMSCampaginScreenRestController API End =====================");
        return info;
	}
	
	@GetMapping(MagikServicePath.GET_SMS_TEMPLATE_MSG_DATA)
	public @ResponseBody RestListInfo<SMSDetailsTemplateTO> getSMSData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId,@RequestParam(value = "categoryId") int categoryId) 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : getSMSData");
		RestListInfo<SMSDetailsTemplateTO> info = new RestListInfo<SMSDetailsTemplateTO>();
		try {
			  info.setData(serviceLayer.getTemplateRecord(userId,categoryId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SMSCampaginScreenRestController  |  Method : getSMSData " + e);
			e.printStackTrace();
		}
		logger.info("================== SMSCampaginScreenRestController API End =====================");
		return info;
		
	}
	
	@GetMapping(MagikServicePath.GET_SMS_SELECTED_DATA)
	public @ResponseBody RestListInfo<SMSDetailsTemplateTO> getSMSDetailsData(HttpServletRequest httpServletRequest,@RequestParam(value = "menuTemplateId") int msgId) 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : getSMSDetailsData");
		RestListInfo<SMSDetailsTemplateTO> info = new RestListInfo<SMSDetailsTemplateTO>();
		try {
			  info.setData(serviceLayer.getSelectedTemplate_Detials(msgId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SMSCampaginScreenRestController  |  Method : getSMSDetailsData " + e);
			e.printStackTrace();
		}
		logger.info("================== SMSCampaginScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.SAVEUPDATE_SMS_DATA)
	public @ResponseBody RestInfo<String> SaveorUpdateMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO requestBodyTo
							) throws IOException 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateSMSData(requestBodyTo);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : SMSCampaginScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== SMSCampaginScreenRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(MagikServicePath.DELETE_SMS_CATEGORGY_DATA)
	public @ResponseBody RestListInfo<String> deleteCategory(HttpServletRequest httpServletRequest,@RequestParam(value = "campId") int campId) 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : deleteCategory");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteCategory(campId);
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SMSCampaginScreenRestController  |  Method : deleteCategory " + e);
			e.printStackTrace();
		}
		logger.info("================== SMSCampaginScreenRestController API End =====================");
		return info;
	}
	
	
	@PostMapping(MagikServicePath.DELETE_SMS_MENU_DATA)
	public @ResponseBody RestListInfo<String> deleteMenu(HttpServletRequest httpServletRequest,@RequestParam(value = "menuId") String menuId) 
	{
		
		logger.info("================== SMSCampaginScreenRestController API Start =====================");
		logger.info("Class : SMSCampaginScreenRestController | Method : deleteMenu");
		String statuscode= "SC0001";
		RestListInfo<String> info = new RestListInfo<String>();
		try {
			statuscode=serviceLayer.deleteMenu(Integer.parseInt(menuId));
			  info.setOperationCode(0);
				info.setMessage("Success");
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SMSCampaginScreenRestController  |  Method : deleteMenu " + e);
			e.printStackTrace();
		}
		logger.info("================== SMSCampaginScreenRestController API End =====================");
		return info;
	}
	
}
