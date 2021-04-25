package com.sixdee.magik.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LoyaltyStatusTO;
import com.sixdee.magik.services.service.LoyaltyStatusLoyaltyScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */
 
@RestController
public class LoyaltyStatusRestController {
	
	
	
static final Logger logger = Logger.getLogger(LoyaltyStatusRestController.class);
	
	@Autowired LoyaltyStatusLoyaltyScreenService serviceLayer;

	@GetMapping(MagikServicePath.GET_LOYALTYSTATUS)
	public @ResponseBody RestListInfo<LoyaltyStatusTO> getAllVoucherDetails(HttpServletRequest httpServletRequest) {

		logger.info("================== LoyaltyStatusRestController API Start =====================");
		logger.info("Class : LoyaltyStatusRestController | Method : getAllTierDtls");
		RestListInfo<LoyaltyStatusTO> info = new RestListInfo<LoyaltyStatusTO>();
		try {
			info.setData(serviceLayer.getAllRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : LoyaltyStatusRestController  |  Method : getAllVoucherDetails " + e);
			e.printStackTrace();
		}
		logger.info("================== LoyaltyStatusRestController API End =====================");
	
		return info;

    }
	
	@GetMapping(value=MagikServicePath.EDIT_LOYALTYSTATUS)
	public @ResponseBody RestInfo<LoyaltyStatusTO> retrieveSelectedData(HttpServletRequest httpServletRequest,@RequestParam(value = "autoID") int autoId) 
	{
			logger.info("================== LoyaltyStatusRestController API Start =====================");
			logger.info("Class : LoyaltyStatusRestController | Method : retrieveSelectedData");
			RestInfo<LoyaltyStatusTO> info = new RestInfo<LoyaltyStatusTO>();
			LoyaltyStatusTO object =null;
			try 
			{
				   object=serviceLayer.getSelectedRecord(autoId);
				   info.setData(object);
				   info.setOperationCode(0);
			} 
			catch (Exception e) {
				ExceptionUtil.handle(e, info);
				logger.error("Class : LoyaltyStatusRestController  |  Method : retrieveSelectedData " + e);
				e.printStackTrace();
			}
			logger.info("================== LoyaltyStatusRestController API End =====================");
			return info;
	}
	
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_LOYALTYSTATUS)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
							@RequestBody LoyaltyStatusTO LoyaltyStatusTO
							) throws IOException 
	{
		
		logger.info("================== LoyaltyStatusRestController API Start =====================");
		logger.info("Class : LoyaltyStatusRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateRecord(LoyaltyStatusTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : LoyaltyStatusRestController  |  Method : SaveorUpdateRecord " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== LoyaltyStatusRestController API End =====================");
		return info;
	}
	
	@GetMapping(value = MagikServicePath.DELETE_LOYALTYSTATUS)
	public @ResponseBody RestInfo<String> deleteSelectedRecord(HttpServletRequest httpServletRequest,@RequestParam(value = "autoID") int autoId) {
		logger.info("================== LoyaltyStatusRestController API Start =====================");
		logger.info("Class : LoyaltyStatusRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : LoyaltyStatusRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== LoyaltyStatusRestController API End =====================");
		return info;
	}
	
}
