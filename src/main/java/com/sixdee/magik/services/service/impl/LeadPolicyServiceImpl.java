package com.sixdee.magik.services.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.LeadPolicyDAO;
import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.WhatIfBasicTO;
import com.sixdee.magik.services.service.LeadPolicyService;


@Service
@Transactional
public class LeadPolicyServiceImpl implements LeadPolicyService {
	
	static final Logger logger = Logger.getLogger(LeadPolicyServiceImpl.class);
	@Autowired
	private LeadPolicyDAO leadPolicyDAO;
	
	
	@Override
	public String createLeadPolicy(LeadPolicyTO leadPolicyTO) {
		// TODO Auto-generated method stub
		return leadPolicyDAO.createLeadPolicy(leadPolicyTO);
	}


	@Override
	public List<LeadPolicyTO> getLeadPolicyList(int userId) {
		// TODO Auto-generated method stub
		return leadPolicyDAO.getLeadPolicyList(userId);
	}


	@Override
	public String deleteLeadPolicy(LeadPolicyTO leadPolicyTO) {
		// TODO Auto-generated method stub
		return leadPolicyDAO.deleteLeadPolicy(leadPolicyTO);
	}


	
	@Override
	public List<WhatIfBasicTO> getWhatIfAnalysisBasicDetails() {
		// TODO Auto-generated method stub
		return leadPolicyDAO.getWhatIfAnalysisBasicDetails();
	}
	
	

}
