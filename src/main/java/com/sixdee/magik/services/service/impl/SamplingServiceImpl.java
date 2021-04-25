package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.SamplingDAO;
import com.sixdee.magik.services.model.Action;
import com.sixdee.magik.services.model.Sampling;
import com.sixdee.magik.services.model.SamplingTO;
import com.sixdee.magik.services.model.StratSegProfilesTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.service.SamplingService;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author Rajesh
 * @category Sampling Service Implements
 * 
 */

@Service
public class SamplingServiceImpl implements SamplingService {

	static final Logger logger = Logger.getLogger(SamplingServiceImpl.class);
	@Autowired
	SamplingDAO samplingDAO;


	@Override
	public RestInfo<String> getSamplingNodes() {
		// TODO Auto-generated method stub
		return samplingDAO.getSamplingNodes();
	}

	@Override
	public void saveSampling(SamplingTO samplingTO,String flag) {
		// TODO Auto-generated method stub
		samplingDAO.saveSampling(samplingTO,flag);
	}


	@Override
	public RestInfo<String> getSamplingDetails(int samplingId) {
		// TODO Auto-generated method stub
		return samplingDAO.getSamplingDetails(samplingId);
	}
	

	@Override
	public void renameSampling(int id, String name) {
		// TODO Auto-generated method stub
		 samplingDAO.renameSampling(id,name);
	}


	@Override
	public void deleteSampling(int id) {
		// TODO Auto-generated method stub
		samplingDAO.deleteSampling(id);
	}


	@Override
	public Sampling getSamplingJson(int id) {
		// TODO Auto-generated method stub
		return samplingDAO.getSamplingJson(id);
	}


	@Override
	public List<StratSegProfilesTO> getStratSamplingProfiles() {
		// TODO Auto-generated method stub
		return samplingDAO.getStratSamplingProfiles();
	}

	@Override
	public List<String> getProfileOptions(String profile) {
		// TODO Auto-generated method stub
		return samplingDAO.getProfileOptions(profile);
	}

	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.SamplingService#getSamplesList(com.sixdee.magik.services.model.UserSessionTO)
	 */
	@Override
	public List<SamplingTO> getSamplesList(UserSessionTO user) {
		// TODO Auto-generated method stub
		return samplingDAO.getSamplesList(user);
	}

	@Override
	public List<SamplingTO> getSamplingList(SamplingTO samplingTO) {
		// TODO Auto-generated method stub
		return samplingDAO.getSamplingList(samplingTO);
	}
	
	@Override
	public Action getRuleActionJson(Action action) {
		// TODO Auto-generated method stub
		return samplingDAO.getRuleActionJson(action);
	}
}
