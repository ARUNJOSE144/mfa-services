package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.TelegramDetailsTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
public interface TelegramCampaignScreenDAO {
	
	public List<MessageLanguagesTO> getLanguages();
	public List<TelegramDetailsTO> getTelegramData(int userId);
	public TelegramDetailsTO getSelectedRecord(int msgAutoId);
	public String deleteTelegramMsg(int msgAutoId);
	public String saveorUpdateTelegramData(RequestBodyTO telegramRequestBodyTO);

}
