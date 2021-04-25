package com.sixdee.magik.services.dao.impl;

import java.util.List;

import com.sixdee.magik.services.dao.CampaignInformationReportDAO;
import com.sixdee.magik.services.model.CampaignDefMasterTO;
import com.sixdee.magik.services.model.CampaignInformationReportTO;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

/**
 * @author JANARDHAN REDDY
 * @Date : February, 2021
 */

@Repository
@SuppressWarnings("unchecked")
public class CampaignInformationReportDAOImpl implements CampaignInformationReportDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}

	/*
	 * @Override public List<CampaignDefMasterTO> getCampaignData() {
	 * 
	 * List<CampaignDefMasterTO> list =
	 * currentSession().createCriteria(CampaignDefMasterTO.class).list(); return
	 * list; }
	 */

	@Override
	public List<CampaignInformationReportTO> getData() {
		List<CampaignInformationReportTO> list = currentSession().createCriteria(CampaignInformationReportTO.class)
				.list();
//		for(CampaignInformationReportTO parentObj : list)
//		{
//			Hibernate.initialize(parentObj.getCampMasterTO()); //Optional to check
//		}
		return list;

	}

}
