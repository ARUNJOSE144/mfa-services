
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.KpiMetricsDao;
import com.sixdee.magik.services.model.KPIMetricsTO;
import com.sixdee.magik.services.service.KpiMetricsService;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Service
@Transactional
public class KpiMetricsServiceImpl implements KpiMetricsService {

	@Autowired
	KpiMetricsDao kpiMetricsDao;

	@Override
	public void saveKPIMetrics(KPIMetricsTO kpiMetricsTO) {
		kpiMetricsDao.saveKPIMetrics(kpiMetricsTO);
		
	}

	@Override
	public KPIMetricsTO getKPIMetrics(KPIMetricsTO kpiMetricsTO) {
		// TODO Auto-generated method stub
		return kpiMetricsDao.getKPIMetrics(kpiMetricsTO);
	}

	@Override
	public List<KPIMetricsTO> getKPIMetricsList() {
		// TODO Auto-generated method stub
		return kpiMetricsDao.getKPIMetricsList();
	}

	@Override
	public String deleteKPIDefinition(KPIMetricsTO kpiMetricsTO) {
		// TODO Auto-generated method stub
		return kpiMetricsDao.deleteKPIDefinition(kpiMetricsTO);
	}


	
	
}
