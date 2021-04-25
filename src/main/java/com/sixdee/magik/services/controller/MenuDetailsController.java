/**
 * 
 */
package com.sixdee.magik.services.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.MenuDetailDTO;
import com.sixdee.magik.services.service.MenuDetailsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Vinay Kariyappa
 *
 *         Sep 26, 2018
 */
@RestController
public class MenuDetailsController {

	static final Logger logger = Logger.getLogger(MenuDetailsController.class);

	@Autowired
	MenuDetailsService menuDetailsService;

	@GetMapping(MagikServicePath.GET_MENU_DETAILS)
	public @ResponseBody RestListInfo<MenuDetailDTO> getMenuDetails(HttpServletRequest httpServletRequest) {

		logger.info("================== Language Rest Controllar API Start =====================");
		logger.info("Class : LanguageRestControllar | Method : getLanguages");
		int parentId = Integer.parseInt(httpServletRequest.getParameter("parentId"));
	//	System.out.println("user id  " +httpServletRequest.getParameter("userId") );
		int userId = Integer.parseInt(httpServletRequest.getParameter("userId"));
		//int userId =1;
		RestListInfo<MenuDetailDTO> info = new RestListInfo<MenuDetailDTO>();
		try {
			info.setData(menuDetailsService.getMenus(parentId,userId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	}

	@GetMapping("GetMainMenuDetails")
	public @ResponseBody RestListInfo<Map<Integer, MenuDetailDTO>> getMainMenuDetails(
			HttpServletRequest httpServletRequest) {

		logger.info("================== Language Rest Controllar API Start =====================");
		logger.info("Class : LanguageRestControllar | Method : getLanguages");
		RestListInfo<Map<Integer, MenuDetailDTO>> info = new RestListInfo<Map<Integer, MenuDetailDTO>>();
		String groupId = httpServletRequest.getParameter("roleId");
		try {
			info.setData(menuDetailsService.getMainMenuDetails(Integer.parseInt(groupId)));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	}

	@GetMapping("GetSubMenuDetails")
	public @ResponseBody RestListInfo<Map<Integer, Map<Integer, List<String>>>> getSubMenuDetails(
			HttpServletRequest httpServletRequest) {

		logger.info("================== Language Rest Controllar API Start =====================");
		logger.info("Class : LanguageRestControllar | Method : getLanguages");
		RestListInfo<Map<Integer, Map<Integer, List<String>>>> info = new RestListInfo<Map<Integer, Map<Integer, List<String>>>>();
		try {
			info.setData(menuDetailsService.getSubMenuDetails());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	}

	@GetMapping("GetAllRoleMenuDetails")
	public @ResponseBody RestListInfo<MenuDetailDTO> getAllRoleMenuDetails(HttpServletRequest httpServletRequest) {

		logger.info("================== Language Rest Controllar API Start =====================");
		logger.info("Class : LanguageRestControllar | Method : getLanguages");
		RestListInfo<MenuDetailDTO> info = new RestListInfo<MenuDetailDTO>();
		String groupId = httpServletRequest.getParameter("roleId");
		String mainMenuId = httpServletRequest.getParameter("mainMenuId");
		try {
			info.setData(
					menuDetailsService.getAllRoleMenuDetails(Integer.parseInt(groupId), Integer.parseInt(mainMenuId)));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : AuthenticationRestControllar  |  Method : getLanguages " + e);
			e.printStackTrace();
		}
		logger.info("================== Language Rest Controllar API End =====================");
		return info;
	}
}
