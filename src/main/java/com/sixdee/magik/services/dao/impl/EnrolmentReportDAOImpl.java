package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.EnrolmentReportDAO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.DaywiseCampaignConversionTO;
import com.sixdee.magik.services.model.EnrolmentTO;
import com.sixdee.magik.services.model.EnrolmentTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.SubscriberBaseReportTO;
import com.sixdee.magik.services.util.SystemProperties;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository

public class EnrolmentReportDAOImpl implements EnrolmentReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	

	@Override
	public List<EnrolmentTO> getData() {
		List<EnrolmentTO> list = currentSession().createCriteria(EnrolmentTO.class).list();
		return list;
		 
	}

	
}
