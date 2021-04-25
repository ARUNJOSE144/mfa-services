package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.FunctionMetricsTO;

/**
 * @author arun.jose
 * @Date : November, 2018
 *
 */

public interface FunctionMetricsService {

	public String saveFunctionMetrics(FunctionMetricsTO funTo);

	public List<FunctionMetricsTO> getFunctionMetricsList(FunctionMetricsTO funTo);

	public FunctionMetricsTO getFunctionMetrics(int funId);

	public String deleteFunctionMetrics(FunctionMetricsTO funTo);

}
