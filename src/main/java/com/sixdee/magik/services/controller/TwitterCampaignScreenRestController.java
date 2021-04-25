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
import com.sixdee.magik.services.model.TwitterDetailsTO;
import com.sixdee.magik.services.service.TwitterCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
@RestController
public class TwitterCampaignScreenRestController {
	
	static final Logger logger = Logger.getLogger(TwitterCampaignScreenRestController.class);
	
	@Autowired
	TwitterCampaignScreenService twitterService;
	
	@GetMapping(value=MagikServicePath.GET_TWITTER_MSG_DATA)
	public @ResponseBody RestListInfo<TwitterDetailsTO> getTwitterData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId)
	{
		
		logger.info("================== TwitterCampaignScreenRestController API Start =====================");
		logger.info("Class : TwitterCampaignScreenRestController | Method : getTwitterData");
		RestListInfo<TwitterDetailsTO> info = new RestListInfo<TwitterDetailsTO>();
		try {
			  info.setData(twitterService.getDetailsData(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TwitterCampaignScreenRestController  |  Method : getTwitterData " + e);
			e.printStackTrace();
		}
		logger.info("================== TwitterCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	
	@GetMapping(value=MagikServicePath.GET_EDIT_TWITTER_RECORD_DATA)
	public @ResponseBody RestInfo<TwitterDetailsTO> getEdiTwitterData(HttpServletRequest httpServletRequest,@RequestParam(value = "messageAutoID") int autoId) 
	{
		logger.info("================== TwitterCampaignScreenRestController API Start =====================");
		logger.info("Class : TwitterCampaignScreenRestController | Method : getEdiTwitterData");
		RestInfo<TwitterDetailsTO> info = new RestInfo<TwitterDetailsTO>();
		TwitterDetailsTO twitterdata =null;
		try {
			twitterdata=twitterService.getSelectedRecord(autoId);
			   info.setData(twitterdata);
			   info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TwitterCampaignScreenRestController  |  Method : getEdiTwitterData " + e);
			e.printStackTrace();
		}
		logger.info("================== TwitterCampaignScreenRestController API End =====================");
		return info;
		
	}	 
	
	@PostMapping(value=MagikServicePath.SAVE_UPDATE_TWITTER_MSG)
	public @ResponseBody RestInfo<String> SaveorUpdateTwitterMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO twitterRequestBodyTO
							) throws IOException 
	{
		
		logger.info("================== TwitterCampaignScreenRestController API Start =====================");
		logger.info("Class : TwitterCampaignScreenRestController | Method : SaveorUpdateTwitterMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=twitterService.saveorUpdateRecord(twitterRequestBodyTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : TwitterCampaignScreenRestController  |  Method : SaveorUpdateTwitterMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== TwitterCampaignScreenRestController API End =====================");
			
					
		return info;
			
		
	}
	
	@GetMapping(value = MagikServicePath.DELETE_TWITTER_MSG)
	public @ResponseBody RestInfo<String> deleteTwitterMsg(HttpServletRequest httpServletRequest,
			@RequestParam(value = "messageAutoID") int autoId) {
		logger.info("================== TwitterCampaignScreenRestController API Start =====================");
		logger.info("Class : TwitterCampaignScreenRestController | Method : deleteTwitterMsg");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(twitterService.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TwitterCampaignScreenRestController  |  Method : deleteTwitterMsg " + e);
			e.printStackTrace();
		}
		logger.info("================== TwitterCampaignScreenRestController API End =====================");
		return info;
	}
	
	
	

}
