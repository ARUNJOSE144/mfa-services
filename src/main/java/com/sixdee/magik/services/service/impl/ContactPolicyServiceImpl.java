package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.ContactPolicyDAO;
import com.sixdee.magik.services.model.ContactPolicyFieldDescriptionTO;
import com.sixdee.magik.services.model.ContactPolicyFieldTO;
import com.sixdee.magik.services.model.ContactPolicyTO;
import com.sixdee.magik.services.service.ContactPolicyService;

@Service
public class ContactPolicyServiceImpl implements ContactPolicyService {

	static final Logger logger = Logger.getLogger(ContactPolicyServiceImpl.class);

	@Autowired
	ContactPolicyDAO contactPolicyDAO;

	@Override
	public List<ContactPolicyTO> getContactPolicies() {
		// TODO Auto-generated method stub
		return contactPolicyDAO.getContactPolicies();
	}

	@Override
	public String createContactPolicy(ContactPolicyTO contactPolicyTO) {
		// TODO Auto-generated method stub
		return contactPolicyDAO.createContactPolicy(contactPolicyTO);
	}

	@Override
	public List<ContactPolicyFieldTO> getSegmentTypes(int id) {
		// TODO Auto-generated method stub
		return contactPolicyDAO.getSegmentTypes(id);
	}

	@Override
	public List<ContactPolicyFieldDescriptionTO> getSegmentCategoryTypes(int categoryField) {
		// TODO Auto-generated method stub
		return contactPolicyDAO.getSegmentCategoryTypes(categoryField);

	}

	@Override
	public ContactPolicyTO getContactPolicyInfo(int policyId) {
		// TODO Auto-generated method stub
		return contactPolicyDAO.getContactPolicyInfo(policyId);
	}

	@Override
	public String deleteContactPolicy(ContactPolicyTO contactPolicyTO) {
		// TODO Auto-generated method stub
		return contactPolicyDAO.deleteContactPolicy(contactPolicyTO);
	}
}