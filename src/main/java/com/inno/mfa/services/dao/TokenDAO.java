
package com.inno.mfa.services.dao;

import java.util.Date;

import javax.transaction.Transactional;

import org.apache.commons.collections4.map.HashedMap;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.TokenMaster;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class TokenDAO {

	static final Logger logger = Logger.getLogger(TokenDAO.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Value("${sessionTimeOutInseconds}")
	private int sessionTimeOutInseconds;

	public static HashedMap<String, Date> sessionExpiryMap = new HashedMap<String, Date>();

	public void addSession(String token, int userId) {
		Date date = new Date();
		logger.info("Login Time : " + date.getTime());
		sessionExpiryMap.put(token + "_" + userId, new Date());
		mergeToTable(token, userId, date);
	}

	public boolean sessionExist(String token, int userId) {
		Long expiresIn = sessionExpiryMap.get(token + "_" + userId).getTime() + (1000 * sessionTimeOutInseconds);

		logger.info("expiresIn : " + expiresIn);
		logger.info("curr time : " + new Date().getTime());

		if (new Date().getTime() <= expiresIn) {
			sessionExpiryMap.put(token + "_" + userId, new Date());
			return true;
		}
		return false;
	}

	public void mergeToTable(String token, int userId, Date date) {
		try {
			Session session = sessionFactory.getCurrentSession();
			TokenMaster tokenMaster = new TokenMaster();
			tokenMaster.setToken(token);
			tokenMaster.setUserId(userId);
			tokenMaster.setLastActiveTime(date);
			session.save(tokenMaster);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
