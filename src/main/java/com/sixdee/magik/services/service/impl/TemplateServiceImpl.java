package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.TemplateDao;
import com.sixdee.magik.services.model.TemplateResponseTO;
import com.sixdee.magik.services.model.TemplateTO;
import com.sixdee.magik.services.service.TemplateService;


@Service
public class TemplateServiceImpl implements TemplateService {

	
	@Autowired
	TemplateDao templateDao;
	
	@Override
	public TemplateResponseTO saveTemplate(TemplateTO templateTO) {
		// TODO Auto-generated method stub
		return templateDao.saveTemplate(templateTO);
	}

	@Override
	public TemplateResponseTO deleteTemplate(TemplateTO templateTO) {
		// TODO Auto-generated method stub
		return templateDao.deleteTemplate(templateTO);
	}

	@Override
	public TemplateResponseTO getTemplate(TemplateTO templateTO) {
		// TODO Auto-generated method stub
		return templateDao.getTemplate(templateTO);
	}

	@Override
	public TemplateResponseTO viewTemplate(TemplateTO templateTO) {
		// TODO Auto-generated method stub
		return templateDao.viewTemplate(templateTO);
	}


	

}
