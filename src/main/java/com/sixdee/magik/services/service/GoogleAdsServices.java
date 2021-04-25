package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.GoogleAdsMaster;

public interface GoogleAdsServices {

	GoogleAdsMaster createAds(GoogleAdsMaster request);

	GoogleAdsMaster updateAds(GoogleAdsMaster request);

	GoogleAdsMaster deleteAds(GoogleAdsMaster request);

	List<GoogleAdsMaster> getAds();
}
