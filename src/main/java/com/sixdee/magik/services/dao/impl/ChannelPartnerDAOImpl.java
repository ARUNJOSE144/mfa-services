package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ChannelPartnerDAO;
import com.sixdee.magik.services.model.ChannelPartnerDetails;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.DesignationHierarchyDetails;

/**
 * @author Rajesh
 * @Date : Sep, 2019
 *
 */

@Repository
public class ChannelPartnerDAOImpl implements ChannelPartnerDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	private Session session=null;
	private Query query;
	
	@Override
	public List<ChannelPartnerDetails> getChannelPartners() {
		// TODO Auto-generated method stub
		List<ChannelPartnerDetails> listToReturn=new ArrayList<>();
		ChannelPartnerDetails channelPartnerDetails=null;
		session=sessionFactory.getCurrentSession();
		hql="select channelType,name from ChannelTypeHeirarchy";
		query=session.createQuery(hql);
		List<Object[]> result=(List<Object[]>)query.list();
		if(result!=null && !result.isEmpty()) {
			for(Object[] objects:result) {
				channelPartnerDetails=new ChannelPartnerDetails();
				channelPartnerDetails.setChannelType((Integer)objects[0]);
				channelPartnerDetails.setName((String)objects[1]);
				listToReturn.add(channelPartnerDetails);
			}
		}
		
		return listToReturn;
	}

	@Override
	public List<DesignationHierarchyDetails> getDesignationsByChannelPartner(long channelType) {
		List<DesignationHierarchyDetails> listToReturn=new ArrayList<>();
		DesignationHierarchyDetails designationHierarchyDetails=null;
		session=sessionFactory.getCurrentSession();
		hql="select designationId,name from DesignationHierarchy where channelType=:channelType";
		query=session.createQuery(hql);
		query.setParameter("channelType", channelType);
		List<Object[]> result=(List<Object[]>)query.list();
		if(result!=null && !result.isEmpty()) {
			for(Object[] objects:result) {
				designationHierarchyDetails=new DesignationHierarchyDetails();
				designationHierarchyDetails.setDesignationId((Long)objects[0]);
				designationHierarchyDetails.setName((String)objects[1]);
				listToReturn.add(designationHierarchyDetails);
			}
		}
		
		return listToReturn;
		
	} 

	
}
