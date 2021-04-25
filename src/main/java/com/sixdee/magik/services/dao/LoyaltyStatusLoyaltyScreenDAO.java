package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.LoyaltyStatusTO;

public interface LoyaltyStatusLoyaltyScreenDAO {

	List<LoyaltyStatusTO> getAllRecords();

	LoyaltyStatusTO getSelectedRecord(int autoId);

	String saveorUpdateRecord(LoyaltyStatusTO loyaltyStatusTO);

	String deleteRecord(int autoId);

}
