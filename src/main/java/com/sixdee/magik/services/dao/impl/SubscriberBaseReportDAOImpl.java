package com.sixdee.magik.services.dao.impl;

import java.util.List;

import com.sixdee.magik.services.dao.SubscriberBaseReportDAO;
import com.sixdee.magik.services.model.SubscriberBaseReportTO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;


/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository

public class SubscriberBaseReportDAOImpl implements SubscriberBaseReportDAO{
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}


	@Override
	public List<SubscriberBaseReportTO> getData() {
		List<SubscriberBaseReportTO> list = currentSession().createCriteria(SubscriberBaseReportTO.class).list();
		return list;
		 
	}

}
