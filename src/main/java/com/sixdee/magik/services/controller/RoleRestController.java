package com.sixdee.magik.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ModuleMaster;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.service.RoleService;
import com.sixdee.magik.services.util.CommonStringUtils;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author arun.jose
 * @Date : Augest, 2010
 *
 */

@RestController
public class RoleRestController {

	static final Logger logger = Logger.getLogger(RoleRestController.class);

	@Autowired
	RoleService roleService;

	@GetMapping(MagikServicePath.GET_ROLE_PERMISSIONS)
	public @ResponseBody RestListInfo<ModuleMaster> getRolePermissions(HttpServletRequest httpServletRequest) {

		logger.info("Class : RoleRestController | Method : getRolePermissions");

		RestListInfo<ModuleMaster> list = new RestListInfo<ModuleMaster>();
		try {
			list.setData(roleService.getRolePermissions());
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : RoleRestController  |  Method : getRolePermissions " + e);
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping(MagikServicePath.CREATE_ROLE)
	public @ResponseBody RestInfo saveRole(HttpServletRequest httpServletRequest, @RequestBody RoleMaster roleMaster) {

		logger.info("Class : RoleRestController | Method : saveRole");

		RestInfo resp = new RestInfo<>();
		try {
			roleService.saveRole(roleMaster);
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {

			if (CommonStringUtils.roleMaster_COnstraintException) {

				System.err.println(
						"------------Duplicate entry,While saving/Modifiying roleMaster-----------------------------");
				resp.setOperationCode(999);
				// resp.setMessage(errorCause);
				CommonStringUtils.roleMaster_COnstraintException = false; // set to normal...
			} else {
				resp.setOperationCode(1);
				ExceptionUtil.handle(e, resp);
			}

			logger.error("Class : RoleRestController  |  Method : saveRole " + e);
			e.printStackTrace();
		}
		return resp;
	}

	@PostMapping(MagikServicePath.VIEW_ROLE)
	public @ResponseBody RestInfo<RoleMaster> viewRole(HttpServletRequest httpServletRequest,
			@RequestBody RoleMaster roleMaster) {

		logger.info("Class : RoleRestController | Method : viewRole");

		RestInfo<RoleMaster> resp = new RestInfo<RoleMaster>();
		try {

			resp.setData(roleService.viewRole(roleMaster));
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : RoleRestController  |  Method : viewRole " + e);
			e.printStackTrace();
		}

		return resp;
	}

	@GetMapping(MagikServicePath.GET_ROLE_LIST)
	public @ResponseBody RestListInfo<RoleMaster> getRoleList(HttpServletRequest httpServletRequest) {

		logger.info("Class : RoleRestController | Method : viewRole");

		RestListInfo<RoleMaster> resp = new RestListInfo<RoleMaster>();
		try {

			resp.setData(roleService.getRoleList());
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : RoleRestController  |  Method : getRoleList " + e);
			e.printStackTrace();
		}

		return resp;
	}

	@PostMapping(MagikServicePath.DELETE_ROLE)
	public @ResponseBody RestInfo<RoleMaster> deleteRole(HttpServletRequest httpServletRequest,
			@RequestBody RoleMaster roleMaster) {

		logger.info("Class : RoleRestController | Method : viewRole");

		RestInfo<RoleMaster> resp = new RestInfo<RoleMaster>();
		try {

			resp.setData(roleService.deleteRole(roleMaster));
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : RoleRestController  |  Method : deleteRole " + e);
			e.printStackTrace();
		}

		return resp;
	}

	@PostMapping(MagikServicePath.GET_ROLE_LIST_PAGINATION)
	public @ResponseBody RestInfo<PaginationDTO<RoleMaster>> getRoleListPagination(
			HttpServletRequest httpServletRequest, @RequestBody PaginationDTO<RoleMaster> paginationDTO) {

		logger.info("Class : RoleRestController | Method : viewRole");

		RestInfo<PaginationDTO<RoleMaster>> resp = new RestInfo<PaginationDTO<RoleMaster>>();
		try {

			resp.setData(roleService.getRoleListPagination(paginationDTO));
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : RoleRestController  |  Method : getRoleList " + e);
			e.printStackTrace();
		}

		return resp;
	}

}
