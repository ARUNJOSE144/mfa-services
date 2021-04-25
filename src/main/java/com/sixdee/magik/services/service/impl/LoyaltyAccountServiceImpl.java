package com.sixdee.magik.services.service.impl;

import java.util.List;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LoyaltyAccountDAO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.model.RateCardDTO;
import com.sixdee.magik.services.model.TierConfigDTO;
import com.sixdee.magik.services.model.TierInfoDTO;
import com.sixdee.magik.services.service.LoyaltyAccountService;
import com.sixdee.magik.services.util.LoyaltyReloadService;

@Service
@Transactional
public class LoyaltyAccountServiceImpl implements LoyaltyAccountService {

	static final Logger logger = Logger.getLogger(LoyaltyAccountServiceImpl.class);

	@Autowired
	private LoyaltyAccountDAO loyaltyAccountDAO;
	
	@Autowired
	private Environment env;
	
	@Autowired
	private LoyaltyReloadService loyaltyReloadService;
	
	@Override
	public LoyaltyRequestDTO accountMerging(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		
		if (loyaltyRequestDTO.getSubscriber1() != null && loyaltyRequestDTO.getSubscriber2() != null) {
			loyaltyRequestDTO = loyaltyAccountDAO.accountMerging(loyaltyRequestDTO);
		}
		return loyaltyRequestDTO;
	}

	@Override
	public LoyaltyRequestDTO accountTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		
		if (loyaltyRequestDTO.getSubscriber1() != null && loyaltyRequestDTO.getSubscriber2() != null
				&& loyaltyRequestDTO.getPoints() != null) {
			logger.info("##Account Transfer With Points ##");
			loyaltyAccountDAO.accountTransferPoints(loyaltyRequestDTO);
		} else {
			logger.info("##Account Transfer Without Points ##");
			loyaltyAccountDAO.accountTransfer(loyaltyRequestDTO);
		}
	
		return loyaltyRequestDTO;
	}

	@Override
	public TierInfoDTO getTierAndCategory(TierInfoDTO tierInfoDTO) throws Exception {
		return loyaltyAccountDAO.getTierAndCategory(tierInfoDTO);
	}

	@Override
	public TierInfoDTO createTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		return loyaltyAccountDAO.createTierDetails(tierInfoDTO);
	}

	@Override
	public List<TierInfoDTO> getAllTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		return loyaltyAccountDAO.getAllTierDetails(tierInfoDTO);
	}

	@Override
	public TierInfoDTO createCategoryDetails(TierInfoDTO tierInfoDTO) throws Exception {
		return loyaltyAccountDAO.createCategoryDetails(tierInfoDTO);
	}

	@Override
	public TierInfoDTO updateTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		return loyaltyAccountDAO.updateTierDetails(tierInfoDTO);
	}

	@Override
	public TierInfoDTO deleteTierDetails(TierInfoDTO tierInfoDTO) throws Exception {
		return loyaltyAccountDAO.deleteTierDetails(tierInfoDTO);
	}

	@Override
	public List<TierConfigDTO> getAllTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception {
		return loyaltyAccountDAO.getAllTierConfigDetails(tierConfigDTO);
	}

	@Override
	public TierConfigDTO createTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception {
		
		tierConfigDTO = loyaltyAccountDAO.createTierConfigDetails(tierConfigDTO);
		if(tierConfigDTO.getStatus() == 0) {
			callForReload(env.getProperty("loyalty.reload.service.url"));
		}
		return tierConfigDTO;
	}

	@Override
	public RateCardDTO createRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		return loyaltyAccountDAO.createRateCardDetails(rateCardDTO);
	}

	@Override
	public List<RateCardDTO> getAllRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		return loyaltyAccountDAO.getAllRateCardDetails(rateCardDTO);
	}

	@Override
	public RateCardDTO updateRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		return loyaltyAccountDAO.updateRateCardDetails(rateCardDTO);
	}

	@Override
	public RateCardDTO deleteRateCardDetails(RateCardDTO rateCardDTO) throws Exception {
		return loyaltyAccountDAO.deleteRateCardDetails(rateCardDTO);
	}
	
	private void callForReload(String url) {
		StringTokenizer stk = new StringTokenizer(url,"|");
		String urlToCall = null;
		while(stk.hasMoreTokens()){
			 urlToCall = stk.nextToken();
			logger.info("Url to be called for LMS Reload "+urlToCall);
			try {
				loyaltyReloadService.sendGetRequest(urlToCall);
			} catch (Exception e) {
				logger.error("Reload Request failed "+e.getMessage());
			}
		}
	}

	@Override
	public TierConfigDTO deleteTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception {
		
		tierConfigDTO = loyaltyAccountDAO.deleteTierConfigDetails(tierConfigDTO);
		if(tierConfigDTO.getStatus() == 0) {
			callForReload(env.getProperty("loyalty.reload.service.url"));
		}
		return tierConfigDTO;
	}

	@Override
	public TierConfigDTO updateTierConfigDetails(TierConfigDTO tierConfigDTO) throws Exception {
		
		tierConfigDTO = loyaltyAccountDAO.updateTierConfigDetails(tierConfigDTO);
		if(tierConfigDTO.getStatus() == 0) {
			callForReload(env.getProperty("loyalty.reload.service.url"));
		}
		return tierConfigDTO;
	}
}
