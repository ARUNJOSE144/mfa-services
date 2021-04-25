package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.EmailMasterTO;
import com.sixdee.magik.services.model.EmailMenuTO;
import com.sixdee.magik.services.model.EmailMessageTO;
import com.sixdee.magik.services.model.EmailResponseTO;

public interface EmailDao {

	EmailMenuTO getEmailMenus(String createUser);

	List<EmailMessageTO> getEmailMessages(int menuId);

	EmailResponseTO deleteEmail(int menuId);

	EmailResponseTO saveEmail(EmailMasterTO emailMasterTO);

	EmailMasterTO getEmail(int menuId);
	
	EmailResponseTO editEmail(EmailMasterTO emailMasterTO);

}