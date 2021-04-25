package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.BLGroupDetailsDTO;
import com.sixdee.magik.services.model.DataTypeTO;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.KPITO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public interface KPIDAO {

	// Add KPI
	public GenericTO addKPI(KPITO to);

	// Get All KPI's
	public List<KPITO> getKPIS();

	// Search KPI's
	public List<KPITO> searchKPI(String srchEle);

	// Get Data Types
	public List<DataTypeTO> getDataTypes();

	public List<BLGroupDetailsDTO> getBLGroup(BLGroupDetailsDTO blGroupDetailsDTO);
	
	public List<KPITO> getAllKPIs();
	
	public void updateKPIs(GenericGroupDTO genericGroupDTO) throws Exception;
	
	public void deleteKPIs(GenericGroupDTO genericGroupDTO) throws Exception;
}
