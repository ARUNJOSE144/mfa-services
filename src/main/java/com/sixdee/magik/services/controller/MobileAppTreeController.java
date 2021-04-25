/*package com.sixdee.magik.services.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.MobileAppCategoryTO;
import com.sixdee.magik.services.model.MobileAppMenusTO;
import com.sixdee.magik.services.model.MobileAppTreeDTO;
import com.sixdee.magik.services.service.MobileAppTreeService;

*//**
 * @author bhavyalakshmi.k
 *
 *//*
@RestController
public class MobileAppTreeController {
	static final Logger logger = Logger.getLogger(MobileAppTreeController.class);

	@Autowired
	MobileAppTreeService mobileAppTreeService;
	@PostMapping("/getMobileAppCategory")
	public MobileAppTreeDTO getCategory(@RequestBody MobileAppTreeDTO mobileAppTreeDTO) {
		logger.info("::::::::::::  getCategory  ::::::::::::::::::");
		mobileAppTreeDTO.setCategory(mobileAppTreeService.getCategory(mobileAppTreeDTO));
		return mobileAppTreeDTO;

	}

	@PostMapping("/createMobileAppCategory")
	public MobileAppCategoryTO createCategory(@RequestBody MobileAppCategoryTO mobileAppTreeDTO) {
		logger.info("::::::::::::  createCategory  ::::::::::::::::::");
		logger.info(mobileAppTreeDTO.getCampId()+"---------"+mobileAppTreeDTO.getCreatedUserId()+"-----------"+mobileAppTreeDTO.getCampName());
		mobileAppTreeDTO = mobileAppTreeService.createCategory(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/editMobileAppCategory")
	public MobileAppCategoryTO editCategory(@RequestBody MobileAppCategoryTO mobileAppTreeDTO) {
		logger.info("::::::::::::  editCategory  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.editCategory(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/delMobileAppCategory")
	public MobileAppTreeDTO delCategory(@RequestBody MobileAppTreeDTO mobileAppTreeDTO) {
		logger.info("::::::::::::  delCategory  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.delCategory(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/getMobileAppLanguage")
	public MobileAppTreeDTO getLanguage(@RequestBody MobileAppTreeDTO mobileAppTreeDTO) {
		logger.info("::::::::::::  getLanguage  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.getLanguage(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/getMobileAppMenuName")
	public MobileAppTreeDTO getMenuName(@RequestBody MobileAppTreeDTO mobileAppTreeDTO) {
		logger.info("::::::::::::  getMenuName  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.getMenuName(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/getMobileAppMessage")
	public MobileAppTreeDTO getMessage(@RequestBody MobileAppTreeDTO mobileAppTreeDTO) {
		logger.info("::::::::::::  getMessage  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.getMessage(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/createMobileAppMessage")
	public MobileAppMenusTO createMessage(@RequestBody MobileAppMenusTO mobileAppTreeDTO) {
		logger.info("::::::::::::  createMessage  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.createMessage(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/editMobileAppMessage")
	public MobileAppMenusTO editMessage(@RequestBody MobileAppMenusTO mobileAppTreeDTO) {
		logger.info("::::::::::::  editMessage  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.editMessage(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

	@PostMapping("/delMobileAppMenu")
	public MobileAppTreeDTO delMenu(@RequestBody MobileAppTreeDTO mobileAppTreeDTO) {
		logger.info("::::::::::::  delMenu  ::::::::::::::::::");
		mobileAppTreeDTO = mobileAppTreeService.delMenu(mobileAppTreeDTO);
		return mobileAppTreeDTO;

	}

}
*/