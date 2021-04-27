package com.inno.mfa.services.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.LoginTo;
import com.inno.mfa.services.model.RolePermissionsTo;

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
		// TODO Auto-generated method stub
		LoginTo loginTo = null;
		try {
			Session session = sessionFactory.getCurrentSession();
			loginTo = new LoginTo();

			loginTo.setResultCode("0");
			loginTo.setResponseMsg("Success");
			loginTo.setToken("12467e1b-e135-41a3-85a8-d229794308f9");
			loginTo.setRefreshToken("d00aeaef-ae18-4fdd-bbce-9825773026c7");
			loginTo.setUserName("c3lzYWRtaW4=");
			loginTo.setUserId(1);
			loginTo.setFullName("SysAdmin SysAdmin");
			// List<Integer> privilages =

			loginTo.setPrivilages(getPrivilages(1, session));
		} catch (Exception e) {
			// TODO: handle exception
		}

		return loginTo;
	}

	List<Integer> getPrivilages(int roleId, Session session) {
		List<RolePermissionsTo> list = null;
		List<Integer> permissions = new ArrayList<Integer>();
		try {
			list = (List<RolePermissionsTo>) session.createCriteria(RolePermissionsTo.class).list();
			for (RolePermissionsTo rolePermissionsTo : list) {
				System.out.println("-=-fEatture Id  : " + rolePermissionsTo.getFeatureId());
				permissions.add(rolePermissionsTo.getFeatureId());
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return permissions;

	}

}
