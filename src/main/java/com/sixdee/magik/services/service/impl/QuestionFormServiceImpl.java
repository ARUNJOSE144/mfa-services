package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.QuestionFormDAO;
import com.sixdee.magik.services.model.QuestionFormDetailsTO;
import com.sixdee.magik.services.model.QuestionFormInfoTO;
import com.sixdee.magik.services.model.QuestionFormTO;
import com.sixdee.magik.services.model.QuestionTypesTO;
import com.sixdee.magik.services.service.QuestionFormService;

@Service
@Transactional
public class QuestionFormServiceImpl implements QuestionFormService {

	@Autowired
	QuestionFormDAO questionFormDAO;
	
	@Override
	public List<QuestionTypesTO> getquestionTypes() {
		return questionFormDAO.getquestionTypes();
	}

	@Override
	public int saveQuestionForm(String questionName,int userId, List<QuestionFormDetailsTO> questionFormDetailsList) {
		// TODO Auto-generated method stub
		return questionFormDAO.saveQuestionForm(questionName,userId, questionFormDetailsList);
	}

	@Override
	public List<QuestionFormTO> getquestionFormList(int userId) {
		return questionFormDAO.getquestionFormList(userId);
	}

	@Override
	public List<QuestionFormInfoTO> getquestionDtlsList(Integer questionId) {
		return questionFormDAO.getquestionDtlsList(questionId);
	}

	@Override
	public int deleteQuestionForm(String questionId) {
		return questionFormDAO.deleteQuestionForm(questionId);
		
	}
 
}
