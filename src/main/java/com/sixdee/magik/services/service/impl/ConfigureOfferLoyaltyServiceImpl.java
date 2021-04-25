package com.sixdee.magik.services.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.ConfigureOfferLoyaltyDAO;
import com.sixdee.magik.services.model.ConfigureOfferLMSTransientTO;
import com.sixdee.magik.services.model.ConfigureOfferNomenclatureLMSTO;
import com.sixdee.magik.services.model.SegmentDtlsLMSTO;
import com.sixdee.magik.services.service.ConfigureOfferLoyaltyService;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@Transactional
@Service
public class ConfigureOfferLoyaltyServiceImpl implements ConfigureOfferLoyaltyService {
	
	@Autowired ConfigureOfferLoyaltyDAO daoLayer;

	@Override
	public SegmentDtlsLMSTO segmentDtls() {
		// TODO Auto-generated method stub
		return daoLayer.segmentDtls();
	}

	@Override
	public String saveOrUpdateConfigureObjects(ConfigureOfferLMSTransientTO obj) {
		// TODO Auto-generated method stub
		return daoLayer.saveOrUpdateConfigureObjects(obj);
	}

	@Override
	public List<ConfigureOfferNomenclatureLMSTO> getAllOffers() {
		// TODO Auto-generated method stub
		return daoLayer.getAllOffers();
	}

	/*@Override
	public <OBJECT> String saveOrUpdateConfigureObjects(OBJECT obj) {
		// TODO Auto-generated method stub
		return daoLayer.saveOrUpdateConfigureObjects(obj);
	}*/
	
	

}
