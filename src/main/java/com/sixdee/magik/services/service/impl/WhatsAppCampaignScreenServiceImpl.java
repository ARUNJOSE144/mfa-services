package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.WhatsAppCampaignScreenDAO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.OptionTransientTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;
import com.sixdee.magik.services.model.WhatsAppMessageDetailsTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WhatsAppDetailsCategoryTO;
import com.sixdee.magik.services.service.WhatsAppCampaignScreenService;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@Service
@Transactional
public class WhatsAppCampaignScreenServiceImpl implements WhatsAppCampaignScreenService {

	 
	@Autowired
	WhatsAppCampaignScreenDAO whatsAppDAO;
	
	@Override
	public List<MessageLanguagesTO> getLanguages() {
		// TODO Auto-generated method stub
		return whatsAppDAO.getLanguages();
	}

	@Override
	public List<WhatsAppMessageDetailsTO> saveWhatsAppMsg(String messageName, List<OptionTransientTO> languages, String messageDesc, String userId) {
		// TODO Auto-generated method stub
		return whatsAppDAO.saveWhatsAppMsg(messageName,languages,messageDesc,userId);
	}

	@Override
	public List<WhatsAppDetailsTO> getWhatsAppData(int userId,int categoryId) {
		return whatsAppDAO.getWhatsAppData(userId,categoryId);
	}

	@Override
	public WhatsAppDetailsTO getSelectedRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return whatsAppDAO.getSelectedRecord(msgAutoId);
	}

	@Override
	public List<WhatsAppMessageDetailsTO> updateWhatsAppMsg(int messageAutoId, String messageName,
			List<OptionTransientTO> languages, String messageDesc, String userId) {
		return whatsAppDAO.updateWhatsAppMsg(messageAutoId,messageName,languages,messageDesc,userId);
	}

	@Override
	public String deleteWhatsAppMsg(int msgAutoId) {
		// TODO Auto-generated method stub
		return whatsAppDAO.deleteWhatsAppMsg(msgAutoId);
	}

	@Override
	public String saveorUpdateWhatsAppData(RequestBodyTO whatsAppRequestBodyTO) {
		return whatsAppDAO.saveorUpdateWhatsAppData(whatsAppRequestBodyTO);
	}

	@Override
	public List<WhatsAppDetailsCategoryTO> getWhatsAppCategories(int userId) {
		return whatsAppDAO.getWhatsAppCategories(userId);
	}

	@Override
	public String saveorUpdateWhatsAppCategoryData(WhatsAppDetailsCategoryTO whatsAppRequestBodyTO) {
		return whatsAppDAO.saveorUpdateWhatsAppCategoryData(whatsAppRequestBodyTO);
	}

	@Override
	public String getSelectedCategoryRecord(int msgAutoId) {
		return whatsAppDAO.getSelectedCategoryRecord(msgAutoId);
	}

}
