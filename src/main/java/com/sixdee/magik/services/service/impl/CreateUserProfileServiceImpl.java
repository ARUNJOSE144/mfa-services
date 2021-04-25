package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CreateAccountCustmCareDAO;
import com.sixdee.magik.services.dao.CreateUserProfileDao;
import com.sixdee.magik.services.model.CreateUSerProfileTO;
import com.sixdee.magik.services.service.CreateUserProfileCustmCareService;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

@Service
@Transactional
public class CreateUserProfileServiceImpl implements CreateUserProfileCustmCareService {

	@Autowired
	CreateUserProfileDao createUserProfileDao;

	@Override
	public CreateUSerProfileTO getUserProfiles(CreateUSerProfileTO profileTO) {
		// TODO Auto-generated method stub
		return createUserProfileDao.getUserProfiles(profileTO);
	}

	

}
