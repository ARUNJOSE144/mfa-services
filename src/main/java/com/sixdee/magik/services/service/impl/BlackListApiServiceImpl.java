package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.adaptor.BlackListApiTO;
import com.sixdee.magik.services.dao.BlackListApiDao;
import com.sixdee.magik.services.service.BlackListApiService;

/**
 * @author Nakhil Kurian
 * @Date : April 2020
 *
 */
@Service
@Transactional
public class BlackListApiServiceImpl implements BlackListApiService {
	@Autowired
	BlackListApiDao blackListApiDao;

	@Override
	public BlackListApiTO updateBlackListApi(BlackListApiTO blackListTO) {
		// TODO Auto-generated method stub
		return blackListApiDao.updateBlackListApi(blackListTO);
	}

	@Override
	public BlackListApiTO getStatus(BlackListApiTO blackListTO) {
		// TODO Auto-generated method stub
		return blackListApiDao.getStatus(blackListTO);
	}

}
