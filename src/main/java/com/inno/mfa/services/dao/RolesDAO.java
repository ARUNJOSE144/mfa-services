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

import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.FeatureMasterTo;
import com.inno.mfa.services.model.ModuleMasterTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.RolePermissionsTo;
import com.inno.mfa.services.model.RolesTo;
import com.inno.mfa.services.util.Util;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class RolesDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public PaginationTo<RolesTo> getRolesList(PaginationTo<RolesTo> paginationTo) {
		List<RolesTo> list = new ArrayList<RolesTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			if (paginationTo.getDataTotalSize() == 0) {
				paginationTo.setDataTotalSize(getRowCount(paginationTo, session));
			}

			Criteria criteria = session.createCriteria(RolesTo.class);
			if (Util.validate(paginationTo.getSearchKey1())) {
				criteria.add(Restrictions.ilike("roleName", "%" + paginationTo.getSearchKey1() + "%"));
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

	public Integer getRowCount(PaginationTo<RolesTo> paginationTo, Session session) {
		Long count = 0l;
		try {
			Criteria criteria = session.createCriteria(RolesTo.class);
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

	@SuppressWarnings("unchecked")
	public void getModulePermission(CommonRespTo<ModuleMasterTo> to) {
		List<ModuleMasterTo> masterTos = null;
		List<FeatureMasterTo> featureMasterTos = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria criteria = session.createCriteria(ModuleMasterTo.class);
			masterTos = (List<ModuleMasterTo>) criteria.list();

			criteria = session.createCriteria(FeatureMasterTo.class);
			featureMasterTos = (List<FeatureMasterTo>) criteria.list();

			for (ModuleMasterTo mto : masterTos) {
				List<FeatureMasterTo> list = new ArrayList<FeatureMasterTo>();
				for (FeatureMasterTo fto : featureMasterTos) {
					if (fto.getModuleId() == mto.getModuleId()) {
						list.add(fto);
					}
				}
				mto.setFeatures(list);

			}
			to.setList(masterTos);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void create(RolesTo rolesTo) {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(rolesTo);
			if (rolesTo.getFeatureIds().length > 0) {
				List<RolePermissionsTo> permissionList = new ArrayList<RolePermissionsTo>();
				for (int featureId : rolesTo.getFeatureIds()) {
					RolePermissionsTo to = new RolePermissionsTo();
					to.setFeatureId(featureId);
					to.setRoleId(rolesTo.getRoleId());
					permissionList.add(to);
					session.save(to);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public RolesTo view(RolesTo rolesTo) {
		Criteria criteria = null;
		Session session = null;

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(RolesTo.class);
			criteria.add(Restrictions.eq("roleId", rolesTo.getRoleId()));
			rolesTo = (RolesTo) criteria.uniqueResult();

			criteria = session.createCriteria(RolePermissionsTo.class);
			criteria.add(Restrictions.eq("roleId", rolesTo.getRoleId()));
			rolesTo.setFeatureList((List<RolePermissionsTo>) criteria.list());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rolesTo;
	}

	public void delete(RolesTo rolesTo) {
		Session session = null;
		String hql = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.delete(rolesTo);

			hql = "delete from RolePermissionsTo where roleId=" + rolesTo.getRoleId();
			session.createQuery(hql).executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("unchecked")
	public List<RolesTo> getAllRoles() {
		Criteria criteria = null;
		Session session = null;
		List<RolesTo> rolesTo = new ArrayList<RolesTo>();

		try {
			session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(RolesTo.class);
			rolesTo = (List<RolesTo>) criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rolesTo;
	}
}
