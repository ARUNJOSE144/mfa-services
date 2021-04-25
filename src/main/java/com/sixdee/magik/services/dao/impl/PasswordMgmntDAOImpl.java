package com.sixdee.magik.services.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.model.PasswordMgmnt;
import com.sixdee.magik.services.util.SystemConstants;

/**
 * @author Rajesh January 2019
 */
@Repository
public class PasswordMgmntDAOImpl {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	Session session =null;
	
	public String findUserNameByUserId(String userId) {
		String userName = "";
		session= sessionFactory.getCurrentSession();
		hql="select userName from UserMaster where userId=:userId";
		query=session.createQuery(hql);
		query.setParameter("userId", userId);
		userName=(String)query.uniqueResult();

		return userName;
	}
	
	public PasswordMgmnt findById(int userPassword) {
		session= sessionFactory.getCurrentSession();
		hql="from PasswordMgmnt where id=:id";
		query=session.createQuery(hql);
		query.setParameter("id", userPassword);
		PasswordMgmnt obj=(PasswordMgmnt)query.uniqueResult();
		return obj;
	}
	


}
