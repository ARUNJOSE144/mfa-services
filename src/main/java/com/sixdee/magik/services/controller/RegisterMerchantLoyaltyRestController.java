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

import com.sixdee.magik.services.model.RegisterMerchantTO;
import com.sixdee.magik.services.service.RegisterMerchantLoyaltyService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

@RestController
public class RegisterMerchantLoyaltyRestController {

	static final Logger logger = Logger.getLogger(RegisterMerchantLoyaltyRestController.class);

	@Autowired
	RegisterMerchantLoyaltyService serviceLayer;

	@GetMapping(MagikServicePath.GET_ALL_MERCHANTS)
	public @ResponseBody RestListInfo<RegisterMerchantTO> getAllMerchantsDetails(
			HttpServletRequest httpServletRequest) {

		logger.info("================== RegisterMerchantLoyaltyRestController API Start =====================");
		logger.info("Class : RegisterMerchantLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<RegisterMerchantTO> info = new RestListInfo<RegisterMerchantTO>();
		try {
			info.setData(serviceLayer.getAllRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : RegisterMerchantLoyaltyRestController  |  Method : getAllTierDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== RegisterMerchantLoyaltyRestController API End =====================");

		return info;

	}

	@GetMapping(value = MagikServicePath.GET_MERCHANT_DETAILS)
	public @ResponseBody RestInfo<RegisterMerchantTO> retrieveSelectedData(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== RegisterMerchantLoyaltyRestController API Start =====================");
		logger.info("Class : RegisterMerchantLoyaltyRestController | Method : retrieveSelectedData");
		RestInfo<RegisterMerchantTO> info = new RestInfo<RegisterMerchantTO>();
		RegisterMerchantTO object = null;
		try {
			object = serviceLayer.getSelectedRecord(autoId);
			info.setData(object);
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : RegisterMerchantLoyaltyRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== RegisterMerchantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.EDIT_MERCHANT_DETAILS)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
			@RequestBody RegisterMerchantTO tierDetailsTO) throws IOException {

		logger.info("================== RegisterMerchantLoyaltyRestController API Start =====================");
		logger.info("Class : RegisterMerchantLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.saveorUpdateRecord(tierDetailsTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : RegisterMerchantLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== RegisterMerchantLoyaltyRestController API End =====================");
		return info;
	}

	@GetMapping(value = MagikServicePath.DELETE_MERCHANT)
	public @ResponseBody RestInfo<String> deleteSelectedRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== RegisterMerchantLoyaltyRestController API Start =====================");
		logger.info("Class : RegisterMerchantLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : RegisterMerchantLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== RegisterMerchantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.SAVE_MERCHANT_DETAILS)
	public @ResponseBody RestInfo<String> addRecord(HttpServletRequest httpServletRequest,
			@RequestBody RegisterMerchantTO registerMerchantTO) throws IOException {
		logger.info("================== RegisterMerchantLoyaltyRestController API Start =====================");
		logger.info("Class : RegisterMerchantLoyaltyRestController | Method : SaveCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.addRecord(registerMerchantTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : RegisterMerchantLoyaltyRestController  |  Method : SaveCategory " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== RegisterMerchantLoyaltyRestController API End =====================");
		return info;

	}

}
