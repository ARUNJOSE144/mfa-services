package com.inno.mfa.services.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.inno.mfa.services.dao.QuestionsDAO;
import com.inno.mfa.services.model.CommonRespTo;
import com.inno.mfa.services.model.PaginationTo;
import com.inno.mfa.services.model.QuestionImageTo;
import com.inno.mfa.services.model.QuestionMasterTo;
import com.inno.mfa.services.model.SubjectCategoryTo;

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

	@PostMapping(value = "/question/v1/getImageDetails")
	public @ResponseBody List<QuestionImageTo> getImageDetails(HttpServletRequest httpServletRequest,
			@RequestBody QuestionImageTo imageTo) throws IOException {

		System.out.println("================Search Request : " + imageTo.toString());
		List<QuestionImageTo> list = null;
		try {
			list = questionsDAO.getImageDetails(imageTo);
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
	public @ResponseBody CommonRespTo<QuestionMasterTo> create(@ModelAttribute final QuestionMasterTo questionMasterTo,
			@RequestParam(value = "file1", required = false) MultipartFile file1,
			@RequestParam(value = "file2", required = false) MultipartFile file2,
			@RequestParam(value = "file3", required = false) MultipartFile file3,
			@RequestParam(value = "file4", required = false) MultipartFile file4) throws IOException {
		logger.info("Filesssss=====>" + file1);

		List<MultipartFile> files = new ArrayList<MultipartFile>();
		if (file1 != null)
			files.add(file1);
		if (file2 != null)
			files.add(file2);
		if (file3 != null)
			files.add(file3);
		if (file4 != null)
			files.add(file4);

		questionMasterTo.setFiles(files);

		CommonRespTo<QuestionMasterTo> to = new CommonRespTo<QuestionMasterTo>();
		try {
			questionsDAO.create(questionMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/question/v1/view")
	public @ResponseBody CommonRespTo<QuestionMasterTo> view(HttpServletRequest httpServletRequest,
			@RequestBody QuestionMasterTo dto) throws IOException {
		CommonRespTo<QuestionMasterTo> to = new CommonRespTo<QuestionMasterTo>();
		try {
			to.setData(questionsDAO.view(dto));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@PostMapping(value = "/question/v1/delete")
	public @ResponseBody CommonRespTo<QuestionMasterTo> delete(HttpServletRequest httpServletRequest,
			@RequestBody QuestionMasterTo questionMasterTo) throws IOException {
		CommonRespTo<QuestionMasterTo> to = new CommonRespTo<QuestionMasterTo>();
		try {
			questionsDAO.delete(questionMasterTo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@GetMapping(value = "/question/v1/getSubjectCategories")
	public @ResponseBody CommonRespTo<SubjectCategoryTo> getSubjects(HttpServletRequest httpServletRequest)
			throws IOException {
		CommonRespTo<SubjectCategoryTo> to = new CommonRespTo<SubjectCategoryTo>();
		try {
			to.setList(questionsDAO.getSubjects());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return to;
	}

	@GetMapping("/getDownloadFiles")
	public String getDownloadAttachedFiles(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(name = "imagePath") String imagePath) {

		System.out.println("");
		String resp = "";
		try {
			questionsDAO.downloadFile(req, res, imagePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}

	@GetMapping("/loadFileDataToDB")
	public String loadFileDataToDB(HttpServletRequest req, HttpServletResponse res,
			@RequestParam(name = "filePath") String filePath) {

		System.out.println("Inside File Loading to Table Function  :File Name : " + filePath);
		String resp = "";
		try {
			questionsDAO.loadFileDataToDB(req, res, filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
