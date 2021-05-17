
package com.inno.mfa.services.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.LoginTo;
import com.inno.mfa.services.model.RolePermissionsTo;
import com.inno.mfa.services.model.UserMasterTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class AuthenticationDAO {

	static final Logger logger = Logger.getLogger(AuthenticationDAO.class);
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	@Autowired
	TokenDAO tokenDAO;

	public LoginTo login(LoginTo loginTo) {

		UserMasterTo userMasterTo = null;
		// loginTo = new LoginTo();
		try {
			Session session = sessionFactory.getCurrentSession();

			logger.info("Login : " + loginTo.toString());
			Criteria criteria = session.createCriteria(UserMasterTo.class);
			criteria.add(Restrictions.eq("username", loginTo.getUsername()));
			userMasterTo = (UserMasterTo) criteria.uniqueResult();

			logger.info("User : " + userMasterTo.toString());

			if (userMasterTo != null) {
				if (userMasterTo.getPassword().equals(loginTo.getPassword()) && userMasterTo.getStatus() == 0
						&& userMasterTo.getWrongPasswordAttempts() < 3) {
					String token = UUID.randomUUID().toString();
					int userId = userMasterTo.getUserId();
					tokenDAO.addSession(token, userId);
					logger.info("==========User Loggin success");
					loginTo.setResultCode("0");
					loginTo.setResponseMsg("Success");
					loginTo.setToken(token);
					loginTo.setRefreshToken(UUID.randomUUID().toString());
					loginTo.setUsername(userMasterTo.getUsername());
					loginTo.setUserId(userId);
					loginTo.setFullName(userMasterTo.getName());
					loginTo.setPrivilages(getPrivilages(userMasterTo.getRole().getRoleId(), session));

					userMasterTo.setWrongPasswordAttempts(0);
					session.save(userMasterTo);
				} else if (userMasterTo.getStatus() != 0 || userMasterTo.getWrongPasswordAttempts() >= 3) {
					loginTo.setResultCode("1");
					loginTo.setResponseMsg("User Blocked!");
					logger.info("User Already Blocked :  Incrementing the wrong attepts count");
					userMasterTo.setWrongPasswordAttempts(userMasterTo.getWrongPasswordAttempts() + 1);
					if (userMasterTo.getWrongPasswordAttempts() > 3) {
						userMasterTo.setStatus(1);
					}
					session.update(userMasterTo);
				} else {
					loginTo.setResultCode("2");
					loginTo.setResponseMsg("Wrong Password!");
					logger.info("Wrong Password :  Incrementing the wrong attepts count");
					userMasterTo.setWrongPasswordAttempts(userMasterTo.getWrongPasswordAttempts() + 1);
					if (userMasterTo.getWrongPasswordAttempts() > 3) {
						userMasterTo.setStatus(1);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginTo;
	}

	public LoginTo authorize(int userId) {
		LoginTo loginTo = null;
		UserMasterTo userMasterTo = null;
		loginTo = new LoginTo();
		try {
			Session session = sessionFactory.getCurrentSession();
			userMasterTo = getUser(userId, "", session);

			loginTo.setResultCode("0");
			loginTo.setResponseMsg("Success");
			loginTo.setToken("12467e1b-e135-41a3-85a8-d229794308f9");
			loginTo.setRefreshToken("d00aeaef-ae18-4fdd-bbce-9825773026c7");
			loginTo.setUsername(userMasterTo.getUsername());
			loginTo.setUserId(userMasterTo.getUserId());
			loginTo.setFullName(userMasterTo.getName());
			loginTo.setPrivilages(getPrivilages(userMasterTo.getRole().getRoleId(), session));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loginTo;
	}

	@SuppressWarnings("unchecked")
	List<Integer> getPrivilages(int roleId, Session session) {
		List<RolePermissionsTo> list = null;
		List<Integer> permissions = new ArrayList<Integer>();
		try {
			list = (List<RolePermissionsTo>) session.createCriteria(RolePermissionsTo.class)
					.add(Restrictions.eq("roleId", roleId)).list();
			for (RolePermissionsTo rolePermissionsTo : list) {
				permissions.add(rolePermissionsTo.getFeatureId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return permissions;
	}

	UserMasterTo getUser(int userId, String userName, Session session) {
		UserMasterTo userMasterTo = null;
		try {
			if (userId != 0) {
				userMasterTo = (UserMasterTo) session.createCriteria(UserMasterTo.class, "user")
						.add(Restrictions.eq("id", userId)).uniqueResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMasterTo;
	}

	public void logout(String token, int userId) {
		try {
			tokenDAO.deleteSession(token, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
