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
import com.sixdee.magik.services.model.FacebookDetailsTO;
import com.sixdee.magik.services.service.FacebookCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
@RestController
public class FacebookTemplateCampaignScreenRestController {
	
static final Logger logger = Logger.getLogger(FacebookTemplateCampaignScreenRestController.class);
	
	@Autowired
	FacebookCampaignScreenService mediaService;
	
	@GetMapping(value=MagikServicePath.GET_FACEBOOK_MSG_DATA)
	public @ResponseBody RestListInfo<FacebookDetailsTO> getData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== FacebookTemplateCampaignScreenRestController API Start =====================");
		logger.info("Class : FacebookTemplateCampaignScreenRestController | Method : getData");
		RestListInfo<FacebookDetailsTO> info = new RestListInfo<FacebookDetailsTO>();
		try {
			  info.setData(mediaService.getDetailsData(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FacebookTemplateCampaignScreenRestController  |  Method : getData " + e);
			e.printStackTrace();
		}
		logger.info("================== FacebookTemplateCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	
	@GetMapping(value=MagikServicePath.GET_EDIT_FACEBOOK_RECORD_DATA)
	public @ResponseBody RestInfo<FacebookDetailsTO> retrieveSelectedData(HttpServletRequest httpServletRequest,@RequestParam(value = "messageAutoID") int autoId) 
	{
		logger.info("================== FacebookTemplateCampaignScreenRestController API Start =====================");
		logger.info("Class : FacebookTemplateCampaignScreenRestController | Method : retrieveSelectedData");
		RestInfo<FacebookDetailsTO> info = new RestInfo<FacebookDetailsTO>();
		FacebookDetailsTO object =null;
		try {
			object=mediaService.getSelectedRecord(autoId);
			   info.setData(object);
			   info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FacebookTemplateCampaignScreenRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== FacebookTemplateCampaignScreenRestController API End =====================");
		return info;
		
	}	 
	
	@PostMapping(value=MagikServicePath.SAVE_UPDATE_FACEBOOK_MSG)
	public @ResponseBody RestInfo<String> SaveorUpdateMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO telegramRequestBodyTO
							) throws IOException 
	{
		
		logger.info("================== FacebookTemplateCampaignScreenRestController API Start =====================");
		logger.info("Class : FacebookTemplateCampaignScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=mediaService.saveorUpdateRecord(telegramRequestBodyTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : FacebookTemplateCampaignScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== FacebookTemplateCampaignScreenRestController API End =====================");
			
					
		return info;
			
		
	}
	
	@GetMapping(value = MagikServicePath.DELETE_FACEBOOK_MSG)
	public @ResponseBody RestInfo<String> deleteSelectedMsg(HttpServletRequest httpServletRequest,
			@RequestParam(value = "messageAutoID") int autoId) {
		logger.info("================== FacebookTemplateCampaignScreenRestController API Start =====================");
		logger.info("Class : FacebookTemplateCampaignScreenRestController | Method : deleteSelectedMsg");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(mediaService.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FacebookTemplateCampaignScreenRestController  |  Method : deleteSelectedMsg " + e);
			e.printStackTrace();
		}
		logger.info("================== FacebookTemplateCampaignScreenRestController API End =====================");
		return info;
	}
	

}
