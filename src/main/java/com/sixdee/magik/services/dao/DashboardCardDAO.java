/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;
import java.util.Map;

import com.sixdee.magik.services.model.DashboardCardDTO;

/**
 * @author Vinay Kariyappa
 *
 * Oct 3, 2018
 */
public interface DashboardCardDAO {

	public List<DashboardCardDTO> getOnlineDashboardCardDetails(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails1(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails2(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails3(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails4(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails5(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails6(DashboardCardDTO dashboardCardDTO);
	
	public List<DashboardCardDTO> getDashboardGraphDetails7(DashboardCardDTO dashboardCardDTO);	
}
