package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.TemplateDao;
import com.sixdee.magik.services.model.TemplateResponseTO;
import com.sixdee.magik.services.model.TemplateTO;

@Repository
@Transactional
public class TemplateDaoImpl implements TemplateDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	

	@Override
	public TemplateResponseTO saveTemplate(TemplateTO templateTO) {
		TemplateResponseTO response = new TemplateResponseTO();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			session.saveOrUpdate(templateTO);
			response.setStatusCode("SC0000");
			response.setStatusDesc("Success");

			System.out.println("Template data successfully inserted");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("Failure");
		} finally {
			templateTO = null;
		}

		return response;
	}


	@Override
	public TemplateResponseTO deleteTemplate(TemplateTO templateTO) {
		TemplateResponseTO response = new TemplateResponseTO();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			int templateId = templateTO.getTemplateId();
			TemplateTO tempTO = new TemplateTO();
			tempTO.setTemplateId(templateId);
			session.delete(tempTO);
			response.setStatusCode("SC0000");
			response.setStatusDesc("Success");

			System.out.println("Template data successfully Deleted");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("Failure");
		} finally {
			templateTO = null;
		}

		return response;
	}


	@SuppressWarnings("unchecked")
	public TemplateResponseTO getTemplate(TemplateTO templateTO) {
		TemplateResponseTO response = new TemplateResponseTO();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			int templateId = templateTO.getTemplateId();
			List<TemplateTO> templates = session.createCriteria(TemplateTO.class).add(Restrictions.eq("templateId",templateId)).list();
			response.setTemplates(templates);
			System.out.println("Templates: "+templates);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			templateTO = null;
		}

		return response;
	}


	@SuppressWarnings("unchecked")
	public TemplateResponseTO viewTemplate(TemplateTO templateTO) {
		TemplateResponseTO response = new TemplateResponseTO();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			String userId = templateTO.getUserId();
			List<TemplateTO> templates = null;
			if(Integer.parseInt(userId)!=1)
			{
				 templates = session.createCriteria(TemplateTO.class).add(Restrictions.eq("userId",userId)).list();
			}
			else
			{
				 templates = session.createCriteria(TemplateTO.class).list();
				
			}
			response.setTemplates(templates);
			System.out.println("TemplatesList: "+templates);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			templateTO = null;
		}

		return response;
	}
}
