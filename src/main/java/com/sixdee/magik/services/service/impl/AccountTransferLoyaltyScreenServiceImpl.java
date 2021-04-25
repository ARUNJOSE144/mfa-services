package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.AccountTransferLoyaltyScreenDAO;
import com.sixdee.magik.services.model.AccountTransferTO;
import com.sixdee.magik.services.service.AccountTransferLoyaltyScreenService;

/**
 * @author: JANARDHAN REDDY
 * @Date : March, 2021
 */

@Service
@Transactional
public class AccountTransferLoyaltyScreenServiceImpl implements AccountTransferLoyaltyScreenService{

	@Autowired AccountTransferLoyaltyScreenDAO daoLayer;
	
	@Override
	public String saveorUpdateRecord(AccountTransferTO accountTransferTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(accountTransferTO);
	}

}
