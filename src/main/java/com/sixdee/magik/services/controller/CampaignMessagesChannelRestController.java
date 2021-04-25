package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ChannelMessageTO;
import com.sixdee.magik.services.service.NotificationsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2020
 */
 
@RestController
public class CampaignMessagesChannelRestController {
	
	
	static final Logger logger = Logger.getLogger(CampaignMessagesChannelRestController.class);
	
		@Autowired
		CampaignMessagesChannelService services;



		@GetMapping(MagikServicePath.GET_CHANNEL_MESSAGES)
		public @ResponseBody RestListInfo<ChannelMessageTO> getChannelSearch(HttpServletRequest httpServletRequest) {
		
			logger.info("================== CampaignMessagesChannelRestController API Start =====================");
			logger.info("Class : CampaignMessagesChannelRestController | Method : getChannelSearch");
		
			RestListInfo<ChannelMessageTO> info = new RestListInfo<ChannelMessageTO>();
			String channel = httpServletRequest.getParameter("channel");
			int userId = 1;
			try {
				info.setData(services.getChannelSearch(channel, userId));
			} catch (Exception e) {
				ExceptionUtil.handle(e, info);
				logger.error("Class : CampaignMessagesChannelRestController  |  Method : getChannelSearch " + e);
				e.printStackTrace();
			}
		
			logger.info("================== CampaignMessagesChannelRestController API End =====================");
		
			return info;
		}
}