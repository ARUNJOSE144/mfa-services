package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.GeoLocationsTO;

/**
 * 
 * @author ramesh.cheerla
 *
 */

public interface GeoFencingService {

	public int saveGeoLocation(GeoLocationsTO to);

	public List<GeoLocationsTO> getGeoLocationsTree();

	public String deleteGeoLocation(int locId);

	public GeoLocationsTO getGeoLocation(int locId);
	
	public int updateGeoLocation(GeoLocationsTO to);

}
