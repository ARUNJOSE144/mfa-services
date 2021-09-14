package com.inno.mfa.services.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.QuestionMasterTo;
import com.inno.mfa.services.util.Util;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class QuestionsDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	static final Logger logger = Logger.getLogger(QuestionsDAO.class);

	@SuppressWarnings("unchecked")
	public PaginationTo<QuestionMasterTo> getQustionList(PaginationTo<QuestionMasterTo> paginationTo) {
		List<QuestionMasterTo> list = new ArrayList<QuestionMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(paginationTo, session));
			}

			Criteria criteria = session.createCriteria(QuestionMasterTo.class);
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("name", "%" + paginationTo.getSearchKey1() + "%"));
			}
			criteria.setFirstResult(paginationTo.getFirstRecord());
			criteria.setMaxResults(paginationTo.getRecordCount());
			list = criteria.list();
			paginationTo.setList(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return paginationTo;
	}

	public Integer getRowCount(PaginationTo<QuestionMasterTo> paginationTo, Session session) {
		Long count = 0l;
		try {
			Criteria criteria = session.createCriteria(QuestionMasterTo.class);
			criteria.setProjection(Projections.rowCount());
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("roleName", "%" + paginationTo.getSearchKey1() + "%"));
			}
			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count.intValue();
	}

	/*
	 * @SuppressWarnings("unchecked") public void
	 * getModulePermission(CommonRespTo<ModuleMasterTo> to) { List<ModuleMasterTo>
	 * masterTos = null; List<FeatureMasterTo> featureMasterTos = null; try {
	 * Session session = sessionFactory.getCurrentSession(); Criteria criteria =
	 * session.createCriteria(ModuleMasterTo.class); masterTos =
	 * (List<ModuleMasterTo>) criteria.list();
	 * 
	 * criteria = session.createCriteria(FeatureMasterTo.class); featureMasterTos =
	 * (List<FeatureMasterTo>) criteria.list();
	 * 
	 * for (ModuleMasterTo mto : masterTos) { List<FeatureMasterTo> list = new
	 * ArrayList<FeatureMasterTo>(); for (FeatureMasterTo fto : featureMasterTos) {
	 * if (fto.getModuleId() == mto.getModuleId()) { list.add(fto); } }
	 * mto.setFeatures(list);
	 * 
	 * } to.setList(masterTos); } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 */
	public void create(QuestionMasterTo questionMasterTo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			questionMasterTo.setCreatedTime(new Date());

			logger.info("=========Question Record : " + questionMasterTo.toString());
			session.save(questionMasterTo);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<QuestionMasterTo> searchQuestion(QuestionMasterTo searchTO) {
		List<QuestionMasterTo> list = new ArrayList<QuestionMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();

			Criteria criteria = session.createCriteria(QuestionMasterTo.class);
			/* if (Util.validate(searchTO.getKey())) { */
			/*
			 * criteria.add(Restrictions.disjunction().add(Restrictions.ilike("key", "%" +
			 * searchTO.getKey() + "%")) .add(Restrictions.ilike("question", "%" +
			 * searchTO.getQuestion() + "%")) .add(Restrictions.ilike("answer", "%" +
			 * searchTO.getAnswer() + "%")));
			 */
			/* } */

			Disjunction orRes = Restrictions.disjunction();

			if (Util.validate(searchTO.getKey())) {
				orRes.add(Restrictions.ilike("key", "%" + searchTO.getKey().trim() + "%"));
			}
			if (Util.validate(searchTO.getQuestion())) {
				orRes.add(Restrictions.ilike("question", "%" + searchTO.getQuestion().trim() + "%"));
			}
			if (Util.validate(searchTO.getAnswer())) {
				orRes.add(Restrictions.ilike("answer", "%" + searchTO.getAnswer().trim() + "%"));
			}
			criteria.add(orRes);

			list = criteria.list();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/*
	 * @SuppressWarnings("unchecked") public RolesTo view(RolesTo rolesTo) {
	 * Criteria criteria = null; Session session = null;
	 * 
	 * try { session = sessionFactory.getCurrentSession(); criteria =
	 * session.createCriteria(RolesTo.class); criteria.add(Restrictions.eq("roleId",
	 * rolesTo.getRoleId())); rolesTo = (RolesTo) criteria.uniqueResult();
	 * 
	 * criteria = session.createCriteria(RolePermissionsTo.class);
	 * criteria.add(Restrictions.eq("roleId", rolesTo.getRoleId()));
	 * rolesTo.setFeatureList((List<RolePermissionsTo>) criteria.list());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return rolesTo; }
	 * 
	 * public void delete(RolesTo rolesTo) { Session session = null; String hql =
	 * null; try { session = sessionFactory.getCurrentSession();
	 * session.delete(rolesTo);
	 * 
	 * hql = "delete from RolePermissionsTo where roleId=" + rolesTo.getRoleId();
	 * session.createQuery(hql).executeUpdate();
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * @SuppressWarnings("unchecked") public List<RolesTo> getAllRoles() { Criteria
	 * criteria = null; Session session = null; List<RolesTo> rolesTo = new
	 * ArrayList<RolesTo>();
	 * 
	 * try { session = sessionFactory.getCurrentSession(); criteria =
	 * session.createCriteria(RolesTo.class); rolesTo = (List<RolesTo>)
	 * criteria.list(); } catch (Exception e) { e.printStackTrace(); } return
	 * rolesTo; }
	 */
}
