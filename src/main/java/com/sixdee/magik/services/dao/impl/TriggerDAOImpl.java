package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.TriggerDAO;
import com.sixdee.magik.services.model.TriggerGroupTO;
import com.sixdee.magik.services.model.TriggerInfoTO;
import com.sixdee.magik.services.model.TriggerTO;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */

@Repository
public class TriggerDAOImpl implements TriggerDAO{

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	@Override
	public TriggerTO loadTriggerData() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		TriggerTO triggerTO=new TriggerTO();
		List<TriggerGroupTO> triggerGroupsList = (List<TriggerGroupTO>) session.createCriteria(TriggerGroupTO.class).list();
		List<TriggerInfoTO> triggerInfoList = (List<TriggerInfoTO>) session.createCriteria(TriggerInfoTO.class).list();
		
		triggerTO.setTriggerInfoList(triggerInfoList);
		triggerTO.setTriggerGroupsList(triggerGroupsList);
		
		return triggerTO;
	}

	@Override
	public void saveTrigger(TriggerTO triggerTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		if(triggerTO.getId()==0)
			triggerTO.setCreateDate(new Date());
		
		session.saveOrUpdate(triggerTO);
		
	}

	@Override
	public List<TriggerTO> getTriggerList(TriggerTO triggerTO) {
		Session session = sessionFactory.getCurrentSession();
		List<TriggerTO> rs = null;
		List<TriggerTO> list = new ArrayList<TriggerTO>();
		TriggerTO to = null;

		query = "FROM TriggerTO ";
		query += " where userId = " + triggerTO.getUserId();
		if (triggerTO.getName() != null)
			query += " and  name like '" + triggerTO.getName() + "%'";
		query += " order by id desc";
		LinkedHashMap<Integer, String> triggerGroupMap=getTriggerGroupMap();
		LinkedHashMap<Integer, String> triggerInfoMap=getTriggerInfoMap();

		rs = (List<TriggerTO>) session.createQuery(query).list();
		

		for (TriggerTO obj : rs) {
			to = new TriggerTO();
			to.setId(obj.getId());
			to.setName(obj.getName());
			to.setGroupId(obj.getGroupId());
			to.setUserId(obj.getUserId());
			to.setCreateDateUI(simpleDateFormat.format(obj.getCreateDate()));
			if(triggerGroupMap!=null && !triggerGroupMap.isEmpty() && triggerGroupMap.get(obj.getGroupId())!=null)
				to.setTriggerGroupName(triggerGroupMap.get(obj.getGroupId()));
			else
				to.setTriggerGroupName("Generic");
			if(triggerInfoMap!=null && !triggerInfoMap.isEmpty() && triggerInfoMap.get(obj.getMappedTriggerId())!=null)
				to.setTriggerInfoGroupName(triggerInfoMap.get(obj.getMappedTriggerId()));
			

			list.add(to);
		}
		return list;
		
	}

	@Override
	public TriggerTO getTrigger(TriggerTO triggerTO) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";

		query = "from TriggerTO where id=" + triggerTO.getId();
		TriggerTO to = (TriggerTO) session.createQuery(query).uniqueResult();

		return to;
	}

	@Override
	public String deleteTrigger(TriggerTO triggerTO) {
		String statusCode = "1";
		Session session = sessionFactory.getCurrentSession();

		session.delete(triggerTO);
		statusCode = "0";
		
		return statusCode;
	}
	

	public LinkedHashMap<Integer, String> getTriggerGroupMap(){
		Session session = sessionFactory.getCurrentSession();
		LinkedHashMap<Integer, String> triggerGroupMap=new LinkedHashMap<>();
		List<TriggerGroupTO> triggerGroupsList = (List<TriggerGroupTO>) session.createCriteria(TriggerGroupTO.class).list();
		for(TriggerGroupTO triggerGroupTO:triggerGroupsList) {
			triggerGroupMap.put(triggerGroupTO.getId(),triggerGroupTO.getName());
		}
			
		return triggerGroupMap;
	}
	public LinkedHashMap<Integer, String> getTriggerInfoMap(){
		Session session = sessionFactory.getCurrentSession();
		LinkedHashMap<Integer, String> triggerInfoMap=new LinkedHashMap<>();
		List<TriggerInfoTO> triggerGroupsList = (List<TriggerInfoTO>) session.createCriteria(TriggerInfoTO.class).list();
		for(TriggerInfoTO triggerGroupTO:triggerGroupsList) {
			triggerInfoMap.put(triggerGroupTO.getTriggerId(),triggerGroupTO.getTriggerName());
		}
			
		return triggerInfoMap;
	}


}
