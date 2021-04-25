package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.CalenderCampaignTypesDataTO;
import com.sixdee.magik.services.model.CalenderDataTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.CampaignTypeTO;

/**
 * @author Ramesh Babu Cheerla
 * @Date : April, 2019
 *
 */

public interface CampaignCalenderService {

	List<CalenderDataTO> getCalenderData(String startDate, String endDate, String userId);

	List<CalenderCampaignTypesDataTO> getCampaignTypesData(String date);

	List<CalenderCampaignTypesDataTO> getAllCampaign();

	String viewPromotion();

	List<CampaignMasterTO> getCampaignInfo(String date);
	
	List<CampaignTypeTO> getCampaignTypeInfo();

}
