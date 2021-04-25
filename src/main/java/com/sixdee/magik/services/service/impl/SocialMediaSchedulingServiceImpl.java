package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.SocialMediaSchedulingDAO;
import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TelegramBodyDTO;
import com.sixdee.magik.services.model.TelegramBotConfig;
import com.sixdee.magik.services.model.TelegramDTO;
import com.sixdee.magik.services.model.TelegramSendMessageDTO;
import com.sixdee.magik.services.service.SocialMediaSchedulingService;

@Service
@Transactional
public class SocialMediaSchedulingServiceImpl implements SocialMediaSchedulingService {

	@Autowired
	SocialMediaSchedulingDAO socialMediaDao;

	public SocialMediaScheduleMasterDTO createScheduling(SocialMediaScheduleMasterDTO request) {
		SocialMediaScheduleMasterDTO reponse = socialMediaDao.createScheduling(request);
		return reponse;
	}
	
	public SocialMediaScheduleMasterDTO updateScheduling(SocialMediaScheduleMasterDTO request) {
		SocialMediaScheduleMasterDTO reponse = socialMediaDao.updateScheduling(request);
		return reponse;
	}
	
	public SocialMediaScheduleMasterDTO deleteScheduling(SocialMediaScheduleMasterDTO request) {
		SocialMediaScheduleMasterDTO reponse = socialMediaDao.deleteScheduling(request);
		return reponse;
	}
	
	public SocialMediaScheduleMasterDTO getSchedulingDetails(String campaignId) {

		SocialMediaScheduleMasterDTO reponse = socialMediaDao.getSchedulingDetails(campaignId);
		return reponse;
	
	}
	
	public void chatWithTelegramBot(TelegramBodyDTO request, String botId) {
		socialMediaDao.chatWithTelegramBot(request, botId);
	}
	
	public String sendMessageToChat(TelegramSendMessageDTO request) {
		return  socialMediaDao.sendMessageToChat(request);
	}
	
	public TelegramDTO configureBotCommands(TelegramDTO request) {
		return  socialMediaDao.configureBotCommands(request);
	}
	
	public TelegramBotConfig manageTelegramBot(TelegramBotConfig request) {
		return  socialMediaDao.manageTelegramBot(request);
	}

	public TelegramBotConfig deleteTelegramBot(TelegramBotConfig request) {
		return  socialMediaDao.deleteTelegramBot(request);
	}
	public TelegramBotConfig getTelegramBotList() {
		return  socialMediaDao.getTelegramBotList();
	}
	
	public TelegramSendMessageDTO getBroadcastDetails() {
		return  socialMediaDao.getBroadcastDetails();
	}
	
	public TelegramDTO updateBotCommands(TelegramDTO request) {
		return  socialMediaDao.updateBotCommands(request);
	}
	
	public TelegramDTO getBotCommands() {
		return  socialMediaDao.getBotCommands();
	}
	
	public TelegramDTO deleteBotCommands(String commandId) {
		return  socialMediaDao.deleteBotCommands(commandId);
	}
	
	public TelegramDTO uniqueBotCommands(String command, String botId) {
		return  socialMediaDao.uniqueBotCommands(command, botId);
	}
}
