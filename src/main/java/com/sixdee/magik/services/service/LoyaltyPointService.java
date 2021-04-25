package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.LoyaltyRequestDTO;

public interface LoyaltyPointService {

	public LoyaltyRequestDTO pointTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;
}
