package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CreateUserProfileDao;
import com.sixdee.magik.services.dao.TransactionHistoryDao;
import com.sixdee.magik.services.model.TransactionHistoryTO;
import com.sixdee.magik.services.service.TransactionHistoryService;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

@Service
@Transactional
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	TransactionHistoryDao transactionHistoryDao;

	@Override
	public TransactionHistoryTO getTransactionHistory(TransactionHistoryTO transTO) {
		// TODO Auto-generated method stub
		return transactionHistoryDao.getSearchTransactionHistory(transTO);
	}

}
