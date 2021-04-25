package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.model.LoyaltyRequestDTO;

public interface LoyaltyPointsDAO {

	public LoyaltyRequestDTO pointTransfer(LoyaltyRequestDTO loyaltyRequestDTO) throws Exception;
}
