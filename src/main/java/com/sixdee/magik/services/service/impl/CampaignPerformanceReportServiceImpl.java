package com.sixdee.magik.services.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.CampaignPerformanceReportDAO;
import com.sixdee.magik.services.model.CampaignPerformanceReportTransientTO;
import com.sixdee.magik.services.service.CampaignPerformanceReportService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : March, 2021
 */

@Transactional
@Service
public class CampaignPerformanceReportServiceImpl implements CampaignPerformanceReportService {
	
	@Autowired CampaignPerformanceReportDAO daoLayer;

	@Override
	public CampaignPerformanceReportTransientTO getAllRcrds() {
		// TODO Auto-generated method stub
		return daoLayer.getAllRcrds();
	}

}
