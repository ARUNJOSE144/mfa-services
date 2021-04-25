package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RegisterMerchantLoyaltyDAO;
import com.sixdee.magik.services.model.RegisterMerchantTO;



@Repository
public class RegsiterMerchantLoyaltyDAOImpl implements RegisterMerchantLoyaltyDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<RegisterMerchantTO> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RegisterMerchantTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateRecord(RegisterMerchantTO registerMerchantTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addRecord(RegisterMerchantTO registerMerchantTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
