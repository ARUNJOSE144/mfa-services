package com.sixdee.magik.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CategoryLoyaltyLMSTO;
import com.sixdee.magik.services.model.TierConfigurationLMSTO;
import com.sixdee.magik.services.model.TierDetailsLMSTO;
import com.sixdee.magik.services.service.TierDetailsLoyaltyScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */
 
@RestController
public class TierConfigurationLoyaltyRestController {
	
static final Logger logger = Logger.getLogger(TierConfigurationLoyaltyRestController.class);
	
	@Autowired TierDetailsLoyaltyScreenService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_CATEGORYGDTLS)
	public @ResponseBody RestListInfo<CategoryLoyaltyLMSTO> getAllCategoryDtls(HttpServletRequest httpServletRequest) {

		logger.info("================== TierConfigurationLoyaltyRestController API Start =====================");
		logger.info("Class : TierConfigurationLoyaltyRestController | Method : getAllCategoryDtls");
		RestListInfo<CategoryLoyaltyLMSTO> info = new RestListInfo<CategoryLoyaltyLMSTO>();
		try {
			info.setData(serviceLayer.getAllCategory());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TierConfigurationLoyaltyRestController  |  Method : getAllCategoryDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TierConfigurationLoyaltyRestController API End =====================");
	
		return info;

    }
	
	
	@GetMapping(MagikServicePath.GET_TIERCONFIGDTLS)
	public @ResponseBody RestListInfo<TierConfigurationLMSTO> getAllTierConfigDtls(HttpServletRequest httpServletRequest) {

		logger.info("================== TierConfigurationLoyaltyRestController API Start =====================");
		logger.info("Class : TierConfigurationLoyaltyRestController | Method : getAllTierConfigDtls");
		RestListInfo<TierConfigurationLMSTO> info = new RestListInfo<TierConfigurationLMSTO>();
		try {
			info.setData(serviceLayer.getAllConfigRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TierConfigurationLoyaltyRestController  |  Method : getAllTierConfigDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TierConfigurationLoyaltyRestController API End =====================");
	
		return info;

    }
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_TIERCONFIG)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
							@RequestBody TierConfigurationLMSTO tierConfigurationTO
							) throws IOException 
	{
		
		logger.info("================== TierConfigurationLoyaltyRestController API Start =====================");
		logger.info("Class : TierConfigurationLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateConfigRecord(tierConfigurationTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : TierConfigurationLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== TierConfigurationLoyaltyRestController API End =====================");
		return info;
	}
	
	@GetMapping(value = MagikServicePath.DELETE_TIERCONFIG)
	public @ResponseBody RestInfo<String> deleteSelectedRecord(HttpServletRequest httpServletRequest,@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TierConfigurationLoyaltyRestController API Start =====================");
		logger.info("Class : TierConfigurationLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
		try {
			 statuscode=serviceLayer.deleteConfigRecord(autoId);
				info.setData(statuscode);
				if(statuscode.equals("SC0005"))
				     info.setOperationCode(-55);
				else
					info.setOperationCode(0);
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			if (e instanceof HibernateObjectRetrievalFailureException || e instanceof DataIntegrityViolationException) 
			{
				System.err.println("Class : TierConfigurationLoyaltyRestController  |  Method : deleteSelectedRecord ");
				System.err.println("-------Database error,Violated parent-Child relation-------");
				//System.err.println("-------ForeignKey-Relation is not handled in Tables of LMS_TELKOMSEL_DB-------");
				info.setOperationCode(-55);
				
			} 
			logger.error("Class : TierConfigurationLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TierConfigurationLoyaltyRestController API End =====================");
		return info;
	}

}
