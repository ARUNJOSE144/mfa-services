package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.TargetingAudienceDTO;

public interface TargetingAudienceService {

	TargetingAudienceDTO showEstimatedGraph(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO manageAudience(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO updateAudience(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO deleteAudience(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO getTargetAudience();

	TargetingAudienceDTO getCountries(String updateValue, String limit, String offset, String mediaType, String query, String country);

}
