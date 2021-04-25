package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.MOBILE_APP_CategoryTO;
import com.sixdee.magik.services.model.MOBILE_APP_RequestBody;
import com.sixdee.magik.services.model.MOBILE_APP_TemplateTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

public interface MobileAppCampaignScreenService {
	
	
	public List<MOBILE_APP_CategoryTO> getCategoriesList(int userId);
	public String deleteCategory(int campId);
	public String deleteMenu(int menuId);
	public List<MOBILE_APP_TemplateTO> getTemplateRecord(int userId,int categoryId);
	public List<MOBILE_APP_TemplateTO> getSelectedTemplate_Detials(int menuId);
	public String  saveorUpdateCategoryData(MOBILE_APP_CategoryTO categoryTO);
	public String  saveorUpdateMsgDetailsData(MOBILE_APP_RequestBody requestBody);

}
