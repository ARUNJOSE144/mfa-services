package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RegionOfferUptakeReportDAO;
import com.sixdee.magik.services.model.RegionOfferUptakeTO;



@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class RegionOfferUptakeReportDAOImpl implements RegionOfferUptakeReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {return sessionFactory.getCurrentSession();}
	
	
	@Override
	public List<RegionOfferUptakeTO> getData() {
		
		List<RegionOfferUptakeTO> list = currentSession().createCriteria(RegionOfferUptakeTO.class).list();
		return list;
		
	}
	
}
