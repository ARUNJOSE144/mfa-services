package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.RegionOfferUptakeTO;
import com.sixdee.magik.services.service.RegionOfferUptakeReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

 
@RestController
public class RegionOfferUptakeRPTRestController {
	
	
	static final Logger logger = Logger.getLogger(RegionOfferUptakeRPTRestController.class);
	
	@Autowired RegionOfferUptakeReportService serviceLayer;
	
	
			
	@GetMapping(MagikServicePath.GET_REGION_OFFER_UPTAKE_RPT_DATA)
	public @ResponseBody RestListInfo<RegionOfferUptakeTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== RegionwiseOfferImpactRPTRestController API Start =====================");
		logger.info("Class : RegionwiseOfferImpactRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<RegionOfferUptakeTO> info = new RestListInfo<RegionOfferUptakeTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : RegionwiseOfferImpactRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== RegionwiseOfferImpactRPTRestController API End =====================");
	
		return info;

    }
	
	

}
