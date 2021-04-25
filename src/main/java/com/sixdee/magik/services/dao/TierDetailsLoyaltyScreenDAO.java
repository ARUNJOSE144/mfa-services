package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.CategoryLoyaltyLMSTO;
import com.sixdee.magik.services.model.TierConfigurationLMSTO;
import com.sixdee.magik.services.model.TierDetailsLMSTO;

public interface TierDetailsLoyaltyScreenDAO {
	
	
	public List<CategoryLoyaltyLMSTO> getAllCategory();
	
	//TierDetails
	public List<TierDetailsLMSTO> getAllRecords();
	public String deleteRecord(int autoId);
	public String saveorUpdateRecord(TierDetailsLMSTO tierDetailsTO);
	public List<CategoryLoyaltyLMSTO> saveTierCategory(CategoryLoyaltyLMSTO categoryLoyaltyTO);
	
	
	//TierConfig
		public List<TierConfigurationLMSTO> getAllConfigRecords();
		public String deleteConfigRecord(int autoId);
		public String saveorUpdateConfigRecord(TierConfigurationLMSTO tierConfigTO);
	

}
