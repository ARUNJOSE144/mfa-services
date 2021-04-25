package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ConfigureOfferLMSTransientTO;
import com.sixdee.magik.services.model.ConfigureOfferNomenclatureLMSTO;
import com.sixdee.magik.services.model.SegmentDtlsLMSTO;

public interface ConfigureOfferLoyaltyDAO {
	
	public SegmentDtlsLMSTO segmentDtls();
	
	public  String saveOrUpdateConfigureObjects(ConfigureOfferLMSTransientTO obj);
	public List<ConfigureOfferNomenclatureLMSTO> getAllOffers();

}
