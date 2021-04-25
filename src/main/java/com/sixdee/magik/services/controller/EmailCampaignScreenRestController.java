package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.EmailMasterTO;
import com.sixdee.magik.services.model.EmailMenuTO;
import com.sixdee.magik.services.model.EmailMessageTO;
import com.sixdee.magik.services.model.EmailResponseTO;
import com.sixdee.magik.services.service.EmailService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * 
 * @author Anees CK
 * @Date : June, 2020
 *
 */
@RestController
public class EmailCampaignScreenRestController {

	
	static final Logger logger = Logger.getLogger(EmailCampaignScreenRestController.class);
	

	@Autowired
	EmailService  emailService;
		
	@PostMapping(MagikServicePath.SAVE_EMAIL)
	public @ResponseBody EmailResponseTO saveEmail(@RequestBody EmailMasterTO emailDetails) {
		logger.info("================== EmailCampaignScreenRestController:saveEmail");
		EmailResponseTO response = emailService.saveEmail(emailDetails);
		return response;
	}
	
	@GetMapping(MagikServicePath.GET_MENUS)
	public @ResponseBody EmailMenuTO getEmailMenus(@RequestParam String createUser) {
		logger.info("================== EmailCampaignScreenRestController:getEmailMenus");
		EmailMenuTO emailMenuTO = emailService.getEmailMenus(createUser);
		return emailMenuTO;
	}
	
	@GetMapping(MagikServicePath.DELETE_EMAIL)
	public @ResponseBody EmailResponseTO deleteEmail(@RequestParam int menuId) {
		logger.info("================== EmailCampaignScreenRestController:deleteEmail");
		EmailResponseTO response = emailService.deleteEmail(menuId);
		return response;
	}
	
	@GetMapping(MagikServicePath.GET_EMAIL)
	public @ResponseBody EmailMasterTO getEmail(@RequestParam int menuId) {
		logger.info("================== EmailCampaignScreenRestController:getEmail");
		EmailMasterTO emailMasterTO = emailService.getEmail(menuId);
		return emailMasterTO;
	}
	
	@PostMapping(MagikServicePath.EDIT_EMAIL)
	public @ResponseBody EmailResponseTO editEmail(@RequestBody EmailMasterTO emailDetails) {
		logger.info("================== EmailCampaignScreenRestController:editEmail");
		EmailResponseTO response = emailService.editEmail(emailDetails);
		return response;
	}
}
