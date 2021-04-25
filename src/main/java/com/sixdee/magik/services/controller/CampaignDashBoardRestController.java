package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ARPUSegmentsTO;
import com.sixdee.magik.services.model.CampaignPerformanceTO;
import com.sixdee.magik.services.model.CampaignResponseTO;
import com.sixdee.magik.services.model.HourlyResponseTO;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.ROI_Total_RevenueTO;
import com.sixdee.magik.services.model.TC_CG_ResponseTO;
import com.sixdee.magik.services.model.TGCountResponseTO;
import com.sixdee.magik.services.model.TG_CountTO;
import com.sixdee.magik.services.model.Top5CampaignTO;
import com.sixdee.magik.services.service.CampaignDashBoardService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@RestController
public class CampaignDashBoardRestController {
	static final Logger logger = Logger.getLogger(CampaignDashBoardRestController.class);

	@Autowired
	CampaignDashBoardService campaignDashboardService;

	@GetMapping(MagikServicePath.OVERALL_CAMPAIGN_PUSH)
	public @ResponseBody RestListInfo<OverAllCampaignPushTO> getOverallCampaignPush(
			HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getOverallCampaignPush API Start =====================");
		RestListInfo<OverAllCampaignPushTO> listInfo = new RestListInfo<OverAllCampaignPushTO>();
		try {
			listInfo.setData(campaignDashboardService.getOverallCampaignPush());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getOverallCampaignPush \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.TG_CG_RESPONSE)
	public @ResponseBody RestListInfo<TC_CG_ResponseTO> getTG_CG_Response(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getTG_CG_Response API Start =====================");
		RestListInfo<TC_CG_ResponseTO> listInfo = new RestListInfo<TC_CG_ResponseTO>();
		try {
			listInfo.setData(campaignDashboardService.getTG_CG_Response());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getTG_CG_Response \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.ROI_TOTAL_REVENUE)
	public @ResponseBody RestListInfo<ROI_Total_RevenueTO> getROI_Total_Revenue(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getROI_Total_Revenue API Start =====================");
		RestListInfo<ROI_Total_RevenueTO> listInfo = new RestListInfo<ROI_Total_RevenueTO>();
		try {
			listInfo.setData(campaignDashboardService.getROI_Total_Revenue());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getROI_Total_Revenue \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.CAMPAIGN_PERFORMANCE)
	public @ResponseBody RestListInfo<CampaignPerformanceTO> getCampaignPerformance(
			HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getCampaignPerformance API Start =====================");
		RestListInfo<CampaignPerformanceTO> listInfo = new RestListInfo<CampaignPerformanceTO>();
		try {
			listInfo.setData(campaignDashboardService.getCampaignPerformance());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getCampaignPerformance \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.TG_COUNT)
	public @ResponseBody RestListInfo<TG_CountTO> getTG_Count(HttpServletRequest httpServletRequest) {

		logger.info("================== CampaignDashBoardRestController getTG_Count API Start =====================");
		RestListInfo<TG_CountTO> listInfo = new RestListInfo<TG_CountTO>();
		try {
			listInfo.setData(campaignDashboardService.getTG_Count());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getTG_Count \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.HOURLY_RESPONSE)
	public @ResponseBody RestListInfo<HourlyResponseTO> getHourlyResponse(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getHourlyResponse API Start =====================");
		RestListInfo<HourlyResponseTO> listInfo = new RestListInfo<HourlyResponseTO>();
		try {
			listInfo.setData(campaignDashboardService.getHourlyResponse());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getHourlyResponse \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.ARPU_SEGMENTS)
	public @ResponseBody RestListInfo<ARPUSegmentsTO> getARPUSegments(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getARPUSegments API Start =====================");
		RestListInfo<ARPUSegmentsTO> listInfo = new RestListInfo<ARPUSegmentsTO>();
		try {
			listInfo.setData(campaignDashboardService.getARPUSegments());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getARPUSegments \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.TOP_5_CAMPAIGN)
	public @ResponseBody RestListInfo<Top5CampaignTO> getTop5Campaign(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getTop5Campaign API Start =====================");
		RestListInfo<Top5CampaignTO> listInfo = new RestListInfo<Top5CampaignTO>();
		try {
			listInfo.setData(campaignDashboardService.getTop5Campaign());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getTop5Campaign \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.CAMPAIGN_RESPONSE)
	public @ResponseBody RestListInfo<CampaignResponseTO> getCampaignResponse(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getTop5Campaign API Start =====================");
		RestListInfo<CampaignResponseTO> listInfo = new RestListInfo<CampaignResponseTO>();
		try {
			listInfo.setData(campaignDashboardService.getCampaignResponse());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getTop5Campaign \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

	@GetMapping(MagikServicePath.TG_COUNT_RESPONSE)
	public @ResponseBody RestListInfo<TGCountResponseTO> getTGCountResponse(HttpServletRequest httpServletRequest) {

		logger.info(
				"================== CampaignDashBoardRestController getTGCountResponse API Start =====================");
		RestListInfo<TGCountResponseTO> listInfo = new RestListInfo<TGCountResponseTO>();
		try {
			listInfo.setData(campaignDashboardService.getTGCountResponse());
		} catch (Exception e) {
			ExceptionUtil.handle(e, listInfo);
			logger.error("Class : CampaignDashBoardRestController  |  Method : getTGCountResponse \n" + e);
			e.printStackTrace();
		}

		return listInfo;
	}

}
