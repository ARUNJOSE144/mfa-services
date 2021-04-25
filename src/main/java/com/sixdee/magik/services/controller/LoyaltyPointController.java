package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.LoyaltyRequestDTO;
import com.sixdee.magik.services.service.LoyaltyPointService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestInfo;

@RestController
public class LoyaltyPointController {
	
	static final Logger logger = Logger.getLogger(LoyaltyPointController.class);
	
	@Autowired
	private LoyaltyPointService loyaltyPointService;
	
	
	@PostMapping("/{talent}/pointTransfer")
	public @ResponseBody RestInfo<LoyaltyRequestDTO> accountTransfer(HttpServletRequest httpServletRequest, @PathVariable String talent, @RequestBody LoyaltyRequestDTO loyaltyRequestDTO) {
		logger.info("LoyaltyAccountController>>>>>>>accountTransfer>>>>>>>");
		RestInfo<LoyaltyRequestDTO> info = new RestInfo<LoyaltyRequestDTO>();
		try {
			info.setOperationCode(0);
			if(talent!=null && talent.equalsIgnoreCase("1.0")) {
				info.setData(loyaltyPointService.pointTransfer(loyaltyRequestDTO));
			}
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			logger.error("Exception :: " + e.getMessage());
		}
		return info;
	}
}
