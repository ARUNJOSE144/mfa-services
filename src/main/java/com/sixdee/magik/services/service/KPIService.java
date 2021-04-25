package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.BLGroupDetailsDTO;
import com.sixdee.magik.services.model.DataTypeTO;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.KPITO;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

public interface KPIService {

	// Add KPI
	public GenericTO addKPI(KPITO to);

	// Get All KPI's
	public List<KPITO> getKPIS();

	// Get Data Types
	public List<DataTypeTO> getDataTypes();

	public List<BLGroupDetailsDTO> getBLGroup(BLGroupDetailsDTO blGroupDetailsDTO);
	
	public List<KPITO> getAllKPIs();
	
	public void updateKPIs(GenericGroupDTO genericGroupDTO) throws Exception;
	
	public void deleteKPIs(GenericGroupDTO genericGroupDTO) throws Exception;
}
