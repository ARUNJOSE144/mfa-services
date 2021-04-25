package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.AppXNotifierToneTO;
import com.sixdee.magik.services.model.AppXNotifierTypesTO;
import com.sixdee.magik.services.model.CampaignMessageLanguagesTO;

public interface AppXnotifierService {

	List<CampaignMessageLanguagesTO> getAppXLanguages();

	List<AppXNotifierToneTO> getToneDetails(String langAbb);

	List<AppXNotifierTypesTO> getNotificationTypes(String langAbb);

	String getTopicNames();

	String sendnotification(String requestBody);

	String manageSubscription(String requestBody, String subType);

}
