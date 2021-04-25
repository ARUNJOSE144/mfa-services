package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.CardClassTO;
import com.sixdee.magik.services.model.CustomerTO;
import com.sixdee.magik.services.model.CycleCodeTO;
import com.sixdee.magik.services.model.TenantConfigurationTO;
import com.sixdee.magik.services.model.TenantTO;


public interface TenantLoyaltyService {
	
	
	public List<TenantTO> getAllRecords();
	public TenantTO getSelectedRecord(int autoId);
	public String deleteRecord(int autoId);
	public String saveorUpdateRecord(TenantTO tenantTO);
	public String addRecord(TenantTO tenantTO);
	
	
	public List<CustomerTO> getAllCustomerRecords();
	public CustomerTO getSelectedCustomerRecord(int autoId);
	public String deleteCustomerRecord(int autoId);
	public String saveorUpdateCustomerRecord(CustomerTO customerTO);
	public String addCustomerRecord(CustomerTO customerTO);
	
	
	public List<CardClassTO> getAllCardRecords();
	public CardClassTO getSelectedCardRecord(int autoId);
	public String deleteCardRecord(int autoId);
	public String saveorUpdateCardRecord(CardClassTO customerTO);
	public String addCardRecord(CardClassTO customerTO);
	
	public List<CycleCodeTO> getAllCycleRecords();
	public CycleCodeTO getSelectedCycleRecord(int autoId);
	public String deleteCycleRecord(int autoId);
	public String saveorUpdateCycleRecord(CycleCodeTO cycleCodeTO);
	public String addCycleRecord(CycleCodeTO cycleCodeTO);
	
	public List<TenantConfigurationTO> getAllTenantRecords();
	public TenantConfigurationTO getSelectedTenantRecord(int autoId);
	public String deleteTenantRecord(int autoId);
	public String saveorUpdateTenantRecord(TenantConfigurationTO cycleCodeTO);
	public String addTenantRecord(TenantConfigurationTO cycleCodeTO);


}
