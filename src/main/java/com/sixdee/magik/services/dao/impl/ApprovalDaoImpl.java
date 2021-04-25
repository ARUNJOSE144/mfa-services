package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.ApprovalDao;
import com.sixdee.magik.services.model.ApprovalAuditTO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.OverAllCampaignPushTO;
import com.sixdee.magik.services.model.UserDetails;
import com.sixdee.magik.services.model.UserMaster;

/**
 * @author Nakhil Kurian
 * @Date : February, 2021
 */
@Repository
public class ApprovalDaoImpl implements ApprovalDao {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String hql = null;
	Session session = null;

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@SuppressWarnings("unchecked")
	@Override
	public List<DesignationHierarchy> getDesignationDetails(String designationID) {
		session = sessionFactory.getCurrentSession();
		System.out.println("designationID  ::  " + designationID.toString());

		hql = " from DesignationHierarchy where designationId=" + designationID + "order by designationId desc";
		List<DesignationHierarchy> list = (List<DesignationHierarchy>) session.createQuery(hql).list();

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMaster> getUserDetails(String parentId, String channelType) {
		List<UserMaster> list = new ArrayList<UserMaster>();
		UserMaster listTO = null;
		Session session1 = null;
		session1 = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = session1.createCriteria(UserMaster.class);

		int id = Integer.parseInt(parentId);
		Long id1 = new Long(id);
		int type = Integer.parseInt(channelType);

		System.out.println("channelType  ::  " + type);
		System.out.println("parentId  ::  " + id1);

		criteria.add(Restrictions.eq("designationId", id1));
		criteria.add(Restrictions.eq("channelType", type));
		criteria.addOrder(Order.desc("userId"));
		List<UserMaster> userList = criteria.list();
		if (!userList.isEmpty()) {

			for (UserMaster userData : userList) {
				listTO = new UserMaster();
				long userId = userData.getUserId();
				listTO.setUsername(userData.getUsername());
				listTO.setUserIDUI(Long.toString(userId));
				listTO.setParentId(userData.getParentId());
				listTO.setFirstName(userData.getFirstName());
				listTO.setLastName(userData.getLastName());
				listTO.setParentId(userData.getParentId());
				listTO.setIsApprovalRequired(userData.getIsApprovalRequired());
				listTO.setApprovalTypeFlag(userData.getApprovalTypeFlag());
				list.add(listTO);
			}

		}
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserMaster> viewUserDetails(String parentId) {
		List<UserMaster> list = new ArrayList<UserMaster>();
		UserMaster listTO = null;
		Session session1 = null;
		session1 = sessionFactory.getCurrentSession();
		Criteria criteria = null;
		criteria = session1.createCriteria(UserMaster.class);

		int id = Integer.parseInt(parentId);
		Long id1 = new Long(id);

		System.out.println("parentId  ::  " + id1);

		criteria.add(Restrictions.eq("userId", id1));
		criteria.addOrder(Order.desc("userId"));
		List<UserMaster> userList = criteria.list();
		if (!userList.isEmpty()) {

			for (UserMaster userData : userList) {
				listTO = new UserMaster();
				listTO.setParentUserName(userData.getUsername());
				list.add(listTO);
			}

		}
		return list;
	}

	@Override
	public String updateUserDetails(UserDetails userTO) {
		System.out.println("getUserId  " + userTO.getUserId());
		System.out.println("getApprovalTypeFlag  " + userTO.getApprovalTypeFlag());
		System.out.println("getIsApprovalRequired  " + userTO.getIsApprovalRequired());
		String status = "-1";
		Session session = sessionFactory.getCurrentSession();

		String hql = "update UserMaster set isApprovalRequired = '" + userTO.getIsApprovalRequired()
				+ "', approvalTypeFlag = '" + userTO.getApprovalTypeFlag() + "' where userId = '" + userTO.getUserId()
				+ "'";
		Query query = session.createQuery(hql);
		int result = query.executeUpdate();
		System.out.println("result  Value :: " + result);
		if (result > 0) {
			status = "0";

		}
		return status;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApprovalAuditTO> getApprovalAuditInfo() {
		session = sessionFactory.getCurrentSession();
		List<ApprovalAuditTO> list = new ArrayList<ApprovalAuditTO>();
		ApprovalAuditTO to = null;
		List<Object[]> res = null;
		String type = null;
		hql = " select id, operator, designation, user,approvalType,approver,status,ruleId,ruleName,campaignName,approvalDate from ApprovalAuditTO order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ApprovalAuditTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setOperator(obj[1] + "");
			to.setDesignation(obj[2] + "");
			to.setUser(obj[3] + "");
			type = obj[4].toString();
			int typecheck = Integer.parseInt(type + "");
			System.out.println("type  " + typecheck);
			if (typecheck == 0) {
				to.setApprovalType("Immediate");
			} else {
				to.setApprovalType("Hierarchy");
			}
			to.setApprover(Integer.parseInt(obj[5] + ""));
			to.setStatus(obj[6] + "");
			to.setRuleId(Integer.parseInt(obj[7] + ""));
			to.setRuleName(obj[8] + "");
			to.setCampaignName(obj[9] + "");
			to.setCreateDateUI(simpleDateFormat.format(obj[10]));
			list.add(to);
		}
		System.out.println("list  ::  " + list.toString());

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApprovalAuditTO> searchApprovalAuditInfo() {
		session = sessionFactory.getCurrentSession();
		List<ApprovalAuditTO> list = new ArrayList<ApprovalAuditTO>();
		ApprovalAuditTO to = null;
		List<Object[]> res = null;
		hql = " select  distinct(ruleId),(ruleName) from ApprovalAuditTO order by id desc";
		res = session.createQuery(hql).list();
		for (Object[] obj : res) {
			to = new ApprovalAuditTO();
			to.setRuleId(Integer.parseInt(obj[0] + ""));
			to.setRuleName(obj[1] + "");
			list.add(to);
		}
		System.out.println("list  ::  " + list.toString());

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ApprovalAuditTO> getSearchApprovalAuditInfo(ApprovalAuditTO auditInfoTO) {
		List<ApprovalAuditTO> auditList = new ArrayList<ApprovalAuditTO>();
		ApprovalAuditTO mainTO = null;
		session = sessionFactory.getCurrentSession();
		Criteria criteria = null;

		criteria = session.createCriteria(ApprovalAuditTO.class);
		criteria.add(Restrictions.eq("ruleName", auditInfoTO.getRuleName()));
		criteria.add(Restrictions.eq("ruleId", auditInfoTO.getRuleId()));

		List<ApprovalAuditTO> auditInfoList = criteria.list();

		if (!auditInfoList.isEmpty()) {
			for (ApprovalAuditTO auditTO : auditInfoList) {
				mainTO = new ApprovalAuditTO();
				mainTO.setId(auditTO.getId());
				mainTO.setOperator(auditTO.getOperator());

				//String pattern = "yyyy/MM/dd HH:mm:ss";
				//String pattern = "yyyy-MM-dd ";
				SimpleDateFormat df = new SimpleDateFormat(pattern);
				Date today = auditTO.getApprovalDate();
				String todayAsString = df.format(today);
				mainTO.setCreateDateUI(todayAsString);

				mainTO.setDesignation(auditTO.getDesignation());
				mainTO.setUser(auditTO.getUser());

				int typecheck = Integer.parseInt(auditTO.getApprovalType());
				if (typecheck == 0) {
					mainTO.setApprovalType("Immediate");
				} else {
					mainTO.setApprovalType("Hierarchy");
				}

				mainTO.setApprover(auditTO.getApprover());
				mainTO.setStatus(auditTO.getStatus());
				mainTO.setRuleId(auditTO.getRuleId());
				mainTO.setRuleName(auditTO.getRuleName());
				mainTO.setCampaignName(auditTO.getCampaignName());

				auditList.add(mainTO);
			}
		}
		return auditList;
	}

}
