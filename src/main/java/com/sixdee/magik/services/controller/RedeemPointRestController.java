package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.CreateAccountCustmCareTO;
import com.sixdee.magik.services.model.RedeemPointTO;
import com.sixdee.magik.services.service.CreateAccountCustmCareService;
import com.sixdee.magik.services.service.RedeemPointService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author Nakhil Kurian
 * @Date : March, 2021
 */

@RestController
public class RedeemPointRestController {
	static final Logger logger = Logger.getLogger(RedeemPointRestController.class);

	@Autowired
	RedeemPointService redeemPointService;

	@PostMapping(MagikServicePath.GET_PACKAGES)
	public @ResponseBody RestInfo<RedeemPointTO> getPackage(HttpServletRequest httpServletRequest,
			@RequestBody RedeemPointTO redeemPointTO) {
		RestInfo<RedeemPointTO> info = new RestInfo<RedeemPointTO>();
		RedeemPointTO status;
		try {
			info.setOperationCode(0);
			info.setMessage("Success");
			info.setData(redeemPointService.getPackage(redeemPointTO));

		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			e.printStackTrace();
			info.setOperationCode(1);
			info.setMessage("Failure");
		}
		return info;

	}

	@PostMapping(MagikServicePath.GET_REDEEM_POINTS)
	public @ResponseBody RestInfo<RedeemPointTO> getRedeempoint(HttpServletRequest httpServletRequest,
			@RequestBody RedeemPointTO redeemPointTO) {
		RestInfo<RedeemPointTO> info = new RestInfo<RedeemPointTO>();
		RedeemPointTO status;
		try {
			status = redeemPointService.getRedeempoint(redeemPointTO);
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
