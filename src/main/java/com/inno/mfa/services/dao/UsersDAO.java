package com.inno.mfa.services.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.UserMasterTo;
import com.inno.mfa.services.util.Util;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class UsersDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public PaginationTo<UserMasterTo> getUsersList(PaginationTo<UserMasterTo> paginationTo) {
		List<UserMasterTo> list = new ArrayList<UserMasterTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(paginationTo, session));
			}

			Criteria criteria = session.createCriteria(UserMasterTo.class, "user");

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

	public Integer getRowCount(PaginationTo<UserMasterTo> paginationTo, Session session) {
		Long count = 0l;
		try {
			Criteria criteria = session.createCriteria(UserMasterTo.class);
			criteria.setProjection(Projections.rowCount());
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("name", "%" + paginationTo.getSearchKey1() + "%"));
			}
			count = (Long) criteria.uniqueResult();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return count.intValue();
	}

	public void create(UserMasterTo UserMasterTo) {

		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(UserMasterTo);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * @SuppressWarnings("unchecked") public UserMasterTo view(UserMasterTo
	 * UserMasterTo) {
	 * 
	 * Criteria criteria = null; Session session = null;
	 * 
	 * try { session = sessionFactory.getCurrentSession(); criteria =
	 * session.createCriteria(UserMasterTo.class);
	 * criteria.add(Restrictions.eq("roleId", UserMasterTo.getRoleId()));
	 * UserMasterTo = (UserMasterTo) criteria.uniqueResult();
	 * 
	 * criteria = session.createCriteria(RolePermissionsTo.class);
	 * criteria.add(Restrictions.eq("roleId", UserMasterTo.getRoleId()));
	 * UserMasterTo.setFeatureList((List<RolePermissionsTo>) criteria.list());
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } return UserMasterTo;
	 * 
	 * }
	 */
	public void delete(UserMasterTo UserMasterTo) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.delete(UserMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
