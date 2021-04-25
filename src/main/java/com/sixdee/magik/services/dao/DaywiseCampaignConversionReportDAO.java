package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.DaywiseCampaignConversionTO;
import com.sixdee.magik.services.model.PaginationDTO;

public interface DaywiseCampaignConversionReportDAO {

	public List<DaywiseCampaignConversionTO> getData();

	public List<CampaignDefMasterTO> getCampaignData();

	public PaginationDTO<DaywiseCampaignConversionTO> getAllRecordsWithPagination(
			PaginationDTO<DaywiseCampaignConversionTO> paginationDTO, boolean isDowdload);

}
