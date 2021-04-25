package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CreateAccountCustmCareDAO;
import com.sixdee.magik.services.dao.RedeemPointDao;
import com.sixdee.magik.services.model.RedeemPointTO;
import com.sixdee.magik.services.service.RedeemPointService;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */

@Service
@Transactional
public class RedeemPointSeviceImpl implements RedeemPointService {

	@Autowired
	RedeemPointDao redeePointDao;

	@Override
	public RedeemPointTO getPackage(RedeemPointTO redeemPointTO) {
		// TODO Auto-generated method stub
		return redeePointDao.getPackage(redeemPointTO);
	}

	@Override
	public RedeemPointTO getRedeempoint(RedeemPointTO redeemPointTO) {
		// TODO Auto-generated method stub
		return redeePointDao.getRedeempoint(redeemPointTO);
	}
}
