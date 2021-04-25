package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ShortcutsTO;
import com.sixdee.magik.services.service.ShortcutsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Arun Jose
 * @Date : April, 2019
 *
 */

@RestController
public class ShortcutsRestController {

	static final Logger logger = Logger.getLogger(ShortcutsRestController.class);

	@Autowired
	ShortcutsService shortcutsService;

	@GetMapping(MagikServicePath.GET_MENU_OPTIONS_FOR_SHORTCUTS)
	public @ResponseBody RestListInfo<ShortcutsTO> getMenus(HttpServletRequest httpServletRequest) {

		logger.info("==================  ShortcutsRest Controllar API Start =====================");
		logger.info("Class : ShortcutsRestControllar | Method : getShortcuts");
		RestListInfo<ShortcutsTO> info = new RestListInfo<ShortcutsTO>();
		try {
			info.setData(shortcutsService.getMenus());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ShortcutsRestControllar  |  Method : getShortcuts " + e);
			e.printStackTrace();
		}
		logger.info("================== Shortcuts Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.CREATE_SHORTCUTS)
	public @ResponseBody RestInfo<String> createShortcut(HttpServletRequest httpServletRequest,
			@RequestBody ShortcutsTO shortcutsTO) {

		logger.info("==================  ShortcutsRest Controllar API Start =====================");
		logger.info("Class : ShortcutsRestControllar | Method : getShortcuts");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(shortcutsService.createShortcuts(shortcutsTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ShortcutsRestControllar  |  Method : getShortcuts " + e);
			e.printStackTrace();
		}
		logger.info("================== Shortcuts Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_SHORTCUTS)
	public @ResponseBody RestListInfo<ShortcutsTO> getShortcuts(HttpServletRequest httpServletRequest) {

		logger.info("==================  ShortcutsRest Controllar API Start =====================");
		logger.info("Class : ShortcutsRestControllar | Method : getShortcuts");
		RestListInfo<ShortcutsTO> info = new RestListInfo<ShortcutsTO>();
		try {
			info.setData(shortcutsService.getShortcuts());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ShortcutsRestControllar  |  Method : getShortcuts " + e);
			e.printStackTrace();
		}
		logger.info("================== Shortcuts Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.DELETE_SHORTCUTS)
	public @ResponseBody RestInfo<String> deleteShortcuts(HttpServletRequest httpServletRequest,
			@RequestBody ShortcutsTO shortcutsTO) {

		logger.info("==================  ShortcutsRest Controllar API Start =====================");
		logger.info("Class : ShortcutsRestControllar | Method : getShortcuts");
		RestInfo<String> info = new RestInfo<String>();
		try {
			info.setData(shortcutsService.deleteShortcuts(shortcutsTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : ShortcutsRestControllar  |  Method : getShortcuts " + e);
			e.printStackTrace();
		}
		logger.info("================== Shortcuts Rest Controllar API End =====================");
		return info;
	}

}
