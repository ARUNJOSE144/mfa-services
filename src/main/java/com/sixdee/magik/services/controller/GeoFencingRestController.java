package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.model.GeoLocationsTO;
import com.sixdee.magik.services.service.GeoFencingService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * 
 * @author ramesh.cheerla
 *
 */
@RestController
public class GeoFencingRestController {
	static final Logger logger = Logger.getLogger(FunctionMetricsRestControllar.class);

	@Autowired
	GeoFencingService geoFencingService;

	@PostMapping(MagikServicePath.SAVE_GEO_LOCATION)
	public @ResponseBody RestInfo<Integer> saveGeoLocation(HttpServletRequest httpServletRequest, @RequestBody GeoLocationsTO to) {

		logger.info("================== Geo Loaction API Start =====================");
		logger.info("Class :  GeoFencingController | Method : saveGeoLocation");

		RestInfo<Integer> info = new RestInfo<Integer>();

		try {
			info.setData(geoFencingService.saveGeoLocation(to));
			info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GeoFencingController  |  Method : saveGeoLocation " + e);
			e.printStackTrace();
		}

		logger.info("================== Geo Loaction API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_GEO_LOCATIONS_TREE)
	public @ResponseBody RestListInfo<GeoLocationsTO> getGeoLocationsTree(HttpServletRequest httpServletRequest) {

		logger.info("================== Geo Loaction API Start =====================");
		logger.info("Class :  GeoFencingController | Method : getGeoLocationsTree");

		RestListInfo<GeoLocationsTO> info = new RestListInfo<GeoLocationsTO>();

		try {
			info.setData(geoFencingService.getGeoLocationsTree());
			info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GeoFencingController  |  Method : getGeoLocationsTree " + e);
			e.printStackTrace();
		}

		logger.info("================== Geo Loaction API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.DELETE_GEO_LOCATION)
	public @ResponseBody RestInfo<String> deleteGeoLocation(HttpServletRequest httpServletRequest) {

		logger.info("================== Geo Loaction API Start =====================");
		logger.info("Class :  GeoFencingController | Method : deleteGeoLocation");

		RestInfo<String> info = new RestInfo<String>();
		int locId = Integer.parseInt(httpServletRequest.getParameter("locId"));

		try {
			
			info.setData(geoFencingService.deleteGeoLocation(locId));
			info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GeoFencingController  |  Method : deleteGeoLocation " + e);
			e.printStackTrace();
		}

		logger.info("================== Geo Loaction API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.GET_GEO_LOCATION)
	public @ResponseBody RestInfo<GeoLocationsTO> getGeoLocation(HttpServletRequest httpServletRequest) {

		logger.info("================== Geo Loaction API Start =====================");
		logger.info("Class :  GeoFencingController | Method : getGeoLocation");

		RestInfo<GeoLocationsTO> info = new RestInfo<GeoLocationsTO>();
		int locId = Integer.parseInt(httpServletRequest.getParameter("locId"));

		try {
			info.setData(geoFencingService.getGeoLocation(locId));
			info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GeoFencingController  |  Method : getGeoLocation " + e);
			e.printStackTrace();
		}

		logger.info("================== Geo Loaction API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.UPDATE_GEO_LOCATION)
	public @ResponseBody RestInfo<Integer> updateGeoLocation(HttpServletRequest httpServletRequest, @RequestBody GeoLocationsTO to) {

		logger.info("================== Geo Loaction API Start =====================");
		logger.info("Class :  GeoFencingController | Method : updateGeoLocation");

		RestInfo<Integer> info = new RestInfo<Integer>();

		try {
			info.setData(geoFencingService.updateGeoLocation(to));
			info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GeoFencingController  |  Method : updateGeoLocation " + e);
			e.printStackTrace();
		}

		logger.info("================== Geo Loaction API End =====================");

		return info;
	}
}
