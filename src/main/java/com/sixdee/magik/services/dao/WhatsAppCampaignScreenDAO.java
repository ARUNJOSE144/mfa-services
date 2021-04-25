package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.OptionTransientTO;
import com.sixdee.magik.services.model.WhatsAppDetailsTO;
import com.sixdee.magik.services.model.WhatsAppMessageDetailsTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WhatsAppDetailsCategoryTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */
 
public interface WhatsAppCampaignScreenDAO {
	public List<MessageLanguagesTO> getLanguages();
	public List<WhatsAppDetailsTO> getWhatsAppData(int userId,int categoryId);
	public List<WhatsAppDetailsCategoryTO> getWhatsAppCategories(int userId);
	public String getSelectedCategoryRecord(int msgAutoId);
	public WhatsAppDetailsTO getSelectedRecord(int msgAutoId);
	public String deleteWhatsAppMsg(int msgAutoId);
	
	public String saveorUpdateWhatsAppData(RequestBodyTO whatsAppRequestBodyTO);
	public String  saveorUpdateWhatsAppCategoryData(WhatsAppDetailsCategoryTO whatsAppRequestBodyTO);
	
	//Old Methods.........
	public List<WhatsAppMessageDetailsTO> saveWhatsAppMsg(String messageName,List<OptionTransientTO> languages,String messageDesc,String userId);
	public List<WhatsAppMessageDetailsTO> updateWhatsAppMsg(int messageAutoId,String messageName,List<OptionTransientTO> languages,String messageDesc,String userId);
}
