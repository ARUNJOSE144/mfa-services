/**
 * 
 */
package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.facebook.ads.sdk.Lead;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.LeadPolicyDAO;
import com.sixdee.magik.services.dao.NotificationsDao;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.CampaignMasterTO;
import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.NotificationsTO;
import com.sixdee.magik.services.model.WhatIfBasicTO;

/**
 * @author Rajesh
 *
 *         Feb 2019
 */
@Repository
public class LeadPolicyDAOImpl implements LeadPolicyDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	AuditInfoDAO auditInfo;

	@Autowired
	NotificationsDao notInfo;
	NotificationsTO notInfoto = null;

	private Session session = null;
	static Logger logger = Logger.getLogger(LeadPolicyDAOImpl.class);

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	AuditInfoTO audtoTO = null;
	int idValue;
	String nameValue;
	String expiryValue;
	String descValue;
	String actionkeyValue;
	String actionkeyNameValue;
	String campaginValue;

	@SuppressWarnings("unchecked")
	@Override
	public String createLeadPolicy(LeadPolicyTO leadPolicyTO) {
		// TODO Auto-generated method stub
		String status = "1";

		session = sessionFactory.getCurrentSession();

		leadPolicyTO.setCreateDate(new Date());

		if (leadPolicyTO.getId() == 0) {
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Lead Policy");
			audtoTO.setAddedName(leadPolicyTO.getPolicyName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			String a = "Created";
			String b = leadPolicyTO.getPolicyName().concat(a);
			audtoTO.setDesc(b);
			audtoTO.setCreatedBy(leadPolicyTO.getAuditUserName());
			audtoTO.setRoleName(leadPolicyTO.getAuditRoleName());
			auditInfo.addAuditLog(audtoTO);
			System.out.println("-----------------------Lead  Policy Successfully-----------------");

			notInfoto = new NotificationsTO();
			notInfoto.setSubject("Lead Policy");
			notInfoto.setNotification("Lead Policy Created");
			notInfo.addNotification(notInfoto);
			System.out.println("-----------------------Notification Saved-----------------");
			session.save(leadPolicyTO);
			status = "0";
		} else if (leadPolicyTO.getId() != 0) {
			System.out.println("   else if  ::::  ");
			System.out.println("   else if  1  ::::  " + leadPolicyTO.getPolicyName());
			System.out.println("   else if  id  ::::  " + leadPolicyTO.getId());

			String hql = "select ID ,POLICY_NAME ,EXPIRY_DAYS,DESCRIPTION,ACTION_KEY,ACTION_KEY_NAMES , CAMPAIGNS from  RE_LEAD_POLICY where ID= '"
					+ leadPolicyTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			LeadPolicyTO cdrTO = null;

			for (Object[] obj : rs) {
				// idValue = (Integer) obj[0];

				String id = obj[0].toString();
				idValue = Integer.parseInt(id);

				nameValue = obj[1].toString();
				expiryValue = obj[2].toString();

				if (Objects.isNull(obj[3])) {
					System.out.println("Object is Null 1");
				} else {
					descValue = obj[3].toString();
				}

				if (Objects.isNull(obj[4])) {
					System.out.println("Object is Null 2");
				} else {
					actionkeyValue = obj[4].toString();
				}

				if (Objects.isNull(obj[5])) {
					System.out.println("Object is Null 3");
				} else {
					actionkeyNameValue = obj[5].toString();
				}

				if (Objects.isNull(obj[6])) {
					System.out.println("Object is Null 4");
				} else {
					campaginValue = obj[6].toString();
				}

			}
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Lead Policy");
			audtoTO.setAddedName(leadPolicyTO.getPolicyName());
			audtoTO.setActionType("MODIFY");
			String a = "Modified";
			String b = leadPolicyTO.getPolicyName().concat(a);
			audtoTO.setDesc(b);
			if (nameValue != null && !nameValue.equalsIgnoreCase(leadPolicyTO.getPolicyName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("Lead Policy Name");
				audtoTO.setPreviousValue(nameValue);
				audtoTO.setNewValue(leadPolicyTO.getPolicyName());
			}

			if (expiryValue != null && !expiryValue.equalsIgnoreCase(leadPolicyTO.getExpiryDays())) {
				System.out.println(" expiryValue inside ");
				audtoTO.setAttribute("Number Of Days");
				audtoTO.setPreviousValue(expiryValue);
				audtoTO.setNewValue(leadPolicyTO.getExpiryDays());
			}
			if (descValue != null && !descValue.equalsIgnoreCase(leadPolicyTO.getDescription())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("Policy Description");
				audtoTO.setPreviousValue(descValue);
				audtoTO.setNewValue(leadPolicyTO.getDescription());

			}
			if (actionkeyNameValue != null
					&& !actionkeyNameValue.equalsIgnoreCase(leadPolicyTO.getExcludedActionKeysNames())) {
				System.out.println(" actionkeyNameValue inside ");
				audtoTO.setAttribute("Action Keys");
				audtoTO.setPreviousValue(actionkeyNameValue);
				audtoTO.setNewValue(leadPolicyTO.getExcludedActionKeysNames());

			}
			if (campaginValue != null && !campaginValue.equalsIgnoreCase(leadPolicyTO.getExcludedCampaignIds())) {
				System.out.println(" campaginValue inside ");
				audtoTO.setAttribute("Campaigns");
				audtoTO.setPreviousValue(campaginValue);
				audtoTO.setNewValue(leadPolicyTO.getExcludedCampaignIds());

			}
			/*
			 * audtoTO.setPreviousValue(nameValue);
			 * audtoTO.setNewValue(leadPolicyTO.getPolicyName());
			 */
			audtoTO.setCreatedBy(leadPolicyTO.getAuditUserName());
			audtoTO.setRoleName(leadPolicyTO.getAuditRoleName());
			auditInfo.addAuditLog(audtoTO);
			session.update(leadPolicyTO);
			status = "0";
			System.out.println("-----------------------Lead  Policy  Edit-----------------");
		} else

		{
			System.out.println("-----------------------Lead  Policy Not saved-----------------");
			status = "1";
		}

		return status;
	}

	@Override
	public List<LeadPolicyTO> getLeadPolicyList(int userId) {
		logger.info("Class : LeadPolicyDAOImpl | Method : getLeadPolicyList");
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = session.createCriteria(LeadPolicyTO.class);
		List<LeadPolicyTO> mainList = new ArrayList<LeadPolicyTO>();
		/*
		 * if(userId!=0) { criteria.add(Restrictions.eq("userId", userId)); }
		 */

		criteria.addOrder(Order.desc("id"));

		List<LeadPolicyTO> tempList = (List<LeadPolicyTO>) criteria.list();
		LeadPolicyTO mainTO = null;
		for (LeadPolicyTO to : tempList) {
			mainTO = (LeadPolicyTO) to.cloneObject(to);
			mainTO.setCreateDateUI(simpleDateFormat.format(to.getCreateDate()));
			mainTO.setStartdateConvert(simpleDateFormat.format(to.getStartDate()));

			// String pattern = "dd-MM-YYYY";
			String pattern = "YYYY-MM-dd";
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Date today = mainTO.getStartDate();
			Date endDate = mainTO.getEndDate();

			String todayAsString = df.format(today);
			mainTO.setStartdateConvert(todayAsString);

			String enddateString = df.format(endDate);
			mainTO.setEnddateConvert(enddateString);

			if (to.getExcludedCampaignIds() != null && !to.getExcludedCampaignIds().trim().equalsIgnoreCase(""))
				mainTO.setExcludedCampaignNames(getCampaignNamesFromId(to.getExcludedCampaignIds()));
			else
				mainTO.setExcludedCampaignNames("");
			mainList.add(mainTO);
		}

		return mainList;
	}

	public LinkedHashMap<String, String> getCampaignsMap() {
		LinkedHashMap<String, String> campaignTypeMap = new LinkedHashMap<String, String>();
		Session session = sessionFactory.getCurrentSession();

		@SuppressWarnings({ "unchecked", "deprecation" })
		List<CampaignMasterTO> list = (List<CampaignMasterTO>) session.createCriteria(CampaignMasterTO.class).list();
		campaignTypeMap.put("-1", "All");
		for (CampaignMasterTO to : list) {
			campaignTypeMap.put(to.getId() + "", to.getCampaignName());
		}

		return campaignTypeMap;
	}

	public String getCampaignNamesFromId(String campaingIds) {
		String campaignNames = "";
		if (campaingIds != null) {
			String[] campIdsArray = campaingIds.split(",");
			if (campIdsArray != null) {
				for (String campId : campIdsArray) {
					if (getCampaignsMap().keySet().contains(campId)) {
						if (campaignNames == "") {
							campaignNames = getCampaignsMap().get(campId);
						} else {
							campaignNames += "," + getCampaignsMap().get(campId);
						}
					}

				}
			}
		}
		return campaignNames;

	}

	@Override
	public String deleteLeadPolicy(LeadPolicyTO leadPolicyTO) {
		// TODO Auto-generated method stub

		logger.info("Class : LeadPolicyDAOImpl | Method : deleteLeadPolicy ::" + leadPolicyTO.getId());
		String status = "1";
		Session session = sessionFactory.getCurrentSession();

		audtoTO = new AuditInfoTO();
		audtoTO.setFeatureName("Lead Policy");
		audtoTO.setAddedName(leadPolicyTO.getPolicyName());
		audtoTO.setActionType("DELETE");
		audtoTO.setAttribute("N/A");
		audtoTO.setPreviousValue("N/A");
		audtoTO.setNewValue("N/A");
		String a = "Deleted";
		String b = leadPolicyTO.getPolicyName().concat(a);
		audtoTO.setDesc(b);
		audtoTO.setCreatedBy(leadPolicyTO.getAuditUserName());
		audtoTO.setRoleName(leadPolicyTO.getAuditRoleName());
		auditInfo.addAuditLog(audtoTO);
		session.delete(leadPolicyTO);
		status = "0";
		return status;
	}

	@Override
	public List<WhatIfBasicTO> getWhatIfAnalysisBasicDetails() {

		String query = null;
		Session session = sessionFactory.getCurrentSession();
		List<WhatIfBasicTO> list = new ArrayList<WhatIfBasicTO>();

		query = "FROM WhatIfBasicTO ";
		list = (List<WhatIfBasicTO>) session.createQuery(query).list();

		return list;

	}

}
