package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CreateAccountCustmCareTO;
import com.sixdee.magik.services.service.CreateAccountCustmCareService;
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
public class CreateAccountCustmCareRestController {

	static final Logger logger = Logger.getLogger(CreateAccountCustmCareRestController.class);

	@Autowired
	SystemProperties properties;

	@Autowired
	CreateAccountCustmCareService createAccountSerivce;

	@PostMapping(MagikServicePath.CREATE_ACCOUNT)
	public @ResponseBody RestInfo<CreateAccountCustmCareTO> CreateAccount(HttpServletRequest httpServletRequest,
			@RequestBody CreateAccountCustmCareTO createAccountTO) {
		RestInfo<CreateAccountCustmCareTO> info = new RestInfo<CreateAccountCustmCareTO>();
		CreateAccountCustmCareTO status;
		try {
			status = createAccountSerivce.CreateAccount(createAccountTO);
			System.out.println("status rest :  " + status);
			if (status.getStatus() == "0") {
				info.setOperationCode(0);
				info.setMessage(status.getMessage());

			} else {
				if (status.getStatus() == "1") {
					info.setOperationCode(1);
					info.setMessage(status.getMessage());
				} else {
					info.setOperationCode(2);
					info.setMessage("Application Process Error !.");
				}
			}

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;

	}

	@PostMapping(MagikServicePath.DELETE_ACCOUNT)
	public @ResponseBody RestInfo<CreateAccountCustmCareTO> deleteAccount(HttpServletRequest httpServletRequest,
			@RequestBody CreateAccountCustmCareTO deleteAccountTO) {
		RestInfo<CreateAccountCustmCareTO> info = new RestInfo<CreateAccountCustmCareTO>();
		CreateAccountCustmCareTO status;
		try {
			status = createAccountSerivce.deleteAccount(deleteAccountTO);
			System.out.println("status rest :  " + status);
			if (status.getStatus() == "0") {
				info.setOperationCode(0);
				info.setMessage(status.getMessage());

			} else {
				if (status.getStatus() == "1") {
					info.setOperationCode(1);
					info.setMessage(status.getMessage());
				} else {
					info.setOperationCode(2);
					info.setMessage("Application Process Error !.");
				}
			}

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;
	}

}
