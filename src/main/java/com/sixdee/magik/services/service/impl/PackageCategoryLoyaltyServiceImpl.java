package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ConversionLoyaltyDAO;
import com.sixdee.magik.services.model.ConversionTO;
import com.sixdee.magik.services.model.PackageCategoryTO;
import com.sixdee.magik.services.service.PackageCategoryLoyaltyService;

@Service
@Transactional
public class PackageCategoryLoyaltyServiceImpl implements PackageCategoryLoyaltyService {

	@Autowired
	ConversionLoyaltyDAO daoLayer;

	@Override
	public String savePackageCategory(PackageCategoryTO packageCategoryTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
