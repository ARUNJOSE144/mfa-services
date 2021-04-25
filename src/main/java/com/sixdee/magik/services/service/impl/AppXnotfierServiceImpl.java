package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.AppXnotifierDao;
import com.sixdee.magik.services.model.AppXNotifierToneTO;
import com.sixdee.magik.services.model.AppXNotifierTypesTO;
import com.sixdee.magik.services.model.CampaignMessageLanguagesTO;
import com.sixdee.magik.services.service.AppXnotifierService;

@Service
@Transactional
public class AppXnotfierServiceImpl implements AppXnotifierService {

	@Autowired
	AppXnotifierDao appXnotifierDao;
	
	public List<CampaignMessageLanguagesTO> getAppXLanguages() {
		return appXnotifierDao.getAppXLanguages();
	}
	
	public List<AppXNotifierToneTO> getToneDetails(String langAbb) {
		return appXnotifierDao.getToneDetails(langAbb);
	}
	
	public List<AppXNotifierTypesTO> getNotificationTypes(String langAbb) {
		return appXnotifierDao.getNotificationTypes(langAbb);
	}
	
	public String getTopicNames() {
		return appXnotifierDao.getTopicNames();
	}
	
	public String sendnotification(String requestBody) {
		return appXnotifierDao.sendNotification(requestBody);
	}
	
	public String manageSubscription(String requestBody, String subType) {
		return appXnotifierDao.manageSubscription(requestBody, subType);
	}
	
}
