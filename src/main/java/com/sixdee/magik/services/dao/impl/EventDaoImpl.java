package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.EventDao;
import com.sixdee.magik.services.model.EventTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.model.TriggerGroupTO;

@Repository
@Transactional
public class EventDaoImpl implements EventDao{

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;
	private Query query1 = null;
	
	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	@Override
	public void saveEvent(EventTO eventTO) {
		try {
			Session session = sessionFactory.getCurrentSession();
			
			if(eventTO.getId()==0)
				eventTO.setCreateDate(new Date());
			session.saveOrUpdate(eventTO);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}

	@Override
	public EventTO getEvent(int eventId) {

		Session session = sessionFactory.getCurrentSession();
		EventTO eventTO = session.get(EventTO.class, eventId);
		System.out.println("eventTO>>>"+eventTO);
		
		return eventTO;
	}

	@Override
	public List<EventTO> getAllEvents(EventTO  eventTO) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<EventTO> rs = null;
		List<EventTO> list = new ArrayList<EventTO>();
		EventTO to = null;

		query = "FROM EventTO ";
		//query += " where userId = " + eventTO.getUserId();
		if (eventTO.getName() != null)
			query += " and  name like '" + eventTO.getName() + "%'";
		query +=" order by Id desc";
		
		LinkedHashMap<Integer, String> eventGroupMap=getEventGroupMap();

		rs = (List<EventTO>) session.createQuery(query).list();
		

		for (EventTO obj : rs) {
			to = new EventTO();
			to.setId(obj.getId());
			to.setName(obj.getName());
			to.setGroupId(obj.getGroupId());
			//to.setUserId(obj.getUserId());
			to.setCreateDateUI(simpleDateFormat.format(obj.getCreateDate()));
			if(eventGroupMap!=null && !eventGroupMap.isEmpty() && eventGroupMap.get(obj.getGroupId())!=null)
				to.setEventGroupName(eventGroupMap.get(obj.getGroupId()));
			else
				to.setEventGroupName("Generic");

			list.add(to);
		}
		return list;
	}
	
	@Override
	public PaginationDTO<EventTO> getEventsPagination(PaginationDTO<EventTO> paginationDTO) {
		System.out.println(paginationDTO);
		String sql = "";
		EventTO to = null;
		List<EventTO> list = new ArrayList<EventTO>();
		List<EventTO> eventsList = null;
		Session session = sessionFactory.getCurrentSession();
		try
		{
			if(paginationDTO.getDataTotalSize() == 0)
			{
				paginationDTO.setDataTotalSize(getRowCount(session,paginationDTO));
				System.out.println("ROW COUNT"+paginationDTO.getDataTotalSize());
			}
			sql = "FROM EventTO";
			if(paginationDTO.getSearchKey1() != null && !paginationDTO.getSearchKey1().equalsIgnoreCase(""))
				sql += " WHERE name LIKE '"+paginationDTO.getSearchKey1() + "%'";
			if(validate(paginationDTO.getSortKey1())) {
				sql += " ORDER BY " +  paginationDTO.getSortKey1();
			}
			else {
				sql += " order by Id DESC";
			}
			query1 = session.createQuery(sql);
			query1.setMaxResults(paginationDTO.getRecordCount());
			query1.setFirstResult(paginationDTO.getFirstRecord());
			LinkedHashMap<Integer, String> eventGroupMap=getEventGroupMap();
			eventsList = (List<EventTO>) query1.list();
			
			for (EventTO obj : eventsList) {
				to = new EventTO();
				to.setId(obj.getId());
				to.setName(obj.getName());
				to.setGroupId(obj.getGroupId());
				//to.setUserId(obj.getUserId());
				to.setCreateDateUI(simpleDateFormat.format(obj.getCreateDate()));
				if(eventGroupMap!=null && !eventGroupMap.isEmpty() && eventGroupMap.get(obj.getGroupId())!=null)
					to.setEventGroupName(eventGroupMap.get(obj.getGroupId()));
				else
					to.setEventGroupName("Generic");

				list.add(to);
			}
			
			paginationDTO.setData(list);
			System.out.println("Events"+eventsList);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paginationDTO;
	}
	
	
	private int getRowCount(Session session, PaginationDTO<EventTO> paginationDTO) {
		
		String sql = "";
		int rowCount = 0;
		try
		{
			sql = "SELECT COUNT(*) FROM EventTO ";
			if(paginationDTO.getSearchKey1() != null && !paginationDTO.getSearchKey1().equalsIgnoreCase(""))
				sql += "WHERE name LIKE '"+paginationDTO.getSearchKey1() + "%'";
			System.out.println("SQL QUERY:==> "+sql);
			query1 = session.createQuery(sql);
			List<Long> count = query1.list();
			rowCount = Integer.parseInt(count.get(0).toString());
		}
		 catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return rowCount;
	}

	public LinkedHashMap<Integer, String> getEventGroupMap(){
		Session session = sessionFactory.getCurrentSession();
		LinkedHashMap<Integer, String> eventGroupMap=new LinkedHashMap<>();
		List<TriggerGroupTO> triggerGroupsList = (List<TriggerGroupTO>) session.createCriteria(TriggerGroupTO.class).list();
		for(TriggerGroupTO triggerGroupTO:triggerGroupsList) {
			eventGroupMap.put(triggerGroupTO.getId(),triggerGroupTO.getName());
		}
			
		return eventGroupMap;
	}

	@Override
	public String deleteEvent(EventTO  eventTO) {
		String statusCode = "1";
		Session session = sessionFactory.getCurrentSession();

		session.delete(eventTO);
		statusCode = "0";
		
		return statusCode;
	}
	
	boolean validate(String val) {
		if (val != null && !val.equalsIgnoreCase("Undefined") && !val.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}

}
