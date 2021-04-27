package com.inno.mfa.services.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.inno.mfa.services.model.RolesTo;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Repository
@Service
@Transactional
public class RolesDAO {

	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;

	public List<RolesTo> getRolesList(RolesTo rolesTo) {
		// TODO Auto-generated method stub
		List<RolesTo> list = new ArrayList<RolesTo>();
		try {
			Session session = sessionFactory.getCurrentSession();
			Criteria cr_mod = session.createCriteria(RolesTo.class);
			list = cr_mod.list();

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return list;
	}
}
