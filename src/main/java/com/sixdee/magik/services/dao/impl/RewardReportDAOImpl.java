package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RewardReportDAO;
import com.sixdee.magik.services.model.RewardReportTO;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository

public class RewardReportDAOImpl implements RewardReportDAO{

	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public List<RewardReportTO> getData() {
		List<RewardReportTO> list = currentSession().createCriteria(RewardReportTO.class).list();
		return list;
	}

}
