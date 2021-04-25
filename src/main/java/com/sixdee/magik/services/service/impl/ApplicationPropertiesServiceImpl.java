package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ApplicationPropertiesDAO;
import com.sixdee.magik.services.model.ApplicationPropertyTO;
import com.sixdee.magik.services.service.ApplicationPropertiesService;

/**
 * @author Rajesh
 *
 * January, 2019
 */
@Service
@Transactional
public class ApplicationPropertiesServiceImpl implements ApplicationPropertiesService{
	
	@Autowired
	ApplicationPropertiesDAO applicationPropertiesDAO;

	@Override
	public List<ApplicationPropertyTO> getApplicationProperties() {
		// TODO Auto-generated method stub
		return applicationPropertiesDAO.getApplicationProperties();
	}

	@Override
	public String changePropertyStatus(ApplicationPropertyTO applicationPropertyTO) {
		// TODO Auto-generated method stub
		return applicationPropertiesDAO.changePropertyStatus(applicationPropertyTO);
	}

}
