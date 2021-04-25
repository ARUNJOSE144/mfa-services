/**
 * 
 */
package com.sixdee.magik.services.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.MenuDetailsDAO;
import com.sixdee.magik.services.model.MenuDetailDTO;
import com.sixdee.magik.services.service.MenuDetailsService;

/**
 * @author Vinay Kariyappa
 *
 * Sep 26, 2018
 */
@Service
@Transactional
public class MenuDetailsServiceImpl implements MenuDetailsService {

	@Autowired
	private MenuDetailsDAO menuDetailsDAO;
	
	@Override
	public List<MenuDetailDTO> getMenus(int parentId,int userId) {
		return menuDetailsDAO.getMenus(parentId,userId);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.MenuDetailsService#getMainMenuDetails()
	 */
	@Override
	public List<Map<Integer, MenuDetailDTO>> getMainMenuDetails(Integer roleId) throws Exception {
		
		Map	MAIN_MENU_DETAILS	= Collections.synchronizedMap(new HashMap());
		List mainMenuList = new ArrayList();
		List<Map<Integer,MenuDetailDTO>> allMenuDetailsMapList = menuDetailsDAO.getMainMenuDetails(roleId);
		 List<Map<Integer, Map<Integer, List<String>>>> menuDetailsList = menuDetailsDAO.getSubMenuDetails();
		 if(allMenuDetailsMapList != null && menuDetailsList!= null) {
			Map<Integer, Map<Integer, List<String>>> menuDetails = menuDetailsList.get(0);
			
			Map mainMenuDetails = (Map) menuDetails.get(roleId);
			if (mainMenuDetails == null)
				return null;

			Set set = mainMenuDetails.keySet();
			Iterator it = set.iterator();
				
			MenuDetailDTO mdto = null;
			MenuDetailDTO tdto = null;

			//MenuOrder = menuId inside menuOrderList
			List menuOrderList = new ArrayList();
			List tempMainMenuList = new ArrayList();
			
			Integer menuId = null;
			while (it.hasNext()) {
				menuId = (Integer) it.next();
				mdto = new MenuDetailDTO();
				subMenuLoop: for(Map<Integer,MenuDetailDTO> subMenu : allMenuDetailsMapList) {
					tdto = (MenuDetailDTO) subMenu.get(menuId);
					if(tdto != null) {
						break subMenuLoop;
					}
				}
				mdto.setMenuId(menuId);
				mdto.setUrl(tdto.getUrl());
				mdto.setMenuName(tdto.getMenuName());
				//Logic for sorting 
				menuOrderList.add(tdto.getMenuOrder()+1000 + "=" + menuId);
				tempMainMenuList.add(mdto);
			}
			
			
			//This is because of maintain menu in order
			Collections.sort(menuOrderList);
			StringTokenizer st = null;			
			for (int i = 0; menuOrderList != null && i < menuOrderList.size(); i++) {
				st = new StringTokenizer(menuOrderList.get(i).toString(),"=");
				if(st.hasMoreElements() && st.hasMoreElements()) {
					st.nextElement();
					int id = Integer.parseInt(st.nextElement().toString());
					for (int j = 0; tempMainMenuList != null && j < tempMainMenuList.size(); j++) {
						if ((mdto =(MenuDetailDTO)tempMainMenuList.get(j)).getMenuId() == id) {
							mainMenuList.add(mdto);
							break;
						}
					}
				}
			}
			
			MAIN_MENU_DETAILS.put(roleId, mainMenuList);
		 }
		 return (List) MAIN_MENU_DETAILS.get(roleId);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.MenuDetailsService#getSubMenuDetails()
	 */
	@Override
	public List<Map<Integer, Map<Integer, List<String>>>> getSubMenuDetails() throws Exception {
		return menuDetailsDAO.getSubMenuDetails();
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.MenuDetailsService#getAllRoleMenuDetails()
	 */
	@Override
	public List<MenuDetailDTO> getAllRoleMenuDetails(Integer roleId, Integer mainMenuId) throws Exception {
		// TODO Auto-generated method stub
		Map SUB_MENU_DETAILS = Collections.synchronizedMap(new HashMap());
		String key = roleId + "." + mainMenuId;
		List<Map<Integer,MenuDetailDTO>> allMenuDetailsMapList = menuDetailsDAO.getMainMenuDetails(roleId);
		List<Map<Integer, Map<Integer, List<String>>>> menuDetailsList = menuDetailsDAO.getSubMenuDetails();
		
		if(allMenuDetailsMapList != null && menuDetailsList!= null) {
			Map<Integer, Map<Integer, List<String>>> menuDetails = menuDetailsList.get(0);
			List<MenuDetailDTO> subMenuList = new ArrayList<MenuDetailDTO>();
		
			Map<Integer, List<String>> mainMenuDetails = (Map<Integer, List<String>>) menuDetails.get(roleId);
			if (mainMenuDetails == null)
				return null;

			Map subMenuDetails = (Map) mainMenuDetails.get(mainMenuId);
			if (subMenuDetails == null)
				return null;
			
			Set set = subMenuDetails.keySet();
			Iterator it = set.iterator();

			MenuDetailDTO mdto = null;
			MenuDetailDTO tdto = null;
			
			Integer subMenuId = null;
			while (it.hasNext()) {
				subMenuId = (Integer) it.next();
				mdto = new MenuDetailDTO();
				subMenuLoop: for(Map<Integer,MenuDetailDTO> subMenu : allMenuDetailsMapList) {
					tdto = (MenuDetailDTO) subMenu.get(subMenuId);
					if(tdto != null) {
						break subMenuLoop;
					}
				}
				mdto.setMenuId(subMenuId);
				mdto.setUrl(tdto.getUrl());
				mdto.setMenuName(tdto.getMenuName());
				mdto.setSubMenuFnList((List) subMenuDetails.get(subMenuId));
				subMenuList.add(mdto);
			}
			SUB_MENU_DETAILS.put(key, subMenuList);
		}
		
		return (List) SUB_MENU_DETAILS.get(key);
	}
}
