/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ApplicationPropertyTO;

/**
 * @author Rajesh
 * January 2019
 */
public interface ApplicationPropertiesDAO {
	public List<ApplicationPropertyTO> getApplicationProperties();
	public String changePropertyStatus(ApplicationPropertyTO applicationPropertyTO);

}
