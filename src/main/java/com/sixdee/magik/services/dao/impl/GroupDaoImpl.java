package com.sixdee.magik.services.dao.impl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionImpl;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.GroupDao;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.DataTypeTO;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GroupTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.model.SubGroupTO;
import com.sixdee.magik.services.util.DateFormat;

import oracle.net.aso.k;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Repository
public class GroupDaoImpl implements GroupDao {

	DateFormat dateFormat = new DateFormat();

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	private Session session = null;
	private String hql = null;
	String idText;
	String nameText;
	String idText1;
	String nameText1;

	String idText2;
	String nameText2;

	String idText3;
	String nameText3;

	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	/*
	 * Add Group
	 */
	@Override
	public void addGroup(GroupTO to) {
		session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(to);
	}

	/*
	 * Get Groups
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<GroupTO> getGroups() {
		session = sessionFactory.getCurrentSession();
		List<GroupTO> list = new ArrayList<GroupTO>();
		List<Object[]> rs = null;
		GroupTO to = null;

		hql = "SELECT id, name, createdBy, createDate, updateDate FROM GroupTO order by id desc";

		rs = (List<Object[]>) session.createQuery(hql).list();

		for (Object[] obj : rs) {
			to = new GroupTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setCreatedBy(obj[2] + "");
			to.setCreateDate(dateFormat.strDate(obj[3] + ""));
			to.setUpdateDate(dateFormat.strDate(obj[4] + ""));

			list.add(to);
		}

		return list;
	}

	/*
	 * Add Sub Group
	 */
	@Override
	public void addSubGroup(SubGroupTO to) {
		session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(to);

		hql = "INSERT INTO RE_GROUP_SUBGROUP_MAPING (GROUP_ID, SUB_GROUP_ID) VALUES (?,?)";

		session.createSQLQuery(hql).setParameter(0, to.getGroupId()).setParameter(1, to.getId()).executeUpdate();
	}

	/*
	 * Add Sub Groups
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<SubGroupTO> getSubGroups(int groupId) {
		System.out.println(" 2 ");
		session = sessionFactory.getCurrentSession();
		List<SubGroupTO> list = new ArrayList<SubGroupTO>();
		List<Object[]> rs = null;
		SubGroupTO to = null;

		hql = "SELECT ID, NAME, CREATED_BY, CREATE_DATE, UPDATE_DATE FROM RE_SUB_GROUPS ";

		if (groupId != -1) {
			hql += "WHERE ID IN (SELECT SUB_GROUP_ID FROM RE_GROUP_SUBGROUP_MAPING WHERE GROUP_ID = " + groupId + ")"
					+ " order by ID desc";
		}

		rs = (List<Object[]>) session.createSQLQuery(hql).list();

		for (Object[] obj : rs) {
			to = new SubGroupTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setCreatedBy(obj[2] + "");
			to.setCreateDate(dateFormat.strDate(obj[3] + ""));
			to.setUpdateDate(dateFormat.strDate(obj[4] + ""));

			list.add(to);
		}

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.GroupDao#addGenericGroups(com.sixdee.magik.
	 * services.model.GenericGroupDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public Integer addGenericGroups(GenericGroupDTO genericGroupDTO) throws Exception {
		Session session = null;
		Connection connection = null;
		Integer groupId = 0;
		try {
			session = sessionFactory.getCurrentSession();
			connection = ((SessionImpl) session).connection();
			connection.setAutoCommit(false);
			if (genericGroupDTO != null) {
				if (genericGroupDTO.getMode().equalsIgnoreCase("group")) {
					groupId = addREGroups(genericGroupDTO, connection);
				} else if (genericGroupDTO.getMode().equalsIgnoreCase("subGroup")) {
					Integer subGroupId = addRESubGroups(genericGroupDTO, connection);
					addGroupSubGroupMapping(genericGroupDTO, connection, genericGroupDTO.getGroupId(), subGroupId);
				} else if (genericGroupDTO.getMode().equalsIgnoreCase("kpis")) {
					addREBussinessKPI(genericGroupDTO, connection, genericGroupDTO.getGroupId(),
							genericGroupDTO.getSubGroupId());
				} else if (genericGroupDTO.getMode().equalsIgnoreCase("all")) {
					Integer subGroupId = genericGroupDTO.getSubGroupId();
					groupId = genericGroupDTO.getGroupId();
					if (genericGroupDTO.getGroupId() == null && genericGroupDTO.getGroupName() != null) {
						groupId = addREGroups(genericGroupDTO, connection);
					}
					if (genericGroupDTO.getSubGroupId() == null && genericGroupDTO.getSubGroupName() != null) {
						subGroupId = addRESubGroups(genericGroupDTO, connection);
					}
					if (subGroupId != null && groupId != null) {
						addGroupSubGroupMapping(genericGroupDTO, connection, groupId, subGroupId);
						addREBussinessKPI(genericGroupDTO, connection, groupId, subGroupId);
					}
				}
			}
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
			throw new Exception();
		}
		return groupId;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Integer addREGroups(GenericGroupDTO genericGroupDTO, Connection connection) throws Exception {
		String query = null;
		Integer id = 0;
		System.out.println(" gropos ");
		try {
			query = "INSERT INTO RE_GROUPS (NAME, CREATED_BY) VALUES (?,?)";
			// PreparedStatement statement = connection.prepareStatement(query,
			// Statement.RETURN_GENERATED_KEYS);
			String generatedColumns[] = { "ID" };
			PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
			statement.setString(1, genericGroupDTO.getGroupName());
			statement.setString(2, genericGroupDTO.getCreatedBy());
			int affectedRows = statement.executeUpdate();

			System.out.println(" inside if");
			System.out.println(" inside if 22 " + genericGroupDTO.getGroupName());
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Group");
			audtoTO.setAddedName(genericGroupDTO.getGroupName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			String a = "Created";
			String b = genericGroupDTO.getGroupName().concat(a);
			audtoTO.setDesc(b);

			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				} else {
					throw new Exception("Creating user failed, no ID obtained.");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Integer addRESubGroups(GenericGroupDTO genericGroupDTO, Connection connection) throws Exception {
		String query = null;
		Integer id = 0;
		System.out.println(" test7");
		try {
			query = "INSERT INTO RE_SUB_GROUPS (NAME, CREATED_BY) VALUES (?,?)";
			// PreparedStatement statement = connection.prepareStatement(query,
			// Statement.RETURN_GENERATED_KEYS);
			String generatedColumns[] = { "ID" };
			PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
			statement.setString(1, genericGroupDTO.getSubGroupName());
			statement.setString(2, genericGroupDTO.getCreatedBy());
			int affectedRows = statement.executeUpdate();

			System.out.println(" inside if");
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("SubGroup");
			audtoTO.setAddedName(genericGroupDTO.getSubGroupName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");
			String a = "Created";
			String b = genericGroupDTO.getSubGroupName().concat(a);
			audtoTO.setDesc(b);

			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					id = generatedKeys.getInt(1);
				} else {
					throw new Exception("Creating user failed, no ID obtained.");
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return id;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void addGroupSubGroupMapping(GenericGroupDTO genericGroupDTO, Connection connection, Integer groupId,
			Integer subgroupId) throws Exception {
		String query = null;
		try {
			query = "INSERT INTO RE_GROUP_SUBGROUP_MAPING (GROUP_ID, SUB_GROUP_ID) VALUES (?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, groupId);
			statement.setInt(2, subgroupId);
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void addREBussinessKPI(GenericGroupDTO genericGroupDTO, Connection connection, Integer groupId,
			Integer subgroupId) throws Exception {
		System.out.println(" kp");
		String query = null;

		try {
			session = sessionFactory.getCurrentSession();
			/*
			 * query =
			 * "INSERT INTO RE_BUSINESS_KPIS (NAME, PROFILE_NAME,GROUP_ID,SUB_GROUP_ID,DATA_TYPE,CREATED_BY,SELECT_VALUE) VALUES (?,?,?,?,?,?,?)"
			 * ; PreparedStatement statement = connection.prepareStatement(query);
			 * statement.setString(1, genericGroupDTO.getEnglishName());
			 * statement.setString(2, genericGroupDTO.getBlSubGroupName());
			 * statement.setInt(3, groupId); statement.setInt(4, subgroupId);
			 * statement.setInt(5, genericGroupDTO.getDataTypeId()); statement.setString(6,
			 * genericGroupDTO.getCreatedBy()); if (genericGroupDTO.getSelectValue() == "")
			 * { System.out.println(" value"); statement.setString(7, ""); } else {
			 * System.out.println(" else "); statement.setString(7,
			 * genericGroupDTO.getSelectValue()); } int affectedRows =
			 * statement.executeUpdate(); if (affectedRows == 0) { throw new
			 * Exception("Creating failed, no rows affected."); }
			 * System.out.println("subgroupId  ::  " + subgroupId);
			 * System.out.println("groupId ::  " + groupId);
			 */
			// if (genericGroupDTO.getGroupId() != 0) {

			KPITO kpito = new KPITO();
			kpito.setName(genericGroupDTO.getEnglishName());
			kpito.setProfileName(genericGroupDTO.getBlSubGroupName());
			kpito.setGroupId(groupId);
			kpito.setSubGroupId(subgroupId);
			kpito.setDataTypeId(genericGroupDTO.getDataTypeId());
			kpito.setCretedBy(genericGroupDTO.getCreatedBy());
			kpito.setValues(genericGroupDTO.getSelectValue());
			kpito.setCretedDate(new Date());
			kpito.setLastUpdatedDate(new Date());

			session.save(kpito);

			if (groupId != 0) {
				System.out.println(" inside if");
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("Segment Variable");
				audtoTO.setAddedName(genericGroupDTO.getEnglishName());
				audtoTO.setActionType("CREATE");
				audtoTO.setAttribute("N/A");
				audtoTO.setPreviousValue("N/A");
				audtoTO.setNewValue("N/A");

				String a = "Created";
				String b = genericGroupDTO.getEnglishName().concat(a);
				audtoTO.setDesc(b);

				audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
				audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

				auditInfo.addAuditLog(audtoTO);
				System.out.println("-----------------------Lead  Policy Successfully-----------------");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public List<SubGroupTO> getAllSubGroups() {
		// Getting all the services for the Redux
		// TODO Auto-generated method stub
		System.out.println("  12 ");
		session = sessionFactory.getCurrentSession();
		List<SubGroupTO> list = new ArrayList<SubGroupTO>();
		List<Object[]> rs = null;
		SubGroupTO to = null;

		hql = "SELECT a.ID, a.NAME, a.CREATED_BY,b.GROUP_ID FROM RE_SUB_GROUPS a,RE_GROUP_SUBGROUP_MAPING b  ";

		hql += " where a.ID=b.SUB_GROUP_ID     ";

		rs = (List<Object[]>) session.createSQLQuery(hql).list();

		for (Object[] obj : rs) {
			to = new SubGroupTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setCreatedBy(obj[2] + "");
			to.setGroupId(Integer.parseInt(obj[3] + ""));

			list.add(to);
		}

		return list;
	}

	@Override
	public void editGroups(GenericGroupDTO genericGroupDTO) throws Exception {

		Session session = null;
		String query = null;
		try {
			session = sessionFactory.getCurrentSession();

			String hql = "select ID,NAME from RE_GROUPS where ID= '" + genericGroupDTO.getGroupId() + "'  ";

			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj1 : rs) {
				idText1 = obj1[0].toString();
				nameText1 = obj1[1].toString();

			}
			System.out.println(" nameText" + nameText);

			query = "UPDATE RE_GROUPS SET NAME=:name WHERE ID=:id";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("name", genericGroupDTO.getGroupName());
			sqlQuery.setParameter("id", genericGroupDTO.getGroupId());
			int affectedRows = sqlQuery.executeUpdate();

			System.out.println(" genericGroupDTO.getGroupName() 00  " + genericGroupDTO.getGroupName());
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Group");
			audtoTO.setAddedName(genericGroupDTO.getGroupName());
			audtoTO.setActionType("MODIFY");

			String a = "Modified";
			String b = genericGroupDTO.getGroupName().concat(a);
			audtoTO.setDesc(b);
			if (nameText1 != null && !nameText1.equalsIgnoreCase(genericGroupDTO.getGroupName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("Group Name");
				audtoTO.setPreviousValue(nameText1);
				audtoTO.setNewValue(genericGroupDTO.getGroupName());
			}
			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void editSubGroups(GenericGroupDTO genericGroupDTO) throws Exception {

		Session session = null;
		String query = null;
		System.out.println(" variable edit ");
		try {
			session = sessionFactory.getCurrentSession();

			String hql = "select ID,NAME from RE_SUB_GROUPS where ID= '" + genericGroupDTO.getSubGroupId() + "'  ";

			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				idText3 = obj[0].toString();
				nameText3 = obj[1].toString();

			}
			System.out.println("nameText3 " + nameText3);

			query = "UPDATE RE_SUB_GROUPS SET NAME=:name WHERE ID=:id";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("name", genericGroupDTO.getSubGroupName());
			sqlQuery.setParameter("id", genericGroupDTO.getSubGroupId());
			int affectedRows = sqlQuery.executeUpdate();

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("SubGroup");
			audtoTO.setAddedName(genericGroupDTO.getSubGroupName());
			audtoTO.setActionType("MODIFY");

			String a = "Modified";
			String b = genericGroupDTO.getSubGroupName().concat(a);
			audtoTO.setDesc(b);
			if (nameText3 != null && !nameText3.equalsIgnoreCase(genericGroupDTO.getSubGroupName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("SubGroup Name");
				audtoTO.setPreviousValue(nameText3);
				audtoTO.setNewValue(genericGroupDTO.getSubGroupName());
			}

			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public void deleteGroups(GenericGroupDTO genericGroupDTO) throws Exception {

		Connection connection = null;
		Session session = null;
		String query = null;
		SQLQuery sqlQuery = null;
		try {
			session = sessionFactory.getCurrentSession();
			connection = ((SessionImpl) session).connection();
			connection.setAutoCommit(false);
			sqlQuery = session.createSQLQuery("SELECT SUB_GROUP_ID FROM RE_GROUP_SUBGROUP_MAPING WHERE GROUP_ID=:id");
			sqlQuery.setParameter("id", genericGroupDTO.getGroupId());
			List<BigDecimal> obj = sqlQuery.list();
			System.out.println(" genericGroupDTO.getGroupId()" + genericGroupDTO.getGroupId());

			String hql = "select ID,NAME from RE_GROUPS where ID= '" + genericGroupDTO.getGroupId() + "'  ";

			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj1 : rs) {
				// int idValues = (Integer) obj1[0];

				String id = obj1[0].toString();
				int idValues = Integer.parseInt(id);
				idText = obj1[0].toString();
				nameText = obj1[1].toString();

			}
			System.out.println(" nameText" + nameText);

			if (obj != null) {
				for (BigDecimal val : (obj)) {

					session = sessionFactory.getCurrentSession();
					String hql2 = "delete from SubGroupTO where id = " + val;
					session.createQuery(hql2).executeUpdate();
				}
			}

			if (obj != null && obj.size() > 0) {
				session = sessionFactory.getCurrentSession();
				String hql1 = "delete from SubGroupMappingTO where groupId = " + genericGroupDTO.getGroupId()
						+ "and subGroupId=" + genericGroupDTO.getSubGroupId();
				session.createQuery(hql1).executeUpdate();
			}

			session = sessionFactory.getCurrentSession();
			String hql3 = "delete from BussinessKpiTO where groupId = " + genericGroupDTO.getGroupId();
			session.createQuery(hql3).executeUpdate();

			session = sessionFactory.getCurrentSession();
			String hql4 = "delete from GroupTO where Id = " + genericGroupDTO.getGroupId();
			session.createQuery(hql4).executeUpdate();

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Group");
			audtoTO.setAddedName(nameText);
			audtoTO.setActionType("DELETE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a = "Deleted";
			String b = nameText.concat(a);
			audtoTO.setDesc(b);

			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

//			if (affectedRs == 0) {
//				throw new Exception("Creating failed, no rows affected.");
//			}
//			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
			throw new Exception();
		}

	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public void deleteSubGroups(GenericGroupDTO genericGroupDTO) throws Exception {

		Connection connection = null;
		Session session = null;
		String query = null;
		SQLQuery sqlQuery = null;
		try {
			session = sessionFactory.getCurrentSession();
			connection = ((SessionImpl) session).connection();
			connection.setAutoCommit(false);
			System.out.println(" sub");

			String hql = "select ID,NAME from RE_SUB_GROUPS where ID= '" + genericGroupDTO.getSubGroupId() + "'  ";

			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("genericGroupDTO.getSubGroupId()  " + genericGroupDTO.getSubGroupId());

			for (Object[] obj1 : rs) {
				// int idValues = (Integer) obj1[0];

				String id = obj1[0].toString();
				int idValues = Integer.parseInt(id);

				idText2 = obj1[0].toString();
				nameText2 = obj1[1].toString();

			}

			session = sessionFactory.getCurrentSession();
			String hql1 = "delete from SubGroupMappingTO where groupId = " + genericGroupDTO.getGroupId()
					+ "and subGroupId=" + genericGroupDTO.getSubGroupId();
			session.createQuery(hql1).executeUpdate();
			String hql2 = "delete from SubGroupTO where id = " + genericGroupDTO.getSubGroupId();
			session.createQuery(hql2).executeUpdate();
			String hql3 = "delete from BussinessKpiTO where groupId = " + genericGroupDTO.getGroupId()
					+ "and subGroupId =" + genericGroupDTO.getSubGroupId();
			session.createQuery(hql3).executeUpdate();

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("SubGroup");
			audtoTO.setAddedName(nameText2);
			audtoTO.setActionType("DELETE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a = "Deleted";
			String b = nameText2.concat(a);
			audtoTO.setDesc(b);
			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
			throw new Exception();
		}

	}

	@Override
	public List<KPITO> getVariableForGroups(int groupId, int subGroupId) {
		Session session = null;
		String query = null;
		KPITO kpiDto = null;
		List<KPITO> kipDtoList = null;
		try {
			session = sessionFactory.getCurrentSession();
			if (groupId != 0 && subGroupId != 0) {
				query = "SELECT businsessKPI.ID as id, businsessKPI.NAME as englishName, businsessKPI.PROFILE_NAME as profileName, businsessKPI.GROUP_ID as groupId,businsessKPI.SUB_GROUP_ID as subGroupId,D.NAME as dataTypeName,businsessKPI.SELECT_VALUE as selectValue FROM RE_BUSINESS_KPIS businsessKPI LEFT JOIN RE_GROUPS T ON T.ID = businsessKPI.GROUP_ID "
						+ "LEFT JOIN RE_SUB_GROUPS A ON A.ID = businsessKPI.SUB_GROUP_ID LEFT JOIN RE_DATA_TYPES D ON D.ID = businsessKPI.DATA_TYPE WHERE businsessKPI.GROUP_ID = "
						+ groupId + " AND businsessKPI.SUB_GROUP_ID = " + subGroupId + "";
			} else {
				query = "SELECT businsessKPI.ID as id, businsessKPI.NAME as englishName, businsessKPI.PROFILE_NAME as profileName, businsessKPI.GROUP_ID as groupId,businsessKPI.SUB_GROUP_ID as subGroupId,D.NAME as dataTypeName,businsessKPI.SELECT_VALUE as selectValue FROM RE_BUSINESS_KPIS businsessKPI LEFT JOIN RE_GROUPS T ON T.ID = businsessKPI.GROUP_ID "
						+ "LEFT JOIN RE_SUB_GROUPS A ON A.ID = businsessKPI.SUB_GROUP_ID LEFT JOIN RE_DATA_TYPES D ON D.ID = businsessKPI.DATA_TYPE WHERE businsessKPI.GROUP_ID = "
						+ groupId + "";
			}
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("englishName", StringType.INSTANCE);
			sqlQuery.addScalar("profileName", StringType.INSTANCE);
			sqlQuery.addScalar("groupId", IntegerType.INSTANCE);
			sqlQuery.addScalar("subGroupId", IntegerType.INSTANCE);
			sqlQuery.addScalar("dataTypeName", StringType.INSTANCE);
			sqlQuery.addScalar("selectValue", StringType.INSTANCE);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				kipDtoList = new ArrayList<KPITO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null && aRow[2] != null && aRow[3] != null
							&& aRow[4] != null && aRow[5] != null) {
						kpiDto = new KPITO();
						kpiDto.setId((Integer) aRow[0]);
						kpiDto.setName((String) aRow[1]);
						kpiDto.setSubGroupName((String) aRow[2]);
						kpiDto.setGroupId((Integer) aRow[3]);
						kpiDto.setSubGroupId((Integer) aRow[4]);
						kpiDto.setDataType((String) aRow[5]);
						if (aRow[6] != null) {
							kpiDto.setCretedBy((String) aRow[6]);
						}
						kipDtoList.add(kpiDto);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kipDtoList;
	}
}
