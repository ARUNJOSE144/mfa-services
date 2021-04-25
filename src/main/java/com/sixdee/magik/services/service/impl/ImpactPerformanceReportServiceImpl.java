package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ImpactPerformanceReportDAO;
import com.sixdee.magik.services.model.ImpactPerformanceTO;
import com.sixdee.magik.services.service.ImpactPerformanceReportService;


@Service
@Transactional
public class ImpactPerformanceReportServiceImpl implements ImpactPerformanceReportService {

	@Autowired ImpactPerformanceReportDAO daoLayer;
	 
	@Override
	public List<ImpactPerformanceTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
