package com.sixdee.magik.services.dao.impl;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AppXnotifierDao;
import com.sixdee.magik.services.model.AppXNotifierToneTO;
import com.sixdee.magik.services.model.AppXNotifierTypesTO;
import com.sixdee.magik.services.model.CampaignMessageLanguagesTO;

@Repository
public class AppXnotifierDaoImpl implements AppXnotifierDao {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	@Autowired
	private Environment env;
	
	
	@SuppressWarnings("unchecked")
	public List<CampaignMessageLanguagesTO> getAppXLanguages() {
		
		Session session = null;
		List<CampaignMessageLanguagesTO> respList = null;
		try {
			
			session = sessionFactory.getCurrentSession();
			respList = session.createCriteria(CampaignMessageLanguagesTO.class).list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return respList;
	}
	
	@SuppressWarnings("unchecked")
	public List<AppXNotifierToneTO> getToneDetails(String langAbb) {
		
		Session session = null;
		List<AppXNotifierToneTO> respList = null;
		try {
			
			session = sessionFactory.getCurrentSession();
			respList = session.createCriteria(AppXNotifierToneTO.class)
					.add(Restrictions.eq("langAbbrevation", langAbb)).list();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return respList;
	}
	
	@SuppressWarnings("unchecked")
	public List<AppXNotifierTypesTO> getNotificationTypes(String langAbb) {

		
		Session session = null;
		List<AppXNotifierTypesTO> respList = null;
		try {
			
			session = sessionFactory.getCurrentSession();
			respList = session.createCriteria(AppXNotifierTypesTO.class)
					.add(Restrictions.eq("languageAbb", langAbb)).list();
			
			System.out.println(Arrays.asList(respList));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return respList;
	
	}
	
	public String getTopicNames() {
		
		String response = null;
		
		try {
			
			response = CallThirdPartyUrl.callGet(env.getProperty("XNOTIFIER_TOPIC_URL"));
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	public String sendNotification(String requestBody) {
		
		String response = null;
		
		try {
			
			response = CallThirdPartyUrl.callPost(env.getProperty("XNOTIFIER_NOTIFY_URL"), requestBody, "JSON");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return response;
	}
	
	public String manageSubscription(String requestBody, String subType) {

		
		String response = null;
		String url = null;
		
		try {
			
			if (subType.equalsIgnoreCase("subscribe")) {
				url = env.getProperty("XNOTIFIER_SUBSCRIBE_URL");
			} else if (subType.equalsIgnoreCase("unsubscribe")) {
				url = env.getProperty("XNOTIFIER_UNSUBSCRIBE_URL");
			}
			
			response = CallThirdPartyUrl.callPost(url, requestBody, "JSON");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return response;
	
	}

}
