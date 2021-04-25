package com.sixdee.magik.services.dao.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ProgramWiseReportDAO;
import com.sixdee.magik.services.model.ProgramWiseReportTO;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository

public class ProgramWiseReportDAOImpl implements ProgramWiseReportDAO{

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<ProgramWiseReportTO> getData() {
		List<ProgramWiseReportTO> list = currentSession().createCriteria(ProgramWiseReportTO.class).list();
		return list;
	}

}
