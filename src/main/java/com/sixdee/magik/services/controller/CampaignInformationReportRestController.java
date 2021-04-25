package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.CampaignInformationReportTO;
import com.sixdee.magik.services.service.CampaignInformationReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */
 
@RestController
public class CampaignInformationReportRestController {

static final Logger logger = Logger.getLogger(CampaignInformationReportRestController.class);
	
	@Autowired CampaignInformationReportService serviceLayer;
	
	
	/*@GetMapping(MagikServicePath.GET_CAMPIAGNNAMES_DATA)
	public @ResponseBody RestListInfo<CampaignDefMasterTO> getCampaignRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignInformationReportRestController API Start =====================");
		logger.info("Class : CampaignInformationReportRestController | Method : getCampaignRecords");
		
		
		RestListInfo<CampaignDefMasterTO> info = new RestListInfo<CampaignDefMasterTO>();
		try
		{
			info.setData(serviceLayer.getCampaignData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignInformationReportRestController  |  Method : getCampaignRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== CampaignInformationReportRestController API End =====================");
	
		return info;

    }*/
	
	@GetMapping(MagikServicePath.GET_CAMPAIGNINFORMATION_RPT_DATA)
	public @ResponseBody RestListInfo<CampaignInformationReportTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignInformationReportRestController API Start =====================");
		logger.info("Class : CampaignInformationReportRestController | Method : getAllRecords");
		
		
		RestListInfo<CampaignInformationReportTO> info = new RestListInfo<CampaignInformationReportTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : CampaignInformationReportRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== CampaignInformationReportRestController API End =====================");
	
		return info;

    }
	
	

}

