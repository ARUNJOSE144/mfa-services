package com.sixdee.magik.services.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LeaderReportTO;
import com.sixdee.magik.services.service.LeaderReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */
 
@RestController
public class LeaderRPTRestController {
	
    static final Logger logger = Logger.getLogger(LeaderRPTRestController.class);
	
	@Autowired LeaderReportService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_LEADER_RPT_DATA)
	public @ResponseBody RestListInfo<LeaderReportTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== LeaderRPTRestController API Start =====================");
		logger.info("Class : LeaderRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<LeaderReportTO> info = new RestListInfo<LeaderReportTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : LeaderRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== LeaderRPTRestController API End =====================");
	
		return info;

    }
	
	
}


