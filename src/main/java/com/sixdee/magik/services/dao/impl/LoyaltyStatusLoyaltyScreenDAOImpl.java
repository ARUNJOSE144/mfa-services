package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.LoyaltyStatusLoyaltyScreenDAO;
import com.sixdee.magik.services.model.LoyaltyStatusTO;

/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */


@Repository
public class LoyaltyStatusLoyaltyScreenDAOImpl implements LoyaltyStatusLoyaltyScreenDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<LoyaltyStatusTO> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoyaltyStatusTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateRecord(LoyaltyStatusTO loyaltyStatusTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}
}
