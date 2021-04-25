package com.sixdee.magik.services.dao.impl;

import org.apache.catalina.realm.RealmBase;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.controller.bean.LoginResponse;
import com.sixdee.magik.services.dao.AuditInfoDAO;
import com.sixdee.magik.services.dao.AuthenticationDao;
import com.sixdee.magik.services.model.AuditInfoTO;
import com.sixdee.magik.services.model.AuthenticationTO;
import com.sixdee.magik.services.model.PasswordMgmnt;
import com.sixdee.magik.services.model.UserMaster;
import com.sixdee.magik.services.util.EmailNotification;
import com.sixdee.magik.services.util.PasswordGenerator;
import com.sixdee.magik.services.util.SystemConstants;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Repository
public class AuthenticationDaoImpl implements AuthenticationDao {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	private String query = null;
 
	@Autowired
	AuditInfoDAO auditInfo;
	
	@Autowired
	UserDAOImpl userDAOImpl;
	/*
	 * User and Language validation
	 */
	@Override
	public AuthenticationTO authLoginCredentails(AuthenticationTO authTO) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";

		query = " from AuthenticationTO where userName = '" + authTO.getUserName().toLowerCase() + "'";
		AuthenticationTO to = (AuthenticationTO) session.createQuery(query).uniqueResult();

		if (to != null) {
			if (to.getNoOfAttemps() < 3) {
				if (to.getPassword().equals(authTO.getPassword())) {
					// statusCode = "SC0000";
					to.setStatusCode("SC0000");
					to.setNoOfAttemps(0);
				} else {
					System.out.println("------------Wrong Password--------Login Blocked----------");
					to.setStatusCode("SC0001");
					to.setNoOfAttemps(to.getNoOfAttemps() + 1);
				}
			} else {
				// statusCode = "SC0002";
				to.setStatusCode("SC0002");
				to.setNoOfAttemps(to.getNoOfAttemps() + 1);
				System.out.println("-------------User  Blocked----------Contact Admin");
			}
			session.saveOrUpdate(to);
		} else {
			to = new AuthenticationTO();
			to.setStatusCode("SC0001");
		}

		return to;
	}

	/*
	 * User and Language validation
	 */
	@Override
	public int resetPassword(AuthenticationTO authTO) {
		Session session = sessionFactory.getCurrentSession();

		PasswordGenerator pswdGen;
		EmailNotification emailNotif;

		int statusCode = 0;
		String genPswd;

		System.out.println("User name : " + authTO.getUserName());
		System.out.println("Mail id : " + authTO.getMailId());

		query = " from AuthenticationTO where userName = '" + authTO.getUserName().toLowerCase() + "'";
		AuthenticationTO to = (AuthenticationTO) session.createQuery(query).uniqueResult();

		if (to != null) {
			pswdGen = new PasswordGenerator();
			genPswd = pswdGen.generatePassword();
			System.out.println("Generated Password  >>>> " + genPswd);

			emailNotif = new EmailNotification();
			statusCode = emailNotif.sendMail(authTO.getMailId(), genPswd);

			if (statusCode == 0) {
				query = "update AuthenticationTO set password = '" + genPswd + "', noOfAttemps = 0 where userName = '"
						+ authTO.getUserName() + "'";
				session.createQuery(query).executeUpdate();
			}

		} else {
			statusCode = 4;
		}

		return statusCode;
	}

	@Override
	public int ChangePassword(AuthenticationTO authTO) {
		AuditInfoTO audtoTO = null;
		Session session = sessionFactory.getCurrentSession();

		int statusCode = 1;

		System.out.println("User name : " + authTO.getUserName());
		System.out.println("New Password name : " + authTO.getPassword());

		query = " from AuthenticationTO where userName = '" + authTO.getUserName() + "'";
		AuthenticationTO to = (AuthenticationTO) session.createQuery(query).uniqueResult();

		if (to != null) {
			if (to.getPassword().equals(authTO.getCurrentPassword())) {
				to.setPassword(authTO.getPassword());
				session.update(to);
				statusCode = 0;
				audtoTO = new AuditInfoTO();
				audtoTO.setDesc("Passwored Changed.");
				audtoTO.setUserId(1);
				audtoTO.setFeatureName("Password Changed");
				audtoTO.setAddedName("N/A");
				audtoTO.setActionType("MODIFY");
				audtoTO.setAttribute("N/A");
				audtoTO.setPreviousValue("N/A");
				audtoTO.setNewValue("N/A");
				audtoTO.setCreatedBy("Admin");
				audtoTO.setDesc("Password Changed");
				auditInfo.addAuditLog(audtoTO);
				System.out.println("-----------------------Password Changed Successfully in authenticationTO-----------------");
				 
				
			} else {
				statusCode = 3;
				System.out.println("-----------------------Current Password is not matching-----------------");
			}

		} else {
			statusCode = 2;
			System.out.println("-----------------------Error Decs : Invalid UserName-----------------");

		}

		return statusCode;
	}

	@Override
	public LoginResponse authorize(String userId, String token) {
		Session session = sessionFactory.getCurrentSession();
		String statusCode = "SC0001";
		LoginResponse loginResponse = new LoginResponse();

		try {
			query = " from UserMaster where userId = '" + userId + "'";
			UserMaster to = (UserMaster) session.createQuery(query).uniqueResult();
			loginResponse.setUserId(to.getUserId()+"");
			loginResponse.setUserName(to.getUsername());
			loginResponse.setToken(token);
			loginResponse.setPrivilages(userDAOImpl.getPrivilegesByDesignation(to.getDesignationId()));
			
			System.out.println("privilages list : "+loginResponse.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		return loginResponse;

	}
	
	/**
	 * @addedBy ABHIRAM MACHIRAJU
	 *
	 */
	@Override
	public int ChangeUserPassword(UserMaster usereMasterTO) {
		
		Session session = sessionFactory.getCurrentSession();
		int statusCode = 1;
		System.out.println("User name : " + usereMasterTO.getUsername());
		query = " from UserMaster where userId = '" + usereMasterTO.getUserId()+ "'";
		UserMaster userMaster_DB = (UserMaster) session.createQuery(query).uniqueResult();
		if (userMaster_DB != null) 
			{
				
				String current_EncryptedPassword = RealmBase.Digest(usereMasterTO.getCurrentPassword(), "MD5", "UTF-8"); //From_UI
				if(current_EncryptedPassword!=null && current_EncryptedPassword.equals(userMaster_DB.getPassword())) //UI_ENC_PWD ==DB_ENC_PWD
				{
					userMaster_DB.setPassword(RealmBase.Digest(usereMasterTO.getPassword(), "MD5", "UTF-8"));
					session.update(userMaster_DB);
					statusCode = 0;
				}
				 else {
						statusCode = 3;
						System.out.println("-----------------------Current Password is not matching-----------------");
					}
				
				
			}
		else
			{
				statusCode = 2;
				System.out.println("-----------------------Error Decs : Invalid UserName-----------------");
	
			}
		return statusCode;
	}

}
