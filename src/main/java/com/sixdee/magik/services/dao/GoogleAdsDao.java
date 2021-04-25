package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.GoogleAdsMaster;

public interface GoogleAdsDao {

	GoogleAdsMaster createAds(GoogleAdsMaster request);

	GoogleAdsMaster updateAds(GoogleAdsMaster request);

	GoogleAdsMaster deleteAds(GoogleAdsMaster request);

	List<GoogleAdsMaster> getAds();

}
