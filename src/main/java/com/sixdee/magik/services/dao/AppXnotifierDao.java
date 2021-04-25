package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.AppXNotifierToneTO;
import com.sixdee.magik.services.model.AppXNotifierTypesTO;
import com.sixdee.magik.services.model.CampaignMessageLanguagesTO;

public interface AppXnotifierDao {

	List<CampaignMessageLanguagesTO> getAppXLanguages();

	List<AppXNotifierToneTO> getToneDetails(String langAbb);

	List<AppXNotifierTypesTO> getNotificationTypes(String langAbb);

	String getTopicNames();

	String sendNotification(String requestBody);

	String manageSubscription(String requestBody, String subType);

}
