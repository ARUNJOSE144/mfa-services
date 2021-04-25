package com.sixdee.magik.services.service.impl;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.impl.UserDAOImpl;
import com.sixdee.magik.services.exception.CommonException;
import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.exception.NoObjectMatchingException;
import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.service.UserService;


/**
 * @author Rajesh
 * @version 1.0.0.0
 * @since Sep 16, 2020 : 4:54:34 PM
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserDAOImpl userDAOImpl;
	
	@Override
	public void create(UserDetails request, int userType, String origUserId) throws DAOException, CommonException {
		// TODO Auto-generated method stub
		userDAOImpl.create(request, userType, origUserId);
		
	}

	@Override
	public List<UserDetails> getUserDetails(int loginUserId) {
		// TODO Auto-generated method stub
		return userDAOImpl.getUserDetails(loginUserId);
	}

	@Override
	public void update(UserDetails request, String origUserId) throws DAOException, NoObjectMatchingException {
		// TODO Auto-generated method stub
		userDAOImpl.update(request, origUserId);
		
	}

	@Override
	public void delete(String userId, String origUserId) throws DAOException, NoObjectMatchingException {
		// TODO Auto-generated method stub
		userDAOImpl.delete(userId, origUserId);
		
	}

	@Override
	public UserDetails view(String origUserId, String userId) throws DAOException, NoObjectMatchingException {
		// TODO Auto-generated method stub
		return userDAOImpl.view(origUserId, userId);
	}

	@Override
	public List<UserDetails> getAllUserDetails() {
		return userDAOImpl.getAllUserDetails();
	}
    
    
}

   