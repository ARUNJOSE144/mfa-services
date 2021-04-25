package com.sixdee.magik.services.dao;

import java.util.List;

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

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
public interface CampaignDashBoardDao {

	List<OverAllCampaignPushTO> getOverallCampaignPush();

	List<TC_CG_ResponseTO> getTG_CG_Response();

	List<ROI_Total_RevenueTO> getROI_Total_Revenue();

	List<CampaignPerformanceTO> getCampaignPerformance();

	List<TG_CountTO> getTG_Count();

	List<HourlyResponseTO> getHourlyResponse();

	List<ARPUSegmentsTO> getARPUSegments();

	List<Top5CampaignTO> getTop5Campaign();

	List<CampaignResponseTO> getCampaignResponse();

	List<TGCountResponseTO> getTGCountResponse();


}
