/**
 * 
 */
package com.sixdee.magik.services.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.MenuDetailsDAO;
import com.sixdee.magik.services.model.FunctionMetricsTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.model.MenuDetailDTO;

/**
 * @author Vinay Kariyappa
 *
 *         Sep 26, 2018
 */
@Repository
public class MenuDetailsDAOImpl implements MenuDetailsDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public List<MenuDetailDTO> getMenus(int parentId,int userId) {
System.out.println("userId:::"+userId);
		String query = "";
		System.out.println(" test 1  "+userId);
		Session session = sessionFactory.getCurrentSession();
		List<Object[]> rs = null;
		
	//	query = "from RolePermission,FeatureMaster,MenuDetailDTO where roleId = "+20;
		List<MenuDetailDTO> detailDTOs = new ArrayList<MenuDetailDTO>();
		if(userId == 1) {
			query = "from MenuDetailDTO where status = "+1+" order by parentId,menuOrder";
			//	query = "from MenuDetailDTO order by parentId,menuOrder ";

				/*if (parentId == -1)
					query += " parentId= 0";
				else
					query += " parentId= " + parentId;
		*/
				List<MenuDetailDTO> list  = (List<MenuDetailDTO>) session.createQuery(query).list();
				detailDTOs.addAll(list);
		}else {
			query = "SELECT DISTINCT c.menuId,c.changeUser,c.description,c.isBootstrap,c.menuName,c.menuOrder,c.url,c.parentId,c.iconName,c.shortcut FROM RolePermission a, FeatureMaster b, MenuDetailDTO c";
			query += " where a.featureId = b.featureId and b.moduleId=c.moduleId and  a.roleId in (select y.roleId from UserMaster u, DesignationRoles y where u.designationId = y.designationId and u.userId = "+userId+")" ;
			// where a.featureId =b.featureId and b.moduleId=c.moduleId and
			rs = (List<Object[]>) session.createQuery(query).list();
			
			for (Object[] obj : rs) {
				MenuDetailDTO detailDTO = new  MenuDetailDTO();
				detailDTO.setMenuId(Integer.parseInt(obj[0] + ""));
				//detailDTO.setChangeDate(obj[1]);
				detailDTO.setChangeUser(obj[1]+"");
				detailDTO.setDescription(obj[2]+"");
				detailDTO.setIsBootstrap(Integer.parseInt(obj[3]+""));
				detailDTO.setMenuName(obj[4]+"");
				detailDTO.setMenuOrder(Integer.parseInt(obj[5]+""));
				detailDTO.setUrl(obj[6]+"");
				detailDTO.setParentId(Integer.parseInt(obj[7]+""));
				detailDTO.setIconName(obj[8]+"");
				System.out.println(obj[9]+"");
			    detailDTO.setShortcut(Boolean.parseBoolean(obj[9]+""));
				
				detailDTOs.add(detailDTO);
				System.out.println("detailDTO  ::  " + obj[4].toString());
				System.out.println("Sizze:::"+detailDTOs.size());
				//System.out.println("IN>>>"+Integer.parseInt(obj[0] + ""));
				//System.out.println("IN>>>"+obj[5] + "");
			}
			//List   = session.createQuery(query).list();
			
			
			query = "from MenuDetailDTO where parentId="+0+" order by parentId,menuOrder";
			List<MenuDetailDTO>   list      = (List<MenuDetailDTO>)session.createQuery(query).list();
			System.out.println("Sizze:::"+list.size());
			detailDTOs.addAll(list);
			System.out.println("Sizze:::"+detailDTOs.size());
		}
		

	//	query = "from MenuDetailDTO order by parentId,menuOrder ";

		/*if (parentId == -1)
			query += " parentId= 0";
		else
			query += " parentId= " + parentId;
*/

		/*
		 * Session session = sessionFactory.getCurrentSession();
		 * 
		 * @SuppressWarnings({ "unchecked", "deprecation" }) List<MenuDetailDTO> list =
		 * (List<MenuDetailDTO>) session.createCriteria(MenuDetailDTO.class).list();
		 */
		return detailDTOs;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sixdee.magik.services.dao.MenuDetailsDAO#getMainMenuDetails()
	 */
	@Override
	public List<Map<Integer, MenuDetailDTO>> getMainMenuDetails(Integer roleId) throws Exception {

		Session session = null;
		String query = null;
		Map<Integer, MenuDetailDTO> mainMenuDetails = null;
		List<Map<Integer, MenuDetailDTO>> mainMenuDetailsList = null;
		try {
			System.out.println(" test 2 ");
			session = sessionFactory.getCurrentSession();
			query = "SELECT MENU_ID as id,MENU_NAME as menuName, DESCRIPTION as menuDesc,URL as url, MENU_ORDER as menuOrder FROM MENU_DETAILS";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("id", IntegerType.INSTANCE);
			sqlQuery.addScalar("menuName", StringType.INSTANCE);
			sqlQuery.addScalar("menuDesc", StringType.INSTANCE);
			sqlQuery.addScalar("url", StringType.INSTANCE);
			sqlQuery.addScalar("menuOrder", IntegerType.INSTANCE);
			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				mainMenuDetailsList = new ArrayList<Map<Integer, MenuDetailDTO>>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null && aRow[2] != null && aRow[3] != null
							&& aRow[4] != null) {
						MenuDetailDTO menuDetailDTO = new MenuDetailDTO();
						mainMenuDetails = new HashMap<Integer, MenuDetailDTO>();
						menuDetailDTO.setMenuId((Integer) aRow[0]);
						menuDetailDTO.setMenuName((String) aRow[1]);
						menuDetailDTO.setDescription((String) aRow[2]);
						menuDetailDTO.setUrl((String) aRow[3]);
						menuDetailDTO.setMenuOrder((Integer) aRow[4]);
						mainMenuDetails.put(menuDetailDTO.getMenuId(), menuDetailDTO);
						mainMenuDetailsList.add(mainMenuDetails);
					}
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return mainMenuDetailsList;
	}

	@Override
	public List<Map<Integer, Map<Integer, List<String>>>> getSubMenuDetails() throws Exception {

		Session session = null;
		String query = null;
		Map<Integer, MenuDetailDTO> mainMenuDetails = null;
		List<Map<Integer, Map<Integer, List<String>>>> mainMenuDetailsList = null;
		Map<Integer, Map<Integer, List<String>>> roleMenuDetails = new LinkedHashMap<Integer, Map<Integer, List<String>>>();
		try {
			session = sessionFactory.getCurrentSession();
			query = "SELECT ROLE_ID AS roleID,MD.PARENT_ID AS parentId,MD.MENU_ID AS menuId,FUNCTION AS menuFuntion FROM ROLE_MENU_DETAILS RMD ,FEATURE_MASTER FM ,MENU_DETAILS MD WHERE RMD.FEATURE_ID=FM.FEATURE_ID AND FM.MENU_ID= MD.MENU_ID  ORDER BY MD.MENU_ORDER";
			SQLQuery sqlQuery = session.createSQLQuery(query);
			sqlQuery.addScalar("roleID", IntegerType.INSTANCE);
			sqlQuery.addScalar("parentId", IntegerType.INSTANCE);
			sqlQuery.addScalar("menuId", IntegerType.INSTANCE);
			sqlQuery.addScalar("menuFuntion", StringType.INSTANCE);

			Integer roleId = null;
			Integer mainMenuId = null;
			Integer subMenuId = null;
			String fnId = null;

			Map menuMap = null;
			Map<Integer, List<String>> subMenuMap = null;
			List<String> subMenuFnList = null;

			List<Object[]> results = (List<Object[]>) sqlQuery.list();
			if (results != null && results.size() > 0) {
				mainMenuDetailsList = new ArrayList<Map<Integer, Map<Integer, List<String>>>>();
				for (Object[] aRow : results) {
					if (aRow != null && aRow[0] != null && aRow[1] != null && aRow[2] != null && aRow[3] != null) {

						roleId = (Integer) aRow[0];
						mainMenuId = (Integer) aRow[1];
						subMenuId = (Integer) aRow[2];
						fnId = (String) aRow[3];

						if ((menuMap = roleMenuDetails.get(roleId)) != null) {
							if ((subMenuMap = (Map) menuMap.get(mainMenuId)) != null) {
								if ((subMenuFnList = (List) subMenuMap.get(subMenuId)) != null) {
									if (fnId != null)
										subMenuFnList.add(fnId);

								} else {
									subMenuFnList = new ArrayList<String>();
									if (fnId != null)
										subMenuFnList.add(fnId);

									subMenuMap.put(subMenuId, subMenuFnList);
								}
							} else {
								subMenuFnList = new ArrayList<String>();
								if (fnId != null)
									subMenuFnList.add(fnId);

								subMenuMap = new LinkedHashMap<Integer, List<String>>();
								subMenuMap.put(subMenuId, subMenuFnList);

								menuMap.put(mainMenuId, subMenuMap);
							}
						} else {
							subMenuFnList = new ArrayList<String>();
							if (fnId != null)
								subMenuFnList.add(fnId);

							subMenuMap = new LinkedHashMap<Integer, List<String>>();
							subMenuMap.put(subMenuId, subMenuFnList);

							menuMap = new LinkedHashMap();
							menuMap.put(mainMenuId, subMenuMap);

							roleMenuDetails.put(roleId, menuMap);
							mainMenuDetailsList.add(roleMenuDetails);
						}

					}
				}
			}
		} catch (Exception e) {
			throw e;
		}

		return mainMenuDetailsList;
	}
}
