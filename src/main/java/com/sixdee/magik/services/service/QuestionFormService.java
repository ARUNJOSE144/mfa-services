package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.QuestionFormDetailsTO;
import com.sixdee.magik.services.model.QuestionFormInfoTO;
import com.sixdee.magik.services.model.QuestionFormTO;
import com.sixdee.magik.services.model.QuestionTypesTO;

public interface QuestionFormService {

	public List<QuestionTypesTO> getquestionTypes();
	public int saveQuestionForm(String questionName,int userId,List<QuestionFormDetailsTO> questionFormDetailsList);
	public List<QuestionFormTO> getquestionFormList(int userId);
	public List<QuestionFormInfoTO> getquestionDtlsList(Integer questionId);
	public int deleteQuestionForm(String questionId);
	
}
 