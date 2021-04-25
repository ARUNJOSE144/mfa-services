package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.CustomerCareDTO;
import com.sixdee.magik.services.model.CustomerCareHeaders;

public interface CustomerCareService {

	public String getDashboardDetails(CustomerCareHeaders customerCareHeaders) throws Exception;

	public List<CustomerCareDTO> getDashboardDetailsOld(CustomerCareDTO customerCareDTO) throws Exception;
}
