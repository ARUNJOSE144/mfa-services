package com.sixdee.magik.services.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.sixdee.magik.services.dao.PackageCategoryLoyaltyDAO;
import com.sixdee.magik.services.model.PackageCategoryTO;;


@Repository
public class PackageCategoryLoyaltyDAOImpl implements PackageCategoryLoyaltyDAO {
	
	@Autowired
	@Qualifier("applicationSessionFactory")
	private SessionFactory sessionFactory;
	
	

	@Override
	public String savePackageCategory(PackageCategoryTO packageCategoryTO) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
