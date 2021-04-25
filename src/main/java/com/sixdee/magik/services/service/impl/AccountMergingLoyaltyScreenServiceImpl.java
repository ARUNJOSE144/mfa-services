package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.AccountMergingLoyaltyScreenDAO;
import com.sixdee.magik.services.model.AccountMergingTO;
import com.sixdee.magik.services.service.AccountMergingLoyaltyScreenService;

/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */

@Service
@Transactional
public class AccountMergingLoyaltyScreenServiceImpl implements AccountMergingLoyaltyScreenService{

	@Autowired AccountMergingLoyaltyScreenDAO daoLayer;
	
	@Override
	public String saveorUpdateRecord(AccountMergingTO accountMergingTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(accountMergingTO);
	}

}
