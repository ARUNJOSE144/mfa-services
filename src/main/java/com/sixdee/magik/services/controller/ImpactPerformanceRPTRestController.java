package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ImpactPerformanceTO;
import com.sixdee.magik.services.service.ImpactPerformanceReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

 
@RestController
public class ImpactPerformanceRPTRestController {
	
	
	static final Logger logger = Logger.getLogger(ImpactPerformanceRPTRestController.class);
	
	@Autowired ImpactPerformanceReportService serviceLayer;
	
		
	@GetMapping(MagikServicePath.GET_IMPACT_PERFORMANCE_RPT_DATA)
	public @ResponseBody RestListInfo<ImpactPerformanceTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== ImpactPerformanceRPTRestController API Start =====================");
		logger.info("Class : ImpactPerformanceRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<ImpactPerformanceTO> info = new RestListInfo<ImpactPerformanceTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : ImpactPerformanceRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== ImpactPerformanceRPTRestController API End =====================");
	
		return info;

    }
	
	

}
