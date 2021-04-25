package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.PrePostAnalyticsKPITO;
import com.sixdee.magik.services.model.PrePostAnalyticsReportsTO;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */
public interface PrePostAnalyticsService {

	public PrePostAnalyticsReportsTO getPrePostAnalyticsData(PrePostAnalyticsReportsTO to);

	public PrePostAnalyticsReportsTO getPrePostAnalyticsDataOfLatestCampaign();

	public List<PrePostAnalyticsKPITO> getPrePostAnalyticsKpis();
}
