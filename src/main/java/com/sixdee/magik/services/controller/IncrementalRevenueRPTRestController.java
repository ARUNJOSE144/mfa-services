package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ARPUBandsTO;
import com.sixdee.magik.services.model.IncrementalRevenueTO;
import com.sixdee.magik.services.service.IncrementalRevenueReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

 
@RestController
public class IncrementalRevenueRPTRestController {
	
	
	static final Logger logger = Logger.getLogger(IncrementalRevenueRPTRestController.class);
	
	@Autowired IncrementalRevenueReportService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_ARPU_BANDS_DATA)
	public @ResponseBody RestListInfo<ARPUBandsTO> getArpuData(HttpServletRequest httpServletRequest) {

		logger.info("================== IncrementalRevenueRPTRestController API Start =====================");
		logger.info("Class : IncrementalRevenueRPTRestController | Method : getArpuData");
		
		
		RestListInfo<ARPUBandsTO> info = new RestListInfo<ARPUBandsTO>();
		try
		{
			info.setData(serviceLayer.getArpuBandData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : IncrementalRevenueRPTRestController  |  Method : getArpuData " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== IncrementalRevenueRPTRestController API End =====================");
	
		return info;

    }
		
	@GetMapping(MagikServicePath.GET_INCREMENTAL_REVENUE_RPT_DATA)
	public @ResponseBody RestListInfo<IncrementalRevenueTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== IncrementalRevenueRPTRestController API Start =====================");
		logger.info("Class : IncrementalRevenueRPTRestController | Method : getAllRecords");
		
		String month = httpServletRequest.getParameter("month");
		System.out.println("======================= month  ======================"+month);
		
		String arpu = httpServletRequest.getParameter("arpu");
		String segment = httpServletRequest.getParameter("segment");
		
		RestListInfo<IncrementalRevenueTO> info = new RestListInfo<IncrementalRevenueTO>();
		try
		{
			info.setData(serviceLayer.getData(month,arpu,segment));
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : IncrementalRevenueRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== IncrementalRevenueRPTRestController API End =====================");
	
		return info;

    }
	
	

}
