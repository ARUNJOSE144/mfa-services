package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.exception.NoObjectMatchingException;
import com.sixdee.magik.services.model.UserDetails;

/**
 * @author rajesh.b
 *
 * January, 2019
 */
public interface UserService {

	 public void create(UserDetails request, int userType, String origUserId) throws DAOException, CommonException; 
	 
	 public List<UserDetails> getUserDetails(int loginUserId);
	 
	 public List<UserDetails> getAllUserDetails();
	 
	 public void update(UserDetails request, String origUserId) throws DAOException, NoObjectMatchingException;
	 
	 public void delete(String userId, String origUserId) throws DAOException, NoObjectMatchingException;
	 
	 public UserDetails view(String origUserId, String userId) throws DAOException, NoObjectMatchingException;
	
}
