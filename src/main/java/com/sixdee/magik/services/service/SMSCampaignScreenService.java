package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.SMSDetailsCategoryTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateDtlsTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateTO;
import com.sixdee.magik.services.model.SMSLanguagesTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

public interface SMSCampaignScreenService {
	
	public List<SMSLanguagesTO> getLanguages();
	public List<SMSDetailsCategoryTO> getCategoriesList(int userId);
	public String deleteCategory(int campId);
	public String deleteMenu(int menuId);
	public List<SMSDetailsTemplateTO> getTemplateRecord(int userId,int categoryId);
	public List<SMSDetailsTemplateTO> getSelectedTemplate_Detials(int menuId);
	public String  saveorUpdateCategoryData(SMSDetailsCategoryTO categoryTO);
	public String  saveorUpdateSMSData(RequestBodyTO requestBodyTO);

}
