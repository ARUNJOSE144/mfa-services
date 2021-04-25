package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.UrlConfigTO;

/**
 * @author arun.jose
 *
 * January, 2019
 */
public interface URLConfigService {

	List<UrlConfigTO> getUrlList();

	String addUrl(UrlConfigTO configTO);

	String deleteUrl(UrlConfigTO configTO);
	
	
}
