package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import com.sixdee.magik.services.dao.RewardExpiryReportDAO;
import com.sixdee.magik.services.model.RewardExpiryReportTO;
import com.sixdee.magik.services.model.RewardReportTO;

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
public class RewardExpiryReportDAOImpl implements  RewardExpiryReportDAO{


	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	
	@Override
	public List<RewardExpiryReportTO> getData() {
		List<RewardExpiryReportTO> list = currentSession().createCriteria(RewardExpiryReportTO.class).list();
		return list;
	}

}
