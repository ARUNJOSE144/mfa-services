package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.URLConfigDAO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.UrlConfigTO;

/**
 * @author arun.jose January 2019
 */
@Repository
public class URLConfigDAOImpl implements URLConfigDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;
	int idValue;
	String nameValue;
	String urlValue;
	String nodeValue;
	String onlineValue;

	@SuppressWarnings("unchecked")
	@Override
	public List<UrlConfigTO> getUrlList() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		// List<UrlConfigTO> list = (List<UrlConfigTO>)
		// session.createCriteria(UrlConfigTO.class).list();

		List<UrlConfigTO> list = new ArrayList<UrlConfigTO>();
		hql = "select ID,RULENGINE_NAME,RULENGINE_URL,NODE_ID,USER_ID,IS_ONLINE,CREATE_DATE from RE_RULEENGINE_URL  order by id desc";
		List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
		UrlConfigTO cdrTO = null;

		for (Object[] obj : rs) {
			cdrTO = new UrlConfigTO();
			cdrTO.setId(Integer.parseInt(obj[0] + ""));
			cdrTO.setName(obj[1] + "");
			cdrTO.setUrl(obj[2] + "");
			cdrTO.setNodeId(obj[3] + "");
			cdrTO.setUserId(Integer.parseInt(obj[4] + ""));
			cdrTO.setOnlineTrigger(obj[5] + "");
			cdrTO.setCreateDate(obj[6] + "");
			list.add(cdrTO);
		}

		return list;
	}

	@Override
	public String addUrl(UrlConfigTO configTO) {
		String status = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		if (configTO.getId() == 0) {
			System.out.println(" inside if");
			status = "SC0000";
			session.save(configTO);
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("URL Configuration");
			String a = "Created";
			String b = configTO.getName().concat(a);
			audtoTO.setAddedName(configTO.getName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			audtoTO.setDesc(b);
			audtoTO.setCreatedBy(configTO.getAuditUserName());
			audtoTO.setRoleName(configTO.getAuditRoleName());
			auditInfo.addAuditLog(audtoTO);

			System.out.println("-----------------------Lead  Policy Successfully-----------------");
		} else if (configTO.getId() != 0) {
			System.out.println("   else if  ::::  ");
			System.out.println("   else if getName  ::::  " + configTO.getName());
			System.out.println("   else if  getUrl  ::::  " + configTO.getUrl());
			System.out.println("   else if  id  ::::  " + configTO.getNodeId());

			/*
			 * configTO to=(configTO)session.get(configTO.class, configTO);
			 * System.out.println(" to   1 :: " + to); System.out.println(" to  :: " +
			 * to.getPolicyName());
			 */
			String hql = "select ID ,RULENGINE_NAME ,RULENGINE_URL,NODE_ID,IS_ONLINE from  RE_RULEENGINE_URL where ID= '"
					+ configTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				
			//	idValue = (Integer) obj[0];
				String id = obj[0].toString();
				idValue = Integer.parseInt(id);
				
				nameValue = obj[1].toString();
				urlValue = obj[2].toString();
				nodeValue = obj[3].toString();
				onlineValue = obj[4].toString();

			}
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("URL Configuration");
			audtoTO.setAddedName(configTO.getName());
			String a = configTO.getName();
			String c = "Modified";
			String b = a.concat(c);
			audtoTO.setActionType("MODIFY");
			audtoTO.setDesc(b);
			if (nameValue != null && !nameValue.equalsIgnoreCase(configTO.getName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("URL Name");
				audtoTO.setPreviousValue(nameValue);
				audtoTO.setNewValue(configTO.getName());
			}
			if (urlValue != null && !urlValue.equalsIgnoreCase(configTO.getUrl())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("URL");
				audtoTO.setPreviousValue(urlValue);
				audtoTO.setNewValue(configTO.getUrl());

			}
			if (nodeValue != null && !nodeValue.equalsIgnoreCase(configTO.getNodeId())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("Server Node");
				audtoTO.setPreviousValue(nodeValue);
				audtoTO.setNewValue(configTO.getNodeId());

			}
			if (onlineValue != null && !onlineValue.equalsIgnoreCase(configTO.getOnlineTrigger())) {
				System.out.println(" Description inside " + onlineValue);
				audtoTO.setAttribute("Online Trigger");
				audtoTO.setPreviousValue(onlineValue);
				audtoTO.setNewValue(configTO.getOnlineTrigger());

			}
			audtoTO.setCreatedBy(configTO.getAuditUserName());
			audtoTO.setRoleName(configTO.getAuditRoleName());
			auditInfo.addAuditLog(audtoTO);
			session.update(configTO);
			System.out.println("-----------------------Lead  Policy  Edit-----------------");
		} else

		{
			System.out.println("-----------------------Lead  Policy Not saved-----------------");
			status = "SC0000";
		}

		// session.saveOrUpdate(configTO);

		return status;
	}

	@Override
	public String deleteUrl(UrlConfigTO configTO) {
		String status = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		audtoTO = new AuditInfoTO();
		audtoTO.setFeatureName("URL Configuration");
		audtoTO.setAddedName(configTO.getName());
		String a = "Deleted";
		String b = configTO.getName().concat(a);
		audtoTO.setActionType("DELETE");
		audtoTO.setAttribute("N/A");
		audtoTO.setPreviousValue("N/A");
		audtoTO.setNewValue("N/A");
		audtoTO.setDesc(b);
		audtoTO.setCreatedBy(configTO.getAuditUserName());
		audtoTO.setRoleName(configTO.getAuditRoleName());
		auditInfo.addAuditLog(audtoTO);
		session.delete(configTO);
		status = "SC0000";

		return status;
	}

}
