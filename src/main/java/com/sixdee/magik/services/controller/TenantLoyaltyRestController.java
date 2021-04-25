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

import com.sixdee.magik.services.model.CardClassTO;
import com.sixdee.magik.services.model.CustomerTO;
import com.sixdee.magik.services.model.CycleCodeTO;
import com.sixdee.magik.services.model.TenantConfigurationTO;
import com.sixdee.magik.services.model.TenantTO;
import com.sixdee.magik.services.service.TenantLoyaltyService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

@RestController
public class TenantLoyaltyRestController {

	static final Logger logger = Logger.getLogger(TenantLoyaltyRestController.class);

	@Autowired
	TenantLoyaltyService serviceLayer;

	@GetMapping(MagikServicePath.GET_ALL_COUNTRIES)
	public @ResponseBody RestListInfo<TenantTO> getAllMerchantsDetails(
			HttpServletRequest httpServletRequest) {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<TenantTO> info = new RestListInfo<TenantTO>();
		try {
			info.setData(serviceLayer.getAllRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : getAllTierDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");

		return info;

	}

	@GetMapping(value = MagikServicePath.GET_COUNTRY_DETAILS)
	public @ResponseBody RestInfo<TenantTO> retrieveSelectedData(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : retrieveSelectedData");
		RestInfo<TenantTO> info = new RestInfo<TenantTO>();
		TenantTO object = null;
		try {
			object = serviceLayer.getSelectedRecord(autoId);
			info.setData(object);
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.EDIT_COUNTRY_DETAILS)
	public @ResponseBody RestInfo<String> SaveorUpdateRecord(HttpServletRequest httpServletRequest,
			@RequestBody TenantTO tierDetailsTO) throws IOException {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.saveorUpdateRecord(tierDetailsTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@GetMapping(value = MagikServicePath.DELETE_COUNTRY)
	public @ResponseBody RestInfo<String> deleteSelectedRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.SAVE_COUNTRY_DETAILS)
	public @ResponseBody RestInfo<String> addRecord(HttpServletRequest httpServletRequest,
			@RequestBody TenantTO registerMerchantTO) throws IOException {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.addRecord(registerMerchantTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveCategory " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;

	}
	
	// customer class
	
	@GetMapping(MagikServicePath.GET_ALL_CUSTOMER_CLASS)
	public @ResponseBody RestListInfo<CustomerTO> getAllCustomerRecords(
			HttpServletRequest httpServletRequest) {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<CustomerTO> info = new RestListInfo<CustomerTO>();
		try {
			info.setData(serviceLayer.getAllCustomerRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : getAllTierDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");

		return info;

	}

	@GetMapping(value = MagikServicePath.GET_CUSTOMER_DETAILS)
	public @ResponseBody RestInfo<CustomerTO> getSelectedCustomerRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : retrieveSelectedData");
		RestInfo<CustomerTO> info = new RestInfo<CustomerTO>();
		CustomerTO object = null;
		try {
			object = serviceLayer.getSelectedCustomerRecord(autoId);
			info.setData(object);
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.EDIT_CUSTOMER_DETAILS)
	public @ResponseBody RestInfo<String> saveorUpdateCustomerRecord(HttpServletRequest httpServletRequest,
			@RequestBody CustomerTO tierDetailsTO) throws IOException {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.saveorUpdateCustomerRecord(tierDetailsTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@GetMapping(value = MagikServicePath.DELETE_CUSTOMER)
	public @ResponseBody RestInfo<String> deleteCustomerRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteCustomerRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.SAVE_CUSTOMER_DETAILS)
	public @ResponseBody RestInfo<String> addCustomerRecord(HttpServletRequest httpServletRequest,
			@RequestBody CustomerTO registerMerchantTO) throws IOException {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.addCustomerRecord(registerMerchantTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveCategory " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;

	}
	
	// card class
	
	@GetMapping(MagikServicePath.GET_ALL_CARD_CLASS)
	public @ResponseBody RestListInfo<CardClassTO> getAllCardRecords(
			HttpServletRequest httpServletRequest) {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<CardClassTO> info = new RestListInfo<CardClassTO>();
		try {
			info.setData(serviceLayer.getAllCardRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : getAllTierDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");

		return info;

	}

	@GetMapping(value = MagikServicePath.GET_CARD_DETAILS)
	public @ResponseBody RestInfo<CardClassTO> getSelectedCardRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : retrieveSelectedData");
		RestInfo<CardClassTO> info = new RestInfo<CardClassTO>();
		CardClassTO object = null;
		try {
			object = serviceLayer.getSelectedCardRecord(autoId);
			info.setData(object);
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.EDIT_CARD_DETAILS)
	public @ResponseBody RestInfo<String> saveorUpdateCardRecord(HttpServletRequest httpServletRequest,
			@RequestBody CardClassTO tierDetailsTO) throws IOException {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.saveorUpdateCardRecord(tierDetailsTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@GetMapping(value = MagikServicePath.DELETE_CARD)
	public @ResponseBody RestInfo<String> deleteCardRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteCardRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.SAVE_CARD_DETAILS)
	public @ResponseBody RestInfo<String> addCardRecord(HttpServletRequest httpServletRequest,
			@RequestBody CardClassTO registerMerchantTO) throws IOException {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.addCardRecord(registerMerchantTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveCategory " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;

	}
	
	// cycle code
	
	@GetMapping(MagikServicePath.GET_ALL_CYCLE_CODE)
	public @ResponseBody RestListInfo<CycleCodeTO> getAllCycleRecords(
			HttpServletRequest httpServletRequest) {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<CycleCodeTO> info = new RestListInfo<CycleCodeTO>();
		try {
			info.setData(serviceLayer.getAllCycleRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : getAllTierDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");

		return info;

	}

	@GetMapping(value = MagikServicePath.GET_CYCLE_DETAILS)
	public @ResponseBody RestInfo<CycleCodeTO> getSelectedCycleRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : retrieveSelectedData");
		RestInfo<CycleCodeTO> info = new RestInfo<CycleCodeTO>();
		CycleCodeTO object = null;
		try {
			object = serviceLayer.getSelectedCycleRecord(autoId);
			info.setData(object);
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.EDIT_CYCLE_DETAILS)
	public @ResponseBody RestInfo<String> saveorUpdateCycleRecord(HttpServletRequest httpServletRequest,
			@RequestBody CycleCodeTO tierDetailsTO) throws IOException {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.saveorUpdateCycleRecord(tierDetailsTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@GetMapping(value = MagikServicePath.DELETE_CYCLE)
	public @ResponseBody RestInfo<String> deleteCycleRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteCycleRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.SAVE_CYCLE_DETAILS)
	public @ResponseBody RestInfo<String> addCycleRecord(HttpServletRequest httpServletRequest,
			@RequestBody CycleCodeTO registerMerchantTO) throws IOException {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.addCycleRecord(registerMerchantTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveCategory " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;

	}
	
	//Tenant Configuration
	
	@GetMapping(MagikServicePath.GET_ALL_TENANTS)
	public @ResponseBody RestListInfo<TenantConfigurationTO> getAllTenantRecords(
			HttpServletRequest httpServletRequest) {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : getAllTierDtls");
		RestListInfo<TenantConfigurationTO> info = new RestListInfo<TenantConfigurationTO>();
		try {
			info.setData(serviceLayer.getAllTenantRecords());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : getAllTierDtls " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");

		return info;

	}

	@GetMapping(value = MagikServicePath.GET_TENANT_DETAILS)
	public @ResponseBody RestInfo<TenantConfigurationTO> getSelectedTenantRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : retrieveSelectedData");
		RestInfo<TenantConfigurationTO> info = new RestInfo<TenantConfigurationTO>();
		TenantConfigurationTO object = null;
		try {
			object = serviceLayer.getSelectedTenantRecord(autoId);
			info.setData(object);
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : retrieveSelectedData " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.EDIT_TENANT_DETAILS)
	public @ResponseBody RestInfo<String> saveorUpdateTenantRecord(HttpServletRequest httpServletRequest,
			@RequestBody TenantConfigurationTO tierDetailsTO) throws IOException {

		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.saveorUpdateTenantRecord(tierDetailsTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@GetMapping(value = MagikServicePath.DELETE_TENANT)
	public @ResponseBody RestInfo<String> deleteTenantRecord(HttpServletRequest httpServletRequest,
			@RequestParam(value = "autoID") int autoId) {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : deleteSelectedRecord");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(serviceLayer.deleteTenantRecord(autoId));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TenantLoyaltyRestController  |  Method : deleteSelectedRecord " + e);
			e.printStackTrace();
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;
	}

	@PostMapping(value = MagikServicePath.SAVE_TENANT_DETAILS)
	public @ResponseBody RestInfo<String> addTenantRecord(HttpServletRequest httpServletRequest,
			@RequestBody TenantConfigurationTO registerMerchantTO) throws IOException {
		logger.info("================== TenantLoyaltyRestController API Start =====================");
		logger.info("Class : TenantLoyaltyRestController | Method : SaveCategory");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.addTenantRecord(registerMerchantTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TenantLoyaltyRestController  |  Method : SaveCategory " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TenantLoyaltyRestController API End =====================");
		return info;

	}

}
