package com.inno.mfa.services.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

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
import com.inno.mfa.services.util.Util;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class AuthenticationDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	public LoginTo login() {
		LoginTo loginTo = null;
		UserMasterTo userMasterTo = null;
		loginTo = new LoginTo();
		try {
			Session session = sessionFactory.getCurrentSession();
			userMasterTo = getUser(1, "", session);

			loginTo.setResultCode("0");
			loginTo.setResponseMsg("Success");
			loginTo.setToken("12467e1b-e135-41a3-85a8-d229794308f9");
			loginTo.setRefreshToken("d00aeaef-ae18-4fdd-bbce-9825773026c7");
			loginTo.setUserName(userMasterTo.getUserName());
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
				System.out.println("-=-fEatture Id  : " + rolePermissionsTo.getFeatureId());
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
			} else if (Util.validate(userName)) {
				userMasterTo = (UserMasterTo) session.createCriteria(UserMasterTo.class, "user")
						.add(Restrictions.eq("userName", userName)).uniqueResult();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userMasterTo;
	}

}
