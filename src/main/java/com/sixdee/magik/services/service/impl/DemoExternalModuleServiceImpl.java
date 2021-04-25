package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.DemoExternalModuleDao;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.model.DemoExternalModuleTO;
import com.sixdee.magik.services.service.DemoExternalModuleService;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */
@Service
@Transactional
public class DemoExternalModuleServiceImpl implements DemoExternalModuleService {

	@Autowired
	DemoExternalModuleDao demoExternalModuleDao;

	@Override
	public DemoExternalModuleTO getActiveOffer() {
		// TODO Auto-generated method stub
		return demoExternalModuleDao.getActiveOffer();
	}

}
