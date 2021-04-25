/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.cache.DbCacheManager;
import com.sixdee.magik.services.exception.DAOException;
import com.sixdee.magik.services.exception.NoObjectMatchingException;
import com.sixdee.magik.services.model.UserAuth;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Afthab
 * @version 1.0.0.0
 * @since 30-Nov-2017 : 12:54:58 PM
 */

@Component
@Service
public class UserAuthServiceImpl {
	
	private static Log LOGGER = LogFactory.getLog(UserAuthServiceImpl.class);

//	private static Logger LOGGER = LoggerFactory.getLogger(UserAuthServiceImpl.class);


	@Autowired
	DbCacheManager dbCacheManager;

	/**
	 * 
	 * @param authorization
	 * @return UserAuth
	 * @throws DAOException
	 */
	public UserAuth findByAuthorization(String authorization) throws DAOException {
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("UserAuthServiceImpl.findByAuthorization:: status : processing...");
		}
		UserAuth userAuth = null;
		try {
			if (LOGGER.isInfoEnabled()) {
				LOGGER.info("UserAuthServiceImpl.findByAuthorization:: total auths... "+dbCacheManager.getAuths() + ", CURRENT AUTH----->"+authorization+"<<<");
			}
			userAuth = dbCacheManager.getAuths().get(authorization);
			if (userAuth == null) {
				throw new NoObjectMatchingException(
						"No User Auth found for " + " UserAuthServiceImpl Authorization : " + authorization);
			}
		} catch (Exception e) {
			throw new DAOException("Exception in DAO Layer (UserAuthServiceImpl.findByAuthorization) " + e + "\n"
					+ e.getMessage() + "\n" + e.getCause());
		}
		if (LOGGER.isInfoEnabled()) {
			LOGGER.info("UserAuthServiceImpl.findByAuthorization:: status : Done...");
		}
		return userAuth;
	}

}
