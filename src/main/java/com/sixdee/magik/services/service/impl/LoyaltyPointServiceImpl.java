package com.sixdee.magik.services.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.LoyaltyPointsDAO;
import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.service.LoyaltyPointService;

@Service
@Transactional
public class LoyaltyPointServiceImpl implements LoyaltyPointService {
	
	static final Logger logger = Logger.getLogger(LoyaltyPointServiceImpl.class);
	
	@Autowired
	private LoyaltyPointsDAO loyaltyPointsDAO;
	
	@Override
	public LoyaltyRequestDTO pointTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception {
		if (loyaltyRequestDTO.getSubscriber1() != null && loyaltyRequestDTO.getSubscriber2() != null) {
			loyaltyRequestDTO = loyaltyPointsDAO.pointTransfer(loyaltyRequestDTO);
		}
		return loyaltyRequestDTO;
	}

}
