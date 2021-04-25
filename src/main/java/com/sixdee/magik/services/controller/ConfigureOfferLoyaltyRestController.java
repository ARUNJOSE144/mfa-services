package com.sixdee.magik.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ConfigureOfferLMSTransientTO;
import com.sixdee.magik.services.model.ConfigureOfferNomenclatureLMSTO;
import com.sixdee.magik.services.model.SegmentDtlsLMSTO;
import com.sixdee.magik.services.service.ConfigureOfferLoyaltyService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@RestController
public class ConfigureOfferLoyaltyRestController {
	
	
	@Autowired ConfigureOfferLoyaltyService serviceLayer;
	
	
	static final Logger logger = Logger.getLogger(ConfigureOfferLoyaltyRestController.class);
	
	
	@GetMapping(value=MagikServicePath.GET_SEGMENT_and_DRPDWN_DTLS)
	public @ResponseBody RestInfo<SegmentDtlsLMSTO> getSegmentDtls(HttpServletRequest httpServletRequest) 
	{
		
		logger.info("================== ConfigureOfferLoyaltyRestController API Start =====================");
		logger.info("Class : ConfigureOfferLoyaltyRestController | Method : getSegmentDtls");
		RestInfo<SegmentDtlsLMSTO> info = new RestInfo<SegmentDtlsLMSTO>();
		try {
			  info.setData(serviceLayer.segmentDtls());
			  info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ConfigureOfferLoyaltyRestController  |  Method : getSegmentDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== ConfigureOfferLoyaltyRestController API End =====================");
		return info;
		
	}
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_OFFER)
	public @ResponseBody RestInfo<String> SaveorUpdateOffer(HttpServletRequest httpServletRequest,
							@RequestBody ConfigureOfferLMSTransientTO requestTO
							) throws IOException 
	{
		
		logger.info("================== ConfigureOfferLoyaltyRestController API Start =====================");
		logger.info("Class : ConfigureOfferLoyaltyRestController | Method : SaveorUpdateOffer");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveOrUpdateConfigureObjects(requestTO);
						info.setOperationCode(0);
						info.setMessage("Success");
						info.setData(statuscode);
					} 
				    catch (Exception e) {
						logger.error("Class : ConfigureOfferLoyaltyRestController  |  Method : SaveorUpdateOffer " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== ConfigureOfferLoyaltyRestController API End =====================");
			
					
		return info;
			
		
	}
	
	@GetMapping(value=MagikServicePath.GET_ALL_CONFIGUREOFFERDTLS)
	public @ResponseBody RestListInfo<ConfigureOfferNomenclatureLMSTO> getAllOffers(HttpServletRequest httpServletRequest) 
	{
		
		logger.info("================== ConfigureOfferLoyaltyRestController API Start =====================");
		logger.info("Class : ConfigureOfferLoyaltyRestController | Method : getAllOffers");
		RestListInfo<ConfigureOfferNomenclatureLMSTO> info = new RestListInfo<ConfigureOfferNomenclatureLMSTO>();
		try {
			  info.setData(serviceLayer.getAllOffers());
			  info.setOperationCode(0);
		} 
		catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ConfigureOfferLoyaltyRestController  |  Method : getAllOffers " + e);
			e.printStackTrace();
		}
		logger.info("================== ConfigureOfferLoyaltyRestController API End =====================");
		return info;
		
	}
	
	
	
	
	

}
