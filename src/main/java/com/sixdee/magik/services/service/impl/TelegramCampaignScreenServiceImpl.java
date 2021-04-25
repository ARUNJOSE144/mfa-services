package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.TelegramCampaignScreenDAO;
import com.sixdee.magik.services.dao.WhatsAppCampaignScreenDAO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.TelegramDetailsTO;
import com.sixdee.magik.services.service.TelegramCampaignScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@Service
@Transactional
public class TelegramCampaignScreenServiceImpl implements TelegramCampaignScreenService {
	
	@Autowired
	TelegramCampaignScreenDAO telegramDAO;

	@Override
	public List<MessageLanguagesTO> getLanguages() {
		// TODO Auto-generated method stub
		return telegramDAO.getLanguages();
	}

	@Override
	public List<TelegramDetailsTO> getTelegramData(int userId) {
		// TODO Auto-generated method stub
		return telegramDAO.getTelegramData(userId);
	}

	@Override
	public TelegramDetailsTO getSelectedRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return telegramDAO.getSelectedRecord(msgAutoId);
	}

	@Override
	public String deleteTelegramMsg(int msgAutoId) {
		// TODO Auto-generated method stub
		return telegramDAO.deleteTelegramMsg(msgAutoId);
	}

	@Override
	public String saveorUpdateTelegramData(RequestBodyTO telegramRequestBodyTO) {
		// TODO Auto-generated method stub
		return telegramDAO.saveorUpdateTelegramData(telegramRequestBodyTO);
	}

}
