package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.sixdee.magik.services.dao.CampaignInformationReportDAO;
import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.CampaignInformationReportTO;
import com.sixdee.magik.services.service.CampaignInformationReportService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author JANARDHAN REDDY
 * @Date : March, 2021
 */


@Service
@Transactional
public class CampaignInformationReportServiceImpl implements CampaignInformationReportService{

	@Autowired CampaignInformationReportDAO daoLayer;
	
	/*@Override
	public List<CampaignDefMasterTO> getCampaignData() {
		// TODO Auto-generated method stub
		return daoLayer.getCampaignData();
	}
*/
	@Override
	public List<CampaignInformationReportTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

}
