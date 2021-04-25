package com.sixdee.magik.services.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AccountTransferLoyaltyScreenDAO;
import com.sixdee.magik.services.model.AccountTransferTO;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */


@Repository
public class AccountTransferLoyaltyScreenDAOImpl implements AccountTransferLoyaltyScreenDAO{


	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public String saveorUpdateRecord(AccountTransferTO accountTransferTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
