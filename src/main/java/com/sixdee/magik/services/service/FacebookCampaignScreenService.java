package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.FacebookDetailsTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
public interface FacebookCampaignScreenService {
	
	
	public List<MessageLanguagesTO> getLanguages();
	public List<FacebookDetailsTO> getDetailsData(int userId);
	public FacebookDetailsTO getSelectedRecord(int msgAutoId);
	public String deleteRecord(int msgAutoId);
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO);

}
