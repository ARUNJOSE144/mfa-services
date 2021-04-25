package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.LmsPackageCategoryMasterDTO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.OrderDetailsDTO;

public interface LoyaltyTransactionService {
	
	public List<LoyaltyRequestDTO> pointsHistory(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;

	public LoyaltyRequestDTO createAccount(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;

	public LoyaltyRequestDTO unsubscribeAccount(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;
	
	public OrderDetailsDTO getOrderDetails(OrderDetailsDTO orderDetailsDTO) throws Exception;

	public LmsPackageCategoryMasterDTO createCategory(LmsPackageCategoryMasterDTO categoryMasterDTO) throws Exception;

	public LmsPackageCategoryMasterDTO getCategories(LmsPackageCategoryMasterDTO categoryMasterDTO) throws Exception;

	public LmsPackageCategoryMasterDTO deleteCategory(LmsPackageCategoryMasterDTO categoryMasterDTO) throws Exception;

}
