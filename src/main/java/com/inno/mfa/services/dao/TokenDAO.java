
package com.inno.mfa.services.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.inno.mfa.services.util.Util;

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

	@Value("${sessionTimeOutInMinutes}")
	private int sessionTimeOutInMinutes;

	public static HashedMap<String, Date> sessionExpiryMap = new HashedMap<String, Date>();

	public void addSession(String token, int userId) {
		Date date = new Date();
		logger.info("Login Time : " + date.getTime());
		sessionExpiryMap.put(token + "_" + userId, new Date());
		UpdateSessionsInDB();
	}

	public boolean sessionExist(String token, int userId) {
		Long expiresIn = sessionExpiryMap.get(token + "_" + userId).getTime() + (1000 * 60 * sessionTimeOutInMinutes);

		logger.info("expiresIn : " + expiresIn);
		logger.info("curr time : " + new Date().getTime());

		if (new Date().getTime() <= expiresIn) {
			sessionExpiryMap.put(token + "_" + userId, new Date());
			return true;
		} else if (Util.validate(expiresIn.toString())) {
			sessionExpiryMap.remove(token + "_" + userId);
		}
		return false;
	}

	public void UpdateSessionsInDB() {
		String hql = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			hql = "delete from TokenMaster";
			session.createQuery(hql).executeUpdate();

			refreshSession();
			for (Map.Entry<String, Date> entry : sessionExpiryMap.entrySet()) {
				TokenMaster tokenMaster = new TokenMaster();
				tokenMaster.setToken(entry.getKey().split("_")[0]);
				tokenMaster.setUserId(Integer.parseInt(entry.getKey().split("_")[1]));
				tokenMaster.setLastActiveTime(entry.getValue());
				session.save(tokenMaster);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void refreshSession() {
		for (Map.Entry<String, Date> entry : sessionExpiryMap.entrySet()) {

			String token = entry.getKey().split("_")[0];
			String userId = entry.getKey().split("_")[1];
			Date time = entry.getValue();

			Long expiresIn = time.getTime() + (1000 * 60 * sessionTimeOutInMinutes);
			if (new Date().getTime() > expiresIn) {
				sessionExpiryMap.remove(token + "_" + userId);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loadExixstingSessionsFromDB() {
		List<TokenMaster> list = null;
		try {

			sessionExpiryMap = new HashedMap<String, Date>();
			Session session = sessionFactory.getCurrentSession();
			list = (List<TokenMaster>) session.createCriteria(TokenMaster.class).list();
			for (TokenMaster to : list) {
				sessionExpiryMap.put(to.getToken() + "_" + to.getUserId(), to.getLastActiveTime());
			}
			logger.info("Loaded " + list.size() + " User Sessions");
			refreshSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteSession(String token, int userId) {
		sessionExpiryMap.remove(token + "_" + userId);
		UpdateSessionsInDB();
	}
}
