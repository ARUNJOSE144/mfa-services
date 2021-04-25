package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.KPIMetricsTO;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

public interface KpiMetricsService {


	public void saveKPIMetrics(KPIMetricsTO kpiMetricsTO);
	public KPIMetricsTO getKPIMetrics(KPIMetricsTO kpiMetricsTO);
	public List<KPIMetricsTO> getKPIMetricsList();
	public String deleteKPIDefinition(KPIMetricsTO kpiMetricsTO);
}
