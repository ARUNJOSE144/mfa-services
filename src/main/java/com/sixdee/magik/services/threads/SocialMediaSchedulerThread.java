package com.sixdee.magik.services.threads;
/**
 * @author Amal A S
 * @category Scheduler for facebook campaigns
 * @date 23/06/2020
 * 
 */

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.sixdee.magik.services.dao.impl.StatusCodeDAOImpl;
import com.sixdee.magik.services.service.FacebookCampaigningService;
import com.sixdee.magik.services.service.GoogleCampaigningService;

@SpringBootApplication
@EnableScheduling
public class SocialMediaSchedulerThread {

	@Autowired
	FacebookCampaigningService facebookCampaigningService;
	
	@Autowired
	GoogleCampaigningService googleCampaigningService;
	
	@Autowired
	StatusCodeDAOImpl statusCodeImpl;

	@Autowired
	Environment env;
	
	public Map<String, String> telegramCommands = null;
	public Map<String, String> botDetails = null;
	public Map<String, String> botNamings = null;
	public Map<String, String> botIDId = null;
	public static String fileUploadPath = null;

	@Scheduled(fixedDelay = 30000)
	public void scheduler() {
		try {
			
			if (env.getProperty("facebook.campaigner.enabled").equalsIgnoreCase("true")) {
			facebookCampaigningService.manageCampaigns();
			}

			if (env.getProperty("facebook.scheduler.enabled").equalsIgnoreCase("true")) {
				facebookCampaigningService.scheduleCampaigns();
			}
			
			if (env.getProperty("google.campaigner.enabled").equalsIgnoreCase("true")) {
				googleCampaigningService.manageCampaigns();
			}
			
			

			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	@EventListener(ApplicationReadyEvent.class)
	public void loadSocialMediaCache() {
		try {
			
			fileUploadPath = env.getProperty("file.upload.path");
			
			if (env.getProperty("telegram.commands.cache.enabled").equalsIgnoreCase("true")) {
				telegramCommands = statusCodeImpl.getCommands();
				System.out.println("Telegram Map : "+telegramCommands);
				botDetails = statusCodeImpl.getBotDetails();
				System.out.println("Bot Map : "+botDetails);
				botNamings = statusCodeImpl.getBotNamings();
				System.out.println("Bot Namings : "+botNamings);
				botIDId = statusCodeImpl.getBotId();
				System.out.println("Bot Id : "+botNamings);
				}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
