package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LoyaltyTransactionDao;
import com.sixdee.magik.services.model.LmsPackageCategoryMasterDTO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.OrderDetailsDTO;
import com.sixdee.magik.services.service.LoyaltyTransactionService;

@Service
@Transactional
public class LoyaltyTransactionServiceImpl implements LoyaltyTransactionService {

	static final Logger logger = Logger.getLogger(LoyaltyTransactionServiceImpl.class);

	@Autowired
	private LoyaltyTransactionDao loyaltyTransactionDao;

	@Override
	public List<LoyaltyRequestDTO> pointsHistory(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		
		loyaltyRequestDTO.setTransactionList(loyaltyTransactionDao.getPointsHistoryTransactions(loyaltyRequestDTO));
	
		return loyaltyRequestDTO.getTransactionList();
	}
	
	@Override
	public LoyaltyRequestDTO createAccount(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		
		loyaltyRequestDTO= loyaltyTransactionDao.createAccount(loyaltyRequestDTO);
	
		return loyaltyRequestDTO;
	}
	@Override
	public LoyaltyRequestDTO unsubscribeAccount(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		
		loyaltyRequestDTO= loyaltyTransactionDao.unsubscribeAccount(loyaltyRequestDTO);
	
		return loyaltyRequestDTO;
	}
	

	@Override
	public OrderDetailsDTO getOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception {
		
		logger.info("IS SEARCH DTO VALUE IN BL"+orderDetailsDTO.isSearch());
		logger.info("IS DELIVER DTO VALUE IN BL"+orderDetailsDTO.isDeliver());
		
		try {
			if(orderDetailsDTO.isSearch() && !orderDetailsDTO.isDeliver()) {
				loyaltyTransactionDao.getOrderDetails(orderDetailsDTO);	
			} else {
				loyaltyTransactionDao.getOrderStatus(orderDetailsDTO);
			}
		} catch (Exception e) {
			throw e;
		}
		
		return orderDetailsDTO;
	}
	
	@Override
	public LmsPackageCategoryMasterDTO createCategory(LmsPackageCategoryMasterDTO categoryMasterDTO) throws Exception {
		
		categoryMasterDTO= loyaltyTransactionDao.createCategory(categoryMasterDTO);
	
		return categoryMasterDTO;
	}
	
	@Override
	public LmsPackageCategoryMasterDTO deleteCategory(LmsPackageCategoryMasterDTO categoryMasterDTO) throws Exception {
		
		categoryMasterDTO= loyaltyTransactionDao.deleteCategory(categoryMasterDTO);
	
		return categoryMasterDTO;
	}

	@Override
	public LmsPackageCategoryMasterDTO getCategories(LmsPackageCategoryMasterDTO categoryMasterDTO) throws Exception {
		
		categoryMasterDTO= loyaltyTransactionDao.getCategories(categoryMasterDTO);
	
		return categoryMasterDTO;
	}
}
