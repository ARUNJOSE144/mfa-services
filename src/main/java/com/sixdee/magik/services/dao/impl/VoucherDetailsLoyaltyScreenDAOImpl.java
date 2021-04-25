package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.VoucherDetailsLoyaltyScreenDAO;
import com.sixdee.magik.services.model.VoucherDetailsTO;
/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */


@Repository
public class VoucherDetailsLoyaltyScreenDAOImpl implements VoucherDetailsLoyaltyScreenDAO{
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<VoucherDetailsTO> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public VoucherDetailsTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateRecord(VoucherDetailsTO voucherDetailsTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

}
