/**
 * 
 */
package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.LeadPolicyTO;
import com.sixdee.magik.services.model.WhatIfBasicTO;

/**
 * @author Rajesh
 *
 * Feb,2019
 */
public interface LeadPolicyDAO {

	public String createLeadPolicy(LeadPolicyTO leadPolicyTO);
	
	public List<LeadPolicyTO> getLeadPolicyList(int userId);
	
	public String deleteLeadPolicy(LeadPolicyTO leadPolicyTO);

	public List<WhatIfBasicTO> getWhatIfAnalysisBasicDetails();
}
