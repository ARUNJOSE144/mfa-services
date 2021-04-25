package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.util.DateFormat;

/**
 * @author Nakhil Kurian
 * @Date : March, 2020
 *
 */
@Repository
public class AuditInfoDAOImpl implements AuditInfoDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Session session = null;
	Criteria criteria = null;
	private Query query = null;
	private String hql;

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Override
	public List<AuditInfoTO> getAuditInfo(AuditInfoTO auditInfoTO) {
		// TODO Auto-generated method stub
		List<AuditInfoTO> auditList = new ArrayList<AuditInfoTO>();
		AuditInfoTO mainTO = null;
		session = sessionFactory.getCurrentSession();

		criteria = session.createCriteria(AuditInfoTO.class);
		criteria.add(Restrictions.ge("createDate", auditInfoTO.getStartDate()));
		criteria.add(Restrictions.le("createDate", auditInfoTO.getEndDate()));

		List<AuditInfoTO> auditInfoList = criteria.list();

		if (!auditInfoList.isEmpty()) {
			for (AuditInfoTO auditTO : auditInfoList) {
				mainTO = new AuditInfoTO();
				mainTO.setId(auditTO.getId());
				mainTO.setDesc(auditTO.getDesc());

				String pattern = "yyyy/MM/dd HH:mm:ss";
				SimpleDateFormat df = new SimpleDateFormat(pattern);
				Date today = auditTO.getCreateDate();
				String todayAsString = df.format(today);

				mainTO.setCreateDateUI(todayAsString);

				mainTO.setUserId(auditTO.getUserId());
				mainTO.setFeatureName(auditTO.getFeatureName());
				mainTO.setAddedName(auditTO.getAddedName());
				mainTO.setActionType(auditTO.getActionType());
				mainTO.setAttribute(auditTO.getAttribute());
				mainTO.setPreviousValue(auditTO.getPreviousValue());
				mainTO.setNewValue(auditTO.getNewValue());
				mainTO.setCreatedBy(auditTO.getCreatedBy());
				mainTO.setRoleName(auditTO.getRoleName());

				auditList.add(mainTO);
			}
		}
		return auditList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AuditInfoTO> viewDefaultAudit() {
		Session session = sessionFactory.getCurrentSession();
		List<AuditInfoTO> list = new ArrayList<AuditInfoTO>();
		// hql = "from AuditInfoTO order by id desc";
		// return (List<AuditInfoTO>) session.createQuery(hql).list();
		hql = "select ID,CREATE_DATE, DESCRIPTION ,USER_ID,FEATURE,NAME,ACTION_TYPE,ATTRIBUTE,PREVIOUS_VALUE,NEW_VALUE,CREATED_BY ,ROLE_NAME  from  AUDIT_INFO order by ID desc";
		List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
		AuditInfoTO cdrTO = null;

		for (Object[] obj : rs) {
			cdrTO = new AuditInfoTO();
			cdrTO.setId(Integer.parseInt(obj[0] + ""));

			String pattern = "yyyy/MM/dd HH:mm:ss";
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			Date today = (Date) obj[1];
			String todayAsString = df.format(today);

			cdrTO.setCreateDateUI(todayAsString);
			cdrTO.setDesc((obj[2] + ""));
			cdrTO.setUserId(Integer.parseInt(obj[3] + ""));
			cdrTO.setFeatureName((obj[4] + ""));
			cdrTO.setAddedName((obj[5] + ""));
			cdrTO.setActionType((obj[6] + ""));
			cdrTO.setAttribute((obj[7] + ""));
			cdrTO.setPreviousValue((obj[8] + ""));
			cdrTO.setNewValue((obj[9] + ""));
			cdrTO.setCreatedBy((obj[10] + ""));
			cdrTO.setRoleName((obj[11] + ""));
			list.add(cdrTO);
		}

		return list;

	}

	@Override
	public void addAuditLog(AuditInfoTO auditInfoTO) {
		auditInfoTO.setUserId(1);
		// auditInfoTO.setCreatedBy("Admin");
		// auditInfoTO.setRoleName("STS User");
		System.out.println("Add audit log.................");
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(auditInfoTO);
		System.out.println("Add audit log done.................");
	}

	@Override
	public String saveRuleAudit(AuditInfoTO auditTO) {
		System.out.println("auditTO ::   " + auditTO.toString());
		String resp = "SC0001";
		String status = "Modified";
		String statusName = auditTO.getRuleName() + status;
		auditTO.setDesc(statusName);
		auditTO.setUserId(auditTO.getUserId());
		auditTO.setFeatureName(auditTO.getFeatureName());
		auditTO.setAddedName(auditTO.getRuleName());
		auditTO.setActionType(auditTO.getActionType());
		auditTO.setAttribute(auditTO.getAttribute());
		auditTO.setPreviousValue(auditTO.getPreviousValue());
		auditTO.setNewValue(auditTO.getNewValue());
		auditTO.setCreatedBy(auditTO.getCreatedBy());
		auditTO.setRoleName(auditTO.getRoleName());
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(auditTO);
		resp = "SC0000";
		return resp;
	}
}
