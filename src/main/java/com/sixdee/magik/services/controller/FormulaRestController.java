package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.FormulaTO;
import com.sixdee.magik.services.service.FormulaService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author arun.jose
 * @Date : November, 2018
 *
 */
@RestController
public class FormulaRestController {

	static final Logger logger = Logger.getLogger(FormulaRestController.class);
	@Autowired
	FormulaService formulaService;

	@GetMapping(MagikServicePath.GET_FORMULA)
	public @ResponseBody RestInfo<FormulaTO> getFormula(HttpServletRequest httpServletRequest) {

		logger.info("================== FormulaRestController Rest Controllar API Start =====================");
		logger.info("Class : FormulaRestController | Method : saveFunctionMetrics");
		FormulaTO formulaTO = new FormulaTO();
		if (httpServletRequest.getParameter("userId") != null && !httpServletRequest.getParameter("userId").trim().equalsIgnoreCase(""))
			formulaTO.setUserId(Integer.parseInt(httpServletRequest.getParameter("userId")));
		
		if (httpServletRequest.getParameter("formulaId") != null && !httpServletRequest.getParameter("formulaId").trim().equalsIgnoreCase(""))
			formulaTO.setId(Integer.parseInt(httpServletRequest.getParameter("formulaId")));
		
		
		RestInfo<FormulaTO> info = new RestInfo<FormulaTO>();

		try {
			info.setData(formulaService.getFormula(formulaTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FormulaRestController  |  Method : saveFunctionMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== FormulaRestController Rest Controllar API End =====================");
		return info;
	}
	
	
	@GetMapping(MagikServicePath.GET_FORMULA_LIST)
	public @ResponseBody RestListInfo<FormulaTO> getFormulaList(HttpServletRequest httpServletRequest) {

		logger.info("================== FormulaRestController Rest Controllar API Start =====================");
		logger.info("Class : FormulaRestController | Method : saveFunctionMetrics");
		FormulaTO formulaTO = new FormulaTO();
		if (httpServletRequest.getParameter("userId") != null
				&& !httpServletRequest.getParameter("userId").trim().equalsIgnoreCase(""))
			formulaTO.setUserId(Integer.parseInt(httpServletRequest.getParameter("userId")));
		RestListInfo<FormulaTO> info = new RestListInfo<FormulaTO>();

		try {
			info.setData(formulaService.getFormulaList(formulaTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : FormulaRestController  |  Method : saveFunctionMetrics " + e);
			e.printStackTrace();
		}

		logger.info("================== FormulaRestController Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.SAVE_FORMULA)
	public @ResponseBody RestInfo<String> saveFormula(HttpServletRequest httpServletRequest,
			@RequestBody FormulaTO formulaTO) {
		System.out.println("--------------------------call landed--------------------");

		logger.info("================== FormulaRestController  API Start =====================");
		logger.info("Class : FormulaRestController | Method : saveTrigger");

		RestInfo<String> info = new RestInfo<String>();

		try {
			formulaService.saveFormula(formulaTO);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : FormulaRestController  |  Method : saveTrigger " + e);
			e.printStackTrace();
		}

		logger.info("================== Trigger Rest Controllar API End =====================");

		return info;
	}
	
	
	
	@PostMapping(MagikServicePath.DELETE_FORMULA)
	public @ResponseBody RestInfo<String> deleteFormula(HttpServletRequest httpServletRequest,
			@RequestBody FormulaTO formulaTO) {
		System.out.println("--------------------------call landed--------------------");

		logger.info("================== FormulaRestController  API Start =====================");
		logger.info("Class : FormulaRestController | Method : saveTrigger");

		RestInfo<String> info = new RestInfo<String>();
		

		try {
			formulaService.deleteFormula(formulaTO);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : FormulaRestController  |  Method : saveTrigger " + e);
			e.printStackTrace();
		}

		logger.info("================== Trigger Rest Controllar API End =====================");

		return info;
	}

}
