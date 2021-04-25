package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.axis.wsdl.symbolTable.Undefined;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.RoleDao;
import com.sixdee.magik.services.model.FeatureMaster;
import com.sixdee.magik.services.model.ModuleMaster;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.model.RolePermission;
import com.sixdee.magik.services.util.CommonStringUtils;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Repository
public class RoleDaoImpl implements RoleDao {
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private Query query = null;
	private String hql;
	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

	@Override
	public List<ModuleMaster> getRolePermissions() {
		// TODO Auto-generated method stub
		String query = "";
		List<ModuleMaster> moduleMasterList = new ArrayList<ModuleMaster>();
		List<ModuleMaster> list = new ArrayList<ModuleMaster>();
		List<FeatureMaster> featureList = null;
		try {

			Session session = sessionFactory.getCurrentSession();
			/*
			 * query = "from ModuleMaster"; moduleMasterList = (List<ModuleMaster>)
			 * session.createQuery(query).list();
			 */

			/*
			 * query = "from FeatureMaster"; featureList = (List<FeatureMaster>)
			 * session.createQuery(query).list();
			 */

			Criteria cr_mod = session.createCriteria(ModuleMaster.class);
			cr_mod.addOrder(Order.asc("moduleId"));
			moduleMasterList = cr_mod.list();

			Criteria cr_ftr = session.createCriteria(FeatureMaster.class);
			featureList = cr_ftr.list();

			for (ModuleMaster dto : moduleMasterList) {
				dto.setFeatureList(getFeatureMasterArray(featureList, dto.getModuleId()));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return moduleMasterList;
	}

	public List<FeatureMaster> getFeatureMasterArray(List<FeatureMaster> featureList, int moduleId) {
		List<FeatureMaster> result = new ArrayList<FeatureMaster>();
		for (FeatureMaster dto : featureList) {
			if (moduleId == dto.getModuleId()) {
				result.add(dto);
			}
		}
		return result;

	}

	@Override
	public void saveRole(RoleMaster roleMaster) {

		String queryStr = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			if (roleMaster.getRoleId() != 0) {
				queryStr = "DELETE FROM RolePermission WHERE roleId=:roleId";
				query = session.createQuery(queryStr);
				query.setParameter("roleId", roleMaster.getRoleId());
				int affectedRs = query.executeUpdate();
			}

			if (roleMaster.getRoleId() == 0) {
				roleMaster.setCreateDate(new Date());
				session.saveOrUpdate(roleMaster);
			} else {
				session.saveOrUpdate(roleMaster);
			}

			for (RolePermission to : roleMaster.getPermissions()) {
				to.setRoleId(roleMaster.getRoleId());
				session.save(to);
			}
		} catch (HibernateException e) {
			if (e instanceof ConstraintViolationException) {
				CommonStringUtils.roleMaster_COnstraintException = true;
				// ConstraintViolationException ce = (ConstraintViolationException) e;
				// String constraintName = ce.getConstraintName();
				// System.err.println(constraintName);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public RoleMaster viewRole(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		Criteria criteria = null;
		RoleMaster toMaster = null;
		List<RolePermission> rolePermission = null;
		List<FeatureMaster> FeatureMaster = null;
		List<FeatureMaster> rolePermissions = new ArrayList<FeatureMaster>();
		String query = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			criteria = session.createCriteria(RoleMaster.class);
			criteria.add(Restrictions.eq("roleId", roleMaster.getRoleId()));
			toMaster = (RoleMaster) criteria.uniqueResult();

			if (toMaster != null) {
				query = "from RolePermission where roleId=" + roleMaster.getRoleId();
				rolePermission = (List<RolePermission>) session.createQuery(query).list();

				List<FeatureMaster> featureList = null;
				query = "from FeatureMaster";
				featureList = (List<FeatureMaster>) session.createQuery(query).list();

				for (RolePermission to : rolePermission) {
					for (FeatureMaster featureTo : featureList) {
						if (to.getFeatureId() == featureTo.getFeatureId()) {
							rolePermissions.add(featureTo);
						}
					}
				}
				toMaster.setFeatures(rolePermissions);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return toMaster;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<RoleMaster> getRoleList() {
//		String query = null;
//		List<RoleMaster> list = null;
//		try {
//			Session session = sessionFactory.getCurrentSession();
//			query = "from RoleMaster";
//			list = (List<RoleMaster>) session.createQuery(query).list();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		List<RoleMaster> roleList = new ArrayList<RoleMaster>();
		RoleMaster mainTO = null;
		Session session = sessionFactory.getCurrentSession();
		Criteria criteria = null;

		criteria = session.createCriteria(RoleMaster.class);

		List<RoleMaster> roleInfoList = criteria.list();

		if (!roleInfoList.isEmpty()) {
			for (RoleMaster roleTO : roleInfoList) {
				mainTO = new RoleMaster();
				mainTO.setRoleId(roleTO.getRoleId());
				mainTO.setRoleName(roleTO.getRoleName());
				mainTO.setCreateDateUI(simpleDateFormat.format(roleTO.getCreateDate()));
				mainTO.setDescription(roleTO.getDescription());
				roleList.add(mainTO);
			}
		}
		return roleList;

	}

	@Override
	public RoleMaster deleteRole(RoleMaster roleMaster) {
		String queryStr = null;
		try {
			Session session = sessionFactory.getCurrentSession();

			queryStr = "DELETE FROM RolePermission WHERE roleId=:roleId";
			query = session.createQuery(queryStr);
			query.setParameter("roleId", roleMaster.getRoleId());
			int affectedRs = query.executeUpdate();

			session.delete(roleMaster);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleMaster;
	}

	public int getRowCount(Session session, PaginationDTO<RoleMaster> paginationDTO) {
		System.out.println("Class : ConfigureUrlDAOImpl | Method : getUrlInfo");
		String sql = null;
		int rowCount = 0;
		try {
			sql = "SELECT COUNT(*)FROM RoleMaster ";

			if (paginationDTO.getSearchKey1() != null && !paginationDTO.getSearchKey1().equalsIgnoreCase(""))
				sql += "where roleName LIKE '" + paginationDTO.getSearchKey1() + "%'";
			System.out.println("SQL Query : " + sql);
			query = session.createQuery(sql);
			List<Long> count = query.list();
			rowCount = Integer.parseInt(count.get(0).toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rowCount;
	}

	@Override
	public PaginationDTO<RoleMaster> getRoleListPagination(PaginationDTO<RoleMaster> paginationDTO) {
		// getting RowCount
		String sql = "";
		List<RoleMaster> roleList = null;
		Session session = sessionFactory.getCurrentSession();
		try {
			if (paginationDTO.getDataTotalSize() == 0)
				paginationDTO.setDataTotalSize(getRowCount(session, paginationDTO));

			sql = "FROM RoleMaster ";
			System.out.println("pagination To  : " + paginationDTO);
			if (paginationDTO.getSearchKey1() != null && !paginationDTO.getSearchKey1().equalsIgnoreCase(""))
				sql += "where roleName LIKE '" + paginationDTO.getSearchKey1() + "%'";

			if (validate(paginationDTO.getSortKey1())) {
				sql += " order by " + paginationDTO.getSortKey1();
			} else {
				sql += " order by roleId DESC";
			}

			System.out.println("SQL Query : " + sql);

			query = session.createQuery(sql);
			query.setMaxResults(paginationDTO.getRecordCount());
			query.setFirstResult(paginationDTO.getFirstRecord());
			roleList = (List<RoleMaster>) query.list();
			paginationDTO.setData(roleList);
			System.out.println("role"+roleList);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return paginationDTO;
	}

	boolean validate(String val) {
		if (val != null && !val.equalsIgnoreCase("Undefined") && !val.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}

}
