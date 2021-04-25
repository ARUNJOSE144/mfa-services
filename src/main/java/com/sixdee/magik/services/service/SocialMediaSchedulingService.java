package com.sixdee.magik.services.service;

import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TelegramBodyDTO;
import com.sixdee.magik.services.model.TelegramBotConfig;
import com.sixdee.magik.services.model.TelegramDTO;
import com.sixdee.magik.services.model.TelegramSendMessageDTO;

public interface SocialMediaSchedulingService {

	SocialMediaScheduleMasterDTO createScheduling(SocialMediaScheduleMasterDTO request);
	
	SocialMediaScheduleMasterDTO updateScheduling(SocialMediaScheduleMasterDTO request);
	
	SocialMediaScheduleMasterDTO deleteScheduling(SocialMediaScheduleMasterDTO request);

	SocialMediaScheduleMasterDTO getSchedulingDetails(String campaignId);

	void chatWithTelegramBot(TelegramBodyDTO request, String botId);

	String sendMessageToChat(TelegramSendMessageDTO request);

	TelegramDTO configureBotCommands(TelegramDTO request);

	TelegramBotConfig manageTelegramBot(TelegramBotConfig request);

	TelegramBotConfig deleteTelegramBot(TelegramBotConfig request);

	TelegramBotConfig getTelegramBotList();

	TelegramSendMessageDTO getBroadcastDetails();

	TelegramDTO updateBotCommands(TelegramDTO request);

	TelegramDTO getBotCommands();

	TelegramDTO deleteBotCommands(String commandId);

	TelegramDTO uniqueBotCommands(String command, String botId);

}
