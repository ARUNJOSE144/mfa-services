package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.ContactPolicyDAO;
import com.sixdee.magik.services.dao.NotificationsDao;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.ContactPolicyDescriptionTO;
import com.sixdee.magik.services.model.ContactPolicyFieldDescriptionTO;
import com.sixdee.magik.services.model.ContactPolicyFieldTO;
import com.sixdee.magik.services.model.ContactPolicyTO;
import com.sixdee.magik.services.model.NotificationsTO;
import com.sixdee.magik.services.service.impl.ContactPolicyServiceImpl;

@Repository
@Transactional
public class ContactPolicyDAOImpl implements ContactPolicyDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;
	int idValue;
	String nameValue;
	String descValue;

	@Autowired
	NotificationsDao notInfo;
	NotificationsTO notInfoto = null;

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public List<ContactPolicyTO> getContactPolicies() {
		final Logger logger = Logger.getLogger(ContactPolicyServiceImpl.class);
		List<ContactPolicyTO> contactPolicyTOs = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "from ContactPolicyTO order by policyId desc";
		Query query = session.createQuery(hql);
		contactPolicyTOs = query.list();
		for (ContactPolicyTO policyTO : contactPolicyTOs) {
			policyTO.setStrtDate(policyTO.getStartDate() + "");
			policyTO.setEndDatee(policyTO.getEndDate() + "");

			String pattern = "YYYY-MM-dd";
			SimpleDateFormat df = new SimpleDateFormat(pattern);

			Date today = policyTO.getStartDate();
			Date endDate = policyTO.getEndDate();

			String todayAsString = df.format(today);
			policyTO.setStartdateConvert(todayAsString);

			String enddateString = df.format(endDate);
			policyTO.setEnddateConvert(enddateString);

		}
		return contactPolicyTOs;

	}

	@SuppressWarnings("unchecked")
	@Override
	public String createContactPolicy(ContactPolicyTO contactPolicyTO) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";
		try {

			System.out.println("category type========================>" + contactPolicyTO.getCategoryType());
			System.out.println("Policy Id========================>" + contactPolicyTO.getPolicyId());
			// session.saveOrUpdate(contactPolicyTO);

			System.out.println("contact policy childrens " + contactPolicyTO.getChildrens().size());

			if (contactPolicyTO.getPolicyId() == 0) {
				System.out.println(" inside if");
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("Contact Policy");
				audtoTO.setAddedName(contactPolicyTO.getPolicyName());
				audtoTO.setActionType("CREATE");
				audtoTO.setAttribute("N/A");
				audtoTO.setPreviousValue("N/A");
				audtoTO.setNewValue("N/A");
				String a = "Created";
				String b = contactPolicyTO.getPolicyName().concat(a);
				audtoTO.setDesc(b);

				audtoTO.setCreatedBy(contactPolicyTO.getAuditUserName());
				audtoTO.setRoleName(contactPolicyTO.getAuditRoleName());

				auditInfo.addAuditLog(audtoTO);
				statusCode = "SC0000";
				session.save(contactPolicyTO);
				System.out.println("-----------------------Lead  Policy Successfully-----------------");

				notInfoto = new NotificationsTO();
				notInfoto.setSubject("Contact Policy");
				notInfoto.setNotification("Contact Policy Created");
				notInfo.addNotification(notInfoto);
				System.out.println("-----------------------Notification Saved-----------------");
			} else if (contactPolicyTO.getPolicyId() != 0) {
				System.out.println("   else if  ::::  ");
				System.out.println("   else if  1  ::::  " + contactPolicyTO.getPolicyName());
				System.out.println("   else if  id  ::::  " + contactPolicyTO.getPolicyId());

				/*
				 * contactPolicyTO to=(contactPolicyTO)session.get(contactPolicyTO.class,
				 * contactPolicyTO); System.out.println(" to   1 :: " + to);
				 * System.out.println(" to  :: " + to.getPolicyName());
				 */
				String hql = "select POLICY_ID ,POLICY_NAME ,DESCRIPTION from  NCP_DETAIL where POLICY_ID= '"
						+ contactPolicyTO.getPolicyId() + "'  ";
				List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
				System.out.println("size::::::" + rs.size());

				for (Object[] obj : rs) {
					//idValue = (Integer) obj[0];
					
					String id = obj[0].toString();
					idValue = Integer.parseInt(id);
					
					nameValue = obj[1].toString();
					descValue = obj[2].toString();

				}
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("Contact Policy");
				audtoTO.setAddedName(contactPolicyTO.getPolicyName());
				audtoTO.setActionType("MODIFY");

				String a = "Modified";
				String b = contactPolicyTO.getPolicyName().concat(a);
				audtoTO.setDesc(b);
				if (nameValue != null && !nameValue.equalsIgnoreCase(contactPolicyTO.getPolicyName())) {
					System.out.println(" name inside ");
					audtoTO.setAttribute("Contact Policy Name");
					audtoTO.setPreviousValue(nameValue);
					audtoTO.setNewValue(contactPolicyTO.getPolicyName());
				}
				if (descValue != null && !descValue.equalsIgnoreCase(contactPolicyTO.getDescription())) {
					System.out.println(" Description inside ");
					audtoTO.setAttribute("Policy Description");
					audtoTO.setPreviousValue(descValue);
					audtoTO.setNewValue(contactPolicyTO.getDescription());

				}
				/*
				 * audtoTO.setPreviousValue(nameValue);
				 * audtoTO.setNewValue(contactPolicyTO.getPolicyName());
				 */

				audtoTO.setCreatedBy(contactPolicyTO.getAuditUserName());
				audtoTO.setRoleName(contactPolicyTO.getAuditRoleName());

				auditInfo.addAuditLog(audtoTO);
				session.update(contactPolicyTO);
				statusCode = "SC0000";
				System.out.println("-----------------------Lead  Policy  Edit-----------------");
			} else

			{
				System.out.println("-----------------------Lead  Policy Not saved-----------------");
				statusCode = "SC0000";
			}

			// deleting existing entries
			String query = " FROM ContactPolicyDescriptionTO where policyId=" + contactPolicyTO.getPolicyId();
			List<ContactPolicyDescriptionTO> tos = (List<ContactPolicyDescriptionTO>) session.createQuery(query).list();
			if (null != tos) {
				for (ContactPolicyDescriptionTO to : tos) {
					session.delete(to);
					;
				}
			}

			for (int i = 0; i < contactPolicyTO.getChildrens().size(); i++) {
				ContactPolicyDescriptionTO to = contactPolicyTO.getChildrens().get(i);
				String a = to.getFrequency().toUpperCase();
				to.setFrequency(a);
				to.setPolicyId(contactPolicyTO.getPolicyId());
				session.save(to);
			}
			statusCode = "SC0000";

		} catch (Exception e) {
			statusCode = "SC0001";
			e.printStackTrace();
		}

		return statusCode;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactPolicyFieldTO> getSegmentTypes(int id) {
		Session session = sessionFactory.getCurrentSession();

		String query = "FROM ContactPolicyFieldTO where categoryType=" + id;
		List<ContactPolicyFieldTO> dto = (List<ContactPolicyFieldTO>) session.createQuery(query).list();

		return dto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ContactPolicyFieldDescriptionTO> getSegmentCategoryTypes(int categoryField) {
		Session session = sessionFactory.getCurrentSession();

		String query = "FROM ContactPolicyFieldDescriptionTO where categoryField=" + categoryField;
		List<ContactPolicyFieldDescriptionTO> dto = (List<ContactPolicyFieldDescriptionTO>) session.createQuery(query)
				.list();

		return dto;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ContactPolicyTO getContactPolicyInfo(int policyId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		String query = "FROM ContactPolicyTO where policyId=" + policyId;
		ContactPolicyTO policyDto = (ContactPolicyTO) session.createQuery(query).uniqueResult();

		String pattern = "YYYY-MM-dd";
		SimpleDateFormat df = new SimpleDateFormat(pattern);

		Date today = policyDto.getStartDate();
		Date endDate = policyDto.getEndDate();

		String todayAsString = df.format(today);
		policyDto.setStartdateConvert(todayAsString);

		String endDateString = df.format(endDate);
		policyDto.setEnddateConvert(endDateString);

		query = "FROM ContactPolicyDescriptionTO where policyId=" + policyId;
		ArrayList<ContactPolicyDescriptionTO> dtos = (ArrayList<ContactPolicyDescriptionTO>) session.createQuery(query)
				.list();
		policyDto.setChildrens(dtos);

		return policyDto;
	}

	@Override
	public String deleteContactPolicy(ContactPolicyTO contactPolicyTO) {
		String statusCode = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		System.out.println(" contactPolicyTO.getPolicyName() " + contactPolicyTO.getPolicyName());
		System.out.println(" contactPolicyTO.getid() " + contactPolicyTO.getPolicyId());

		audtoTO = new AuditInfoTO();
		audtoTO.setFeatureName("Contact Policy");
		audtoTO.setAddedName(contactPolicyTO.getPolicyName());
		audtoTO.setActionType("DELETE");
		audtoTO.setAttribute("N/A");
		audtoTO.setPreviousValue("N/A");
		audtoTO.setNewValue("N/A");

		String hql = "delete from NCP_DESCRIPTION where POLICY_ID = " + contactPolicyTO.getPolicyId();
		session.createSQLQuery(hql).executeUpdate();

		String a = "Deleted";
		String b = contactPolicyTO.getPolicyName().concat(a);
		audtoTO.setDesc(b);

		audtoTO.setCreatedBy(contactPolicyTO.getAuditUserName());
		audtoTO.setRoleName(contactPolicyTO.getAuditRoleName());

		auditInfo.addAuditLog(audtoTO);
		session.delete(contactPolicyTO);
		statusCode = "SC0000";
		return statusCode;
	}

}
