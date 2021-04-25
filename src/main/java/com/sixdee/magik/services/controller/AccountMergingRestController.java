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
import com.sixdee.magik.services.service.AccountMergingLoyaltyScreenService;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */
 
@RestController
public class AccountMergingRestController {
	
static final Logger logger = Logger.getLogger(AccountMergingRestController.class);
	
	@Autowired AccountMergingLoyaltyScreenService serviceLayer;
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_ACCOUNTMERGING)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
							@RequestBody AccountMergingTO AccountMergingTO
							) throws IOException 
	{
		
		logger.info("================== AccountMergingRestController API Start =====================");
		logger.info("Class : AccountMergingRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateRecord(AccountMergingTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : AccountMergingRestController  |  Method : SaveorUpdateRecord " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== AccountMergingRestController API End =====================");
		return info;
	}
	

}
