package com.sixdee.magik.services.dao.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.model.StatusCodeDTO;
import com.sixdee.magik.services.model.TelegramBotConfig;
import com.sixdee.magik.services.model.TelegramDTO;

/**
 * @author amal.a.s
 * @Date : April, 2019
 *
 */

@Component
@Repository
@Transactional
public class StatusCodeDAOImpl {

	static Logger logger = Logger.getLogger(StatusCodeDAOImpl.class);

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	public static Map<String, StatusCodeDTO> cacheMap = null;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@EventListener(ApplicationReadyEvent.class)
	public void getMessages() {

		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			cacheMap = new HashMap<>();
			List list = session.createCriteria(StatusCodeDTO.class).list();
			Iterator<StatusCodeDTO> tempList = list.iterator();
			while (tempList.hasNext()) {
				StatusCodeDTO dto = tempList.next();
				cacheMap.put(dto.getServiceName() + "-" + dto.getType(), dto);
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Application Cache Loading Failed !!!");
		}

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getCommands() {

		Session session = null;
		Map<String, String> respMap = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TelegramDTO> list = session.createCriteria(TelegramDTO.class)
					.add(Restrictions.eq("commandStatus", "Active")).list();
			if (list != null && list.size() > 0) {
				respMap = new HashMap<>();
				for (TelegramDTO dto : list) {

					respMap.put("/" + dto.getCommand(), dto.getResponseType());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Telegram Cache Loading Failed !!!");
		}

		return respMap;

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getBotDetails() {

		Session session = null;
		Map<String, String> respMap = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TelegramBotConfig> list = session.createCriteria(TelegramBotConfig.class).list();
			if (list != null && list.size() > 0) {
				respMap = new HashMap<>();
				for (TelegramBotConfig dto : list) {

					respMap.put(dto.getBotId(), dto.getBotKey());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Telegram Bot Config Cache Loading Failed !!!");
		}

		return respMap;

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getBotNamings() {

		Session session = null;
		Map<String, String> respMap = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TelegramBotConfig> list = session.createCriteria(TelegramBotConfig.class).list();
			if (list != null && list.size() > 0) {
				respMap = new HashMap<>();
				for (TelegramBotConfig dto : list) {

					respMap.put(String.valueOf(dto.getId()), dto.getBotName());

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Telegram Bot Naming Cache Loading Failed !!!");
		}

		return respMap;

	}

	@SuppressWarnings("unchecked")
	public Map<String, String> getBotId() {

		Session session = null;
		Map<String, String> respMap = null;

		try {
			session = sessionFactory.getCurrentSession();
			session.beginTransaction();
			List<TelegramBotConfig> list = session.createCriteria(TelegramBotConfig.class).list();
			if (list != null && list.size() > 0) {
				respMap = new HashMap<>();
				for (TelegramBotConfig dto : list) {

					respMap.put(dto.getBotId(), String.valueOf(dto.getId()));

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Telegram Bot Id Cache Loading Failed !!!");
		}

		return respMap;

	}

}
