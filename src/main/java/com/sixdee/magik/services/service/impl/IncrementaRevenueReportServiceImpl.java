package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.IncrementalRevenueReportDAO;
import com.sixdee.magik.services.model.ARPUBandsTO;
import com.sixdee.magik.services.model.IncrementalRevenueTO;
import com.sixdee.magik.services.service.IncrementalRevenueReportService;


@Service
@Transactional
public class IncrementaRevenueReportServiceImpl implements IncrementalRevenueReportService {

	@Autowired IncrementalRevenueReportDAO daoLayer;
	 
	@Override
	public List<IncrementalRevenueTO> getData(String month,String arpu, String segment) {
		// TODO Auto-generated method stub
		return daoLayer.getData(month,arpu,segment);
	}
	
	@Override
	public List<ARPUBandsTO> getArpuBandData() {
		// TODO Auto-generated method stub
		return daoLayer.getArpuBandData();
	}

}
