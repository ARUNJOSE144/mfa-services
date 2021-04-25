package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.TransactionHistoryTO;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
public interface TransactionHistoryService {

	TransactionHistoryTO getTransactionHistory(TransactionHistoryTO transTO);

}
