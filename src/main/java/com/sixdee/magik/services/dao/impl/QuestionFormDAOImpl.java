package com.sixdee.magik.services.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.QuestionFormDAO;
import com.sixdee.magik.services.model.QuestionFormDetailsTO;
import com.sixdee.magik.services.model.QuestionFormInfoTO;
import com.sixdee.magik.services.model.QuestionFormTO;
import com.sixdee.magik.services.model.QuestionTypesTO;
import com.sixdee.magik.services.model.TwitterDetailsTO;
import com.sixdee.magik.services.model.UserMaster;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings({ "unchecked", "deprecation" })
@Repository
public class QuestionFormDAOImpl implements QuestionFormDAO {
	 
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	protected Session currentSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<QuestionTypesTO> getquestionTypes() {
		
		
		List<QuestionTypesTO> list = currentSession().createCriteria(QuestionTypesTO.class).list();
		return list;
	}

	@Override
	public int saveQuestionForm(String questionName,int userId,List<QuestionFormDetailsTO> questionFormDetailsList) {
		
		int statusCode = 1;
		
		
		if(questionFormDetailsList.size()>0)
		{
			String typeDescr ="";
			
			QuestionFormTO  questionFormTO = new  QuestionFormTO();
		      questionFormTO.setQname(questionName);
		      questionFormTO.setCreatedUserId(userId);
		      currentSession().save(questionFormTO);
		      
			for(QuestionFormDetailsTO  transientObj : questionFormDetailsList)
			{
				QuestionFormInfoTO questionFormInfoTO = new QuestionFormInfoTO();
				
				
				   //childTable---parentTable
				     questionFormInfoTO.setQuestionFormTO(questionFormTO);
				
				
				questionFormInfoTO.setQuestion(transientObj.getQuestion());
				questionFormInfoTO.setTypeId(transientObj.getType());
				
				if(transientObj.getMultiplechoice_desc()!=null && !transientObj.getMultiplechoice_desc().equals(""))
				{  typeDescr = transientObj.getMultiplechoice_desc();}
				else if(transientObj.getParagraph_desc()!=null && !transientObj.getParagraph_desc().equals(""))
				{  typeDescr = transientObj.getParagraph_desc();}
				else if(transientObj.getShortansw_desc()!=null && !transientObj.getShortansw_desc().equals(""))
				{  typeDescr = transientObj.getShortansw_desc();}
				else if(transientObj.getCheckbox_desc()!=null && !transientObj.getCheckbox_desc().equals(""))
				{  typeDescr = transientObj.getCheckbox_desc();}
				else if(transientObj.getDropdown_desc()!=null && !transientObj.getDropdown_desc().equals(""))
				{  typeDescr = transientObj.getDropdown_desc();}
				
				questionFormInfoTO.setDescrption(typeDescr);
				
				currentSession().save(questionFormInfoTO);
				statusCode = 0;
			}
		}
		return statusCode;
	}

	@Override
	public List<QuestionFormTO> getquestionFormList(int userId) {
		
		//List<QuestionFormTO> list = currentSession().createCriteria(QuestionFormTO.class).list();
		Criteria cr = currentSession().createCriteria(QuestionFormTO.class);
		cr.addOrder(Order.desc("autoId"));
		if(userId!=1)
		{
			cr.add(Restrictions.eq("createdUserId",userId));
		}
		List<QuestionFormTO> list = cr.list();
		return list;
	}

	@Override
	public List<QuestionFormInfoTO> getquestionDtlsList(Integer questionId) {
		
		/*
		 * CriteriaBuilder builder = currentSession().getCriteriaBuilder();
		 * CriteriaQuery query = builder.createQuery(QuestionFormInfoTO.class);
		 * Root<Object> root = query.from(QuestionFormInfoTO.class);
		 * query.select(root).where(builder.and(builder.equal(root.get(
		 * "questionFormTO.Id"),questionId);
		 */
		String query = " from QuestionFormInfoTO where questionFormTO = '" + questionId + "'";
		List<QuestionFormInfoTO> list = currentSession().createQuery(query).list();
		return list;
	}

	@Override
	public int deleteQuestionForm(String questionId) {
		
		int statuscode = -1;
		String queryStr = null;
		Query query = null;
		try {

			queryStr = "from QuestionFormInfoTO where questionFormTO = '" + questionId + "'";
			List<QuestionFormInfoTO> list = currentSession().createQuery(queryStr).list();
			for(QuestionFormInfoTO obj:list)
			{
				currentSession().delete(obj);
			}
			
			queryStr = "from QuestionFormTO where autoId = '" + questionId + "'";
			QuestionFormTO questionFormTO = (QuestionFormTO)currentSession().createQuery(queryStr).uniqueResult();
			currentSession().delete(questionFormTO);
			
			
				statuscode = 0;

		} catch (Exception e) {
			e.printStackTrace();
			statuscode = -1;
		}
		
		
		
		return statuscode;
	}

}
