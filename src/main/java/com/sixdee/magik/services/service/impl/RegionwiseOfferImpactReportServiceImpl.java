package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RegionwiseOfferImpactReportDAO;
import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.RegionwiseOfferImpactTO;
import com.sixdee.magik.services.service.RegionwiseOfferImpactReportService;


@Service
@Transactional
public class RegionwiseOfferImpactReportServiceImpl implements RegionwiseOfferImpactReportService {

	@Autowired RegionwiseOfferImpactReportDAO daoLayer;
	 
	@Override
	public List<RegionwiseOfferImpactTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}
	
	@Override
	public List<CircleTO> getCircleData() {
		// TODO Auto-generated method stub
		return daoLayer.getCircleData();
	}

}
