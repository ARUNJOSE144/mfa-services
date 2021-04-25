package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.Action;
import com.sixdee.magik.services.model.Sampling;
import com.sixdee.magik.services.model.SamplingTO;
import com.sixdee.magik.services.model.StratSegProfilesTO;
import com.sixdee.magik.services.model.UserSessionTO;
import com.sixdee.magik.services.util.RestInfo;

/**
 * @author Rajesh
 * @category Sampling Service Interface
 * 
 */
public interface SamplingService {
	
	public RestInfo<String> getSamplingNodes();
	
	public void saveSampling(SamplingTO samplingTO,String flag);
	
	public RestInfo<String> getSamplingDetails(int samplingId);
	
	public void renameSampling(int id, String name);
	
	public void deleteSampling(int id);
	
	public Sampling getSamplingJson(int id);

	public List<StratSegProfilesTO> getStratSamplingProfiles();
	
	public List<String> getProfileOptions(String profile);
	
	public List<SamplingTO> getSamplesList(UserSessionTO user);
	
	public List<SamplingTO> getSamplingList(SamplingTO samplingTO);
	
	public Action getRuleActionJson(Action action);
	
}
