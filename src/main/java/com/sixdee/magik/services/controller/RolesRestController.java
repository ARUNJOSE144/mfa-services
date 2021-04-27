package com.sixdee.magik.services.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.dao.RolesDAO;
import com.sixdee.magik.services.model.CommonRespTo;
import com.sixdee.magik.services.model.RolesTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class RolesRestController {

	static final Logger logger = Logger.getLogger(RolesRestController.class);
	@Autowired
	RolesDAO rolesDao;

	@PostMapping(value = "/getRoles")
	public @ResponseBody CommonRespTo<RolesTo> getRolesList(HttpServletRequest httpServletRequest,
			@RequestBody RolesTo rolesTo) throws IOException {
		List<RolesTo> list = null;
		CommonRespTo<RolesTo> to = new CommonRespTo<RolesTo>();
		try {
			list = rolesDao.getRolesList(rolesTo);
			to.setList(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

}
