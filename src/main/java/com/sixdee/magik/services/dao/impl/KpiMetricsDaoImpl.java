package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.KpiMetricsDao;
import com.sixdee.magik.services.model.KPIMetricsTO;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Repository
public class KpiMetricsDaoImpl implements KpiMetricsDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	
	@Override
	public void saveKPIMetrics(KPIMetricsTO kpiMetricsTO) {
		Session session = sessionFactory.getCurrentSession();
		kpiMetricsTO.setCreateDate(new Date());
		session.saveOrUpdate(kpiMetricsTO);
		
	}
	@Override
	public KPIMetricsTO getKPIMetrics(KPIMetricsTO kpiMetricsTO) {
		// TODO Auto-generated method stub
		
		KPIMetricsTO mainTO=new KPIMetricsTO();
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria=session.createCriteria(KPIMetricsTO.class);
		criteria.add(Restrictions.eq("id", kpiMetricsTO.getId()));
		mainTO=(KPIMetricsTO)criteria.uniqueResult();
		return mainTO;
	}
	@Override
	public List<KPIMetricsTO> getKPIMetricsList() {
		Session session = sessionFactory.getCurrentSession();
		List<KPIMetricsTO> mainList = new ArrayList<KPIMetricsTO>();
		Criteria criteria=session.createCriteria(KPIMetricsTO.class);
		criteria.addOrder(Order.desc("id"));
		mainList=criteria.list();
		for(KPIMetricsTO to:mainList) {
			to.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
		}
			
		return mainList;
	}
	@Override
	public String deleteKPIDefinition(KPIMetricsTO kpiMetricsTO) {
		String statusCode = "1";
		Session session = sessionFactory.getCurrentSession();

		session.delete(kpiMetricsTO);
		statusCode = "0";
		
		return statusCode;
	}
	

}
