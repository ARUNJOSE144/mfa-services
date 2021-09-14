package com.inno.mfa.services.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.inno.mfa.services.dao.QuestionsDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.QuestionMasterTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@RestController
public class QuestionssRestController {

	static final Logger logger = Logger.getLogger(QuestionssRestController.class);
	@Autowired
	QuestionsDAO questionsDAO;

	@PostMapping(value = "/question/v1/search")
	public @ResponseBody PaginationTo<QuestionMasterTo> getRolesList(HttpServletRequest httpServletRequest,
			@RequestBody PaginationTo<QuestionMasterTo> paginationTo) throws IOException {
		System.out.println("================Search Request : " + paginationTo.toString());
		try {
			questionsDAO.getQustionList(paginationTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return paginationTo;
	}

	@PostMapping(value = "/question/v1/searchQuestion")
	public @ResponseBody List<QuestionMasterTo> searchQuestion(HttpServletRequest httpServletRequest,
			@RequestBody QuestionMasterTo searchTO) throws IOException {

		System.out.println("================Search Request : " + searchTO.toString());
		List<QuestionMasterTo> list = null;
		try {
			list = questionsDAO.searchQuestion(searchTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*
	 * @GetMapping(value = "question/v1/getModulePermissions") public @ResponseBody
	 * CommonRespTo<ModuleMasterTo> getModulePermission(HttpServletRequest
	 * httpServletRequest) throws IOException { CommonRespTo<ModuleMasterTo> to =
	 * new CommonRespTo<ModuleMasterTo>(); try { rolesDao.getModulePermission(to); }
	 * catch (Exception e) { e.printStackTrace(); } return to; }
	 */
	@PostMapping(value = "/question/v1/create")
	public @ResponseBody CommonRespTo<QuestionMasterTo> create(HttpServletRequest httpServletRequest,
			@RequestBody QuestionMasterTo questionMasterTo) throws IOException {
		CommonRespTo<QuestionMasterTo> to = new CommonRespTo<QuestionMasterTo>();
		try {
			questionsDAO.create(questionMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	/*
	 * @PostMapping(value = "/question/v1/view") public @ResponseBody
	 * CommonRespTo<RolesTo> view(HttpServletRequest
	 * httpServletRequest, @RequestBody RolesTo rolesTo) throws IOException {
	 * CommonRespTo<RolesTo> to = new CommonRespTo<RolesTo>(); try {
	 * to.setData(rolesDao.view(rolesTo)); } catch (Exception e) {
	 * e.printStackTrace(); } return to; }
	 * 
	 * @PostMapping(value = "/question/v1/delete") public @ResponseBody
	 * CommonRespTo<RolesTo> delete(HttpServletRequest httpServletRequest,
	 * 
	 * @RequestBody RolesTo rolesTo) throws IOException { CommonRespTo<RolesTo> to =
	 * new CommonRespTo<RolesTo>(); try { rolesDao.delete(rolesTo); } catch
	 * (Exception e) { e.printStackTrace(); } return to; }
	 * 
	 * @GetMapping(value = "/question/v1/getAllRoles") public @ResponseBody
	 * CommonRespTo<RolesTo> getAllRoles(HttpServletRequest httpServletRequest)
	 * throws IOException { CommonRespTo<RolesTo> to = new CommonRespTo<RolesTo>();
	 * try { to.setList(rolesDao.getAllRoles()); } catch (Exception e) {
	 * e.printStackTrace(); } return to; }
	 */
}
