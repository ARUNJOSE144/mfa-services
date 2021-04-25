package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ARPUBandsTO;
import com.sixdee.magik.services.model.IncrementalRevenueTO;

public interface IncrementalRevenueReportService {
	
	public List<IncrementalRevenueTO> getData(String month,String arpu, String segment);
	
	public List<ARPUBandsTO> getArpuBandData();

}
