package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.KPIDAO;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.BLGroupDetailsDTO;
import com.sixdee.magik.services.model.DataTypeTO;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.GroupTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.model.SubGroupTO;
import com.sixdee.magik.services.util.DateFormat;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Repository
public class KPIDaoImpl implements KPIDAO {

	DateFormat dateFormat = new DateFormat();

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	AuditInfoDAO auditInfo;
	AuditInfoTO audtoTO = null;

	@Autowired
	@Qualifier("blConfigSessionFactory")
	private SessionFactory blSessionFactory;

	private Session session = null;
	private String hql = null;

	int idValue;
	String profilenameValue;
	int dataValue;
	String nameValue;
	String nameValues;

	/*
	 * Add KPI
	 */
	@Override
	public GenericTO addKPI(KPITO to) {
		session = sessionFactory.getCurrentSession();
		GenericTO genrcTO = new GenericTO();

		if (to.getGroupId() == 0) {
			GroupTO groupTO = new GroupTO();
			groupTO.setName(to.getGroupName());
			groupTO.setCreatedBy(to.getCretedBy());
			session.saveOrUpdate(groupTO);

			to.setGroupId(groupTO.getId());
		}

		if (to.getSubGroupId() == 0) {
			SubGroupTO subGroupTO = new SubGroupTO();
			subGroupTO.setName(to.getGroupName());

			session.saveOrUpdate(subGroupTO);

			to.setSubGroupId(subGroupTO.getId());

			hql = "INSERT INTO RE_GROUP_SUBGROUP_MAPING (GROUP_ID, SUB_GROUP_ID) VALUES (?,?)";
			session.createSQLQuery(hql).setParameter(0, to.getGroupId()).setParameter(1, to.getSubGroupId())
					.executeUpdate();
		}

		session.saveOrUpdate(to);

		if (to.getId() == 0) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}

		return genrcTO;
	}

	/*
	 * Get All KPI's
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KPITO> getKPIS() {
		System.out.println(" getKPIS ");
		Session session = sessionFactory.getCurrentSession();
		List<KPITO> list = new ArrayList<KPITO>();
		KPITO to = null;
		List<Object[]> rs = null;

		hql = "SELECT a.id, a.name, b.name , c.name, d.name,c.id,a.profileName,a.values,a.groupId FROM KPITO a, GroupTO b, SubGroupTO c, DataTypeTO d";
		hql += " where a.groupId =b.id and a.subGroupId=c.id and a.dataTypeId=d.id order by a.id desc";

		rs = (List<Object[]>) session.createQuery(hql).list();

		for (Object[] obj : rs) {
			to = new KPITO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setGroupName(obj[2] + "");
			to.setSubGroupName(obj[3] + "");
			to.setDataType(obj[4] + "");
			to.setSubGroupId(Integer.parseInt(obj[5] + ""));
			to.setProfileName(obj[6] + "");
			to.setValues(obj[7] + "");
			// System.out.println(" values " + obj[7]);
			to.setGroupId(Integer.parseInt(obj[8] + ""));
			list.add(to);
		}

		return list;
	}

	/*
	 * Search KPI's
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<KPITO> searchKPI(String srchEle) {

		Session session = sessionFactory.getCurrentSession();
		List<KPITO> list = new ArrayList<KPITO>();
		KPITO to = null;
		List<Object[]> rs = null;

		hql = "SELECT id, name, groupId, subGroupId, dataType,profileName FROM KPITO order by id desc";

		rs = (List<Object[]>) session.createQuery(hql).list();

		for (Object[] obj : rs) {
			to = new KPITO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");
			to.setGroupId(Integer.parseInt(obj[2] + ""));
			to.setSubGroupId(Integer.parseInt(obj[3] + ""));
			to.setDataTypeId(Integer.parseInt(obj[4] + ""));
			to.setProfileName(obj[5] + "");
			System.out.println("KPI  NAme: " + to.getName());
			list.add(to);
		}

		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DataTypeTO> getDataTypes() {

		System.out.println(" getDataTypes ");

		Session session = sessionFactory.getCurrentSession();
		List<DataTypeTO> list = new ArrayList<DataTypeTO>();
		DataTypeTO to = null;
		List<Object[]> rs = null;

		hql = "SELECT id, name FROM DataTypeTO order by id desc";

		rs = (List<Object[]>) session.createQuery(hql).list();

		for (Object[] obj : rs) {
			to = new DataTypeTO();
			to.setId(Integer.parseInt(obj[0] + ""));
			to.setName(obj[1] + "");

			list.add(to);
		}

		return list;
	}

	public List<BLGroupDetailsDTO> getBLGroup(BLGroupDetailsDTO blGroupDetailsDTO) {
		System.out.println(" getBLGroup ");

		Session session = null;
		String query = null;
		BLGroupDetailsDTO grpDTO = null;
		List<BLGroupDetailsDTO> lis = null;
		try {
			session = blSessionFactory.getCurrentSession();
			lis = new ArrayList<BLGroupDetailsDTO>();
			if (blGroupDetailsDTO.getMode() != null && blGroupDetailsDTO.getMode().equalsIgnoreCase("group")) {
				// query = "SELECT ID as id,GROUP_NAME as name FROM RE_GROUP_DETAIL where ID>5
				// order by ID desc";
				query = "SELECT ID as id,GROUP_NAME as name FROM RE_GROUP_DETAIL  order by ID desc";
				SQLQuery sqlQuery = session.createSQLQuery(query);
				sqlQuery.addScalar("id", IntegerType.INSTANCE);
				sqlQuery.addScalar("name", StringType.INSTANCE);
				List<Object[]> results = (List<Object[]>) sqlQuery.list();
				if (results != null && results.size() > 0) {
					for (Object[] aRow : results) {
						if (aRow != null && aRow[0] != null && aRow[1] != null) {
							grpDTO = new BLGroupDetailsDTO();
							grpDTO.setId((Integer) aRow[0]);
							grpDTO.setName((String) aRow[1]);
						}
						lis.add(grpDTO);
					}
				}
			} else if (blGroupDetailsDTO.getMode() != null && blGroupDetailsDTO.getMode().equalsIgnoreCase("profile")) {
				if (blGroupDetailsDTO.getGroupId() != null && blGroupDetailsDTO.getGroupId() != "") {
					query = "SELECT PROFILE_ID as id,PROFILE_NAME as name FROM RE_PROFILE_DESCRIPTION ";
					if (blGroupDetailsDTO.getGroupId() != null)
						query += "	WHERE GROUP_ID IN(" + blGroupDetailsDTO.getGroupId() + ")";
					SQLQuery sqlQuery = session.createSQLQuery(query);
					sqlQuery.addScalar("id", IntegerType.INSTANCE);
					sqlQuery.addScalar("name", StringType.INSTANCE);
					List<Object[]> results = (List<Object[]>) sqlQuery.list();
					if (results != null && results.size() > 0) {
						for (Object[] aRow : results) {
							if (aRow != null && aRow[0] != null && aRow[1] != null) {
								grpDTO = new BLGroupDetailsDTO();
								grpDTO.setId((Integer) aRow[0]);
								grpDTO.setName((String) aRow[1]);
							}
							lis.add(grpDTO);
						}
					}

					query = "SELECT ID as id,FIELD_NAME as name FROM RE_CDR_INDEX_DETAIL";

					sqlQuery = session.createSQLQuery(query);
					sqlQuery.addScalar("id", IntegerType.INSTANCE);
					sqlQuery.addScalar("name", StringType.INSTANCE);
					results = (List<Object[]>) sqlQuery.list();
					if (results != null && results.size() > 0) {
						for (Object[] aRow : results) {
							if (aRow != null && aRow[0] != null && aRow[1] != null) {
								grpDTO = new BLGroupDetailsDTO();
								grpDTO.setId((Integer) aRow[0]);
								grpDTO.setName((String) aRow[1]);
							}
							lis.add(grpDTO);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sixdee.magik.services.dao.KPIDAO#getAllKPIs()
	 */
	@Override
	public List<KPITO> getAllKPIs() {
		System.out.println(" getAllKPIs ");

		Session session = null;
		String query = null;
		KPITO kpiDto = null;
		List<KPITO> kipDtoList = null;
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT businsessKPI.ID as id, businsessKPI.NAME as englishName, businsessKPI.PROFILE_NAME as profileName, businsessKPI.GROUP_ID as groupId,businsessKPI.SUB_GROUP_ID as subGroupId,dataType.NAME as dataTypeName,businsessKPI.SELECT_VALUE as selectValue FROM RE_BUSINESS_KPIS businsessKPI,RE_DATA_TYPES dataType,RE_GROUPS kpigroups,RE_SUB_GROUPS subgroup"
					+ " WHERE kpigroups.ID = businsessKPI.GROUP_ID AND subgroup.ID = businsessKPI.SUB_GROUP_ID AND dataType.ID = businsessKPI.DATA_TYPE order by ID desc";
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
				System.out.println(" results.size() " + results.size());
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
						/*
						 * if (aRow[6] != null) { if (aRow[6] == "") { aRow[6] = "No Default Value";
						 * kpiDto.setCretedBy((String) aRow[6]); } else { kpiDto.setCretedBy((String)
						 * aRow[6]); } }
						 */
						kpiDto.setCretedBy((String) aRow[6]);
						kipDtoList.add(kpiDto);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return kipDtoList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.sixdee.magik.services.dao.KPIDAO#updateKPIs(com.sixdee.magik.services.
	 * model.GenericGroupDTO)
	 */
	@Override
	public void updateKPIs(GenericGroupDTO genericGroupDTO) throws Exception {

		Session session = null;
		String query = null;
		session = sessionFactory.getCurrentSession();
		try {
			System.out.println("inside update");

			String hql = "select ID ,NAME ,PROFILE_NAME,DATA_TYPE from  RE_BUSINESS_KPIS where ID= '"
					+ genericGroupDTO.getId() + "'  ";
			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				String id = obj[0].toString();
				idValue = Integer.parseInt(id);
				nameValue = obj[1].toString();
				profilenameValue = obj[2].toString();
				String data = obj[3].toString();
				dataValue = Integer.parseInt(data);

			}
			System.out.println(" nameValue " + nameValue);
			System.out.println(" profilenameValue " + profilenameValue);
			System.out.println(" dataValue " + dataValue);
			System.out.println(" genericGroupDTO " + genericGroupDTO.getDataTypeId());
			System.out.println(" getDataType " + genericGroupDTO.getDataType());

			query = "UPDATE RE_BUSINESS_KPIS SET NAME=:name,PROFILE_NAME=:profileName,DATA_TYPE=:dataTypeId,SELECT_VALUE=:selectValue,CREATED_BY=:createdBy WHERE ID=:id";
			System.out.println(" query " + query);
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.setParameter("name", genericGroupDTO.getEnglishName());
			sqlQuery.setParameter("profileName", genericGroupDTO.getBlSubGroupName());
			sqlQuery.setParameter("dataTypeId", genericGroupDTO.getDataTypeId());
			if (genericGroupDTO.getSelectValue() == "") {
				sqlQuery.setParameter("selectValue", "No Default Value");
				// System.out.println("genericGroupDTO.getSelectValue() if " +
				// genericGroupDTO.getSelectValue());
			} else {
				sqlQuery.setParameter("selectValue", genericGroupDTO.getSelectValue());
				// System.out.println("genericGroupDTO.getSelectValue() else " +
				// genericGroupDTO.getSelectValue());
			}
			sqlQuery.setParameter("createdBy", genericGroupDTO.getCreatedBy());
			sqlQuery.setParameter("id", genericGroupDTO.getId());

			if (genericGroupDTO.getId() != 0) {
				/*
				 * System.out.println("    if  ::::  ");
				 * System.out.println("   else if  1  ::::  " +
				 * genericGroupDTO.getEnglishName());
				 * System.out.println("   else if  id  ::::  " + genericGroupDTO.getId());
				 */

				audtoTO = new AuditInfoTO();
				audtoTO.setFeatureName("Segment Variable");
				audtoTO.setAddedName(genericGroupDTO.getEnglishName());
				audtoTO.setActionType("MODIFY");

				String a1 = "Modified";
				String b1 = genericGroupDTO.getEnglishName().concat(a1);
				audtoTO.setDesc(b1);

				if (nameValue != null && !nameValue.equalsIgnoreCase(genericGroupDTO.getEnglishName())) {
					System.out.println(" name inside ");
					audtoTO.setAttribute("English Name");
					audtoTO.setPreviousValue(nameValue);
					audtoTO.setNewValue(genericGroupDTO.getEnglishName());
				}
				if (profilenameValue != null
						&& !profilenameValue.equalsIgnoreCase(genericGroupDTO.getBlSubGroupName())) {
					System.out.println("Profile name inside ");
					audtoTO.setAttribute("Profile Name");
					audtoTO.setPreviousValue(profilenameValue);
					audtoTO.setNewValue(genericGroupDTO.getBlSubGroupName());
				}
				if (dataValue != genericGroupDTO.getDataTypeId()) {
					System.out.println(" Description inside ");
					audtoTO.setAttribute("Data Type ");
					String a = String.valueOf(dataValue);
					String b = String.valueOf(genericGroupDTO.getDataTypeId());
					audtoTO.setPreviousValue(a);
					audtoTO.setNewValue(b);

				}
				/*
				 * audtoTO.setPreviousValue(nameValue );
				 * audtoTO.setNewValue(genericGroupDTO.getEnglishName());
				 */

				audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
				audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

				auditInfo.addAuditLog(audtoTO);
				System.out.println("-----------------------Lead  Policy  Edit-----------------");
			}

			int affectedRows = sqlQuery.executeUpdate();
			if (affectedRows == 0) {
				throw new Exception("Creating failed, no rows affected.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	@Override
	public void deleteKPIs(GenericGroupDTO genericGroupDTO) throws Exception {

		System.out.println(" deleteKPIs");
		Session session = null;
		String query = null;
		SQLQuery sqlQuery = null;
		try {
			session = sessionFactory.getCurrentSession();
			System.out.println(" deletd " + genericGroupDTO.getEnglishName());
			System.out.println(" getId " + genericGroupDTO.getId());

			String hql = "select ID,NAME from RE_BUSINESS_KPIS where ID= '" + genericGroupDTO.getId() + "'  ";

			List<Object[]> rs = (List<Object[]>) session.createSQLQuery(hql).list();
			System.out.println("size::::::" + rs.size());

			for (Object[] obj : rs) {
				String id = obj[0].toString();
				int idValues = Integer.parseInt(id);
				nameValues = obj[1].toString();

			}
			System.out.println("nameValues " + nameValues);
			audtoTO = new AuditInfoTO();
			audtoTO.setFeatureName("Segment Variable");
			audtoTO.setAddedName(nameValues);
			audtoTO.setActionType("DELETE");
			audtoTO.setAttribute("N/A");
			audtoTO.setPreviousValue("N/A");
			audtoTO.setNewValue("N/A");

			String a1 = "Deleted";
			String b1 = nameValues.concat(a1);
			audtoTO.setDesc(b1);

			audtoTO.setCreatedBy(genericGroupDTO.getAuditUserName());
			audtoTO.setRoleName(genericGroupDTO.getAuditRoleName());

			auditInfo.addAuditLog(audtoTO);

			String hql3 = "delete from BussinessKpiTO where groupId = " + genericGroupDTO.getGroupId()
					+ "and subGroupId =" + genericGroupDTO.getSubGroupId() + "and  id=" + genericGroupDTO.getId();
			session.createQuery(hql3).executeUpdate();

		} catch (Exception e) {
			throw e;
		}
	}
}
