package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.ImpactPerformanceTO;
import com.sixdee.magik.services.model.RegionwiseOfferImpactTO;
import com.sixdee.magik.services.service.RegionwiseOfferImpactReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

 
@RestController
public class RegionwiseOfferImpactRPTRestController {
	
	
	static final Logger logger = Logger.getLogger(RegionwiseOfferImpactRPTRestController.class);
	
	@Autowired RegionwiseOfferImpactReportService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_CIRCLE_DATA)
	public @ResponseBody RestListInfo<CircleTO> getCircleData(HttpServletRequest httpServletRequest) {

		logger.info("================== RegionwiseOfferImpactRPTRestController API Start =====================");
		logger.info("Class : RegionwiseOfferImpactRPTRestController | Method : getCampaignRecords");
		
		
		RestListInfo<CircleTO> info = new RestListInfo<CircleTO>();
		try
		{
			info.setData(serviceLayer.getCircleData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : RegionwiseOfferImpactRPTRestController  |  Method : getCampaignRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== RegionwiseOfferImpactRPTRestController API End =====================");
	
		return info;

    }
		
	@GetMapping(MagikServicePath.GET_REGIONWISE_OFFER_IMPACT_RPT_DATA)
	public @ResponseBody RestListInfo<RegionwiseOfferImpactTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== RegionwiseOfferImpactRPTRestController API Start =====================");
		logger.info("Class : RegionwiseOfferImpactRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<RegionwiseOfferImpactTO> info = new RestListInfo<RegionwiseOfferImpactTO>();
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
