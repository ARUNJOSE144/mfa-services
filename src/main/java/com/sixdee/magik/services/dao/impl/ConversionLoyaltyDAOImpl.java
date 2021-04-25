package com.sixdee.magik.services.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ConversionLoyaltyDAO;
import com.sixdee.magik.services.model.ConversionTO;


@Repository
public class ConversionLoyaltyDAOImpl implements ConversionLoyaltyDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	

	@Override
	public String saveConversion(ConversionTO conversionTO) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
