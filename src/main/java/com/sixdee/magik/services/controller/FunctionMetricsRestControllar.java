package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.FunctionMetricsTO;
import com.sixdee.magik.services.service.FunctionMetricsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author arun.jose
 * @Date : September, 2018
 *
 */

@RestController
public class FunctionMetricsRestControllar {

	static final Logger logger = Logger.getLogger(FunctionMetricsRestControllar.class);

	@Autowired
	FunctionMetricsService functionMetricsService;

	/*
	 * Authenticate Credentials
	 */
	@PostMapping(MagikServicePath.SAVE_FUNCTION_METRIC)
	public @ResponseBody RestInfo<String> saveFunctionMetrics(HttpServletRequest httpServletRequest,
			@RequestBody FunctionMetricsTO funTo) {

		logger.info("================== functionMetrics Rest Controllar API Start =====================");
		logger.info("Class : FunctionMetricsRestControllar | Method : saveFunctionMetrics");

		// funTo.setCreateDate(new Date()+"");

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(functionMetricsService.saveFunctionMetrics(funTo));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FunctionMetricsRestControllar  |  Method : saveFunctionMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== FunctionMetricsRestControllar Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_FUNCTION_METRICS_LIST)
	public @ResponseBody RestListInfo<FunctionMetricsTO> getFunctionMetricsList(HttpServletRequest httpServletRequest) {

		logger.info("================== functionMetrics Rest Controllar API Start =====================");
		logger.info("Class : FunctionMetricsRestControllar | Method : saveFunctionMetrics");
		FunctionMetricsTO funTo = new FunctionMetricsTO();
		if(httpServletRequest.getParameter("userId")!=null && !httpServletRequest.getParameter("userId").trim().equalsIgnoreCase(""))
			funTo.setUserId(Integer.parseInt(httpServletRequest.getParameter("userId")));
		if(httpServletRequest.getParameter("name")!=null && !httpServletRequest.getParameter("name").trim().equalsIgnoreCase(""))
			funTo.setName(httpServletRequest.getParameter("name"));

		RestListInfo<FunctionMetricsTO> info = new RestListInfo<FunctionMetricsTO>();

		try {
			info.setData(functionMetricsService.getFunctionMetricsList(funTo));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FunctionMetricsRestControllar  |  Method : saveFunctionMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== FunctionMetricsRestControllar Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_FUNCTION_METRICS)
	public @ResponseBody RestInfo<FunctionMetricsTO> getFunctionMetrics(HttpServletRequest httpServletRequest) {

		logger.info("================== functionMetrics Rest Controllar API Start =====================");
		logger.info("Class : FunctionMetricsRestControllar | Method : saveFunctionMetrics");
		int funId=0;
		if(httpServletRequest.getParameter("functionMetricsId")!=null && !httpServletRequest.getParameter("functionMetricsId").trim().equalsIgnoreCase(""))
			funId=Integer.parseInt(httpServletRequest.getParameter("functionMetricsId"));
		

		RestInfo<FunctionMetricsTO> info = new RestInfo<FunctionMetricsTO>();

		try {
			info.setData(functionMetricsService.getFunctionMetrics(funId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FunctionMetricsRestControllar  |  Method : saveFunctionMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== FunctionMetricsRestControllar Rest Controllar API End =====================");
		return info;
	}
	
	
	@PostMapping(MagikServicePath.DELETE_FUNCTION_METRICS)
	public @ResponseBody RestInfo<String> deleteFunctionMetrics(HttpServletRequest httpServletRequest) {

		logger.info("================== functionMetrics Rest Controllar API Start =====================");
		logger.info("Class : FunctionMetricsRestControllar | Method : saveFunctionMetrics");

	

		FunctionMetricsTO functionMetricsTO=new FunctionMetricsTO();
		if(httpServletRequest.getParameter("functionMetricsId")!=null && !httpServletRequest.getParameter("functionMetricsId").trim().equalsIgnoreCase(""))
			functionMetricsTO.setId(Integer.parseInt(httpServletRequest.getParameter("functionMetricsId")));
		
		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(functionMetricsService.deleteFunctionMetrics(functionMetricsTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FunctionMetricsRestControllar  |  Method : saveFunctionMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== FunctionMetricsRestControllar Rest Controllar API End =====================");
		return info;
	}

	
	

}
