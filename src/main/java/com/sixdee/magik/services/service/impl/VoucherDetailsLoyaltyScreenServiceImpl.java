package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.VoucherDetailsLoyaltyScreenDAO;
import com.sixdee.magik.services.model.VoucherDetailsTO;
import com.sixdee.magik.services.service.VoucherDetailsLoyaltyScreenService;


/**
 * @author: JANARDHAN REDDY
 * @Date  : March, 2021
 */

@Service
@Transactional
public class VoucherDetailsLoyaltyScreenServiceImpl implements VoucherDetailsLoyaltyScreenService{

	@Autowired VoucherDetailsLoyaltyScreenDAO daoLayer;
	
	@Override
	public List<VoucherDetailsTO> getAllRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllRecords();
	}

	@Override
	public VoucherDetailsTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedRecord(autoId);
	}

	@Override
	public String saveorUpdateRecord(VoucherDetailsTO voucherDetailsTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(voucherDetailsTO);
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return  daoLayer.deleteRecord(autoId);
	}

}
