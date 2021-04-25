package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.ChannelMessageTO;
import com.sixdee.magik.services.model.NotificationsTO;
import com.sixdee.magik.services.service.NotificationsService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@RestController
public class NotificationsRestControllar {

	static final Logger logger = Logger.getLogger(NotificationsRestControllar.class);

	@Autowired
	NotificationsService notifications;

	/*
	 * Authenticate Credentials
	 */
	@GetMapping(MagikServicePath.GET_NOTIFICATIONS)
	public @ResponseBody RestListInfo<NotificationsTO> getNotifications(HttpServletRequest httpServletRequest) {

		logger.info("================== Notifications Rest Controllar API Start =====================");
		logger.info("Class : Notifications Rest Contoller | Method : getNotifications");

		RestListInfo<NotificationsTO> info = new RestListInfo<NotificationsTO>();

		try {
			info.setData(notifications.getNotifications());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : NotificationsControllar  |  Method : getNotifications " + e);
			e.printStackTrace();
		}

		logger.info("================== Notifications Rest Controllar API End =====================");

		return info;
	}
	
	@GetMapping(MagikServicePath.VIEW_ALL_NOTIFICATIONS)
	public @ResponseBody RestListInfo<NotificationsTO> viewallNotifications(HttpServletRequest httpServletRequest) {

		logger.info("================== Notifications Rest Controllar API Start =====================");
		logger.info("Class : Notifications Rest Contoller | Method : getNotifications");

		RestListInfo<NotificationsTO> info = new RestListInfo<NotificationsTO>();

		try {
			info.setData(notifications.viewallNotifications());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : NotificationsControllar  |  Method : getNotifications " + e);
			e.printStackTrace();
		}

		logger.info("================== Notifications Rest Controllar API End =====================");

		return info;
	}

	@PostMapping(MagikServicePath.ADD_NOTIFICATION)
	public @ResponseBody RestInfo<String> getNotifications(HttpServletRequest httpServletRequest,
			@RequestBody NotificationsTO notTo) {

		logger.info("================== Notifications Rest Controllar API Start =====================");
		logger.info("Class : Notifications Rest Contoller | Method : getNotifications");

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(notifications.addNotification(notTo));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : NotificationsControllar  |  Method : getNotifications " + e);
			e.printStackTrace();
		}

		logger.info("================== Notifications Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.DELETE_NOTIFICATION)
	public @ResponseBody RestInfo<String> deleteNotifications(HttpServletRequest httpServletRequest) {

		logger.info("================== Notifications Rest Controllar API Start =====================");
		logger.info("Class : NotificationsRestControllar | Method : deleteNotifications");

		RestInfo<String> info = new RestInfo<String>();
		String ids = httpServletRequest.getParameter("notificationIds");

		try {
			info.setData(notifications.deleteNotifications(ids));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : NotificationsControllar  |  Method : getNotifications " + e);
			e.printStackTrace();
		}

		logger.info("================== Notifications Rest Controllar API End =====================");

		return info;
	}

	

	@PostMapping(MagikServicePath.SEARCH_NOTIFICATION)
	public @ResponseBody RestListInfo<NotificationsTO> searchNotifications(HttpServletRequest httpServletRequest,@RequestBody NotificationsTO notinfoTO) {

	
		logger.info("==================   Notifications Rest Contoller  Controllar API Start =====================");
		logger.info("Class : Notifications Rest Contoller | Method : searchNotifications");
		RestListInfo<NotificationsTO> info = new RestListInfo<NotificationsTO>();
		try {
			info.setData(notifications.searchNotifications(notinfoTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : Notifications Rest Contoller  |  Method : getAuditInfo " + e);
			e.printStackTrace();
		}
		logger.info("================== AuditInfoRestController Rest Controllar API End =====================");
		return info;
	}
}
