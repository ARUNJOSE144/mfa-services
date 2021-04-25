package com.sixdee.magik.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.AccountMergingTO;
import com.sixdee.magik.services.model.AccountTransferTO;
import com.sixdee.magik.services.service.AccountMergingLoyaltyScreenService;
import com.sixdee.magik.services.service.AccountTransferLoyaltyScreenService;
import com.sixdee.magik.services.service.LoyaltyStatusLoyaltyScreenService;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */
 
@RestController
public class AccountTransferRestController {
	
	
static final Logger logger = Logger.getLogger(AccountTransferRestController.class);
	
	@Autowired AccountTransferLoyaltyScreenService serviceLayer;
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_ACCOUNTTRANSFER)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
							@RequestBody AccountTransferTO AccountTransferTO
							) throws IOException 
	{
		
		logger.info("================== AccountTransferRestController API Start =====================");
		logger.info("Class : AccountTransferRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateRecord(AccountTransferTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : AccountTransferRestController  |  Method : SaveorUpdateRecord " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== AccountTransferRestController API End =====================");
		return info;
	}
	

}

