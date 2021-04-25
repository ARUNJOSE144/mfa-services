package com.sixdee.magik.services.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.QuestionFormDetailsTO;
import com.sixdee.magik.services.model.QuestionFormInfoTO;
import com.sixdee.magik.services.model.QuestionFormTO;
import com.sixdee.magik.services.model.QuestionTypesTO;
import com.sixdee.magik.services.service.QuestionFormService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

 
@RestController
public class QuestionFormCampaignScreenRestController {
	
	static final Logger logger = Logger.getLogger(QuestionFormCampaignScreenRestController.class);

	@Autowired
	QuestionFormService questionFormService;
	
	@GetMapping(MagikServicePath.GET_QUESTIONTYPES)
	public @ResponseBody RestListInfo<QuestionTypesTO> getQuestiontypes(HttpServletRequest httpServletRequest) {

		logger.info("================== QuestionFormRestController API Start =====================");
		logger.info("Class : QuestionFormRestController | Method : getQuestiontypes");
		RestListInfo<QuestionTypesTO> info = new RestListInfo<QuestionTypesTO>();
		try {
			info.setData(questionFormService.getquestionTypes());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuestionFormRestController  |  Method : getQuestiontypes " + e);
			e.printStackTrace();
		}
		logger.info("================== QuestionFormRestController API End =====================");
		return info;
	}
	
	
	@PostMapping(MagikServicePath.SAVE_QUESTIONFORM)
	public @ResponseBody RestInfo<String> saveForm(HttpServletRequest httpServletRequest,@RequestParam(value = "Qname") String QuestionName,@RequestParam(value = "userId") int userId,@RequestBody  List<QuestionFormDetailsTO> questionFormDetailsList) {
		
		logger.info("================== QuestionFormRestController API Start =====================");
		logger.info("Class : QuestionFormRestController | Method : saveForm");
		
		RestInfo<String> info = new RestInfo<String>();
		int statusCode = -1;
		
				try {
						statusCode = questionFormService.saveQuestionForm(QuestionName,userId,questionFormDetailsList);
						info.setOperationCode(statusCode);
						//info.setData(CacheDaoImpl.messages.get(statusCode));
						info.setMessage("Success");
				   } 
				catch (Exception e) 
				   {
						ExceptionUtil.handle(e, info);
						logger.error("Class : QuestionFormRestController  |  Method : saveForm " + e);
						e.printStackTrace();
						info.setMessage("Failure");
				   }
				
				logger.info("==================QuestionFormRestController API =====================");

         return info;
	}
	
	
	@GetMapping(MagikServicePath.GET_QUESTION_FORMDATA)
	public @ResponseBody RestListInfo<QuestionFormTO> viewQuestionForm(HttpServletRequest httpServletRequest,@RequestParam(value = "userId") int userId) {

		logger.info("Class : QuestionFormRestController API | Method : viewQuestionForm");

		RestListInfo<QuestionFormTO> resp = new RestListInfo<QuestionFormTO>();
		try {

			resp.setData(questionFormService.getquestionFormList(userId));
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : QuestionFormRestController  |  Method : viewQuestionForm " + e);
			e.printStackTrace();
		}

		return resp;
	}
	
	@GetMapping(MagikServicePath.GET_QUESTION_INFODATA)
	public @ResponseBody RestInfo<List<QuestionFormInfoTO>> getQuestionInfoData(HttpServletRequest httpServletRequest,@RequestParam(value = "questionId") String questionId) {
		
		logger.info("Class : QuestionFormRestController API | Method : getQuestionInfoData");
		
		RestInfo<List<QuestionFormInfoTO>> resp = new RestInfo<List<QuestionFormInfoTO>>();
		try 
		{

			resp.setData(questionFormService.getquestionDtlsList(Integer.parseInt(questionId)));
			resp.setOperationCode(0);
			//resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : QuestionFormRestController  |  Method : getQuestionInfoData " + e);
			e.printStackTrace();
		}
		
		
	
		return resp;
	}
	
	@PostMapping(MagikServicePath.DELETE_QUESTION_INFODATA)
	public @ResponseBody RestInfo<String> deleteQuestionInfoData(HttpServletRequest httpServletRequest,@RequestParam(value = "questionAutoId",required = true) String questionId) {
		
		logger.info("Class : QuestionFormRestController API | Method : deleteQuestionInfoData");
		int statusCode=-1;
		RestInfo<String> info = new RestInfo<String>();
		try {

			 statusCode=questionFormService.deleteQuestionForm(questionId);
			info.setOperationCode(statusCode);
			info.setMessage("Success");
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : QuestionFormRestController  |  Method : deleteQuestionInfoData " + e);
			e.printStackTrace();
		}
		return info;
	}
	 

}
