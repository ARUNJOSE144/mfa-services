package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.WAPCampaignScreenDAO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.WAPDetailsCategoryTO;
import com.sixdee.magik.services.model.WAPDetailsTemplateTO;
import com.sixdee.magik.services.service.WAPCampaignScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */


@Service
@Transactional
public class WAPCampaignScreenServiceImpl implements WAPCampaignScreenService {
	
	@Autowired WAPCampaignScreenDAO daoLayer;

	@Override
	public List<WAPDetailsCategoryTO> getCategoriesList(int userId) {
		// TODO Auto-generated method stub
		return daoLayer.getCategoriesList(userId);
	}

	@Override
	public String deleteCategory(int campId) {
		// TODO Auto-generated method stub
		return daoLayer.deleteCategory(campId);
	}

	@Override
	public String deleteMenu(int menuId) {
		// TODO Auto-generated method stub
		return daoLayer.deleteMenu(menuId);
	}

	@Override
	public List<WAPDetailsTemplateTO> getTemplateRecord(int userId, int categoryId) {
		// TODO Auto-generated method stub
		return daoLayer.getTemplateRecord(userId, categoryId);
	}

	@Override
	public List<WAPDetailsTemplateTO> getSelectedTemplate_Detials(int menuId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedTemplate_Detials(menuId);
	}

	@Override
	public String saveorUpdateCategoryData(WAPDetailsCategoryTO categoryTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateCategoryData(categoryTO);
	}

	@Override
	public String saveorUpdateTemplateData(RequestBodyTO requestBodyTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateTemplateData(requestBodyTO);
	}

}
