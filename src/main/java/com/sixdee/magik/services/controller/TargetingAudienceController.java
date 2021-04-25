package com.sixdee.magik.services.controller;
/**
 * @author Amal A S
 * @category Target Audience for campaigns
 * @date 03/06/2020
 * 
 * @description This controller and related services are using for 
 * 				  crud operations of targeting audience and showing real time graphs 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.TargetingAudienceDTO;
import com.sixdee.magik.services.service.TargetingAudienceService;
import com.sixdee.magik.services.util.MagikServicePath;

@RestController
public class TargetingAudienceController {

	@Autowired
	TargetingAudienceService targetService;

	// ------- Show Targeting Audience Graph -------//

	@PostMapping(MagikServicePath.SHOW_AUDIENCE_GRAPH)
	public @ResponseBody TargetingAudienceDTO showEstimatedGraph(@RequestBody TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO responseDTO = null;

		responseDTO = targetService.showEstimatedGraph(requestDto);

		return responseDTO;

	}

	// ------- Configure Targeting Audience -------//

	@PostMapping(MagikServicePath.MANAGE_AUDIENCE)
	public @ResponseBody TargetingAudienceDTO manageAudience(@RequestBody TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO responseDTO = null;

		responseDTO = targetService.manageAudience(requestDto);

		return responseDTO;

	}

	// ------- Update Targeting Audience -------//

	@PutMapping(MagikServicePath.MANAGE_AUDIENCE)
	public @ResponseBody TargetingAudienceDTO updateAudience(@RequestBody TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO responseDTO = null;

		responseDTO = targetService.updateAudience(requestDto);

		return responseDTO;

	}

	// ------- Delete Targeting Audience -------//

	@DeleteMapping(MagikServicePath.MANAGE_AUDIENCE)
	public @ResponseBody TargetingAudienceDTO deleteAudience(@RequestBody TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO responseDTO = null;

		responseDTO = targetService.deleteAudience(requestDto);

		return responseDTO;

	}

	// ------- Listing of Targeting Audience -------//

	@GetMapping(MagikServicePath.MANAGE_AUDIENCE)
	public @ResponseBody TargetingAudienceDTO getTargetAudience() {

		TargetingAudienceDTO responseDTO = null;

		responseDTO = targetService.getTargetAudience();

		return responseDTO;

	}

	// ------- Listing of Targeting Audience Countries-------//

	@GetMapping(MagikServicePath.SHOW_AUDIENCE_COUNTRIES)
	public @ResponseBody TargetingAudienceDTO getCountryList(@RequestParam String updateValue,
			@RequestParam String limit, @RequestParam String offset, @RequestParam String mediaType,
			@RequestParam String query, @RequestParam String country) {

		TargetingAudienceDTO responseDTO = null;

		responseDTO = targetService.getCountries(updateValue, limit, offset, mediaType, query, country);

		return responseDTO;
	}

}
