package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.MTDLMTDReportDAO;
import com.sixdee.magik.services.model.ImpactPerformanceTO;
import com.sixdee.magik.services.model.MTDLMTDTO;



@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class MTDLMTDReportDAOImpl implements MTDLMTDReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession() {return sessionFactory.getCurrentSession();}
	
	
	@Override
	public List<MTDLMTDTO> getData() {
		
		List<MTDLMTDTO> list = currentSession().createCriteria(MTDLMTDTO.class).list();
		/*for(MTDLMTDTO parentObj : list)
		{
			Hibernate.initialize(parentObj.getCampMasterTO()); //Optional to check
		}*/
		return list;
		
	}

}
