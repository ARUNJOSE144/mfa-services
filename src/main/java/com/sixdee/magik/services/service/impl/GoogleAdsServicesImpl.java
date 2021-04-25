package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.GoogleAdsDao;
import com.sixdee.magik.services.model.GoogleAdsMaster;
import com.sixdee.magik.services.service.GoogleAdsServices;

@Service
@Transactional
public class GoogleAdsServicesImpl implements GoogleAdsServices {
	
	@Autowired
	GoogleAdsDao googleAdsDao;
	
	public GoogleAdsMaster createAds(GoogleAdsMaster request) {
		return googleAdsDao.createAds(request);
	}
	
	public GoogleAdsMaster updateAds(GoogleAdsMaster request) {
		return googleAdsDao.updateAds(request);
	}
	
	public GoogleAdsMaster deleteAds(GoogleAdsMaster request) {
		return googleAdsDao.deleteAds(request);
	}
	
	public List<GoogleAdsMaster> getAds() {
		return googleAdsDao.getAds();
	}
}
