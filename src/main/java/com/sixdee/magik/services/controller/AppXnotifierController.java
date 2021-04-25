package com.sixdee.magik.services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.AppXNotifierToneTO;
import com.sixdee.magik.services.model.AppXNotifierTypesTO;
import com.sixdee.magik.services.model.CampaignMessageLanguagesTO;
import com.sixdee.magik.services.service.AppXnotifierService;
import com.sixdee.magik.services.util.MagikServicePath;

/**
 * @author amal.a.s
 * @Date : 14th Jan, 2021
 * @Description : Managing App Notifications on mobile handsets
 */

@RestController
public class AppXnotifierController {

	@Autowired
	AppXnotifierService appXnotifierService;

	@GetMapping("/AppXLanguage")
	public @ResponseBody List<CampaignMessageLanguagesTO> getAppXLanguages() {
		return appXnotifierService.getAppXLanguages();
	}

	@GetMapping("/AppXTones")
	public @ResponseBody List<AppXNotifierToneTO> getToneDetails(@RequestParam String langAbb) {
		return appXnotifierService.getToneDetails(langAbb);
	}

	@GetMapping("/AppXTypes")
	public @ResponseBody List<AppXNotifierTypesTO> getNotificationTypes(@RequestParam String langAbb) {
		return appXnotifierService.getNotificationTypes(langAbb);
	}

	@GetMapping("/AppXTopics")
	public @ResponseBody String getTopicNames() {
		return appXnotifierService.getTopicNames();
	}
	
	@PostMapping("/AppXSchedule")
	public @ResponseBody String sendNotification(@RequestBody String requestBody) {
		return appXnotifierService.sendnotification(requestBody);
	}
	
	@PostMapping("/AppXManageSubscription")
	public @ResponseBody String manageSubscription(@RequestBody String requestBody, @RequestParam String subType) {
		return appXnotifierService.manageSubscription(requestBody, subType);
	}

}
