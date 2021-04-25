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
import com.sixdee.magik.services.model.SkypeDetailsTO;
import com.sixdee.magik.services.service.SkypeCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
@RestController
public class SkypeCampaignScreenRestController {
	
static final Logger logger = Logger.getLogger(SkypeCampaignScreenRestController.class);
	
	@Autowired
	SkypeCampaignScreenService mediaService;
	
	@GetMapping(value=MagikServicePath.GET_SKYPE_MSG_DATA)
	public @ResponseBody RestListInfo<SkypeDetailsTO> getData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== SkypeCampaignScreenRestController API Start =====================");
		logger.info("Class : SkypeCampaignScreenRestController | Method : getData");
		RestListInfo<SkypeDetailsTO> info = new RestListInfo<SkypeDetailsTO>();
		try {
			  info.setData(mediaService.getDetailsData(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SkypeCampaignScreenRestController  |  Method : getData " + e);
			e.printStackTrace();
		}
		logger.info("================== SkypeCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	
	@GetMapping(value=MagikServicePath.GET_EDIT_SKYPE_RECORD_DATA)
	public @ResponseBody RestInfo<SkypeDetailsTO> retrieveSelectedData(HttpServletRequest httpServletRequest,@RequestParam(value = "messageAutoID") int autoId) 
	{
		logger.info("================== SkypeCampaignScreenRestController API Start =====================");
		logger.info("Class : SkypeCampaignScreenRestController | Method : retrieveSelectedData");
		RestInfo<SkypeDetailsTO> info = new RestInfo<SkypeDetailsTO>();
		SkypeDetailsTO object =null;
		try {
			object=mediaService.getSelectedRecord(autoId);
			   info.setData(object);
			   info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SkypeCampaignScreenRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== SkypeCampaignScreenRestController API End =====================");
		return info;
		
	}	 
	
	@PostMapping(value=MagikServicePath.SAVE_UPDATE_SKYPE_MSG)
	public @ResponseBody RestInfo<String> SaveorUpdateMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO telegramRequestBodyTO
							) throws IOException 
	{
		
		logger.info("================== SkypeCampaignScreenRestController API Start =====================");
		logger.info("Class : SkypeCampaignScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=mediaService.saveorUpdateRecord(telegramRequestBodyTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : SkypeCampaignScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== SkypeCampaignScreenRestController API End =====================");
			
					
		return info;
			
		
	}
	
	@GetMapping(value = MagikServicePath.DELETE_SKYPE_MSG)
	public @ResponseBody RestInfo<String> deleteSelectedMsg(HttpServletRequest httpServletRequest,
			@RequestParam(value = "messageAutoID") int autoId) {
		logger.info("================== SkypeCampaignScreenRestController API Start =====================");
		logger.info("Class : SkypeCampaignScreenRestController | Method : deleteSelectedMsg");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(mediaService.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : SkypeCampaignScreenRestController  |  Method : deleteSelectedMsg " + e);
			e.printStackTrace();
		}
		logger.info("================== SkypeCampaignScreenRestController API End =====================");
		return info;
	}
	

}
