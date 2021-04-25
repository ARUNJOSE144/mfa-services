package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.DaywiseCampaignConversionReportDAO;
import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.DaywiseCampaignConversionTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.service.DaywiseCampaignConversionReportService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : February, 2021
 */

@Service
@Transactional
public class DaywiseCampaignConversionReportServiceImpl implements DaywiseCampaignConversionReportService {

	@Autowired
	DaywiseCampaignConversionReportDAO daoLayer;

	@Override
	public List<DaywiseCampaignConversionTO> getData() {
		// TODO Auto-generated method stub
		return daoLayer.getData();
	}

	@Override
	public List<CampaignDefMasterTO> getCampaignData() {
		// TODO Auto-generated method stub
		return daoLayer.getCampaignData();
	}

	@Override
	public PaginationDTO<DaywiseCampaignConversionTO> getAllRecordsWithPagination(
			PaginationDTO<DaywiseCampaignConversionTO> paginationDTO, boolean isDowdload) {
		// TODO Auto-generated method stub
		return daoLayer.getAllRecordsWithPagination(paginationDTO, isDowdload);
	}

}
