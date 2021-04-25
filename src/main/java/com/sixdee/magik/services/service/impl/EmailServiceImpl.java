package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.EmailDao;
import com.sixdee.magik.services.model.EmailMasterTO;
import com.sixdee.magik.services.model.EmailMenuTO;
import com.sixdee.magik.services.model.EmailResponseTO;
import com.sixdee.magik.services.service.EmailService;


@Service
public class EmailServiceImpl implements EmailService {

	
	@Autowired
	EmailDao emailDao;

	public EmailResponseTO saveEmail(EmailMasterTO emailMasterTO) {
		// TODO Auto-generated method stub
		return emailDao.saveEmail(emailMasterTO);
	}

	public EmailMenuTO getEmailMenus(String createUser) {
		// TODO Auto-generated method stub
		return emailDao.getEmailMenus(createUser);
	}

	
	public EmailResponseTO deleteEmail(int menuId) {
		// TODO Auto-generated method stub
		return emailDao.deleteEmail(menuId);
	}

	@Override
	public EmailMasterTO getEmail(int menuId) {
		// TODO Auto-generated method stub
		return emailDao.getEmail(menuId);
	}
	
	public EmailResponseTO editEmail(EmailMasterTO emailMasterTO) {
		// TODO Auto-generated method stub
		return emailDao.editEmail(emailMasterTO);
	}

	

}
