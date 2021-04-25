package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.GeoFencingDao;
import com.sixdee.magik.services.model.GeoLocationsTO;
import com.sixdee.magik.services.model.GeoTreeJsonTO;
import com.sixdee.magik.services.service.GeoFencingService;

/**
 * 
 * @author ramesh.cheerla
 *
 */

@Service
public class GeoFencingServiceImpl implements GeoFencingService {

	@Autowired
	GeoFencingDao geoFencingDao;

	@Override
	public int saveGeoLocation(GeoLocationsTO to) {
		// TODO Auto-generated method stub
		return geoFencingDao.saveGeoLocation(to);
	}

	@Override
	public List<GeoLocationsTO> getGeoLocationsTree() {
		// TODO Auto-generated method stub
		return geoFencingDao.getGeoLocationsTree();
	}

	@Override
	public String deleteGeoLocation(int locId) {
		// TODO Auto-generated method stub
		return geoFencingDao.deleteGeoLocation(locId);
	}

	@Override
	public GeoLocationsTO getGeoLocation(int locId) {
		// TODO Auto-generated method stub
		return geoFencingDao.getGeoLocation(locId);
	}

	@Override
	public int updateGeoLocation(GeoLocationsTO to) {
		// TODO Auto-generated method stub
		return geoFencingDao.updateGeoLocation(to);
	}

}
