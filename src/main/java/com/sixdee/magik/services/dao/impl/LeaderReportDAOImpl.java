package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.LeaderReportDAO;
import com.sixdee.magik.services.model.LeaderReportTO;


/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository

public class LeaderReportDAOImpl implements LeaderReportDAO{
	
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<LeaderReportTO> getData() {
		List<LeaderReportTO> list = currentSession().createCriteria(LeaderReportTO.class).list();
		return list;
	}

}
