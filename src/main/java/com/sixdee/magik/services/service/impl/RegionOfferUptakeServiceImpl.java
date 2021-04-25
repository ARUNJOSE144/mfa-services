package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RegionOfferUptakeReportDAO;
import com.sixdee.magik.services.model.RegionOfferUptakeTO;
import com.sixdee.magik.services.service.RegionOfferUptakeReportService;


@Service
@Transactional
public class RegionOfferUptakeServiceImpl implements RegionOfferUptakeReportService {

	@Autowired RegionOfferUptakeReportDAO daoLayer;
	 
	@Override
	public List<RegionOfferUptakeTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
