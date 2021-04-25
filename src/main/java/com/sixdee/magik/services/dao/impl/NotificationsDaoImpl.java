package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.NotificationsDao;
import com.sixdee.magik.services.model.ChannelMessageTO;
import com.sixdee.magik.services.model.NotificationsTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Repository
public class NotificationsDaoImpl implements NotificationsDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	Criteria criteria = null;

	/*
	 * Get Notifications
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<NotificationsTO> getNotifications() {

		Session session = sessionFactory.getCurrentSession();
		List<NotificationsTO> list = new ArrayList<NotificationsTO>();
		NotificationsTO to = null;
		List<Object[]> res = null;

		hql = " select id, subject, notification, createDate,userId,updateBy,status,roleID,roleName,dayType ,viewallNotifications from NotificationsTO where status!=1 order by id desc";
		res = session.createQuery(hql).list();

		for (Object[] obj : res) {
			to = new NotificationsTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setSubject(obj[1].toString());
			to.setNotification(obj[2] + "");
			to.setCreateDate(dateConvertion(obj[3] + ""));

			String idValue = obj[4].toString();
			int i = Integer.parseInt(idValue);
			to.setUserId(i);
			to.setUpdateBy(obj[5] + "");
			to.setStatus(obj[6] + "");
			to.setRoleID(obj[7] + "");
			to.setRoleName(obj[8] + "");
			to.setDayType(obj[9] + "");

			list.add(to);
		}

		return list;
	}

	/*
	 * Date conversion
	 */
	public String dateConvertion(String strDate) {
		return strDate.replace(".0", "");
	}

	@Override
	public String addNotification(NotificationsTO notInfoto) {
		String status = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		notInfoto.setUserId(1);
		notInfoto.setUpdateBy("Admin");
		notInfoto.setRoleID("1");
		notInfoto.setRoleName("STS User");
		notInfoto.setStatus("0");
		notInfoto.setViewallNotifications("0");
		System.out.println("Add Notification log.................");
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(notInfoto);
		System.out.println("Add Notification done.................");
		status = "SC0000";
		return status;
	}

	@Override
	public String deleteNotifications(String ids) {
		String status = "SC0001";
		try {
			Session session = sessionFactory.getCurrentSession();
			for (int i = 0; i < ids.split(",").length; i++) {

				hql = "  from NotificationsTO where id=" + ids.split(",")[i];
				NotificationsTO to = (NotificationsTO) session.createQuery(hql).uniqueResult();
				to.setStatus("1");
				session.update(to);
			}
			status = "SC0000";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return status;

	}

	@Override
	public List<ChannelMessageTO> getChannelNotifications(String channel, int userId) {
		Session session = null;
		String query = null,socaiMediaQuery=null;
		List<ChannelMessageTO> list = null;
		Criteria cr=null;
		try {
			session = sessionFactory.getCurrentSession();
			String channelName = channel.split("_")[0];
			String key = "";
			if (channel.split("_").length > 1)
				key = channel.split("_")[1];

			if (channelName.equalsIgnoreCase("SMS")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_SMS_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_SMS_VIEW";
				
			} 
			
			
			else if (channelName.equalsIgnoreCase("USSD")) {
				
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_USSD_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_USSD_VIEW";
				
			} 
			
			
			else if (channelName.equalsIgnoreCase("WAP")) {
				query = "SELECT MENU_ID,LANGUAGE_ID,MESSAGE FROM WAP_MENU_MSG_DETAILS WHERE MENU_ID LIKE '" + key
						+ "%' ORDER BY MENU_ID DESC";
			}
			
			else if (channelName.equalsIgnoreCase("Email")) {
				
				query = "SELECT MENU_ID,LANGUAGE_ID,MESSAGE FROM EMAIL_MESSAGE_DETAILS WHERE MENU_ID LIKE '" + key
						+ "%' ORDER BY MENU_ID DESC";
			}
			
			else if (channelName.equalsIgnoreCase("Mobile")) {
				
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_MOBILE_APP_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_MOBILE_APP_VIEW";
				
			}
			else if (channelName.equalsIgnoreCase("WhatsApp")) {
				if(key!=null && key!="")
					query = "SELECT * FROM RULE_CMP_WHATSAPP_VIEW WHERE MSG_ID = '" + Integer.parseInt(key)
						+ "' ORDER BY ID DESC";
				else
					query = "SELECT * FROM RULE_CMP_WHATSAPP_VIEW";
				
			}
				SQLQuery sqlQuery = session.createSQLQuery(query);
				List<Object[]> results = (List<Object[]>) sqlQuery.list();
				list = processMessage(results, channelName);

		} catch (Exception e) {
			throw e;
		}

		return list;
	}
	
	

	public List<ChannelMessageTO> processMessage(List<Object[]> results, String channelName) {
		List<ChannelMessageTO> list = null;
		ChannelMessageTO msgTo = null;
		int currentMsgId = 0;
		int count = 0;
		if (results != null && results.size() > 0) {
			list = new ArrayList<ChannelMessageTO>();
			for (Object[] aRow : results) {
				count++;
				if (currentMsgId != Integer.parseInt(aRow[0] + "")) {
					if (msgTo != null) {
						list.add(msgTo);
					}
					msgTo = new ChannelMessageTO();
					currentMsgId = Integer.parseInt(aRow[0] + "");
					msgTo.setMessageId(channelName + "_" + aRow[0] + "");
				}
				

				if(channelName!=null &&( channelName.equals("WhatsApp") || channelName.equals("Skype") || channelName.equals("Facebook")|| channelName.equals("Telegram") || channelName.equals("Twitter")))
				{
					
						if (Integer.parseInt(aRow[1] + "") == 1) {
							msgTo.setEnglish(aRow[2].toString());
						}
						if (Integer.parseInt(aRow[1] + "") == 2) {
							msgTo.setSpanish(aRow[2].toString());
						}
						if (Integer.parseInt(aRow[1] + "") == 3) {
							msgTo.setArabic(aRow[2].toString());
						}
				}
				else
				{	
					if (Integer.parseInt(aRow[1] + "") == 1) {
						msgTo.setEnglish(getUtf8((aRow[2] + "")));
				     }
					if (Integer.parseInt(aRow[1] + "") == 2) {
						msgTo.setSpanish(getUtf8(aRow[2] + ""));
					}
					if (Integer.parseInt(aRow[1] + "") == 3) {
						msgTo.setArabic(getUtf8(aRow[2] + ""));
					}
				}
				msgTo.setChannelType(channelName);
		 		
				
				if (results.size() == count) {
					if (msgTo != null) {
						list.add(msgTo);
					}
				}
			}
		}
		return list;
	}

	public static String getUtf8(String word) {
		String decoded = "";
		for (Integer index = 0; index < word.length(); index++) {
			String charAt = "" + word.charAt(index);
			if (charAt.equals("&") && index < word.length() && ("" + word.charAt(index + 1)).equals("#")) {
				try {
					Integer length = word.indexOf(";", index);
					String sub = word.substring(index + 2, length);
					decoded += Character.toString((char) Integer.parseInt(sub));
					index = length;
				} catch (Exception ex) {
					decoded += charAt;
				}
			} else {
				decoded += charAt;
			}
		}
		System.out.println("============ decoded text===============" + decoded);
		return decoded;
	}

	@Override
	public List<NotificationsTO> viewallNotifications() {
		Session session = sessionFactory.getCurrentSession();
		List<NotificationsTO> list = new ArrayList<NotificationsTO>();
		NotificationsTO to = null;
		List<Object[]> res = null;

		hql = " select id, subject, notification, createDate,userId,updateBy,status,roleID,roleName,dayType,viewallNotifications from NotificationsTO where viewallNotifications!=1 order by id desc";
		res = session.createQuery(hql).list();

		for (Object[] obj : res) {
			to = new NotificationsTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setSubject(obj[1].toString());
			to.setNotification(obj[2] + "");
			to.setCreateDate(dateConvertion(obj[3] + ""));

			String idValue = obj[4].toString();
			int i = Integer.parseInt(idValue);
			to.setUserId(i);
			to.setUpdateBy(obj[5] + "");
			to.setStatus(obj[6] + "");
			to.setRoleID(obj[7] + "");
			to.setRoleName(obj[8] + "");
			to.setDayType(obj[9] + "");

			String a = obj[6].toString();
			String b = obj[10].toString();
			int c = Integer.parseInt(a);
			int d = Integer.parseInt(b);
			if (c == d) {
				to.setViewallNotifications("NO");

			} else {
				to.setViewallNotifications("YES");
			}
			list.add(to);
		}

		return list;
	}

	@Override
	public List<NotificationsTO> searchNotifications(NotificationsTO notinfoTO) {
		// TODO Auto-generated method stub
		List<NotificationsTO> auditList = new ArrayList<NotificationsTO>();
		NotificationsTO mainTO = null;
		Session session = sessionFactory.getCurrentSession();

		criteria = session.createCriteria(NotificationsTO.class);
		
		System.out.println("  auditInfoTO.getStartDate() " + notinfoTO.getStartDate());
		System.out.println("  auditInfoTO.getEndDate() " + notinfoTO.getEndDate());

		
		criteria.add(Restrictions.ge("createDate", notinfoTO.getStartDate()));
		criteria.add(Restrictions.le("createDate", notinfoTO.getEndDate()));

		List<NotificationsTO> auditInfoList = criteria.list();

		if (!auditInfoList.isEmpty()) {
			for (NotificationsTO auditTO : auditInfoList) {
				mainTO = new NotificationsTO();
				mainTO.setId(auditTO.getId());
				mainTO.setSubject(auditTO.getSubject());

				String pattern = "yyyy/MM/dd HH:mm:ss";
				SimpleDateFormat df = new SimpleDateFormat(pattern);
				String today = auditTO.getCreateDate();
			//	String todayAsString = df.format(today);

				System.out.println("todayAsString  " + dateConvertion(today));
				mainTO.setCreateDate(dateConvertion(today));

				mainTO.setNotification(auditTO.getNotification());
				mainTO.setViewallNotifications(auditTO.getViewallNotifications());
				mainTO.setUpdateBy(auditTO.getUpdateBy());
				mainTO.setRoleName(auditTO.getRoleName());
				auditList.add(mainTO);
			}
		}
		return auditList;
	}

}
