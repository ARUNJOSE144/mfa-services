
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.NotificationsDao;
import com.sixdee.magik.services.model.ChannelMessageTO;
import com.sixdee.magik.services.model.NotificationsTO;
import com.sixdee.magik.services.service.NotificationsService;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class NotificationsServiceImpl implements NotificationsService {

	@Autowired
	NotificationsDao notificationsDao;

	@Override
	public List<NotificationsTO> getNotifications() {
		return notificationsDao.getNotifications();
	}

	@Override
	public String addNotification(NotificationsTO notTo) {
		// TODO Auto-generated method stub
		return notificationsDao.addNotification(notTo);
	}

	
	@Override
	public String deleteNotifications(String ids) {
		// TODO Auto-generated method stub
		return notificationsDao.deleteNotifications(ids);
	}
	
	@Override
	public List<ChannelMessageTO> getChannelNotifications(String channel, int userId) {
		// TODO Auto-generated method stub
		return notificationsDao.getChannelNotifications(channel,userId);
	}

	@Override
	public List<NotificationsTO> viewallNotifications() {
		// TODO Auto-generated method stub
		return notificationsDao.viewallNotifications();
	}

	@Override
	public List<NotificationsTO> searchNotifications(NotificationsTO notinfoTO) {
		// TODO Auto-generated method stub
		return notificationsDao.searchNotifications(notinfoTO);
	}
	
}
