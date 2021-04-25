
package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CacheDao;
import com.sixdee.magik.services.service.CacheService;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class CacheServiceImpl implements CacheService {

	@Autowired
	CacheDao cacheDao;

	@Override
	public void cacheMessages() {
		cacheDao.cacheMessages();
	}

}
