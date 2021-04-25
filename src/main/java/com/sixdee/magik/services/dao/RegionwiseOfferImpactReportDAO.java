package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.RegionwiseOfferImpactTO;

public interface RegionwiseOfferImpactReportDAO {

	public List<RegionwiseOfferImpactTO> getData();
	
	public List<CircleTO> getCircleData();

}
