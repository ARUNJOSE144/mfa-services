package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.WhatIfBasicTO;

/**
 * @author Rajesh
 * @category LeadPolicyService
 * 
 */
public interface LeadPolicyService {
	
	public String createLeadPolicy(LeadPolicyTO leadPolicyTO);
	
	public List<LeadPolicyTO> getLeadPolicyList(int userId);
	
	public String deleteLeadPolicy(LeadPolicyTO leadPolicyTO);

	public List<WhatIfBasicTO> getWhatIfAnalysisBasicDetails();

}
