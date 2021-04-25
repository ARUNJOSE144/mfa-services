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

import com.sixdee.magik.services.model.VoucherDetailsTO;
import com.sixdee.magik.services.service.VoucherDetailsLoyaltyScreenService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */
 
@RestController
public class VoucherDetailsRestController {
	
static final Logger logger = Logger.getLogger(VoucherDetailsRestController.class);
	
	@Autowired VoucherDetailsLoyaltyScreenService serviceLayer;

	@GetMapping(MagikServicePath.GET_VOUCHERDTLS)
	public @ResponseBody RestListInfo<VoucherDetailsTO> getAllVoucherDetails(HttpServletRequest httpServletRequest) {

		logger.info("================== VoucherDetailsRestController API Start =====================");
		logger.info("Class : VoucherDetailsRestController | Method : getAllTierDtls");
		RestListInfo<VoucherDetailsTO> info = new RestListInfo<VoucherDetailsTO>();
		try {
			info.setData(serviceLayer.getAllRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : VoucherDetailsRestController  |  Method : getAllVoucherDetails " + e);
			e.printStackTrace();
		}
		logger.info("================== VoucherDetailsRestController API End =====================");
	
		return info;

    }
	
	@GetMapping(value=MagikServicePath.EDIT_VOUCHERDTLS)
	public @ResponseBody RestInfo<VoucherDetailsTO> retrieveSelectedData(HttpServletRequest httpServletRequest,@RequestParam(value = "autoID") int autoId) 
	{
			logger.info("================== VoucherDetailsRestController API Start =====================");
			logger.info("Class : VoucherDetailsRestController | Method : retrieveSelectedData");
			RestInfo<VoucherDetailsTO> info = new RestInfo<VoucherDetailsTO>();
			VoucherDetailsTO object =null;
			try 
			{
				   object=serviceLayer.getSelectedRecord(autoId);
				   info.setData(object);
				   info.setOperationCode(0);
			} 
			catch (Exception e) {
				ExceptionUtil.handle(e, info);
				logger.error("Class : VoucherDetailsRestController  |  Method : retrieveSelectedData " + e);
				e.printStackTrace();
			}
			logger.info("================== VoucherDetailsRestController API End =====================");
			return info;
	}
	
	
	@PostMapping(value=MagikServicePath.SAVEORUPDATE_VOUCHERDTLS)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
							@RequestBody VoucherDetailsTO voucherDetailsTO
							) throws IOException 
	{
		
		logger.info("================== VoucherDetailsRestController API Start =====================");
		logger.info("Class : VoucherDetailsRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode= "SC0001";
					try 
					{
						
						statuscode=serviceLayer.saveorUpdateRecord(voucherDetailsTO);
						info.setOperationCode(0);
						info.setMessage("Success");
					} 
				    catch (Exception e) {
						logger.error("Class : VoucherDetailsRestController  |  Method : SaveorUpdateRecord " + e);
						//ExceptionUtil.handle(e, info);
						info.setOperationCode(9999);
						e.printStackTrace();
						info.setMessage("Failure");
				     }
				logger.info("================== VoucherDetailsRestController API End =====================");
		return info;
	}
	
	@GetMapping(value = MagikServicePath.DELETE_VOUCHERDTLS)
	public @ResponseBody RestInfo<String> deleteSelectedRecord(HttpServletRequest httpServletRequest,@RequestParam(value = "autoID") int autoId) {
		logger.info("================== VoucherDetailsRestController API Start =====================");
		logger.info("Class : VoucherDetailsRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : VoucherDetailsRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== VoucherDetailsRestController API End =====================");
		return info;
	}
	
}
