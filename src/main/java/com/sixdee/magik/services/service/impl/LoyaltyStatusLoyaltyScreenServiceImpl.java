package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LoyaltyStatusLoyaltyScreenDAO;
import com.sixdee.magik.services.dao.VoucherDetailsLoyaltyScreenDAO;
import com.sixdee.magik.services.model.LoyaltyStatusTO;
import com.sixdee.magik.services.service.LoyaltyStatusLoyaltyScreenService;


/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */

@Service
@Transactional
public class LoyaltyStatusLoyaltyScreenServiceImpl implements LoyaltyStatusLoyaltyScreenService{

	@Autowired LoyaltyStatusLoyaltyScreenDAO daoLayer;
	
	@Override
	public List<LoyaltyStatusTO> getAllRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllRecords();

	}

	@Override
	public LoyaltyStatusTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedRecord(autoId);
	}

	@Override
	public String saveorUpdateRecord(LoyaltyStatusTO loyaltyStatusTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(loyaltyStatusTO);
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return  daoLayer.deleteRecord(autoId);
	}

}
