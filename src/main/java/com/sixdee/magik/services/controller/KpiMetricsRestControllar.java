package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.KPIMetricsTO;
import com.sixdee.magik.services.service.KpiMetricsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@RestController
public class KpiMetricsRestControllar {

	static final Logger logger = Logger.getLogger(KpiMetricsRestControllar.class);

	@Autowired
	KpiMetricsService kpiMetricsService;

	@PostMapping(MagikServicePath.SAVE_KPI_METRICS)
	public @ResponseBody RestInfo<String> saveKPIMetrics(HttpServletRequest httpServletRequest,
			@RequestBody KPIMetricsTO kpiMetricsTO) {

		logger.info("================== KpiMetricsRestControllar  API Start =====================");
		logger.info("Class : KpiMetricsRestControllar | Method : saveKPIMetrics");

		RestInfo<String> info = new RestInfo<String>();

		try {
			kpiMetricsService.saveKPIMetrics(kpiMetricsTO);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : KpiMetricsRestControllar  |  Method : saveKPIMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== KpiMetrics Rest Controllar API End =====================");

		return info;
	}
	
	@GetMapping(MagikServicePath.GET_KPI_METRICS)
	public @ResponseBody RestInfo<KPIMetricsTO> getKPIMetrics(HttpServletRequest httpServletRequest) {

		logger.info("================== KpiMetricsRestControllar  API Start =====================");
		logger.info("Class : KpiMetricsRestControllar | Method : getKPIMetrics");

		RestInfo<KPIMetricsTO> info = new RestInfo<KPIMetricsTO>();
		
		KPIMetricsTO kpiMetricsTO=new KPIMetricsTO();
		if(httpServletRequest.getParameter("kpiMetricsId")!=null && !httpServletRequest.getParameter("kpiMetricsId").trim().equalsIgnoreCase(""))
			kpiMetricsTO.setId(Integer.parseInt(httpServletRequest.getParameter("kpiMetricsId")));

		try {
			info.setData(kpiMetricsService.getKPIMetrics(kpiMetricsTO));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : KpiMetricsRestControllar  |  Method : getKPIMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== KpiMetrics Rest Controllar API End =====================");

		return info;
	}
	
	@GetMapping(MagikServicePath.GET_ALL_KPI_METRICS)
	public @ResponseBody RestListInfo<KPIMetricsTO> getAllKPIMetrics(HttpServletRequest httpServletRequest) {

		logger.info("================== KpiMetricsRestControllar  API Start =====================");
		logger.info("Class : KpiMetricsRestControllar | Method : getKPIMetrics");

		RestListInfo<KPIMetricsTO> info = new RestListInfo<KPIMetricsTO>();
		

		try {
			info.setData(kpiMetricsService.getKPIMetricsList());
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : KpiMetricsRestControllar  |  Method : getKPIMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== KpiMetrics Rest Controllar API End =====================");

		return info;
	}
	
	@PostMapping(MagikServicePath.DELETE_KPI_DEFINITION)
	public @ResponseBody RestInfo<String> deleteKPIDefintion(HttpServletRequest httpServletRequest) {

		logger.info("================== KpiMetricsRestControllar  API Start =====================");
		logger.info("Class : KpiMetricsRestControllar | Method : deleteKPIDefintion");

		RestInfo<String> info = new RestInfo<String>();
		
		KPIMetricsTO kpiMetricsTO=new KPIMetricsTO();
		if(httpServletRequest.getParameter("kpiDefinitionId")!=null && !httpServletRequest.getParameter("kpiDefinitionId").trim().equalsIgnoreCase(""))
			kpiMetricsTO.setId(Integer.parseInt(httpServletRequest.getParameter("kpiDefinitionId")));

		try {
			info.setData(kpiMetricsService.deleteKPIDefinition(kpiMetricsTO));
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : KpiMetricsRestControllar  |  Method : deleteKPIDefintion " + e);
			e.printStackTrace();
		}

		logger.info("================== KpiMetrics Rest Controllar API End =====================");

		return info;
	}
	
	
}
