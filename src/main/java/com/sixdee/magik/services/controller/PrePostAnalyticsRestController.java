package com.sixdee.magik.services.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.PrePostAnalyticsKPITO;
import com.sixdee.magik.services.model.PrePostAnalyticsReportsTO;
import com.sixdee.magik.services.service.PrePostAnalyticsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@RestController
public class PrePostAnalyticsRestController {

	static final Logger logger = Logger.getLogger(PrePostAnalyticsRestController.class);
	@Autowired
	PrePostAnalyticsService prePostAnalyticsService;

	@PostMapping(MagikServicePath.GET_PRE_POST_ANALYTICS_DATA)
	public @ResponseBody RestInfo<PrePostAnalyticsReportsTO> loadPrePostAnalyticsData(
			HttpServletRequest httpServletRequest, @RequestBody PrePostAnalyticsReportsTO prReportsTO)
			throws ParseException {

		logger.info("================== PrePostAnalytics Rest Controllar API Start =====================");
		logger.info("Class : PrePostAnalyticsRestController | Method : loadPrePostAnalyticsData");
		RestInfo<PrePostAnalyticsReportsTO> info = new RestInfo<PrePostAnalyticsReportsTO>();
		try {
			info.setData(prePostAnalyticsService.getPrePostAnalyticsData(prReportsTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : PrePostAnalyticsRestController  |  Method : loadPrePostAnalyticsData " + e);
			e.printStackTrace();
		}
		logger.info("================== PrePostAnalytics Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_PRE_POST_ANALYTICS_DATA_OF_LATEST_CAMPAIGN)
	public @ResponseBody RestInfo<PrePostAnalyticsReportsTO> loadPrePostAnalyticsDataOfLatestCampaign(
			HttpServletRequest httpServletRequest) {

		logger.info("================== PrePostAnalytics Rest Controllar API Start =====================");
		logger.info("Class : PrePostAnalyticsRestController | Method : loadPrePostAnalyticsDataOfLatestCampaign");
		RestInfo<PrePostAnalyticsReportsTO> info = new RestInfo<PrePostAnalyticsReportsTO>();
		try {
			info.setData(prePostAnalyticsService.getPrePostAnalyticsDataOfLatestCampaign());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : PrePostAnalyticsRestController  |  Method : loadPrePostAnalyticsDataOfLatestCampaign "
					+ e);
			e.printStackTrace();
		}
		logger.info("================== PrePostAnalytics Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_PRE_POST_ANALYTICS_KPIS)
	public @ResponseBody RestListInfo<PrePostAnalyticsKPITO> getPrePostAnalyticsKPI(
			HttpServletRequest httpServletRequest) {

		logger.info("================== PrePostAnalytics Rest Controllar API Start =====================");
		logger.info("Class : PrePostAnalyticsRestController | Method : getPrePostAnalyticsKPI");
		RestListInfo<PrePostAnalyticsKPITO> info = new RestListInfo<PrePostAnalyticsKPITO>();
		try {
			info.setData(prePostAnalyticsService.getPrePostAnalyticsKpis());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : PrePostAnalyticsRestController  |  Method : getPrePostAnalyticsKPI " + e);
			e.printStackTrace();
		}
		logger.info("================== PrePostAnalytics Rest Controllar API End =====================");
		return info;
	}

}
