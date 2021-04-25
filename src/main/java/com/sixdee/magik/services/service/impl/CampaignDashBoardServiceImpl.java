package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CampaignDashBoardDao;
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

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
@Service
@Transactional
public class CampaignDashBoardServiceImpl implements CampaignDashBoardService {

	@Autowired
	CampaignDashBoardDao campaigndashBoardDao;

	@Override
	public List<OverAllCampaignPushTO> getOverallCampaignPush() {
		return campaigndashBoardDao.getOverallCampaignPush();
	}

	@Override
	public List<TC_CG_ResponseTO> getTG_CG_Response() {
		return campaigndashBoardDao.getTG_CG_Response();
	}

	@Override
	public List<ROI_Total_RevenueTO> getROI_Total_Revenue() {
		return campaigndashBoardDao.getROI_Total_Revenue();
	}

	@Override
	public List<CampaignPerformanceTO> getCampaignPerformance() {
		return campaigndashBoardDao.getCampaignPerformance();
	}

	@Override
	public List<TG_CountTO> getTG_Count() {
		return campaigndashBoardDao.getTG_Count();
	}

	@Override
	public List<HourlyResponseTO> getHourlyResponse() {
		return campaigndashBoardDao.getHourlyResponse();
	}

	@Override
	public List<ARPUSegmentsTO> getARPUSegments() {
		return campaigndashBoardDao.getARPUSegments();
	}

	@Override
	public List<Top5CampaignTO> getTop5Campaign() {
		return campaigndashBoardDao.getTop5Campaign();
	}

	@Override
	public List<CampaignResponseTO> getCampaignResponse() {
		return campaigndashBoardDao.getCampaignResponse();
	}

	@Override
	public List<TGCountResponseTO> getTGCountResponse() {
		return campaigndashBoardDao.getTGCountResponse();
	}

	

}
