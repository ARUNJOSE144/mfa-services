package com.sixdee.magik.services.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.model.TokenMaster;


/**
 * @author arun.jose January 2019
 */
@Repository
@Transactional
public class TokenDAOImpl {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	private Session session;
	
	public TokenMaster findByUserIdAndToken(String userId, String token) {
		session=sessionFactory.getCurrentSession();
		hql="from TokenMaster where userId=:userId and token=:token";
		query=session.createQuery(hql);
		query.setParameter("userId", userId);
		query.setParameter("token", token);
		return (TokenMaster)query.uniqueResult();
	}
	
	 public int deleteByUserId(String userId) {
		 	int result=0;
			session=sessionFactory.getCurrentSession();
			hql="delete from TokenMaster where userId=:userId";
			query=session.createQuery(hql);
			query.setParameter("userId", userId);
			result=query.executeUpdate();
			return result;
		 
	 }
	
	
}
