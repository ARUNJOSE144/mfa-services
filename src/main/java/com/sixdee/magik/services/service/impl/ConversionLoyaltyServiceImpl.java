package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ConversionLoyaltyDAO;
import com.sixdee.magik.services.model.ConversionTO;
import com.sixdee.magik.services.service.ConversionLoyaltyService;

@Service
@Transactional
public class ConversionLoyaltyServiceImpl implements ConversionLoyaltyService {

	@Autowired
	ConversionLoyaltyDAO daoLayer;

	@Override
	public String saveConversion(ConversionTO tierDetailsTO) {
		// TODO Auto-generated method stub
		return null;
	}

}
