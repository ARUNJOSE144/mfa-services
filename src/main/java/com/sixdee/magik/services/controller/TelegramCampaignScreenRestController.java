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
import com.sixdee.magik.services.model.TelegramDetailsTO;
import com.sixdee.magik.services.service.TelegramCampaignScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
@RestController
public class TelegramCampaignScreenRestController {
	
static final Logger logger = Logger.getLogger(TelegramCampaignScreenRestController.class);
	
	@Autowired
	TelegramCampaignScreenService mediaService;
	
	@GetMapping(value=MagikServicePath.GET_TELEGRAM_MSG_DATA)
	public @ResponseBody RestListInfo<TelegramDetailsTO> getData(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) 
	{
		
		logger.info("================== TelegramCampaignScreenRestController API Start =====================");
		logger.info("Class : TelegramCampaignScreenRestController | Method : getData");
		RestListInfo<TelegramDetailsTO> info = new RestListInfo<TelegramDetailsTO>();
		try {
			  info.setData(mediaService.getTelegramData(userId));
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TelegramCampaignScreenRestController  |  Method : getData " + e);
			e.printStackTrace();
		}
		logger.info("================== TelegramCampaignScreenRestController API End =====================");
		return info;
		
	}
	
	
	
	@GetMapping(value=MagikServicePath.GET_EDIT_TELEGRAM_RECORD_DATA)
	public @ResponseBody RestInfo<TelegramDetailsTO> retrieveSelectedData(HttpServletRequest httpServletRequest,@RequestParam(value = "messageAutoID") int autoId) 
	{
		logger.info("================== TelegramCampaignScreenRestController API Start =====================");
		logger.info("Class : TelegramCampaignScreenRestController | Method : retrieveSelectedData");
		RestInfo<TelegramDetailsTO> info = new RestInfo<TelegramDetailsTO>();
		TelegramDetailsTO telegramData =null;
		try {
			telegramData=mediaService.getSelectedRecord(autoId);
			   info.setData(telegramData);
			   info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TelegramCampaignScreenRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== TelegramCampaignScreenRestController API End =====================");
		return info;
		
	}	 
	
	@PostMapping(value=MagikServicePath.SAVE_UPDATE_TELEGRAM_MSG)
	public @ResponseBody RestInfo<String> SaveorUpdateMessage(HttpServletRequest httpServletRequest,
							@RequestBody RequestBodyTO telegramRequestBodyTO
							) throws IOException 
	{
		
		logger.info("================== TelegramCampaignScreenRestController API Start =====================");
		logger.info("Class : TelegramCampaignScreenRestController | Method : SaveorUpdateMessage");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=mediaService.saveorUpdateTelegramData(telegramRequestBodyTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : TelegramCampaignScreenRestController  |  Method : SaveorUpdateMessage " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== TelegramCampaignScreenRestController API End =====================");
			
					
		return info;
			
		
	}
	
	@GetMapping(value = MagikServicePath.DELETE_TELEGRAM_MSG)
	public @ResponseBody RestInfo<String> deleteSelectedMsg(HttpServletRequest httpServletRequest,
			@RequestParam(value = "messageAutoID") int autoId) {
		logger.info("================== TelegramCampaignScreenRestController API Start =====================");
		logger.info("Class : TelegramCampaignScreenRestController | Method : deleteSelectedMsg");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(mediaService.deleteTelegramMsg(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TelegramCampaignScreenRestController  |  Method : deleteSelectedMsg " + e);
			e.printStackTrace();
		}
		logger.info("================== TelegramCampaignScreenRestController API End =====================");
		return info;
	}
	
	

}
