package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ContactPolicyFieldDescriptionTO;
import com.sixdee.magik.services.model.ContactPolicyFieldTO;
import com.sixdee.magik.services.model.ContactPolicyTO;


public interface ContactPolicyService {

	
	public List<ContactPolicyTO> getContactPolicies();

	public String createContactPolicy(ContactPolicyTO contactPolicyTO);

	public List<ContactPolicyFieldTO> getSegmentTypes(int id);

	public List<ContactPolicyFieldDescriptionTO> getSegmentCategoryTypes(int categoryField);

	public ContactPolicyTO getContactPolicyInfo(int policyId);

	public String deleteContactPolicy(ContactPolicyTO contactPolicyTO);
}
