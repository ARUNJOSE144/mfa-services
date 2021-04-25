package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.TenantLoyaltyDAO;
import com.sixdee.magik.services.model.CardClassTO;
import com.sixdee.magik.services.model.CustomerTO;
import com.sixdee.magik.services.model.CycleCodeTO;
import com.sixdee.magik.services.model.TenantConfigurationTO;
import com.sixdee.magik.services.model.TenantTO;



@Repository
public class TenantLoyaltyDAOImpl implements TenantLoyaltyDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<TenantTO> getAllRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenantTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateRecord(TenantTO tenantTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addRecord(TenantTO tenantTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//customer

	@Override
	public List<CustomerTO> getAllCustomerRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CustomerTO getSelectedCustomerRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomerRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateCustomerRecord(CustomerTO customerTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCustomerRecord(CustomerTO customerTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CardClassTO> getAllCardRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CardClassTO getSelectedCardRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCardRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateCardRecord(CardClassTO customerTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCardRecord(CardClassTO customerTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CycleCodeTO> getAllCycleRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CycleCodeTO getSelectedCycleRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCycleRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateCycleRecord(CycleCodeTO cycleCodeTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addCycleRecord(CycleCodeTO cycleCodeTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TenantConfigurationTO> getAllTenantRecords() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TenantConfigurationTO getSelectedTenantRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteTenantRecord(int autoId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String saveorUpdateTenantRecord(TenantConfigurationTO cycleCodeTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addTenantRecord(TenantConfigurationTO cycleCodeTO) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
