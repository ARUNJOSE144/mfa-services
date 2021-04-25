package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.MTDLMTDTO;
import com.sixdee.magik.services.service.MTDLMTDReportService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

 
@RestController
public class MTDLMTDRPTRestController {
	
	
	static final Logger logger = Logger.getLogger(MTDLMTDRPTRestController.class);
	
	@Autowired MTDLMTDReportService serviceLayer;
	
		
	@GetMapping(MagikServicePath.GET_MTD_LMTD_RPT_DATA)
	public @ResponseBody RestListInfo<MTDLMTDTO> getAllRecords(HttpServletRequest httpServletRequest) {

		logger.info("================== MTDLMTDRPTRestController API Start =====================");
		logger.info("Class : MTDLMTDRPTRestController | Method : getAllRecords");
		
		
		RestListInfo<MTDLMTDTO> info = new RestListInfo<MTDLMTDTO>();
		try
		{
			info.setData(serviceLayer.getData());
		} catch (Exception e) {
			
			ExceptionUtil.handle(e, info);
			logger.error("Class : MTDLMTDRPTRestController  |  Method : getAllRecords " + e);
			e.printStackTrace();
		}
		
		
		logger.info("================== MTDLMTDRPTRestController API End =====================");
	
		return info;

    }
	
	

}
