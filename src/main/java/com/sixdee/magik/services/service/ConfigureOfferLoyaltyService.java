package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ConfigureOfferLMSTransientTO;
import com.sixdee.magik.services.model.ConfigureOfferNomenclatureLMSTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.SegmentDtlsLMSTO;


public interface ConfigureOfferLoyaltyService {
	
	
	public SegmentDtlsLMSTO segmentDtls();
	/*	public <OBJECT> String saveOrUpdateConfigureObjects(OBJECT obj);*/
	
	public  String saveOrUpdateConfigureObjects(ConfigureOfferLMSTransientTO obj);
	
	public List<ConfigureOfferNomenclatureLMSTO> getAllOffers();
	
	
	
	
	

}
