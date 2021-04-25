package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.URLConfigDAO;
import com.sixdee.magik.services.model.UrlConfigTO;
import com.sixdee.magik.services.service.URLConfigService;

/**
 * @author arun.jose
 *
 * January, 2019
 */
@Service
@Transactional
public class URLConfigServiceImpl implements URLConfigService{
	
	@Autowired
	 URLConfigDAO urlConfigDAO;


	@Override
	public List<UrlConfigTO> getUrlList() {
		// TODO Auto-generated method stub
		return urlConfigDAO.getUrlList();
	}


	@Override
	public String addUrl(UrlConfigTO configTO) {
		// TODO Auto-generated method stub
		return urlConfigDAO.addUrl(configTO);
	}


	@Override
	public String deleteUrl(UrlConfigTO configTO) {
		// TODO Auto-generated method stub
		return urlConfigDAO.deleteUrl(configTO);
	}

}
