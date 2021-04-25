package com.sixdee.magik.services.dao;
/**
 * @author amal.a.s
 * @Date : June, 2020
 *
 */

import com.sixdee.magik.services.model.TargetingAudienceDTO;

public interface TargetingAudienceDao {

	TargetingAudienceDTO getEstimatedGraph(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO manageAudience(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO updateAudience(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO deleteAudience(TargetingAudienceDTO requestDto);

	TargetingAudienceDTO getTargetAudience();

	TargetingAudienceDTO getCountries(String updateValue, String limit, String offset, String mediaType, String query, String country);

}
