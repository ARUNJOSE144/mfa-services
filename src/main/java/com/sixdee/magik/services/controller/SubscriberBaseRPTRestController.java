package com.sixdee.magik.services.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LeaderReportTO;
import com.sixdee.magik.services.model.SubscriberBaseReportTO;
import com.sixdee.magik.services.service.LeaderReportService;
import com.sixdee.magik.services.service.SubscriberBaseReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */
 
@RestController
public class SubscriberBaseRPTRestController {

	 static final Logger logger = Logger.getLogger(SubscriberBaseRPTRestController.class);
		
		@Autowired SubscriberBaseReportService serviceLayer;
		
		
		@GetMapping(MagikServicePath.GET_SUBSCRIBERBASE_RPT_DATA)
		public @ResponseBody RestListInfo<SubscriberBaseReportTO> getAllRecords(HttpServletRequest httpServletRequest) {

			logger.info("================== SubscriberBaseRPTRestController API Start =====================");
			logger.info("Class : SubscriberBaseRPTRestController | Method : getAllRecords");
			
			
			RestListInfo<SubscriberBaseReportTO> info = new RestListInfo<SubscriberBaseReportTO>();
			try
			{
				info.setData(serviceLayer.getData());
			} catch (Exception e) {
				
				ExceptionUtil.handle(e, info);
				logger.error("Class : SubscriberBaseRPTRestController  |  Method : getAllRecords " + e);
				e.printStackTrace();
			}
			
			
			logger.info("================== SubscriberBaseRPTRestController API End =====================");
		
			return info;

	    }
		
		
	}



