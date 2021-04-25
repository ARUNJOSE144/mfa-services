package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.PrePostAnalyticsDAO;
import com.sixdee.magik.services.model.PrePostAnalyticsKPITO;
import com.sixdee.magik.services.model.PrePostAnalyticsReportsTO;
import com.sixdee.magik.services.service.PrePostAnalyticsService;

/**
 * @author Nakhil Kurian
 * @Date : August, 2020
 *
 */

@Service
@Transactional
public class PrePostAnalyticsServiceImpl implements PrePostAnalyticsService {

	@Autowired
	PrePostAnalyticsDAO prePostAnalyticsDAO;

	@Override
	public PrePostAnalyticsReportsTO getPrePostAnalyticsData(PrePostAnalyticsReportsTO to) {
		// TODO Auto-generated method stub
		return prePostAnalyticsDAO.getPrePostAnalyticsData(to);
	}

	@Override
	public PrePostAnalyticsReportsTO getPrePostAnalyticsDataOfLatestCampaign() {
		// TODO Auto-generated method stub
		return prePostAnalyticsDAO.getPrePostAnalyticsDataOfLatestCampaign();
	}

	@Override
	public List<PrePostAnalyticsKPITO> getPrePostAnalyticsKpis() {
		// TODO Auto-generated method stub
		return prePostAnalyticsDAO.getPrePostAnalyticsKpis();
	}

}
