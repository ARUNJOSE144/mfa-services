/**
 * 
 */
package com.sixdee.magik.services.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

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

import com.sixdee.magik.services.dao.ActionDao;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.model.ActionDefaultParamsTO;
import com.sixdee.magik.services.model.ActionGroupTO;
import com.sixdee.magik.services.model.ActionHeaderParameterTo;
import com.sixdee.magik.services.model.ActionParameterTO;
import com.sixdee.magik.services.model.ActionTO;
import com.sixdee.magik.services.model.ActionsDTO;
import com.sixdee.magik.services.model.AuditInfoTO;

/**
 * @author Vinay Kariyappa
 *
 *         Nov 23, 2018
 */
@Repository
public class ActionDaoImpl implements ActionDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	int idValue;
	int actionidValue;
	String nameValue;
	String paramValue;

	int idValue1;
	int actionidValue1;
	String nameValue1;
	String display1;
	String paramValue1;
	String datatypeValue;

	int idValue2;
	String nameValue2;

	@Override
	public List<ActionsDTO> getAllActionGroups() throws Exception {

		Session session = null;
		String query = null;
		ActionsDTO actionsDTO = null;
		List<ActionsDTO> actionsDTOList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT GROUP_ID as groupId, GROUP_NAME as groupName FROM RE_ACTION_GROUP order by GROUP_ID desc";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("groupId", IntegerType.INSTANCE);
			sqlQuery.addScalar("groupName", StringType.INSTANCE);

			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				actionsDTOList = new ArrayList<ActionsDTO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null) {
						actionsDTO = new ActionsDTO();
						actionsDTO.setId((Integer) aRow[0]);
						actionsDTO.setName((String) aRow[1]);
						actionsDTOList.add(actionsDTO);
					}
				}
			}

		} catch (Exception e) {
			throw e;
		}
		return actionsDTOList;
	}

	@Override
	public List<ActionsDTO> getSubActions(int groupId) throws Exception {

		Session session = null;
		String query = null;
		ActionsDTO actionsDTO = null;
		List<ActionsDTO> actionsDTOList = null;

		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT ID as id, NAME as name, ACTION_TYPE as act FROM RE_ACTION_INFO WHERE GROUP_ID = :groupId order  by ID desc";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("name", StringType.INSTANCE);
			sqlQuery.addScalar("act", StringType.INSTANCE);
			sqlQuery.setParameter("groupId", groupId);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				actionsDTOList = new ArrayList<ActionsDTO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null) {
						actionsDTO = new ActionsDTO();
						actionsDTO.setId((Integer) aRow[0]);
						actionsDTO.setName((String) aRow[1]);
						actionsDTO.setActionType(aRow[2] != null ? (String) aRow[2] : null);
						actionsDTOList.add(actionsDTO);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return actionsDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#addHeaderParameter(com.sixdee.magik.
	 * services.model.ActionsDTO)
	 */
	@Override
	public void addHeaderParameter(ActionsDTO actionsDTO) throws Exception {

		Session session = null;
		String query = null;
		System.out.println(" id 11  " + actionsDTO.getId());
		System.out.println(" paramname  " + actionsDTO.getParamName());
		session = sessionFactory.getCurrentSession();
		try {

			if (actionsDTO.getId() != 0) {
				System.out.println(" inside if");
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("Action Header Parameter");
				audtoTO.setAddedName(actionsDTO.getParamName());
				audtoTO.setActionType("CREATE");
				audtoTO.setAttribute("N/A");
				audtoTO.setPreviousValue("N/A");
				audtoTO.setNewValue("N/A");

				String a1 = "Created";
				String b1 = actionsDTO.getParamName().concat(a1);

				audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
				audtoTO.setRoleName(actionsDTO.getAuditRoleName());

				audtoTO.setDesc(b1);
				auditInfo.addAuditLog(audtoTO);
				System.out.println("-----------------------Lead  Policy Successfully-----------------");
			}

			query = "INSERT INTO RE_ACTION_HEADER_PARAMETERS (ACTION_ID, PARAMETER_NAME,PARAMETER_VALUE) VALUES (?,?,?)";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter(0, actionsDTO.getId());
			sqlQuery.setParameter(1, actionsDTO.getParamName());
			sqlQuery.setParameter(2, actionsDTO.getParamValue());
			int affectedRows = sqlQuery.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#addNormalParameter(com.sixdee.magik.
	 * services.model.ActionsDTO)
	 */
	@Override
	public void addNormalParameter(ActionsDTO actionsDTO) throws Exception {

		Session session = null;
		String query = null;
		System.out.println(" actionsDTO.getId() " + actionsDTO.getId());
		System.out.println(" actionsDTO.getActionId() " + actionsDTO.getActionId());
		System.out.println(" actionsDTO.getGroupId() " + actionsDTO.getGroupId());
		System.out.println(" actionsDTO.getNormalParamName() " + actionsDTO.getNormalParamName());
		System.out.println(" actionsDTO.getDisplayName() " + actionsDTO.getDisplayName());
		try {
			session = sessionFactory.getCurrentSession();

			if (actionsDTO.getActionId() != 0) {
				System.out.println(" inside if");
				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("Action Normal Parameter");
				audtoTO.setAddedName(actionsDTO.getDisplayName());
				audtoTO.setActionType("CREATE");
				audtoTO.setAttribute("N/A");
				audtoTO.setPreviousValue("N/A");
				audtoTO.setNewValue("N/A");

				String a1 = "Created";
				String b1 = actionsDTO.getDisplayName().concat(a1);
				audtoTO.setDesc(b1);

				audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
				audtoTO.setRoleName(actionsDTO.getAuditRoleName());

				auditInfo.addAuditLog(audtoTO);
				System.out.println("-----------------------Lead  Policy Successfully-----------------");
			}

			query = "INSERT INTO RE_ACTION_PARAMETERS (ACTION_ID, PARAMETER_NAME,DISPLAY_NAME,PARAMETER_VALUE,DATA_TYPE,VISIBILITY,MANDATORY) VALUES (?,?,?,?,?,?,?)";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter(0, actionsDTO.getActionId());
			sqlQuery.setParameter(1, actionsDTO.getParamName());
			sqlQuery.setParameter(2, actionsDTO.getDisplayName());
			sqlQuery.setParameter(3, actionsDTO.getParamValue());
			sqlQuery.setParameter(4, actionsDTO.getDataType());
			sqlQuery.setParameter(5, actionsDTO.getVisibility());
			sqlQuery.setParameter(6, actionsDTO.getMandatory());
			int affectedRows = sqlQuery.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#getHeaderParameter(com.sixdee.magik.
	 * services.model.ActionsDTO)
	 */
	@Override
	public List<ActionsDTO> getHeaderParameter(int actionId) throws Exception {

		Session session = null;
		String query = null;
		ActionsDTO actionsDTO = null;
		List<ActionsDTO> actionsDTOList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT ID as id, PARAMETER_NAME as name, PARAMETER_VALUE as value, ACTION_ID as actionId FROM RE_ACTION_HEADER_PARAMETERS order by ID desc";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("name", StringType.INSTANCE);
			sqlQuery.addScalar("value", StringType.INSTANCE);
			sqlQuery.addScalar("actionId", IntegerType.INSTANCE);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				actionsDTOList = new ArrayList<ActionsDTO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null) {
						actionsDTO = new ActionsDTO();
						actionsDTO.setId((Integer) aRow[0]);
						actionsDTO.setParamName((String) aRow[1]);
						actionsDTO.setParamValue(aRow[2] != null ? (String) aRow[2] : null);
						actionsDTO.setActionId((Integer) aRow[3]);
						actionsDTOList.add(actionsDTO);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return actionsDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#getNormalParameter(com.sixdee.magik.
	 * services.model.ActionsDTO)
	 */
	@Override
	public List<ActionsDTO> getNormalParameter(int actionId) throws Exception {
		// TODO Auto-generated method stub
		Session session = null;
		String query = null;
		ActionsDTO actionsDTO = null;
		List<ActionsDTO> actionsDTOList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT ID as id, DATA_TYPE as dataType, VISIBILITY as vis, MANDATORY as man, DISPLAY_NAME as disName, PARAMETER_NAME as name, PARAMETER_VALUE as value, ACTION_ID as actionId FROM RE_ACTION_PARAMETERS order by ID desc";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("dataType", StringType.INSTANCE);
			sqlQuery.addScalar("vis", IntegerType.INSTANCE);
			sqlQuery.addScalar("man", IntegerType.INSTANCE);
			sqlQuery.addScalar("disName", StringType.INSTANCE);
			sqlQuery.addScalar("name", StringType.INSTANCE);
			sqlQuery.addScalar("value", StringType.INSTANCE);
			sqlQuery.addScalar("actionId", IntegerType.INSTANCE);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				actionsDTOList = new ArrayList<ActionsDTO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null && aRow[2] != null && aRow[3] != null
							&& aRow[4] != null && aRow[5] != null && aRow[6] != null && aRow[7] != null) {
						actionsDTO = new ActionsDTO();
						actionsDTO.setId((Integer) aRow[0]);
						actionsDTO.setDataType((String) aRow[1]);
						// actionsDTO.setVisibility((String) aRow[2]);

						String visi = aRow[2].toString();
						int visiint = Integer.parseInt(visi);
						if (visiint == 1) {
							aRow[2] = "Yes";
							actionsDTO.setVisibility((String) aRow[2]);

						} else {
							aRow[2] = "No";
							actionsDTO.setVisibility((String) aRow[2]);
						}

						String mana = aRow[3].toString();
						int manaint = Integer.parseInt(mana);
						if (manaint == 1) {
							aRow[3] = "Yes";
							actionsDTO.setMandatory((String) aRow[3]);

						} else {
							aRow[3] = "No";
							actionsDTO.setMandatory((String) aRow[3]);
						}

						// actionsDTO.setMandatory((String) aRow[3]);
						actionsDTO.setDisplayName((String) aRow[4]);
						actionsDTO.setParamName((String) aRow[5]);
						actionsDTO.setParamValue(aRow[6] != null ? (String) aRow[6] : null);
						actionsDTO.setActionId((Integer) aRow[7]);
						actionsDTOList.add(actionsDTO);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		System.out.println(" actionsDTOList ::: " + actionsDTOList.toString());
		return actionsDTOList;

	}

	@Override
	public List<ActionTO> getActionsList() {
		// TODO Auto-generated method stub
		Session session = null;
		String query = null;
		session = sessionFactory.getCurrentSession();

		query = " from ActionTO order by id desc ";
		List<ActionTO> list = (List<ActionTO>) session.createQuery(query).list();
		if (!list.isEmpty()) {
			LinkedHashMap<Integer, String> actionGroupMap = getActionGroupMap();
			if (!actionGroupMap.isEmpty()) {
				for (ActionTO to : list) {
					to.setGroupName(actionGroupMap.get(to.getGroupId()));
				}
			}

		}

		return list;

	}

	@Override
	public List<ActionParameterTO> getActionParameterList(int actionId) {
		// TODO Auto-generated method stub
		Session session = null;
		String query = null;
		session = sessionFactory.getCurrentSession();

		query = " from ActionParameterTO where actionId=" + actionId + "order by id desc";
		List<ActionParameterTO> list = (List<ActionParameterTO>) session.createQuery(query).list();

		return list;
	}

	@Override
	public List<ActionHeaderParameterTo> getActionHeaderParameterList(int actionId) {
		Session session = null;
		String query = null;
		session = sessionFactory.getCurrentSession();

		query = " from ActionHeaderParameterTo where actionId=" + actionId + "order by id desc";
		List<ActionHeaderParameterTo> list = (List<ActionHeaderParameterTo>) session.createQuery(query).list();

		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#createOnlyActions(com.sixdee.magik.
	 * services.model.ActionsDTO)
	 */
	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public void createOnlyActions(ActionsDTO actionsDTO) throws Exception {
		Session session = null;
		Connection connection = null;
		try {
			session = sessionFactory.getCurrentSession();
			connection = ((SessionImpl) session).connection();
			connection.setAutoCommit(false);
			if (actionsDTO.getMode().equalsIgnoreCase("action")) {
				addActionParameter(actionsDTO, connection);

				System.out.println("action alne");
			} else if (actionsDTO.getMode().equalsIgnoreCase("header")) {
				Integer actionId = addActionParameter(actionsDTO, connection);
				addHeader(actionsDTO, connection, actionId);
			} else if (actionsDTO.getMode().equalsIgnoreCase("normal")) {
				Integer actionId = addActionParameter(actionsDTO, connection);
				addNormal(actionsDTO, connection, actionId);
			} else if (actionsDTO.getMode().equalsIgnoreCase("all")) {
				Integer actionId = addActionParameter(actionsDTO, connection);
				addHeader(actionsDTO, connection, actionId);
				addNormal(actionsDTO, connection, actionId);
			}
			connection.commit();
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public Integer addActionParameter(ActionsDTO actionsDTO, Connection connection) throws Exception {
		String query = null;
		Integer id = 0;
		try {
			query = "INSERT INTO RE_ACTION_INFO (NAME, ACTION_TYPE,GROUP_ID,TASK_ID) VALUES (?,?,?,?)";
			// PreparedStatement statement = connection.prepareStatement(query,
			// Statement.RETURN_GENERATED_KEYS);
			String generatedColumns[] = { "ID" };
			PreparedStatement statement = connection.prepareStatement(query, generatedColumns);
			statement.setString(1, actionsDTO.getName());
			statement.setString(2, "ACT");
			statement.setInt(3, actionsDTO.getGroupId());
			statement.setInt(4, 0);

			// System.out.println(" inside if");
			// System.out.println(" inside if 22 " + actionsDTO.getName());
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Action");
			audtoTO.setAddedName(actionsDTO.getName());
			audtoTO.setActionType("CREATE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a1 = "Created";
			String b1 = actionsDTO.getName().concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			int affectedRows = statement.executeUpdate();
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
	public void addHeader(ActionsDTO actionsDTO, Connection connection, Integer actionId) throws Exception {

		String query = null;
		try {
			query = "INSERT INTO RE_ACTION_HEADER_PARAMETERS (ACTION_ID, PARAMETER_NAME,PARAMETER_VALUE) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, actionId);
			statement.setString(2, actionsDTO.getParamName());
			statement.setString(3, actionsDTO.getParamValue());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = { Exception.class })
	public void addNormal(ActionsDTO actionsDTO, Connection connection, Integer actionId) throws Exception {

		String query = null;
		try {
			query = "INSERT INTO RE_ACTION_PARAMETERS (ACTION_ID, PARAMETER_NAME,DISPLAY_NAME,PARAMETER_VALUE,DATA_TYPE,VISIBILITY,MANDATORY) VALUES (?,?,?,?,?,?,?)";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, actionId);
			statement.setString(2, actionsDTO.getNormalParamName());
			statement.setString(3, actionsDTO.getDisplayName());
			statement.setString(4, actionsDTO.getNormalParamValue());
			statement.setString(5, actionsDTO.getDataType());
			statement.setString(6, actionsDTO.getVisibility());
			statement.setString(7, actionsDTO.getMandatory());
			int affectedRows = statement.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#updateHeaderParameter(com.sixdee.
	 * magik.services.model.ActionsDTO)
	 */
	@Override
	public void updateHeaderParameter(ActionsDTO actionsDTO) throws Exception {

		Session session = null;
		String query = null;
		try {
			session = sessionFactory.getCurrentSession();
			System.out.println("   else if  ::::  ");
			System.out.println("   else if  1  ::::  " + actionsDTO.getParamName());
			System.out.println("   else if  id  ::::  " + actionsDTO.getParamValue());

			System.out.println(" actionsDTO.getId() " + actionsDTO.getId());
			System.out.println(" actionsDTO.getActionId() " + actionsDTO.getActionId());
			System.out.println(" actionsDTO.getGroupId() " + actionsDTO.getGroupId());

			String hql = "select ID ,ACTION_ID ,PARAMETER_NAME,PARAMETER_VALUE  from  RE_ACTION_HEADER_PARAMETERS where ID= '"
					+ actionsDTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				// idValue = (Integer) obj[0];

				String id = obj[0].toString();
				idValue = Integer.parseInt(id);
				
				//actionidValue = (Integer) obj[1];
				String action = obj[1].toString();
				idValue = Integer.parseInt(action);

				
				nameValue = obj[2].toString();
				paramValue = obj[3].toString();

			}
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Acton Header Parameter");
			audtoTO.setAddedName(actionsDTO.getParamName());
			audtoTO.setActionType("MODIFY");

			String a1 = "Modified";
			String b1 = actionsDTO.getParamName().concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setDesc(b1);
			if (nameValue != null && !nameValue.equalsIgnoreCase(actionsDTO.getParamName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("ParamName");
				audtoTO.setPreviousValue(nameValue);
				audtoTO.setNewValue(actionsDTO.getParamName());
			}
			if (paramValue != null && !paramValue.equalsIgnoreCase(actionsDTO.getParamValue())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("ParamValue");
				audtoTO.setPreviousValue(paramValue);
				audtoTO.setNewValue(actionsDTO.getParamValue());

			}
			/*
			 * audtoTO.setPreviousValue(nameValue);
			 * audtoTO.setNewValue(actionsDTO.getParamName());
			 */

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);
			System.out.println("-----------------------Lead  Policy  Edit-----------------");

			query = "UPDATE RE_ACTION_HEADER_PARAMETERS SET PARAMETER_NAME=:name,PARAMETER_VALUE=:value WHERE ID=:id";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("name", actionsDTO.getParamName());
			sqlQuery.setParameter("value", actionsDTO.getParamValue());
			sqlQuery.setParameter("id", actionsDTO.getId());
			int affectedRows = sqlQuery.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#updateNormalParameter(com.sixdee.
	 * magik.services.model.ActionsDTO)
	 */
	@Override
	public void updateNormalParameter(ActionsDTO actionsDTO) throws Exception {

		Session session = null;
		String query = null;
		try {
			session = sessionFactory.getCurrentSession();

			System.out.println("   else if  ::::  ");
			System.out.println("   else if  1  ::::  " + actionsDTO.getDisplayName());
			System.out.println("   else if  id  ::::  " + actionsDTO.getParamValue());

			System.out.println(" actionsDTO.getId() " + actionsDTO.getId());
			System.out.println(" actionsDTO.getActionId() " + actionsDTO.getActionId());
			System.out.println(" actionsDTO.getGroupId() " + actionsDTO.getGroupId());

			String hql = "select ID ,ACTION_ID ,PARAMETER_NAME,DISPLAY_NAME,PARAMETER_VALUE,DATA_TYPE  from  RE_ACTION_PARAMETERS where ID= '"
					+ actionsDTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				// idValue1 = (Integer) obj[0];
				// actionidValue1 = (Integer) obj[1];

				String id = obj[0].toString();
				idValue1 = Integer.parseInt(id);

				String action = obj[1].toString();
				actionidValue1 = Integer.parseInt(action);

				nameValue1 = obj[2].toString();
				display1 = obj[3].toString();
				paramValue1 = obj[4].toString();
				datatypeValue = obj[5].toString();

			}

			/*
			 * System.out.println(" nameValue1 " + nameValue1);
			 * System.out.println(" display1 " + display1);
			 * System.out.println(" paramValue1 " + paramValue1);
			 * System.out.println(" datatypeValue " + datatypeValue);
			 * 
			 * System.out.println(" actionsDTO.getParamName() " +
			 * actionsDTO.getNormalParamName());
			 * System.out.println(" actionsDTO.getParamName() " +
			 * actionsDTO.getParamName()); System.out.println(" actionsDTO.getParamName() "
			 * + actionsDTO.getParamValue());
			 * 
			 * System.out.println(" actionsDTO.getNormalParamValue() " +
			 * actionsDTO.getNormalParamValue());
			 * System.out.println(" actionsDTO.getDataType() " + actionsDTO.getDataType());
			 */

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Acton Normal Parameter");
			audtoTO.setAddedName(actionsDTO.getDisplayName());
			audtoTO.setActionType("MODIFY");

			String a1 = "Modified";
			String b1 = actionsDTO.getDisplayName().concat(a1);
			audtoTO.setDesc(b1);

			if (nameValue1 != null && !nameValue1.equalsIgnoreCase(actionsDTO.getParamName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("ParamName");
				audtoTO.setPreviousValue(nameValue1);
				audtoTO.setNewValue(actionsDTO.getParamName());
			}
			if (display1 != null && !display1.equalsIgnoreCase(actionsDTO.getDisplayName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("Display Name");
				audtoTO.setPreviousValue(display1);
				audtoTO.setNewValue(actionsDTO.getDisplayName());
			}
			if (paramValue1 != null && !paramValue1.equalsIgnoreCase(actionsDTO.getParamValue())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("ParamValue");
				audtoTO.setPreviousValue(paramValue1);
				audtoTO.setNewValue(actionsDTO.getParamValue());

			}

			if (datatypeValue != null && !datatypeValue.equalsIgnoreCase(actionsDTO.getDataType())) {
				System.out.println(" Description inside ");
				audtoTO.setAttribute("Data Type");
				audtoTO.setPreviousValue(datatypeValue);
				audtoTO.setNewValue(actionsDTO.getDataType());

			}
			/*
			 * audtoTO.setPreviousValue(display1);
			 * audtoTO.setNewValue(actionsDTO.getDisplayName());
			 */

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);
			System.out.println("-----------------------Lead  Policy  Edit-----------------");

			query = "UPDATE RE_ACTION_PARAMETERS SET PARAMETER_NAME=:name,DISPLAY_NAME=:displayName,PARAMETER_VALUE=:value,DATA_TYPE=:dataType,VISIBILITY=:visbility,MANDATORY=:mandatory WHERE ID=:id";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("name", actionsDTO.getParamName());
			sqlQuery.setParameter("displayName", actionsDTO.getDisplayName());
			sqlQuery.setParameter("value", actionsDTO.getParamValue());
			sqlQuery.setParameter("dataType", actionsDTO.getDataType());
			sqlQuery.setParameter("visbility", actionsDTO.getVisibility());
			sqlQuery.setParameter("mandatory", actionsDTO.getMandatory());
			sqlQuery.setParameter("id", actionsDTO.getId());
			int affectedRows = sqlQuery.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#deleteNormalParameter(com.sixdee.
	 * magik.services.model.ActionsDTO)
	 */
	@Override
	public void deleteNormalParameter(ActionsDTO actionsDTO) throws Exception {
		Session session = null;
		String query = null;
		try {
			session = sessionFactory.getCurrentSession();

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Action Normal Parameter");
			audtoTO.setAddedName(actionsDTO.getDisplayName());
			audtoTO.setActionType("DELETE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a1 = "Deleted";
			String b1 = actionsDTO.getDisplayName().concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			session = sessionFactory.getCurrentSession();
			String hql1 = "delete from NormalParameterTO where id = " + actionsDTO.getId();
			session.createQuery(hql1).executeUpdate();

//			query = "DELETE FROM RE_ACTION_PARAMETERS WHERE ID=:id";
//			SQLQuery sqlQuery = session.createSQLQuery(query);
//			sqlQuery.setParameter("id", actionsDTO.getId());
//			int affectedRows = sqlQuery.executeUpdate();
//			if (affectedRows == 0) {
//				throw new Exception("Creating failed, no rows affected.");
//			}
		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.ActionDao#deleteHeaderParameter(com.sixdee.
	 * magik.services.model.ActionsDTO)
	 */
	@Override
	public void deleteHeaderParameter(ActionsDTO actionsDTO) throws Exception {
		Session session = null;
		String query = null;
		try {
			session = sessionFactory.getCurrentSession();

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Action Header Parameter");
			audtoTO.setAddedName(actionsDTO.getParamName());
			audtoTO.setActionType("DELETE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a1 = "Deleted";
			String b1 = actionsDTO.getParamName().concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			session = sessionFactory.getCurrentSession();
			String hql1 = "delete from HeaderParameterTO where id = " + actionsDTO.getId();
			session.createQuery(hql1).executeUpdate();

//			query = "DELETE FROM RE_ACTION_HEADER_PARAMETERS WHERE ID=:id";
//			SQLQuery sqlQuery = session.createSQLQuery(query);
//			sqlQuery.setParameter("id", actionsDTO.getId());
//			int affectedRows = sqlQuery.executeUpdate();
//			if (affectedRows == 0) {
//				throw new Exception("Creating failed, no rows affected.");
//			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void editSubActions(ActionsDTO actionsDTO) throws Exception {
		Session session = null;
		String query = null;
		try {
			session = sessionFactory.getCurrentSession();
			System.out.println("getActionId 3 " + actionsDTO.getActionId());
			System.out.println("getId 3 " + actionsDTO.getId());
			System.out.println("getGroupId 3 " + actionsDTO.getGroupId());

			String hql = "select ID ,NAME  from  RE_ACTION_INFO where ID= '" + actionsDTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				// idValue2 = (Integer) obj[0];

				String id = obj[0].toString();
				int idValue2 = Integer.parseInt(id);
				nameValue2 = obj[1].toString();
			}

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Acton");
			audtoTO.setAddedName(actionsDTO.getName());
			audtoTO.setActionType("MODIFY");

			String a1 = "Modified";
			String b1 = actionsDTO.getName().concat(a1);
			audtoTO.setDesc(b1);

			if (nameValue2 != null && !nameValue2.equalsIgnoreCase(actionsDTO.getName())) {
				System.out.println(" name inside ");
				audtoTO.setAttribute("Action Name");
				audtoTO.setPreviousValue(nameValue2);
				audtoTO.setNewValue(actionsDTO.getName());
			}
			System.out.println("nameValue2 3 " + nameValue2);
			System.out.println("actionsDTO.getName() 3 " + actionsDTO.getName());

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			query = "UPDATE RE_ACTION_INFO SET NAME=:name WHERE ID=:id AND GROUP_ID=:gid";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("id", actionsDTO.getId());
			sqlQuery.setParameter("gid", actionsDTO.getGroupId());
			sqlQuery.setParameter("name", actionsDTO.getName());
			int affectedRows = sqlQuery.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = { Exception.class })
	public void deleteSubActions(ActionsDTO actionsDTO) throws Exception {

		Connection connection = null;
		Session session = null;
		String query = null;
		SQLQuery sqlQuery = null;
		try {
			session = sessionFactory.getCurrentSession();
			connection = ((SessionImpl) session).connection();
			connection.setAutoCommit(false);

//			sqlQuery = session.createSQLQuery("SELECT ID FROM RE_ACTION_PARAMETERS WHERE ACTION_ID=:id LIMIT 1");
//			sqlQuery.setParameter("id", actionsDTO.getActionId());
//			Object objss = sqlQuery.uniqueResult();
//			if (objss != null) {

			session = sessionFactory.getCurrentSession();
			String hql1 = "delete from NormalParameterTO where id = " + actionsDTO.getActionId();
			session.createQuery(hql1).executeUpdate();

//				query = "DELETE FROM RE_ACTION_PARAMETERS WHERE ACTION_ID=:id";
//				sqlQuery = session.createSQLQuery(query);
//				sqlQuery.setParameter("id", actionsDTO.getActionId());
//				int affectedRs = sqlQuery.executeUpdate();
//				if (affectedRs == 0) {
//					throw new Exception("Creating failed, no rows affected.");
//				}

			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Action");
			audtoTO.setAddedName(actionsDTO.getName());
			audtoTO.setActionType("DELETE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a1 = "Deleted";
			System.out.println(" actionsDTO.getName()" + actionsDTO.getName());
			String b1 = actionsDTO.getName().concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setCreatedBy(actionsDTO.getAuditUserName());
			audtoTO.setRoleName(actionsDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);
			// }

//			sqlQuery = session.createSQLQuery("SELECT ID FROM RE_ACTION_HEADER_PARAMETERS WHERE ACTION_ID=:id LIMIT 1");
//			sqlQuery.setParameter("id", actionsDTO.getActionId());
//			Object objs = sqlQuery.uniqueResult();
//			if (objs != null) {

			session = sessionFactory.getCurrentSession();
			String hql2 = "delete from HeaderParameterTO where id = " + actionsDTO.getActionId();
			session.createQuery(hql2).executeUpdate();

//				query = "DELETE FROM RE_ACTION_HEADER_PARAMETERS WHERE ACTION_ID=:id";
//				sqlQuery = session.createSQLQuery(query);
//				sqlQuery.setParameter("id", actionsDTO.getActionId());
//				int affectedRs = sqlQuery.executeUpdate();
//				if (affectedRs == 0) {
//					throw new Exception("Creating failed, no rows affected.");
//				}
			// }

			session = sessionFactory.getCurrentSession();
			String hql3 = "delete from ActionTO where id = " + actionsDTO.getId() + "and groupId="
					+ actionsDTO.getGroupId();
			session.createQuery(hql3).executeUpdate();

//			query = "DELETE FROM RE_ACTION_INFO WHERE ID=:gid and GROUP_ID=:subId";
//			sqlQuery = session.createSQLQuery(query);
//			sqlQuery.setParameter("gid", actionsDTO.getId());
//			sqlQuery.setParameter("subId", actionsDTO.getGroupId());
//			int affecteds = sqlQuery.executeUpdate();
//			if (affecteds == 0) {
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
	public List<ActionsDTO> getSubGroupHeaderParameter(int actionId) throws Exception {
		Session session = null;
		String query = null;
		ActionsDTO actionsDTO = null;
		List<ActionsDTO> actionsDTOList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT ID as id, PARAMETER_NAME as name, PARAMETER_VALUE as value, ACTION_ID as actionId FROM RE_ACTION_HEADER_PARAMETERS WHERE ACTION_ID=:actId  order by ID desc";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("actId", actionId);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("name", StringType.INSTANCE);
			sqlQuery.addScalar("value", StringType.INSTANCE);
			sqlQuery.addScalar("actionId", IntegerType.INSTANCE);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				actionsDTOList = new ArrayList<ActionsDTO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null) {
						actionsDTO = new ActionsDTO();
						actionsDTO.setId((Integer) aRow[0]);
						actionsDTO.setParamName((String) aRow[1]);
						actionsDTO.setParamValue(aRow[2] != null ? (String) aRow[2] : null);
						actionsDTO.setActionId((Integer) aRow[3]);
						actionsDTOList.add(actionsDTO);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return actionsDTOList;
	}

	@Override
	public List<ActionsDTO> getSubGroupNormalParameter(int actionId) throws Exception {
		Session session = null;
		String query = null;
		ActionsDTO actionsDTO = null;
		List<ActionsDTO> actionsDTOList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT ID as id, DATA_TYPE as dataType, VISIBILITY as vis, MANDATORY as man, DISPLAY_NAME as disName, PARAMETER_NAME as name, PARAMETER_VALUE as value, ACTION_ID as actionId FROM RE_ACTION_PARAMETERS WHERE ACTION_ID=:actId  order by ID desc ";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("actId", actionId);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("dataType", StringType.INSTANCE);
			sqlQuery.addScalar("vis", IntegerType.INSTANCE);
			sqlQuery.addScalar("man", IntegerType.INSTANCE);
			sqlQuery.addScalar("disName", StringType.INSTANCE);
			sqlQuery.addScalar("name", StringType.INSTANCE);
			sqlQuery.addScalar("value", StringType.INSTANCE);
			sqlQuery.addScalar("actionId", IntegerType.INSTANCE);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				actionsDTOList = new ArrayList<ActionsDTO>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null && aRow[2] != null && aRow[3] != null
							&& aRow[4] != null && aRow[5] != null && aRow[6] != null && aRow[7] != null) {
						actionsDTO = new ActionsDTO();
						actionsDTO.setId((Integer) aRow[0]);
						actionsDTO.setDataType((String) aRow[1]);

						String visi = aRow[2].toString();
						int visiint = Integer.parseInt(visi);
						if (visiint == 1) {
							aRow[2] = "Yes";
							actionsDTO.setVisibility((String) aRow[2]);

						} else {
							aRow[2] = "No";
							actionsDTO.setVisibility((String) aRow[2]);
						}

						String mana = aRow[3].toString();
						int manaint = Integer.parseInt(mana);
						if (manaint == 1) {
							aRow[3] = "Yes";
							actionsDTO.setMandatory((String) aRow[3]);

						} else {
							aRow[3] = "No";
							actionsDTO.setMandatory((String) aRow[3]);
						}

						actionsDTO.setDisplayName((String) aRow[4]);
						actionsDTO.setParamName((String) aRow[5]);
						actionsDTO.setParamValue(aRow[6] != null ? (String) aRow[6] : null);
						actionsDTO.setActionId((Integer) aRow[7]);
						actionsDTOList.add(actionsDTO);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}
		return actionsDTOList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sixdee.magik.services.dao.ActionDao#getActionsDefaultParamList(int)
	 */
	@Override
	public List<ActionDefaultParamsTO> getActionsDefaultParamList(int groupId) {
		// TODO Auto-generated method stub
		Session session = null;
		String query = null;
		List<ActionDefaultParamsTO> list = null;

		session = sessionFactory.getCurrentSession();
		query = " from ActionDefaultParamsTO where groupId= " + groupId;
		list = (List<ActionDefaultParamsTO>) session.createQuery(query).list();

		return list;
	}

	public LinkedHashMap<Integer, String> getActionGroupMap() {
		LinkedHashMap<Integer, String> actionGroupMap = new LinkedHashMap<>();
		Session session = null;
		String query = null;
		List<ActionGroupTO> list = null;

		session = sessionFactory.getCurrentSession();
		query = " from ActionGroupTO ";
		list = (List<ActionGroupTO>) session.createQuery(query).list();
		if (!list.isEmpty()) {
			for (ActionGroupTO to : list) {
				actionGroupMap.put(to.getId(), to.getName());
			}
		}

		return actionGroupMap;
	}
}
