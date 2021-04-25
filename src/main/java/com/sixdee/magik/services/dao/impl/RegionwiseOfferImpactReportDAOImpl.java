package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RegionwiseOfferImpactReportDAO;
import com.sixdee.magik.services.model.CircleTO;
import com.sixdee.magik.services.model.RegionwiseOfferImpactTO;;



@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class RegionwiseOfferImpactReportDAOImpl implements RegionwiseOfferImpactReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {return sessionFactory.getCurrentSession();}
	
	
	@Override
	public List<RegionwiseOfferImpactTO> getData() {
		
		List<RegionwiseOfferImpactTO> list = currentSession().createCriteria(RegionwiseOfferImpactTO.class).list();
		return list;
		
	}
	
	@Override
	public List<CircleTO> getCircleData() {
		List<CircleTO> list = currentSession().createCriteria(CircleTO.class).list();
		return list;
	}

}
