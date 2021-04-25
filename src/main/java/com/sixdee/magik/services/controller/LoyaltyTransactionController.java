package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.dao.impl.StatusCodeDAOImpl;
import com.sixdee.magik.services.model.LmsPackageCategoryMasterDTO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.OrderDetailsDTO;
import com.sixdee.magik.services.model.StatusCodeDTO;
import com.sixdee.magik.services.service.LoyaltyTransactionService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author amal.a.s
 * @Date : April, 2019
 *
 */

@RestController
public class LoyaltyTransactionController {

	static final Logger logger = Logger.getLogger(LoyaltyTransactionController.class);

	@Autowired
	private LoyaltyTransactionService loyaltyTransactionService;

	@PostMapping("/{talent}/pointsHistory")
	public @ResponseBody RestListInfo<LoyaltyRequestDTO> pointsHistory(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO) {
		logger.info("LoyaltyTransactionController>>>>>>>Points History>>>>>>>");
		RestListInfo<LoyaltyRequestDTO> info = new RestListInfo<LoyaltyRequestDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.pointsHistory(loyaltyRequestDTO));
				if(loyaltyRequestDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(loyaltyRequestDTO.getStatusDescription());
				
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/createAccount")
	public @ResponseBody RestInfo<LoyaltyRequestDTO> createAccount(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO) {
		logger.info("LoyaltyTransactionController>>>>>Create Account>>>>>>>");
		RestInfo<LoyaltyRequestDTO> info = new RestInfo<LoyaltyRequestDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.createAccount(loyaltyRequestDTO));
				if(loyaltyRequestDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(loyaltyRequestDTO.getStatusDescription());
				
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	@PostMapping("/{talent}/unsubscribeAccount")
	public @ResponseBody RestInfo<LoyaltyRequestDTO> unsubscribeAccount(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO) {
		logger.info("LoyaltyTransactionController>>>>>Create Account>>>>>>>");
		RestInfo<LoyaltyRequestDTO> info = new RestInfo<LoyaltyRequestDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.unsubscribeAccount(loyaltyRequestDTO));
				if(loyaltyRequestDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(loyaltyRequestDTO.getStatusDescription());
				
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/getOrderDetails")
	public @ResponseBody RestInfo<OrderDetailsDTO> getOrderDetails(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody OrderDetailsDTO orderDetailsDTO) {
		logger.info("LoyaltyTransactionController>>>>>getOrderDetails>>>>>>>");
		RestInfo<OrderDetailsDTO> info = new RestInfo<OrderDetailsDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.getOrderDetails(orderDetailsDTO));
				if(orderDetailsDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(orderDetailsDTO.getStatus());
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PostMapping("/{talent}/categoryManagement")
	public @ResponseBody RestInfo<LmsPackageCategoryMasterDTO> createCategory(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LmsPackageCategoryMasterDTO categoryMasterDTO) {
		logger.info("LoyaltyTransactionController>>>>>createCategory>>>>>>>");
		RestInfo<LmsPackageCategoryMasterDTO> info = new RestInfo<LmsPackageCategoryMasterDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.createCategory(categoryMasterDTO));
				StatusCodeDTO statusCodeDTO = StatusCodeDAOImpl.cacheMap.get(categoryMasterDTO.getStatusCode());
				categoryMasterDTO.setStatusCode(statusCodeDTO.getStatusCode());
				if(statusCodeDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(statusCodeDTO.getStatusDescription());
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@PutMapping("/{talent}/categoryManagement")
	public @ResponseBody RestInfo<LmsPackageCategoryMasterDTO> updateCategory(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LmsPackageCategoryMasterDTO categoryMasterDTO) {
		logger.info("LoyaltyTransactionController>>>>>updateCategory>>>>>>>");
		RestInfo<LmsPackageCategoryMasterDTO> info = new RestInfo<LmsPackageCategoryMasterDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.createCategory(categoryMasterDTO));
				StatusCodeDTO statusCodeDTO = StatusCodeDAOImpl.cacheMap.get(categoryMasterDTO.getStatusCode());
				categoryMasterDTO.setStatusCode(statusCodeDTO.getStatusCode());
				if(statusCodeDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(statusCodeDTO.getStatusDescription());
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@DeleteMapping("/{talent}/categoryManagement")
	public @ResponseBody RestInfo<LmsPackageCategoryMasterDTO> deleteCategory(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LmsPackageCategoryMasterDTO categoryMasterDTO) {
		logger.info("LoyaltyTransactionController>>>>>updateCategory>>>>>>>");
		RestInfo<LmsPackageCategoryMasterDTO> info = new RestInfo<LmsPackageCategoryMasterDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.deleteCategory(categoryMasterDTO));
				StatusCodeDTO statusCodeDTO = StatusCodeDAOImpl.cacheMap.get(categoryMasterDTO.getStatusCode());
				categoryMasterDTO.setStatusCode(statusCodeDTO.getStatusCode());
				if(statusCodeDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(statusCodeDTO.getStatusDescription());
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
	
	@GetMapping("/{talent}/categoryManagement")
	public @ResponseBody RestInfo<LmsPackageCategoryMasterDTO> getCategories(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LmsPackageCategoryMasterDTO categoryMasterDTO) {
		logger.info("LoyaltyTransactionController>>>>>getCategories>>>>>>>");
		RestInfo<LmsPackageCategoryMasterDTO> info = new RestInfo<LmsPackageCategoryMasterDTO>();
		try {
			
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyTransactionService.getCategories(categoryMasterDTO));
				if(categoryMasterDTO.getStatusCode().equalsIgnoreCase("SC0000")) {
					info.setOperationCode(0);	
				}else {
					info.setOperationCode(1);
				}
				info.setMessage(categoryMasterDTO.getStatusDescription());
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
}
