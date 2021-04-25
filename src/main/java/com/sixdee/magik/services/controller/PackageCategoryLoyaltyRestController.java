package com.sixdee.magik.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.PackageCategoryTO;
import com.sixdee.magik.services.service.PackageCategoryLoyaltyService;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;

@RestController
public class PackageCategoryLoyaltyRestController {

	static final Logger logger = Logger.getLogger(PackageCategoryLoyaltyRestController.class);

	@Autowired
	PackageCategoryLoyaltyService serviceLayer;

	@PostMapping(value = MagikServicePath.SAVE_PACKAGE_CATEGORY)
	public @ResponseBody RestInfo<String> SaveConversion(HttpServletRequest httpServletRequest,
			@RequestBody PackageCategoryTO packageCategoryTO) throws IOException {

		logger.info("================== TierDetailsLoyaltyRestController API Start =====================");
		logger.info("Class : TierDetailsLoyaltyRestController | Method : SaveorUpdateRecord");
		RestInfo<String> info = new RestInfo<String>();
		String statuscode = "SC0001";
		try {

			statuscode = serviceLayer.savePackageCategory(packageCategoryTO);
			info.setOperationCode(0);
			info.setMessage("Success");
		} catch (Exception e) {
			logger.error("Class : TierDetailsLoyaltyRestController  |  Method : SaveorUpdateRecord " + e);
			// ExceptionUtil.handle(e, info);
			info.setOperationCode(9999);
			e.printStackTrace();
			info.setMessage("Failure");
		}
		logger.info("================== TierDetailsLoyaltyRestController API End =====================");
		return info;
	}

}
