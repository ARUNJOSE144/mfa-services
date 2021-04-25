package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ModuleMaster;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

public interface RoleService {

	List<ModuleMaster> getRolePermissions();

	void saveRole(RoleMaster roleMaster);

	RoleMaster viewRole(RoleMaster roleMaster);

	List<RoleMaster> getRoleList();

	RoleMaster deleteRole(RoleMaster roleMaster);

	PaginationDTO<RoleMaster> getRoleListPagination(PaginationDTO<RoleMaster> paginationDTO);

}
