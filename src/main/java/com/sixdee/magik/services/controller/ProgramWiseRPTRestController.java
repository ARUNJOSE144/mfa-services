package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.EnrolmentTO;
import com.sixdee.magik.services.model.ProgramWiseReportTO;
import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.DaywiseCampaignConversionTO;
import com.sixdee.magik.services.model.EnrolmentTO;
import com.sixdee.magik.services.service.DaywiseCampaignConversionReportService;
import com.sixdee.magik.services.service.EnrolmentReportService;
import com.sixdee.magik.services.service.ProgramWiseReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;
/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */
 
@RestController
public class ProgramWiseRPTRestController {

static final Logger logger = Logger.getLogger(ProgramWiseRPTRestController.class);
	
	@Autowired ProgramWiseReportService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_PROGRAMWISE_RPT_DATA)
	public @ResponseBody RestListInfo<ProgramWiseReportTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== ProgramWiseRPTRestController API Start =====================");
		logger.info("Class : ProgramWiseRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<ProgramWiseReportTO> info = new RestListInfo<ProgramWiseReportTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : ProgramWiseRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== ProgramWiseRPTRestController API End =====================");
	
		return info;

    }
}
