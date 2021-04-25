
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.RoleDao;
import com.sixdee.magik.services.model.ModuleMaster;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.service.RoleService;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleDao roleDao;

	@Override
	public List<ModuleMaster> getRolePermissions() {
		// TODO Auto-generated method stub
		return roleDao.getRolePermissions();
	}

	@Override
	public void saveRole(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		roleDao.saveRole(roleMaster);
	}

	@Override
	public RoleMaster viewRole(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		return roleDao.viewRole(roleMaster);

	}

	@Override
	public List<RoleMaster> getRoleList() {
		// TODO Auto-generated method stub
		return roleDao.getRoleList();
	}

	@Override
	public RoleMaster deleteRole(RoleMaster roleMaster) {
		// TODO Auto-generated method stub
		return roleDao.deleteRole(roleMaster);
	}

	@Override
	public PaginationDTO<RoleMaster> getRoleListPagination(PaginationDTO<RoleMaster> paginationDTO) {
		// TODO Auto-generated method stub
		return roleDao.getRoleListPagination(paginationDTO);
	}

}
