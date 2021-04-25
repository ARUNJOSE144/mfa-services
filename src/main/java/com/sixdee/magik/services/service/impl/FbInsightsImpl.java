/**
 * 
 */
package com.sixdee.magik.services.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sixdee.magik.services.model.FbInsightsDTO;
import com.sixdee.magik.services.service.FbInsightsService;

/**
 * @author bhavyalakshmi.k
 *
 */
@Service
public class FbInsightsImpl implements FbInsightsService {

	@Value("${facebook.url}")
	private String fbUrl;
	@Value("${facebook.accesstoken}")
	private String accessToken;
	static final Logger logger = Logger.getLogger(FbInsightsImpl.class);

	private final RestTemplate restTemplate;

	public FbInsightsImpl(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	@Override
	public FbInsightsDTO getFbInsights(FbInsightsDTO fbInsightsDTO) {
		// TODO Auto-generated method stub
		String campaignId = fbInsightsDTO.getCampaignId();
		String noDays = fbInsightsDTO.getNoOfDays();

		try {
			logger.info("calling fb insights url::");
			String url = fbUrl + "//" + campaignId + "//insights?date_preset=last_" + noDays + "d&access_token="
					+ accessToken;
			System.out.println("url::" + url);
			fbInsightsDTO = this.restTemplate.getForObject(url, FbInsightsDTO.class);
			logger.info("values::" + fbInsightsDTO.toString());

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return fbInsightsDTO;

	}

}
