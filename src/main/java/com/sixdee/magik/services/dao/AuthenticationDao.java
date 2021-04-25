package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.controller.bean.LoginResponse;
import com.sixdee.magik.services.model.AuthenticationTO;
import com.sixdee.magik.services.model.UserMaster;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public interface AuthenticationDao {

	// User validation
	public AuthenticationTO authLoginCredentails(AuthenticationTO authTO);

	// Reset password
	public int resetPassword(AuthenticationTO authTO);

	public int ChangePassword(AuthenticationTO authTO);

	public LoginResponse authorize(String userId, String token);
	
	public int ChangeUserPassword(UserMaster usereMasterTO);

}
