package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.CreateAccountCustmCareTO;
import com.sixdee.magik.services.model.CreateUSerProfileTO;
import com.sixdee.magik.services.service.CreateAccountCustmCareService;
import com.sixdee.magik.services.service.CreateUserProfileCustmCareService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */

@RestController

public class CreateUserProfileCustmCareRestContoller {

	static final Logger logger = Logger.getLogger(CreateUserProfileCustmCareRestContoller.class);

	@Autowired
	SystemProperties properties;

	@Autowired
	CreateUserProfileCustmCareService createuserProfileSerivce;

	@PostMapping(MagikServicePath.GET_USER_PROFILE)
	public @ResponseBody RestInfo<CreateUSerProfileTO> getUserProfiles(HttpServletRequest httpServletRequest,
			@RequestBody CreateUSerProfileTO profileTO) {
		RestInfo<CreateUSerProfileTO> info = new RestInfo<CreateUSerProfileTO>();
		try {
			info.setOperationCode(0);
			info.setMessage("Success");
			info.setData(createuserProfileSerivce.getUserProfiles(profileTO));

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;
	}

}
