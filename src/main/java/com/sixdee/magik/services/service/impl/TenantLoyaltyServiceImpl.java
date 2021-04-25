package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.TenantLoyaltyDAO;
import com.sixdee.magik.services.model.CardClassTO;
import com.sixdee.magik.services.model.CustomerTO;
import com.sixdee.magik.services.model.CycleCodeTO;
import com.sixdee.magik.services.model.TenantConfigurationTO;
import com.sixdee.magik.services.model.TenantTO;
import com.sixdee.magik.services.service.TenantLoyaltyService;;


@Service
@Transactional
public class TenantLoyaltyServiceImpl implements TenantLoyaltyService {
	
	@Autowired TenantLoyaltyDAO daoLayer;

	@Override
	public List<TenantTO> getAllRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllRecords();
	}

	@Override
	public TenantTO getSelectedRecord(int autoId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedRecord(autoId);
	}

	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return  daoLayer.deleteRecord(autoId);
	}

	@Override
	public String saveorUpdateRecord(TenantTO tenantTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(tenantTO);
	}

	@Override
	public String addRecord(TenantTO tenantTO) {
		// TODO Auto-generated method stub
		return daoLayer.addRecord(tenantTO);
	}
	
	// customer
	
	@Override
	public List<CustomerTO> getAllCustomerRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllCustomerRecords();
	}

	@Override
	public CustomerTO getSelectedCustomerRecord(int autoId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedCustomerRecord(autoId);
	}

	@Override
	public String deleteCustomerRecord(int autoId) {
		// TODO Auto-generated method stub
		return  daoLayer.deleteCustomerRecord(autoId);
	}

	@Override
	public String saveorUpdateCustomerRecord(CustomerTO tenantTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateCustomerRecord(tenantTO);
	}

	@Override
	public String addCustomerRecord(CustomerTO tenantTO) {
		// TODO Auto-generated method stub
		return daoLayer.addCustomerRecord(tenantTO);
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
