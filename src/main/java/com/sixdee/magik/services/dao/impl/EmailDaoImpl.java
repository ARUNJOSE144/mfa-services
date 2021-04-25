package com.sixdee.magik.services.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.EmailDao;
import com.sixdee.magik.services.model.EmailFileTO;
import com.sixdee.magik.services.model.EmailMasterTO;
import com.sixdee.magik.services.model.EmailMenuTO;
import com.sixdee.magik.services.model.EmailMessageTO;
import com.sixdee.magik.services.model.EmailResponseTO;
import com.sixdee.magik.services.model.EventTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;

@Repository
@Transactional
public class EmailDaoImpl implements EmailDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	String pattern = "dd MMM yyyy";
	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
	
	
	public List<EmailMessageTO> getEmailMessages(int menuId) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("unchecked")
		List<EmailMessageTO> list = (List<EmailMessageTO>) session.createCriteria(EmailMessageTO.class).add
				(Restrictions.eq("menuId",menuId)).list();
		
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public EmailMenuTO getEmailMenus(String createUser) {
		// TODO Auto-generated method stub
		Session session = null;
		EmailMenuTO emailMenuTO = null;
		
		try {
			session = sessionFactory.getCurrentSession();
			emailMenuTO = new EmailMenuTO();
			//List<EmailMenuTO> emailMenuList = session.createCriteria(EmailMenuTO.class).add(Restrictions.eq("createUser",createUser)).list();
			Criteria cr = session.createCriteria(EmailMenuTO.class);
			if(Integer.parseInt(createUser)!=1)
			{
				cr.add(Restrictions.eq("createUser",createUser));
			}
			List<EmailMenuTO> emailMenuList = cr.list();
			emailMenuTO = new EmailMenuTO();
			emailMenuTO.setEmailMenuList(emailMenuList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return emailMenuTO;
	}

	@Override
	public EmailResponseTO deleteEmail(int menuId) {
		EmailResponseTO response = new EmailResponseTO();
		EmailMasterTO masterDto = null;

		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			masterDto = new EmailMasterTO();
			masterDto.setMenuId(menuId);
			session.delete(masterDto);
			response.setStatusCode("SC0000");
			response.setStatusDesc("Success");

			System.out.println("Email successfully deleted");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("Failure");
		} finally {
			masterDto = null;
		}

		return response;
	}


	@Override
	public EmailResponseTO saveEmail(EmailMasterTO emailMasterTO) {
		EmailResponseTO response = new EmailResponseTO();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			List<EmailMessageTO> messageDetailsList = emailMasterTO.getMessageDetails();
			for(EmailMessageTO messageDetails:messageDetailsList) {
				messageDetails.setMenuDetails(emailMasterTO);
			}
			if (emailMasterTO.getFileDetails() != null) {
				List<EmailFileTO> fileList = emailMasterTO.getFileDetails();
				for (EmailFileTO fileDetails : fileList) {
					fileDetails.setMenuDetails(emailMasterTO);
				}
			}
			session.saveOrUpdate(emailMasterTO);
			response.setStatusCode("SC0000");
			response.setStatusDesc("Success");

			System.out.println("Email details successfully inserted");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("Failure");
		} finally {
			emailMasterTO = null;
		}

		return response;
	}


	@Override
	public EmailMasterTO getEmail(int menuId) {

		// TODO Auto-generated method stub
		Session session = null;
		EmailMasterTO emailMasterTO = null;
		EmailMasterTO masDto = new EmailMasterTO();

		try {
			session = sessionFactory.getCurrentSession();
			emailMasterTO = session.get(EmailMasterTO.class, menuId);
			masDto.setMenuId(emailMasterTO.getMenuId());
			masDto.setMenuName(emailMasterTO.getMenuName());

			List<EmailMessageTO> tempMapList = new ArrayList<>();
			if (emailMasterTO.getMessageDetails() != null) {
				for (EmailMessageTO dto : emailMasterTO.getMessageDetails()) {
					EmailMessageTO temp = new EmailMessageTO();
					temp.setFileName(dto.getFileName());
					temp.setCreateUser(dto.getCreateUser());
					temp.setFilePath(dto.getFilePath());
					temp.setId(dto.getId());
					temp.setLanguageId(dto.getLanguageId());
					temp.setMessage(dto.getMessage());
					temp.setSubject(dto.getSubject());
					tempMapList.add(temp);
				}
			}
			
			List<EmailFileTO> tempMapList1 = new ArrayList<>();
			if (emailMasterTO.getMessageDetails() != null) {
				for (EmailFileTO dto : emailMasterTO.getFileDetails()) {
					EmailFileTO temp = new EmailFileTO();
					temp.setFileName(dto.getFileName());
					temp.setCreateUser(dto.getCreateUser());
					temp.setEncodedFile(dto.getEncodedFile());
					temp.setId(dto.getId());
					temp.setLanguageId(dto.getLanguageId());
					tempMapList1.add(temp);
				}
			}

			masDto.setMessageDetails(tempMapList);
			masDto.setFileDetails(tempMapList1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return masDto;
	}
	
	@Override
	public EmailResponseTO editEmail(EmailMasterTO emailMasterTO) {
		EmailResponseTO response = new EmailResponseTO();
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
			
			session.delete(emailMasterTO);
			
			
			List<EmailMessageTO> messageDetailsList = emailMasterTO.getMessageDetails();
			for(EmailMessageTO messageDetails:messageDetailsList) {
				messageDetails.setMenuDetails(emailMasterTO);
			}
			if (emailMasterTO.getFileDetails() != null) {
				List<EmailFileTO> fileList = emailMasterTO.getFileDetails();
				for (EmailFileTO fileDetails : fileList) {
					fileDetails.setMenuDetails(emailMasterTO);
				}
			}
			session.saveOrUpdate(emailMasterTO);
			response.setStatusCode("SC0000");
			response.setStatusDesc("Success");

			System.out.println("Email details successfully inserted");

		} catch (Exception e) {
			e.printStackTrace();
			response.setStatusCode("SC0001");
			response.setStatusDesc("Failure");
		} finally {
			emailMasterTO = null;
		}

		return response;
	}
}
