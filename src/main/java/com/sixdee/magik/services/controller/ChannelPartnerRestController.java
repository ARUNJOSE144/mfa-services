package com.sixdee.magik.services.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ChannelPartnerDetails;
import com.sixdee.magik.services.model.DesignationHierarchyDetails;
import com.sixdee.magik.services.service.ChannelPartnerService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ramesh.cheerla
 * @Date : January, 2019
 *
 */

@RequestMapping("channelPartner")
@RestController
public class ChannelPartnerRestController {

	static final Logger logger = Logger.getLogger(ChannelPartnerRestController.class);
	
	@Autowired
	ChannelPartnerService channelPartnerService;
	
	@GetMapping("/v1/getChannelPartners")
	public @ResponseBody RestListInfo<ChannelPartnerDetails> getChannelPartners() {

	
		logger.info("==================  ChannelPartnerRestController Controllar API Start =====================");
		logger.info("Class : ChannelPartnerRestController | Method : getChannelPartners");
		RestListInfo<ChannelPartnerDetails> info = new RestListInfo<ChannelPartnerDetails> ();
		try {
			info.setData(channelPartnerService.getChannelPartners());
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ChannelPartnerRestController  |  Method : getChannelPartners " + e);
			e.printStackTrace();
		}
		logger.info("================== ChannelPartnerRestController Rest Controllar API End =====================");
		return info;
	}
	
	@PostMapping("/v1/getDesignationsByChannelPartner")
	public @ResponseBody RestListInfo<DesignationHierarchyDetails> getDesignationsByChannelPartner(@RequestParam(value="channelType") long channelType) {

	
		logger.info("==================  ChannelPartnerRestController Controllar API Start =====================");
		logger.info("Class : ChannelPartnerRestController | Method : getDesignationsByChannelPartner");
		RestListInfo<DesignationHierarchyDetails> info = new RestListInfo<DesignationHierarchyDetails> ();
		try {
			info.setData(channelPartnerService.getDesignationsByChannelPartner(channelType));
			info.setOperationCode(0);
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ChannelPartnerRestController  |  Method : getDesignationsByChannelPartner " + e);
			e.printStackTrace();
		}
		logger.info("================== ChannelPartnerRestController Rest Controllar API End =====================");
		return info;
	}


}
