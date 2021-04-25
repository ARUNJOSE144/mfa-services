package com.sixdee.magik.services.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AccountMergingLoyaltyScreenDAO;
import com.sixdee.magik.services.model.AccountMergingTO;
/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */


@Repository
public class AccountMergingLoyaltyScreenDAOImpl implements AccountMergingLoyaltyScreenDAO{

	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String saveorUpdateRecord(AccountMergingTO accountMergingTO) {
		// TODO Auto-generated method stub
		return null;
	}
}
