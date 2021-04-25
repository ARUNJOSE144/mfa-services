package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.RateCardDTO;
import com.sixdee.magik.services.model.TierConfigDTO;
import com.sixdee.magik.services.model.TierInfoDTO;

public interface LoyaltyAccountService {

	public LoyaltyRequestDTO accountMerging(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;
	
	public LoyaltyRequestDTO accountTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;
	
	public TierInfoDTO getTierAndCategory(TierInfoDTO tierInfoDTO) throws Exception;
	
	public TierInfoDTO createTierDetails(TierInfoDTO tierInfoDTO) throws Exception;
	
	public List<TierInfoDTO> getAllTierDetails(TierInfoDTO tierInfoDTO) throws Exception;
	
	public List<RateCardDTO> getAllRateCardDetails(RateCardDTO rateCardDTO) throws Exception;
	
	public TierInfoDTO createCategoryDetails(TierInfoDTO tierInfoDTO) throws Exception;
	
	public TierInfoDTO updateTierDetails(TierInfoDTO tierInfoDTO) throws Exception;
	
	public TierInfoDTO deleteTierDetails(TierInfoDTO tierInfoDTO) throws Exception;
	
	public List<TierConfigDTO> getAllTierConfigDetails(TierConfigDTO tierInfoDTO) throws Exception;
	
	public TierConfigDTO createTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception;
	
	public RateCardDTO createRateCardDetails(RateCardDTO rateCardDTO) throws Exception;
	
	public RateCardDTO updateRateCardDetails(RateCardDTO rateCardDTO) throws Exception;
	
	public RateCardDTO deleteRateCardDetails(RateCardDTO rateCardDTO) throws Exception;
	
	public TierConfigDTO deleteTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception;
	
	public TierConfigDTO updateTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception;
	
}
