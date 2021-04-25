package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CreateAccountCustmCareDAO;
import com.sixdee.magik.services.model.CreateAccountCustmCareTO;
import com.sixdee.magik.services.service.CreateAccountCustmCareService;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

@Service
@Transactional
public class CreateAccountCustmCareServiceImpl implements CreateAccountCustmCareService {

	@Autowired
	CreateAccountCustmCareDAO createAccountDao;

	@Override
	public CreateAccountCustmCareTO CreateAccount(CreateAccountCustmCareTO createAccountTO) {
		// TODO Auto-generated method stub
		return createAccountDao.CreateAccount(createAccountTO);
	}

	@Override
	public CreateAccountCustmCareTO deleteAccount(CreateAccountCustmCareTO deleteAccountTO) {
		// TODO Auto-generated method stub
		return createAccountDao.deleteAccount(deleteAccountTO);
	}

}
