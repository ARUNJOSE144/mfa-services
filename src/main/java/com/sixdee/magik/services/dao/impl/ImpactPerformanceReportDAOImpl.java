package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ImpactPerformanceReportDAO;
import com.sixdee.magik.services.model.ImpactPerformanceTO;



@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class ImpactPerformanceReportDAOImpl implements ImpactPerformanceReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {return sessionFactory.getCurrentSession();}
	
	
	@Override
	public List<ImpactPerformanceTO> getData() {
		
		List<ImpactPerformanceTO> list = currentSession().createCriteria(ImpactPerformanceTO.class).list();
		/*for(ImpactPerformanceTO parentObj : list)
		{
			Hibernate.initialize(parentObj.getCampMasterTO()); //Optional to check
		}*/
		return list;
		
	}

}
