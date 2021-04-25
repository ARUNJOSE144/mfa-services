package com.sixdee.magik.services.controller;
/**
 * @author amal.a.s
 * @Date : 24th June, 2020
 * @Description : Managing Social Media scheduling details
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.SocialMediaScheduleMasterDTO;
import com.sixdee.magik.services.model.TelegramBodyDTO;
import com.sixdee.magik.services.model.TelegramBotConfig;
import com.sixdee.magik.services.model.TelegramDTO;
import com.sixdee.magik.services.model.TelegramSendMessageDTO;
import com.sixdee.magik.services.service.SocialMediaSchedulingService;
import com.sixdee.magik.services.util.MagikServicePath;

@RestController
public class SocialMediaSchedulingController {

	@Autowired
	SocialMediaSchedulingService socialMediaService;

	@PostMapping(MagikServicePath.SOCIAL_MEDIA_SCHEDULING)
	public @ResponseBody SocialMediaScheduleMasterDTO createScheduling(
			@RequestBody SocialMediaScheduleMasterDTO request) {

		SocialMediaScheduleMasterDTO response = socialMediaService.createScheduling(request);

		return response;
	}

	@PutMapping(MagikServicePath.SOCIAL_MEDIA_SCHEDULING)
	public @ResponseBody SocialMediaScheduleMasterDTO updateScheduling(
			@RequestBody SocialMediaScheduleMasterDTO request) {

		SocialMediaScheduleMasterDTO response = socialMediaService.updateScheduling(request);

		return response;
	}

	@DeleteMapping(MagikServicePath.SOCIAL_MEDIA_SCHEDULING)
	public @ResponseBody SocialMediaScheduleMasterDTO deleteScheduling(
			@RequestBody SocialMediaScheduleMasterDTO request) {

		SocialMediaScheduleMasterDTO response = socialMediaService.deleteScheduling(request);

		return response;
	}

	@GetMapping(MagikServicePath.SOCIAL_MEDIA_SCHEDULING)
	public @ResponseBody SocialMediaScheduleMasterDTO getSchedulingDetails(@RequestParam String campaignId) {

		SocialMediaScheduleMasterDTO response = socialMediaService.getSchedulingDetails(campaignId);

		return response;
	}

	@PostMapping(MagikServicePath.TELEGRAM_CHAT_BOT)
	public @ResponseBody String chatWithTelegramBot(@RequestBody TelegramBodyDTO request, @RequestParam String botId) {

		socialMediaService.chatWithTelegramBot(request, botId);

		return "SC0000";
	}

	@PostMapping(MagikServicePath.TELEGRAM_MANAGEMENT)
	public @ResponseBody String sendMessageToChat(@RequestBody TelegramSendMessageDTO request) {

		return socialMediaService.sendMessageToChat(request);
	}

	@GetMapping(MagikServicePath.TELEGRAM_MANAGEMENT)
	public @ResponseBody TelegramSendMessageDTO getBroadcastDetails() {

		return socialMediaService.getBroadcastDetails();
	}

	@PostMapping(MagikServicePath.TELEGRAM_BOT_COMMANDS)
	public @ResponseBody TelegramDTO configureBotCommands(@RequestBody TelegramDTO request) {

		return socialMediaService.configureBotCommands(request);
	}

	@PutMapping(MagikServicePath.TELEGRAM_BOT_COMMANDS)
	public @ResponseBody TelegramDTO updateBotCommands(@RequestBody TelegramDTO request) {

		return socialMediaService.updateBotCommands(request);
	}

	@GetMapping(MagikServicePath.TELEGRAM_BOT_COMMANDS)
	public @ResponseBody TelegramDTO getBotCommands() {

		return socialMediaService.getBotCommands();
	}
	
	@DeleteMapping(MagikServicePath.TELEGRAM_BOT_COMMANDS)
	public @ResponseBody TelegramDTO deleteBotCommands(@RequestParam String commandId) {

		return socialMediaService.deleteBotCommands(commandId);
	}
	
	@GetMapping(MagikServicePath.TELEGRAM_BOT_COMMANDS_UN)
	public @ResponseBody TelegramDTO uniqueBotCommands(@RequestParam String command, @RequestParam String botId) {

		return socialMediaService.uniqueBotCommands(command, botId);
	}

	@PostMapping(MagikServicePath.TELEGRAM_BOT_MANAGEMENT)
	public @ResponseBody TelegramBotConfig manageTelegramBot(@RequestBody TelegramBotConfig request) {

		return socialMediaService.manageTelegramBot(request);
	}

	@DeleteMapping(MagikServicePath.TELEGRAM_BOT_MANAGEMENT)
	public @ResponseBody TelegramBotConfig deleteTelegramBot(@RequestBody TelegramBotConfig request) {

		return socialMediaService.deleteTelegramBot(request);
	}

	@GetMapping(MagikServicePath.TELEGRAM_BOT_MANAGEMENT)
	public @ResponseBody TelegramBotConfig getTelegramBotList() {

		return socialMediaService.getTelegramBotList();
	}

}
