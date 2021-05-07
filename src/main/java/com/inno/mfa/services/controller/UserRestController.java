package com.inno.mfa.services.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inno.mfa.services.dao.UsersDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.UserMasterTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class UserRestController {

	static final Logger logger = Logger.getLogger(UserRestController.class);
	@Autowired
	UsersDAO usersDao;

	@PostMapping(value = "/user/v1/search")
	public @ResponseBody PaginationTo<UserMasterTo> getUsersList(HttpServletRequest httpServletRequest,
			@RequestBody PaginationTo<UserMasterTo> paginationTo) throws IOException {
		System.out.println("================Search Request : " + paginationTo.toString());
		try {
			usersDao.getUsersList(paginationTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginationTo;
	}

	@PostMapping(value = "/user/v1/create")
	public @ResponseBody CommonRespTo<UserMasterTo> create(HttpServletRequest httpServletRequest,
			@RequestBody UserMasterTo userMasterTo) throws IOException {
		CommonRespTo<UserMasterTo> to = new CommonRespTo<UserMasterTo>();
		try {
			usersDao.create(userMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	/*
	 * @PostMapping(value = "/role/v1/view") public @ResponseBody
	 * CommonRespTo<RolesTo> view(HttpServletRequest
	 * httpServletRequest, @RequestBody RolesTo rolesTo) throws IOException {
	 * CommonRespTo<RolesTo> to = new CommonRespTo<RolesTo>(); try {
	 * to.setData(usersDao.view(rolesTo)); } catch (Exception e) {
	 * e.printStackTrace(); } return to; }
	 */

	@PostMapping(value = "/user/v1/delete")
	public @ResponseBody CommonRespTo<UserMasterTo> delete(HttpServletRequest httpServletRequest,
			@RequestBody UserMasterTo userMasterTo) throws IOException {
		CommonRespTo<UserMasterTo> to = new CommonRespTo<UserMasterTo>();
		try {
			usersDao.delete(userMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

}
