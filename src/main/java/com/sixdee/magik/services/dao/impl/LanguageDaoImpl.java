package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.LanguageDao;
import com.sixdee.magik.services.model.LanguageTO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Repository
public class LanguageDaoImpl implements LanguageDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	/*
	 * Get Languages
	 */
	@Override
	public List<LanguageTO> getLanguages() {

		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<LanguageTO> list = (List<LanguageTO>) session.createCriteria(LanguageTO.class).list();

		return list;
	}

}
