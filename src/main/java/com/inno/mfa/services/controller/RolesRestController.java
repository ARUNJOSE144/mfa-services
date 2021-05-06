package com.inno.mfa.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inno.mfa.services.dao.RolesDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.ModuleMasterTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.RolesTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class RolesRestController {

	static final Logger logger = Logger.getLogger(RolesRestController.class);
	@Autowired
	RolesDAO rolesDao;

	@PostMapping(value = "/role/v1/search")
	public @ResponseBody PaginationTo<RolesTo> getRolesList(HttpServletRequest httpServletRequest,
			@RequestBody PaginationTo<RolesTo> paginationTo) throws IOException {
		System.out.println("================Search Request : " + paginationTo.toString());
		try {
			rolesDao.getRolesList(paginationTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginationTo;
	}

	@GetMapping(value = "role/v1/getModulePermissions")
	public @ResponseBody CommonRespTo<ModuleMasterTo> getModulePermission(HttpServletRequest httpServletRequest)
			throws IOException {
		CommonRespTo<ModuleMasterTo> to = new CommonRespTo<ModuleMasterTo>();
		try {
			rolesDao.getModulePermission(to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/role/v1/create")
	public @ResponseBody CommonRespTo<ModuleMasterTo> create(HttpServletRequest httpServletRequest,
			@RequestBody RolesTo rolesTo) throws IOException {
		CommonRespTo<ModuleMasterTo> to = new CommonRespTo<ModuleMasterTo>();
		try {
			rolesDao.create(rolesTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/role/v1/view")
	public @ResponseBody CommonRespTo<RolesTo> view(HttpServletRequest httpServletRequest, @RequestBody RolesTo rolesTo)
			throws IOException {
		CommonRespTo<RolesTo> to = new CommonRespTo<RolesTo>();
		try {
			to.setData(rolesDao.view(rolesTo));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/role/v1/delete")
	public @ResponseBody CommonRespTo<RolesTo> delete(HttpServletRequest httpServletRequest,
			@RequestBody RolesTo rolesTo) throws IOException {
		CommonRespTo<RolesTo> to = new CommonRespTo<RolesTo>();
		try {
			rolesDao.delete(rolesTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

}
