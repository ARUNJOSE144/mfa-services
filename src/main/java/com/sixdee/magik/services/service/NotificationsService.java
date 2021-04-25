package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ChannelMessageTO;
import com.sixdee.magik.services.model.NotificationsTO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public interface NotificationsService {

	// Get Notifications
	public List<NotificationsTO> getNotifications();

	public String addNotification(NotificationsTO notTo);

	public String deleteNotifications(String ids);
	
	public List<ChannelMessageTO> getChannelNotifications(String channel, int userId);

	public List<NotificationsTO> viewallNotifications();

	public List<NotificationsTO> searchNotifications(NotificationsTO notinfoTO);

}
