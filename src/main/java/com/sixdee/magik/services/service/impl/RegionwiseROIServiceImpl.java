package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RegionwiseROIReportDAO;
import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.RegionwiseROITO;
import com.sixdee.magik.services.service.RegionwiseROIReportService;


@Service
@Transactional
public class RegionwiseROIServiceImpl implements RegionwiseROIReportService {

	@Autowired RegionwiseROIReportDAO daoLayer;
	 
	@Override
	public List<RegionwiseROITO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
