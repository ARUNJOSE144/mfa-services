package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.TierDetailsLoyaltyScreenDAO;
import com.sixdee.magik.services.model.CategoryLoyaltyLMSTO;
import com.sixdee.magik.services.model.TierConfigurationLMSTO;
import com.sixdee.magik.services.model.TierDetailsLMSTO;
import com.sixdee.magik.services.service.TierDetailsLoyaltyScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@Service
@Transactional
public class TierDetailsLoyaltyScreenServiceImpl implements TierDetailsLoyaltyScreenService {
	
	@Autowired TierDetailsLoyaltyScreenDAO daoLayer;

	@Override
	public List<TierDetailsLMSTO> getAllRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllRecords();
	}


	@Override
	public String deleteRecord(int autoId) {
		// TODO Auto-generated method stub
		return  daoLayer.deleteRecord(autoId);
	}

	@Override
	public String saveorUpdateRecord(TierDetailsLMSTO tierDetailsTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateRecord(tierDetailsTO);
	}

	@Override
	public List<CategoryLoyaltyLMSTO> saveTierCategory(CategoryLoyaltyLMSTO categoryLoyaltyTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveTierCategory(categoryLoyaltyTO);
	}

	@Override
	public List<TierConfigurationLMSTO> getAllConfigRecords() {
		// TODO Auto-generated method stub
		return daoLayer.getAllConfigRecords();
	}

	@Override
	public List<CategoryLoyaltyLMSTO> getAllCategory() {
		// TODO Auto-generated method stub
		return daoLayer.getAllCategory();
	}


	@Override
	public String deleteConfigRecord(int autoId) {
		// TODO Auto-generated method stub
		return daoLayer.deleteConfigRecord(autoId);
	}


	@Override
	public String saveorUpdateConfigRecord(TierConfigurationLMSTO tierConfigTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateConfigRecord(tierConfigTO);
	}

}
