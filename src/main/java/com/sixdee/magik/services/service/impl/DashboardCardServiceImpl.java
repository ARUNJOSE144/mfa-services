/**
 * 
 */
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.DashboardCardDAO;
import com.sixdee.magik.services.model.DashboardCardDTO;
import com.sixdee.magik.services.service.DashboardCardService;

/**
 * @author Vinay Kariyappa
 *
 * Oct 3, 2018
 */
@Service
@Transactional
public class DashboardCardServiceImpl implements DashboardCardService {

	@Autowired
	private DashboardCardDAO dashboardCardDAO;
	
	@Override
	public List<DashboardCardDTO> getDashboardCardDetails(DashboardCardDTO dashboardCardDTO) {
		return dashboardCardDAO.getOnlineDashboardCardDetails(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails(DashboardCardDTO dashboardCardDTO) {
		
		return dashboardCardDAO.getDashboardGraphDetails(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails1(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails1(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails1(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails2(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails2(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails2(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails3(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails3(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails3(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails4(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails4(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails4(dashboardCardDTO);
	}
	
	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails4(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails5(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails5(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails6(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails6(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails6(dashboardCardDTO);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.DashboardCardService#getDashboardGraphDetails7(com.sixdee.magik.services.model.DashboardCardDTO)
	 */
	@Override
	public List<DashboardCardDTO> getDashboardGraphDetails7(DashboardCardDTO dashboardCardDTO) {
		// TODO Auto-generated method stub
		return dashboardCardDAO.getDashboardGraphDetails7(dashboardCardDTO);
	}
	
}
