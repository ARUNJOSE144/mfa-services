package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.CustomerCareDTO;

public interface CustomerCareDAO {

	public List<CustomerCareDTO> getMyBestOffers(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getBasicInfo(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getBasicViewInfo(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getTransactionsHistory(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getRevenueDetails(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getVoiceDetails(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getDataDetails(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getSmsDetails(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getLoyaltyDetails(CustomerCareDTO customerCareDTO) throws Exception;
	
	public List<CustomerCareDTO> getInfo(CustomerCareDTO customerCareDTO) throws Exception;
	
	public CustomerCareDTO getArpuDetails(CustomerCareDTO customerCareDTO) throws Exception;
	
	public CustomerCareDTO getEligibleCampaigns(CustomerCareDTO customerCareDTO) throws Exception;

	public List<CustomerCareDTO> getCustomerInsights(CustomerCareDTO customerCareDTO) throws Exception;

	public List<CustomerCareDTO> getTransactionsHistoryInfo(CustomerCareDTO customerCareDTO) throws Exception;
}
