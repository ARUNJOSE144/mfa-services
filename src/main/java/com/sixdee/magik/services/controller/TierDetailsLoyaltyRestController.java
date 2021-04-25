package com.sixdee.magik.services.controller;

import java.io.IOException;
import java.util.List;

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

import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.model.CategoryLoyaltyLMSTO;
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
public class TierDetailsLoyaltyRestController {
	
	static final Logger logger = Logger.getLogger(TierDetailsLoyaltyRestController.class);
	
	@Autowired TierDetailsLoyaltyScreenService serviceLayer;
	
	
	@GetMapping(MagikServicePath.GET_TIERDTLS)
	public @ResponseBody RestListInfo<TierDetailsLMSTO> getAllTierDtls(HttpServletRequest httpServletRequest) {

		logger.info("================== TierDetailsLoyaltyRestController API Start =====================");
		logger.info("Class : TierDetailsLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<TierDetailsLMSTO> info = new RestListInfo<TierDetailsLMSTO>();
		try {
			info.setData(serviceLayer.getAllRecords());
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			if (e instanceof HibernateObjectRetrievalFailureException) 
			{
				System.err.println("Class : TierDetailsLoyaltyRestController  |  Method : getAllTierDtls ");
				System.err.println("-------Database error,Violated parent-Child relation-------");
				//System.err.println("-------ForeignKey-Relation is not handled in Tables of LMS_TELKOMSEL_DB-------");
				info.setOperationCode(-55);
				
			} 
			e.printStackTrace();
			logger.error("Class : TierDetailsLoyaltyRestController  |  Method : getAllTierDtls " + e);
		}
		logger.info("================== TierDetailsLoyaltyRestController API End =====================");
	
		return info;

    }
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_TIERDTLS)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
							@RequestBody TierDetailsLMSTO tierDetailsTO
							) throws IOException 
	{
		
		logger.info("================== TierDetailsLoyaltyRestController API Start =====================");
		logger.info("Class : TierDetailsLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateRecord(tierDetailsTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : TierDetailsLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
						//ExceptionUtil.handle(e, info);
						e.printStackTrace();
						
						if (e instanceof DataIntegrityViolationException || e.getLocalizedMessage().contains("TIER_CAT_COMBO")) {
							
							System.err.println(">>>>For Tier and Category:::Combo a unique constarint of index is Created which was violated>>>>");
							System.err.println(">>>>Tier and Category Combo Already Exist>>>>");
							info.setOperationCode(9999);
						}
						else
						{
							info.setOperationCode(155);
						}
						info.setMessage("Failure");
				     }
				logger.info("================== TierDetailsLoyaltyRestController API End =====================");
		return info;
	}
	
	@GetMapping(value = MagikServicePath.DELETE_TIERDTLS)
	public @ResponseBody RestInfo<String> deleteSelectedRecord(HttpServletRequest httpServletRequest,@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TierDetailsLoyaltyRestController API Start =====================");
		logger.info("Class : TierDetailsLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteRecord(autoId));
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
			logger.error("Class : TierDetailsLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TierDetailsLoyaltyRestController API End =====================");
		return info;
	}
	
	@PostMapping(value=MagikServicePath.SAVE_CATEGORY_TIER)
	public @ResponseBody RestInfo<List<CategoryLoyaltyLMSTO>> SaveCategory(HttpServletRequest httpServletRequest,@RequestBody CategoryLoyaltyLMSTO categoryLoyaltyTO) throws IOException 
	{
		logger.info("================== TierDetailsLoyaltyRestController API Start =====================");
		logger.info("Class : TierDetailsLoyaltyRestController | Method : SaveCategory");
		RestInfo<List<CategoryLoyaltyLMSTO>> info = new RestInfo<List<CategoryLoyaltyLMSTO>>();
					try 
					{
						
						info.setData(serviceLayer.saveTierCategory(categoryLoyaltyTO));
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : TierDetailsLoyaltyRestController  |  Method : SaveCategory " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== TierDetailsLoyaltyRestController API End =====================");
		return info;
		
	}
		
	
	

}
