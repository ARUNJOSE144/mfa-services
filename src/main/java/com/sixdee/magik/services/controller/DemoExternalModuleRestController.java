package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.DemoExternalModuleTO;
import com.sixdee.magik.services.service.DemoExternalModuleService;
import com.sixdee.magik.services.service.AuditInfoService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */
@RestController
public class DemoExternalModuleRestController {

	static final Logger logger = Logger.getLogger(DemoExternalModuleRestController.class);

	@Autowired
	DemoExternalModuleService demoExternalService;

	@GetMapping("getActiveOffer")
	public @ResponseBody RestInfo<DemoExternalModuleTO> getActiveOffer(HttpServletRequest httpServletRequest) {

		RestInfo<DemoExternalModuleTO> info = new RestInfo<DemoExternalModuleTO>();

		try {
			info.setOperationCode(0);
			info.setMessage("get Active Offer.");
			info.setData(demoExternalService.getActiveOffer());
		} catch (Exception e) {
			info.setOperationCode(1);
			info.setMessage("  get Active Offer Failed.");
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
		}

		return info;
	}

}
