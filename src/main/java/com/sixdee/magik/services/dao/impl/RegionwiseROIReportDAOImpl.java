package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RegionwiseROIReportDAO;
import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.RegionwiseROITO;



@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class RegionwiseROIReportDAOImpl implements RegionwiseROIReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {return sessionFactory.getCurrentSession();}
	
	
	@Override
	public List<RegionwiseROITO> getData() {
		
		List<RegionwiseROITO> list = currentSession().createCriteria(RegionwiseROITO.class).list();
		return list;
		
	}
	
}
