package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.RegisterMerchantTO;


public interface RegisterMerchantLoyaltyService {
	
	
	public List<RegisterMerchantTO> getAllRecords();
	public RegisterMerchantTO getSelectedRecord(int autoId);
	public String deleteRecord(int autoId);
	public String saveorUpdateRecord(RegisterMerchantTO registerMerchantTO);
	public String addRecord(RegisterMerchantTO registerMerchantTO);

}
