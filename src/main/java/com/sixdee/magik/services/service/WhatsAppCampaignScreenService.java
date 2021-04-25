package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.OptionTransientTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WhatsAppDetailsCategoryTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;
import com.sixdee.magik.services.model.WhatsAppMessageDetailsTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
public interface WhatsAppCampaignScreenService {
	public List<MessageLanguagesTO> getLanguages();
	public List<WhatsAppDetailsTO> getWhatsAppData(int userId,int categoryId);
	public List<WhatsAppDetailsCategoryTO> getWhatsAppCategories(int userId);
	public WhatsAppDetailsTO getSelectedRecord(int msgAutoId);
	public String getSelectedCategoryRecord(int msgAutoId);
	public String deleteWhatsAppMsg(int msgAutoId);
	
	public String  saveorUpdateWhatsAppData(RequestBodyTO whatsAppRequestBodyTO);
	public String  saveorUpdateWhatsAppCategoryData(WhatsAppDetailsCategoryTO whatsAppRequestBodyTO);
	
	
	//Old Methods.......
	public List<WhatsAppMessageDetailsTO> saveWhatsAppMsg(String messageName,List<OptionTransientTO> languages,String messageDesc,String userId);
	public List<WhatsAppMessageDetailsTO> updateWhatsAppMsg(int messageAutoId,String messageName,List<OptionTransientTO> languages,String messageDesc,String userId);
}
