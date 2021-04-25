
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.KPIDAO;
import com.sixdee.magik.services.model.BLGroupDetailsDTO;
import com.sixdee.magik.services.model.DataTypeTO;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.service.KPIService;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Service
@Transactional
public class KPIServiceImpl implements KPIService {

	@Autowired
	KPIDAO kpiDao;

	@Override
	public GenericTO addKPI(KPITO to) {
		return kpiDao.addKPI(to);
	}

	@Override
	public List<KPITO> getKPIS() {
		return kpiDao.getKPIS();
	}

	@Override
	public List<DataTypeTO> getDataTypes() {
		return kpiDao.getDataTypes();
	}

	@Override
	public List<BLGroupDetailsDTO> getBLGroup(BLGroupDetailsDTO blGroupDetailsDTO) {
		return kpiDao.getBLGroup(blGroupDetailsDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.KPIService#getAllKPIs()
	 */
	@Override
	public List<KPITO> getAllKPIs() {
		return kpiDao.getAllKPIs();
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.KPIService#updateKPIs(com.sixdee.magik.services.model.GenericGroupDTO)
	 */
	@Override
	public void updateKPIs(GenericGroupDTO genericGroupDTO) throws Exception {
		kpiDao.updateKPIs(genericGroupDTO);
	}

	@Override
	public void deleteKPIs(GenericGroupDTO genericGroupDTO) throws Exception {
		kpiDao.deleteKPIs(genericGroupDTO);
	}

}
