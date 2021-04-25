package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ApplicationPropertyTO;

/**
 * @author Rajesh
 *
 * January, 2019
 */
public interface ApplicationPropertiesService {
	
	public List<ApplicationPropertyTO> getApplicationProperties();
	public String changePropertyStatus(ApplicationPropertyTO applicationPropertyTO);

}
