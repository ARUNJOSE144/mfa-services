/**
 * 
 */
package com.sixdee.magik.services.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.DashboardCardDTO;
import com.sixdee.magik.services.service.DashboardCardService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Vinay Kariyappa
 *
 * Oct 3, 2018
 */
@RestController
public class DashboardCardController {

static final Logger logger = Logger.getLogger(DashboardCardController.class);
	
	@Autowired
	DashboardCardService dashboardCardService;
	

	private SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
	
	@PostMapping("/getDasboardCardDetails/{mode}")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardDetails(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardDetails"+dashboardCardDTO.getHHour());
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		}
		
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardCardDetails(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardDetails");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		} 
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph1")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails1(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails1");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		}
		
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails1(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph2")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails2(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails2");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		}
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails2(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph3")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails3(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails3");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		}
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails3(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph4")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails4(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails4");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		}
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails4(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph5")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails5(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {

		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails5");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("online")) {
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
		}
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails5(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	}
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph6")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails6(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {
		
		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails6");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails6(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
	@PostMapping("/getDasboardCardDetails/{mode}/graph7")
	public @ResponseBody RestListInfo<DashboardCardDTO> getDashboardGraphDetails7(HttpServletRequest httpServletRequest, @PathVariable String mode, @RequestBody DashboardCardDTO dashboardCardDTO) {
		
		logger.info("Class : DashboardCardController | Method : getDashboardGraphDetails7");
		RestListInfo<DashboardCardDTO> info = new RestListInfo<DashboardCardDTO>();
		if(mode!=null && mode.equalsIgnoreCase("offline")) {
			dashboardCardDTO.setFeature("offline");
			dashboardCardDTO.setHHour(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
			Date date = new Date();
			String newstring = dateformat.format(date);
			dashboardCardDTO.setCdrDate(newstring);
			if(!dashboardCardDTO.isCustomDate()) {
				dashboardCardDTO.setStartDate(newstring);
				dashboardCardDTO.setEndDate(newstring);
				dashboardCardDTO.setDefaultDash(true);
			}
		}
		try {
			info.setData(dashboardCardService.getDashboardGraphDetails7(dashboardCardDTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	} 
	
}