
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.FunctionMetricsDao;
import com.sixdee.magik.services.model.FunctionMetricsTO;
import com.sixdee.magik.services.service.FunctionMetricsService;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class FunctionMetricsServiceImpl implements FunctionMetricsService {
	
	@Autowired
	FunctionMetricsDao functionMetricsDao;

	@Override
	public String saveFunctionMetrics(FunctionMetricsTO funTo) {
		// TODO Auto-generated method stub
		return functionMetricsDao.saveFunctionMetrics(funTo);
	}

	@Override
	public List<FunctionMetricsTO> getFunctionMetricsList(FunctionMetricsTO funTo) {
		// TODO Auto-generated method stub
		return functionMetricsDao.getFunctionMetricsList(funTo);
	}

	@Override
	public FunctionMetricsTO getFunctionMetrics(int funId) {
		// TODO Auto-generated method stub
		return functionMetricsDao.getFunctionMetrics(funId);
	}

	@Override
	public String deleteFunctionMetrics(FunctionMetricsTO funTo) {
		// TODO Auto-generated method stub
		return functionMetricsDao.deleteFunctionMetrics(funTo);
	}

	

}
