package com.sixdee.magik.services.service.impl;
/**
 * @author amal.a.s
 * @Date : June, 2020
 *
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.TargetingAudienceDao;
import com.sixdee.magik.services.model.TargetingAudienceDTO;
import com.sixdee.magik.services.service.TargetingAudienceService;

@Service
@Transactional
public class TargetingAudienceServiceImpl implements TargetingAudienceService {

	@Autowired
	TargetingAudienceDao targetingAudienceDao;

	public TargetingAudienceDTO showEstimatedGraph(TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO response = targetingAudienceDao.getEstimatedGraph(requestDto);

		return response;
	}

	public TargetingAudienceDTO manageAudience(TargetingAudienceDTO requestDto) {
		
		TargetingAudienceDTO response = targetingAudienceDao.manageAudience(requestDto);

		return response;
	}
	
	public TargetingAudienceDTO updateAudience(TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO response = targetingAudienceDao.updateAudience(requestDto);

		return response;
		
	}
	
	public TargetingAudienceDTO deleteAudience(TargetingAudienceDTO requestDto) {

		TargetingAudienceDTO response = targetingAudienceDao.deleteAudience(requestDto);

		return response;
		
	}
	
	public TargetingAudienceDTO getTargetAudience() {
		
		TargetingAudienceDTO response = targetingAudienceDao.getTargetAudience();

		return response;
	}

	public TargetingAudienceDTO getCountries(String updateValue, String limit, String offset, String mediaType, String query, String country) {
	
		TargetingAudienceDTO response = targetingAudienceDao.getCountries(updateValue, limit, offset, mediaType, query, country);

		return response;

	}
	
}
