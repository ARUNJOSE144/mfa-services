
package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.controller.bean.LoginResponse;
import com.sixdee.magik.services.dao.AuthenticationDao;
import com.sixdee.magik.services.model.AuthenticationTO;
import com.sixdee.magik.services.model.UserMaster;
import com.sixdee.magik.services.service.AuthenticationService;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	AuthenticationDao authenticationDao;

	@Override
	public AuthenticationTO authLoginCredentails(AuthenticationTO authTO) {
		return authenticationDao.authLoginCredentails(authTO);
	}

	@Override
	public int resetPassword(AuthenticationTO authTO) {
		return authenticationDao.resetPassword(authTO);
	}

	@Override
	public int ChangePassword(AuthenticationTO authTO) {
		// TODO Auto-generated method stub
		return authenticationDao.ChangePassword(authTO);
	}

	@Override
	public LoginResponse authorize(String userId, String token) {
		// TODO Auto-generated method stub
		return authenticationDao.authorize(userId, token);

	}

	@Override
	public int ChangeUserPassword(UserMaster userMaster) {
		// TODO Auto-generated method stub
		return authenticationDao.ChangeUserPassword(userMaster);
	}
 
}
