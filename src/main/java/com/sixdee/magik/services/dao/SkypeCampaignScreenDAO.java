package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.FacebookDetailsTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.SkypeDetailsTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

public interface SkypeCampaignScreenDAO {
	
	public List<MessageLanguagesTO> getLanguages();
	public List<SkypeDetailsTO> getDetailsData(int userId);
	public SkypeDetailsTO getSelectedRecord(int msgAutoId);
	public String deleteRecord(int msgAutoId);
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO);

}
