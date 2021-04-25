
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.CampaignCalenderDao;
import com.sixdee.magik.services.model.CalenderCampaignTypesDataTO;
import com.sixdee.magik.services.model.CalenderDataTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignTypeTO;
import com.sixdee.magik.services.service.CampaignCalenderService;

/**
 * @author Ramesh Babu Cheerla
 * @Date : April, 2019
 *
 */

@Service
@Transactional
public class CampaignCalenderServiceImpl implements CampaignCalenderService {

	static final Logger logger = Logger.getLogger(CampaignCalenderServiceImpl.class);

	@Autowired
	CampaignCalenderDao campaignCalenderDao;
	
	@Override
	public List<CalenderDataTO> getCalenderData(String startDate, String endDate, String userId) {
		// TODO Auto-generated method stub
		return campaignCalenderDao.getCalenderData(startDate, endDate, userId);
	}


	@Override
	public List<CalenderCampaignTypesDataTO> getCampaignTypesData(String date) {
		// TODO Auto-generated method stub
		return campaignCalenderDao.getCampaignTypesData(date);
	}


	@Override
	public List<CalenderCampaignTypesDataTO> getAllCampaign() {
		// TODO Auto-generated method stub
		return campaignCalenderDao.getAllCampaign();
	}



	@Override
	public String viewPromotion() {
		// TODO Auto-generated method stub
		return campaignCalenderDao.viewPromotion();
	}


	@Override
	public List<CampaignMasterTO> getCampaignInfo(String date) {
		// TODO Auto-generated method stub
		return campaignCalenderDao.getCampaignInfo(date);
	}


	@Override
	public List<CampaignTypeTO> getCampaignTypeInfo() {
		// TODO Auto-generated method stub
		return campaignCalenderDao.getCampaignTypeInfo();
	}

	
}
