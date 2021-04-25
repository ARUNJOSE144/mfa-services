package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CampaignPerformanceReportTransientTO;
import com.sixdee.magik.services.service.CampaignPerformanceReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@RestController
public class CampaignPerformanceReportRestController {
	
	
	static final Logger logger = Logger.getLogger(CampaignPerformanceReportRestController.class);
	
	
	@Autowired CampaignPerformanceReportService serviceLayer;
	
	@GetMapping(MagikServicePath.GET_CMP_PER_RPT_RECORDS)
	public @ResponseBody RestInfo<CampaignPerformanceReportTransientTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignPerformanceReportRestController API Start =====================");
		logger.info("Class : CampaignPerformanceReportRestController | Method : getAllRecords");
		RestInfo<CampaignPerformanceReportTransientTO> info = new RestInfo<CampaignPerformanceReportTransientTO>();
		try {
			info.setData(serviceLayer.getAllRcrds());
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignPerformanceReportRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		logger.info("================== CampaignPerformanceReportRestController API End =====================");
	
		return info;

    }
	
	

}
