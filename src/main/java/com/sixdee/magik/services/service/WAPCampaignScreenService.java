package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WAPDetailsCategoryTO;
import com.sixdee.magik.services.model.WAPDetailsTemplateTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

public interface WAPCampaignScreenService {
	
	public List<WAPDetailsCategoryTO> getCategoriesList(int userId);
	public String deleteCategory(int campId);
	public String deleteMenu(int menuId);
	public List<WAPDetailsTemplateTO> getTemplateRecord(int userId,int categoryId);
	public List<WAPDetailsTemplateTO> getSelectedTemplate_Detials(int menuId);
	public String  saveorUpdateCategoryData(WAPDetailsCategoryTO categoryTO);
	public String  saveorUpdateTemplateData(RequestBodyTO requestBodyTO);

}
