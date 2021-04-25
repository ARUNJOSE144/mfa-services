package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.USSDDetailsCategoryTO;
import com.sixdee.magik.services.model.USSDDetailsTemplateTO;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

public interface USSDCampaignScreenDAO {
	
	public List<USSDDetailsCategoryTO> getCategoriesList(int userId);
	public String deleteCategory(int campId);
	public String deleteMenu(int menuId);
	public List<USSDDetailsTemplateTO> getTemplateRecord(int userId,int categoryId);
	public List<USSDDetailsTemplateTO> getSelectedTemplate_Detials(int menuId);
	public String  saveorUpdateCategoryData(USSDDetailsCategoryTO categoryTO);
	public String  saveorUpdateTemplateData(RequestBodyTO requestBodyTO);

}
