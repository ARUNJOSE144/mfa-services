package com.sixdee.magik.services.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.model.UserStatus;

/**
 * @author arun.jose January 2019
 */
@Repository
@Transactional
public class UserStatusDAOImpl {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	private Session session;
	
	public List<UserStatus> getUserStatusList() {
		session=sessionFactory.getCurrentSession();
		hql="from UserStatus";
		query=session.createQuery(hql);
		return (List<UserStatus>)query.list();
		
	}
	
}
