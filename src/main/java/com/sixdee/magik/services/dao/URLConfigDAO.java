/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.UrlConfigTO;

/**
 * @author arun.jose
 * January 2019
 */
public interface URLConfigDAO {
	
	public List<UrlConfigTO> getUrlList();

	public String addUrl(UrlConfigTO configTO);

	public String deleteUrl(UrlConfigTO configTO);

}
