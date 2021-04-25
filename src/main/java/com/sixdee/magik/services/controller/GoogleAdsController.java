package com.sixdee.magik.services.controller;
/**
 * @author Amal A S
 * @category Google Ads Management
 * @date 09/07/2020
 *  
 */

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.GoogleAdsMaster;
import com.sixdee.magik.services.service.GoogleAdsServices;
import com.sixdee.magik.services.util.MagikServicePath;

@RestController
public class GoogleAdsController {

	@Autowired
	GoogleAdsServices googleAdsServices;

	@PostMapping(MagikServicePath.GOOGLE_ADS_MANAGEMENT)
	public @ResponseBody GoogleAdsMaster createAds(@RequestBody GoogleAdsMaster request) {
		return googleAdsServices.createAds(request);
	}

	@PutMapping(MagikServicePath.GOOGLE_ADS_MANAGEMENT)
	public @ResponseBody GoogleAdsMaster updateAds(@RequestBody GoogleAdsMaster request) {
		return googleAdsServices.updateAds(request);
	}

	@DeleteMapping(MagikServicePath.GOOGLE_ADS_MANAGEMENT)
	public @ResponseBody GoogleAdsMaster deleteAds(@RequestBody GoogleAdsMaster request) {
		return googleAdsServices.deleteAds(request);
	}

	@GetMapping(MagikServicePath.GOOGLE_ADS_MANAGEMENT)
	public @ResponseBody List<GoogleAdsMaster> getAds() {
		return googleAdsServices.getAds();
	}

}
