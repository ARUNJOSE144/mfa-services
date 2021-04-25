package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.TwitterDetailsTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

public interface TwitterCampaignScreenDAO {
	
	public List<MessageLanguagesTO> getLanguages();
	public List<TwitterDetailsTO> getDetailsData(int userId);
	public TwitterDetailsTO getSelectedRecord(int msgAutoId);
	public String deleteRecord(int msgAutoId);
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO);

}
