/**
 * 
 */
package com.sixdee.magik.services.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.FbInsightsDTO;
import com.sixdee.magik.services.service.FbInsightsService;

/**
 * @author bhavyalakshmi.k
 *
 */
@RestController
public class FbInsightsController {
	static final Logger logger = Logger.getLogger(FbInsightsController.class);

	@Autowired
	FbInsightsService FbInsightsService;

	@PostMapping("/FbInsights")
	public FbInsightsDTO getResponse(@RequestBody FbInsightsDTO fbInsightsDTO) {
		logger.info("inside FbInsights:");
		fbInsightsDTO = FbInsightsService.getFbInsights(fbInsightsDTO);
		return fbInsightsDTO;

	}

}
