package com.sixdee.magik.services.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.CacheDao;
import com.sixdee.magik.services.model.MessagesTO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Repository
public class CacheDaoImpl implements CacheDao {

	static Logger logger = Logger.getLogger(CacheDaoImpl.class);

	public static Map<Integer, String> messages = null;

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	private Session session = null;
	private String query = null;

	/*
	 *  Messages
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void cacheMessages() {

		logger.info("Class : CacheDAOImpl | Method : cacheMessages");

		messages = new HashMap<Integer, String>();
		List<MessagesTO> messgaesList = null;

		try {
			session = sessionFactory.getCurrentSession();
			query = "FROM MessagesTO";
			logger.info("SQL Query : " + query);
			messgaesList = (List<MessagesTO>) session.createQuery(query).list();

			for (MessagesTO to : messgaesList) {
				messages.put(to.getId(), to.getMessage());
			}
		} catch (Exception e) {
			logger.info("Exception on get groups list");
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
