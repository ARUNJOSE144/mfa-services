package com.sixdee.magik.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sixdee.magik.services.model.RewardReportTO;
import com.sixdee.magik.services.service.RewardReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */


@Controller
public class RewardRPTRestController {

static final Logger logger = Logger.getLogger(RewardRPTRestController.class);
	
	@Autowired RewardReportService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_REWARD_RPT_DATA)
	public @ResponseBody RestListInfo<RewardReportTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== RewardRPTRestController API Start =====================");
		logger.info("Class : RewardRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<RewardReportTO> info = new RestListInfo<RewardReportTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : RewardRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== RewardRPTRestController API End =====================");
	
		return info;

    }
}

