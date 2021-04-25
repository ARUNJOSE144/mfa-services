package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ApplicationPropertiesDAO;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.model.ApplicationPropertyTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.MenuDetailDTO;

/**
 * @author Rajesh January 2019
 */
@Repository
public class ApplicationPropertiesDAOImpl implements ApplicationPropertiesDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	@Override
	public List<ApplicationPropertyTO> getApplicationProperties() {

		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<ApplicationPropertyTO> list = (List<ApplicationPropertyTO>) session
				.createCriteria(ApplicationPropertyTO.class).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String changePropertyStatus(ApplicationPropertyTO applicationPropertyTO) {
		// TODO Auto-generated method stub
		MenuDetailDTO to = new MenuDetailDTO();
		String status = "1";
		Session session = sessionFactory.getCurrentSession();
		int statusToUpdate = applicationPropertyTO.getStatus() == 0 ? 1 : 0;
		hql = "update ApplicationPropertyTO set status=" + statusToUpdate + " where id="
				+ applicationPropertyTO.getId();
		query = session.createQuery(hql);
		int result = query.executeUpdate();
		System.out.println("statusToUpdate  " + statusToUpdate);
		List<ApplicationPropertyTO> list = new ArrayList<ApplicationPropertyTO>();
		if (applicationPropertyTO.getId() != 0) {
			if (statusToUpdate == 0) {
				System.out.println(" disable");
				String hql1 = null;
				String hql2 = null;
				int a = 0;
				hql1 = "select NAME   from  APPLICATION_PROPERTIES where ID = '" + applicationPropertyTO.getId()
						+ "'  ";
				List<String[]> rs = (List<String[]>) session.createSQLQuery(hql1).list();
				String text = rs.toString().replace("[", "").replace("]", "");
				System.out.println(" text " + text);
				System.out.println(" a " + a);
				hql2 = "update MenuDetailDTO set status=" + a + " where  menuName= ?";
				System.out.println(" hql2 " + hql2);
				Query q = session.createQuery(hql2);
				q.setParameter(0, text);
				q.executeUpdate();
				
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("App Properties");
				audtoTO.setAddedName(text);
				audtoTO.setActionType("MODIFY");
				audtoTO.setAttribute(text);
				audtoTO.setPreviousValue("Enabled");
				audtoTO.setNewValue("Disabled");
				String des="Disabled.";
				String con=text.concat(des);
				audtoTO.setDesc(con);
				auditInfo.addAuditLog(audtoTO);
		//		session.delete(contactPolicyTO);
			} else if (statusToUpdate == 1) {
				System.out.println(" enable");
				String hql1 = null;
				String hql2 = null;
				int a = 1;
				hql1 = "select NAME   from  APPLICATION_PROPERTIES where ID = '" + applicationPropertyTO.getId()
						+ "'  ";
				List<String[]> rs = (List<String[]>) session.createSQLQuery(hql1).list();
				String text = rs.toString().replace("[", "").replace("]", "");
				System.out.println(" text " + text);
				System.out.println(" a " + a);
				hql2 = "update MenuDetailDTO set status=" + a + " where  menuName= ?";
				System.out.println(" hql2 " + hql2);
				Query q = session.createQuery(hql2);
				q.setParameter(0, text);
				q.executeUpdate();
				
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("App Properties");
				audtoTO.setAddedName(text);
				audtoTO.setActionType("MODIFY");
				audtoTO.setAttribute(text);
				audtoTO.setPreviousValue("Disabled");
				audtoTO.setNewValue("Enabled");
				String des="Enabled.";
				String con=text.concat(des);
				audtoTO.setDesc(con);
				auditInfo.addAuditLog(audtoTO);
		//		session.delete(contactPolicyTO);
			}

		}
		if (result > 0)
			status = "0";

		return status;
	}

}
