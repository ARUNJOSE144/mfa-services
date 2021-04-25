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
import com.sixdee.magik.services.dao.QuarantineDAO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.QuarantineBlackListTO;
import com.sixdee.magik.services.model.QuarantineDetailsTO;
import com.sixdee.magik.services.model.QuarantineSpecialDaysTO;
import com.sixdee.magik.services.model.QuarantineTO;
import com.sixdee.magik.services.model.QuarantineWeekDaysTO;

/**
 * @author arun.jose
 */
@Repository
public class QuarantineDAOImpl implements QuarantineDAO {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	private Query query = null;
	private String hql;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	@Override
	public String createQuarantinePolicy(QuarantineTO quarantineTO) {
		String stat = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		if (quarantineTO.getId() == 0) {
			System.out.println(" inside ");
			session.save(quarantineTO);
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Quarantine");
			audtoTO.setAddedName(quarantineTO.getPolicyName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a = "Created";
			String b = quarantineTO.getPolicyName().concat(a);
			audtoTO.setDesc(b);

			auditInfo.addAuditLog(audtoTO);
			if (quarantineTO.getQuarantineSpecialDaysDetails().getSpecialDays().size() != 0) {
				insertSpecialDays(quarantineTO, session);
			}

			if (quarantineTO.getQuarantineWeekDaysDetails().getWeekDays().size() != 0) {
				insertWeekDays(quarantineTO, session);
			}
			if (quarantineTO.getQuarantineBlackListDetails().getBlackList().size() != 0) {
				insertBlacklist(quarantineTO, session);
			}

			stat = "SC0000";

		}

		/// session.save(quarantineTO);
		System.out.println("");

		return stat;
	}

	private void insertBlacklist(QuarantineTO quarantineTO, Session session) {
		if (quarantineTO.getQuarantineBlackListDetails() != null) {
			QuarantineDetailsTO dto = quarantineTO.getQuarantineBlackListDetails();
			dto.setName(quarantineTO.getPolicyName() + "_BlackList");
			dto.setPolicyId(quarantineTO.getId());
			dto.setQuarantineType(3); // QuarantineType = 1 is for special days
			session.save(dto);
			for (int i = 0; i < dto.getBlackList().size(); i++) {
				QuarantineBlackListTO to = dto.getBlackList().get(i);
				to.setBlackListPolicyDetailsid(dto.getId());
				session.save(to);
			}

		}
	}

	public void insertSpecialDays(QuarantineTO quarantineTO, Session session) {
		if (quarantineTO.getQuarantineSpecialDaysDetails() != null) {
			QuarantineDetailsTO dto = quarantineTO.getQuarantineSpecialDaysDetails();
			dto.setName(quarantineTO.getPolicyName() + "_SpecilDays");
			dto.setPolicyId(quarantineTO.getId());
			dto.setQuarantineType(1); // QuarantineType = 1 is for special days
			session.save(dto);
			for (int i = 0; i < dto.getSpecialDays().size(); i++) {
				QuarantineSpecialDaysTO to = dto.getSpecialDays().get(i);
				to.setSpecialDatePolicyId(dto.getId());
				session.save(to);
			}

		}

	}

	public void insertWeekDays(QuarantineTO quarantineTO, Session session) {
		if (quarantineTO.getQuarantineWeekDaysDetails() != null) {
			QuarantineDetailsTO dto = quarantineTO.getQuarantineWeekDaysDetails();
			dto.setName(quarantineTO.getPolicyName() + "_WeekDays");
			dto.setPolicyId(quarantineTO.getId());
			dto.setQuarantineType(2); // QuarantineType = 1 is for special days
			session.save(dto);
			for (int i = 0; i < dto.getWeekDays().size(); i++) {
				QuarantineWeekDaysTO to = dto.getWeekDays().get(i);
				to.setWeekDayPolicyId(dto.getId());
				session.save(to);
			}

		}

	}

	@Override
	public String deleteQuarantinePolicy(QuarantineTO quarantineTO) {
		String stat = "SC0001";
		Session session = sessionFactory.getCurrentSession();

		audtoTO = new AuditInfoTO();
		audtoTO.setFeatureName("Quarantine");
		audtoTO.setAddedName(quarantineTO.getPolicyName());
		audtoTO.setActionType("DELETE");
		audtoTO.setAttribute("N/A");
		audtoTO.setPreviousValue("N/A");
		audtoTO.setNewValue("N/A");

		String a = "Deleted";
		String b = quarantineTO.getPolicyName().concat(a);
		audtoTO.setDesc(b);
		auditInfo.addAuditLog(audtoTO);
		session.delete(quarantineTO);
		stat = "SC0000";
		return stat;
	}

	@Override
	public String uploadBlacklist(QuarantineBlackListTO to) {

		String stat = "SC0001";
		Session session = sessionFactory.getCurrentSession();
		to.setUploadtype("BulkUpload");
		session.save(to);
		stat = "SC0000";
		return stat;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuarantineTO> getQuarantinePolicyList() {
		int id;
		List<QuarantineTO> policys = new ArrayList<QuarantineTO>();
		Session session = sessionFactory.getCurrentSession();
		QuarantineTO to = null;
		List<Object[]> res = null;
		String hql = "Select ID ,POLICY_NAME,DESCRIPTION from RE_QUARANTINE_POLICY order by id desc";
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new QuarantineTO();
			id = (int) obj[0];
			to.setId(Integer.parseInt(id + ""));
			to.setPolicyName(obj[1].toString());
			to.setDescription(obj[2].toString());
			to.setDetailsAll(getDetails(id));
			policys.add(to);

		}
		return policys;

	}

	@SuppressWarnings("unchecked")
	private List<QuarantineDetailsTO> getDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<QuarantineDetailsTO> listMainTable = new ArrayList<QuarantineDetailsTO>();
		int policyId;
		int type;
		QuarantineDetailsTO to = null;
		List<Object[]> res = null;
		hql = "Select ID,NAME,QUARANTINE_TYPE from RE_QUARANTINE_DETAILS where POLICY_ID = " + id + "";
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new QuarantineDetailsTO();
			policyId = (int) obj[0];
			type = (int) obj[2];
			to.setId(Integer.parseInt(policyId + ""));
			to.setName(obj[1].toString());
			to.setQuarantineType(Integer.parseInt(type + ""));
			if (type == 1) {
				to.setSpecialDays(editSpecialDate(policyId));
				listMainTable.add(to);
			}
			if (type == 2) {
				to.setWeekDays(editWeekDay(policyId));
				listMainTable.add(to);
			}
			if (type == 3) {
				to.setBlackList(editBlackList(policyId));
				listMainTable.add(to);
			}
			// listMainTable.add(to);
		}
		return listMainTable;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuarantineDetailsTO> editQuarantineDetails(int id) {
		Session session = sessionFactory.getCurrentSession();
		List<QuarantineDetailsTO> listMainTable = new ArrayList<QuarantineDetailsTO>();
		int policyId;
		int type;
		QuarantineDetailsTO to = null;
		List<Object[]> res = null;
		hql = "Select ID,NAME,QUARANTINE_TYPE from RE_QUARANTINE_DETAILS where POLICY_ID = " + id + "";
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new QuarantineDetailsTO();
			policyId = (int) obj[0];
			type = (int) obj[2];
			to.setId(Integer.parseInt(policyId + ""));
			to.setName(obj[1].toString());
			to.setQuarantineType(Integer.parseInt(type + ""));
			if (type == 1) {
				to.setSpecialDays(editSpecialDate(policyId));
				listMainTable.add(to);
			}
			if (type == 2) {
				to.setWeekDays(editWeekDay(policyId));
				listMainTable.add(to);
			}
			if (type == 3) {
				to.setBlackList(editBlackList(policyId));
				listMainTable.add(to);
			}
			// listMainTable.add(to);
		}
		return listMainTable;
	}

	@SuppressWarnings("unchecked")
	public List<QuarantineSpecialDaysTO> editSpecialDate(int policyId) {
		Session session = sessionFactory.getCurrentSession();
		List<QuarantineSpecialDaysTO> list = new ArrayList<QuarantineSpecialDaysTO>();
		QuarantineSpecialDaysTO to = null;
		List<Object[]> res = null;
		hql = "Select ID,SPECIAL_DATE,START_TIME,END_TIME,POLICY_DETAILS_ID from RE_QUARANTINE_SPECIAL_DAYS where POLICY_DETAILS_ID = "
				+ policyId + " order by id desc";
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new QuarantineSpecialDaysTO();
			to.setId(Integer.parseInt((obj[0]) + ""));
			to.setSpecialDate((obj[1]) + "");
			to.setSpecialDateStartTime((obj[2]) + "");
			to.setSpecialDateEndTime((obj[3]) + "");
			to.setSpecialDatePolicyId(Integer.parseInt((obj[4]) + ""));
			list.add(to);
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<QuarantineBlackListTO> editBlackList(int policyId) {
		Session session = sessionFactory.getCurrentSession();
		List<QuarantineBlackListTO> list = new ArrayList<QuarantineBlackListTO>();
		QuarantineBlackListTO to = null;
		List<Object[]> res = null;
		hql = "Select ID,MSISDN ,POLICY_DETAILS_ID from RE_QUARANTINE_BLACKLIST where POLICY_DETAILS_ID = " + policyId
				+ " order by id desc";
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new QuarantineBlackListTO();
			to.setId(Integer.parseInt((obj[0]) + ""));
			to.setMsisdn((obj[1]) + "");
			to.setBlackListPolicyDetailsid(Integer.parseInt((obj[2]) + ""));
			list.add(to);
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<QuarantineWeekDaysTO> editWeekDay(int policyId) {
		Session session = sessionFactory.getCurrentSession();
		List<QuarantineWeekDaysTO> list = new ArrayList<QuarantineWeekDaysTO>();
		QuarantineWeekDaysTO to = null;
		List<Object[]> res = null;
		hql = "Select ID,DAY,WEEKS,START_TIME,END_TIME,POLICY_DETAILS_ID from RE_QUARANTINE_WEEK_DAYS where POLICY_DETAILS_ID = "
				+ policyId + " order by id desc";
		res = session.createSQLQuery(hql).list();
		for (Object[] obj : res) {
			to = new QuarantineWeekDaysTO();
			to.setId(Integer.parseInt((obj[0]) + ""));
			to.setDayId(Integer.parseInt((obj[1]) + ""));
			to.setWeeks((obj[2]) + "");
			to.setWeekDayStartTime((obj[3]) + "");
			to.setWeekDayEndTime((obj[4]) + "");
			to.setWeekDayPolicyId(Integer.parseInt((obj[5]) + ""));
			list.add(to);
		}
		return list;

	}

}
