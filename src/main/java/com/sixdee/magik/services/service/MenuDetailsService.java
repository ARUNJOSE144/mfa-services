/**
 * 
 */
package com.sixdee.magik.services.service;

import java.util.List;
import java.util.Map;

import com.sixdee.magik.services.model.MenuDetailDTO;

/**
 * @author Vinay Kariyappa
 *
 * Sep 26, 2018
 */
public interface MenuDetailsService {

	public List<MenuDetailDTO> getMenus(int parentId,int userId);
	
	public List<Map<Integer,MenuDetailDTO>> getMainMenuDetails(Integer roleId) throws Exception;
	
	public List<Map<Integer, Map<Integer, List<String>>>> getSubMenuDetails() throws Exception;
	
	public List<MenuDetailDTO> getAllRoleMenuDetails(Integer roleId, Integer mainMenuId) throws Exception;
}
