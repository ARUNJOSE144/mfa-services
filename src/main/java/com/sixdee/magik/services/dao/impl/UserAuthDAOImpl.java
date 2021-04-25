package com.sixdee.magik.services.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.model.UserAuth;

/**
 * @author Rajesh January 2019
 */
@Repository
@Transactional
public class UserAuthDAOImpl {

	private static Logger LOGGER = LoggerFactory.getLogger(UserAuthDAOImpl.class);

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	Session session = null;

	
	public List<UserAuth> findAll(){
		session =sessionFactory.getCurrentSession();
		hql="from UserAuth";
		query=session.createQuery(hql);
		return query.list();
	}

	

}
