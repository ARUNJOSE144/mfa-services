package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.RewardExpiryReportTO;
import com.sixdee.magik.services.service.RewardExpiryReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */
 
@RestController
public class RewardExpiryRPTRestController {

	
static final Logger logger = Logger.getLogger(RewardExpiryRPTRestController.class);
	
	@Autowired RewardExpiryReportService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_REWARDEXPIRY_RPT_DATA)
	public @ResponseBody RestListInfo<RewardExpiryReportTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== RewardExpiryRPTRestController API Start =====================");
		logger.info("Class : RewardExpiryRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<RewardExpiryReportTO> info = new RestListInfo<RewardExpiryReportTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : RewardExpiryRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== RewardExpiryRPTRestController API End =====================");
	
		return info;

    }
	
	
}



