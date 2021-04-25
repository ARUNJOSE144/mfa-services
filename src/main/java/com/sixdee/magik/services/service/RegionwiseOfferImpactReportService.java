package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.RegionwiseOfferImpactTO;

public interface RegionwiseOfferImpactReportService {
	
	public List<RegionwiseOfferImpactTO> getData();
	
	public List<CircleTO> getCircleData();

}
