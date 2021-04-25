package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.EmailMasterTO;
import com.sixdee.magik.services.model.EmailMenuTO;
import com.sixdee.magik.services.model.EmailResponseTO;

public interface EmailService {

	
	public EmailResponseTO  saveEmail(EmailMasterTO  emailMasterTO);
	
	public EmailMenuTO getEmailMenus(String  createUser);
	
	public EmailResponseTO deleteEmail(int menuId);

	public EmailMasterTO getEmail(int menuId);
	
	public EmailResponseTO  editEmail(EmailMasterTO  emailMasterTO);
	
}
