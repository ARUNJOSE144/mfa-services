package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RegisterMerchantLoyaltyDAO;
import com.sixdee.magik.services.model.RegisterMerchantTO;
import com.sixdee.magik.services.service.RegisterMerchantLoyaltyService;


@Service
@Transactional
public class RegisterMerchantLoyaltyServiceImpl implements RegisterMerchantLoyaltyService {
	
	@Autowired RegisterMerchantLoyaltyDAO daoLayer;

	@Override
	public List<RegisterMerchantTO> getAllRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllRecords();
	}

	@Override
	public RegisterMerchantTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedRecord(autoId);
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return  daoLayer.deleteRecord(autoId);
	}

	@Override
	public String saveorUpdateRecord(RegisterMerchantTO registerMerchantTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(registerMerchantTO);
	}

	@Override
	public String addRecord(RegisterMerchantTO registerMerchantTO) {
		// TODO Auto-generated method stub
		return daoLayer.addRecord(registerMerchantTO);
	}

}
