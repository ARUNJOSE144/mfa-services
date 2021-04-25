package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.MobileAppCampaignScreenDAO;
import com.sixdee.magik.services.model.MOBILE_APP_CategoryTO;
import com.sixdee.magik.services.model.MOBILE_APP_RequestBody;
import com.sixdee.magik.services.model.MOBILE_APP_TemplateTO;
import com.sixdee.magik.services.service.MobileAppCampaignScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */


@Service
@Transactional
public class MobileAppCampaignScreenServiceImpl implements MobileAppCampaignScreenService{

	@Autowired
	MobileAppCampaignScreenDAO daoLayer;

	@Override
	public List<MOBILE_APP_CategoryTO> getCategoriesList(int userId) {
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
	public List<MOBILE_APP_TemplateTO> getTemplateRecord(int userId, int categoryId) {
		// TODO Auto-generated method stub
		return daoLayer.getTemplateRecord(userId, categoryId);
	}

	@Override
	public List<MOBILE_APP_TemplateTO> getSelectedTemplate_Detials(int menuId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedTemplate_Detials(menuId);
	}

	@Override
	public String saveorUpdateCategoryData(MOBILE_APP_CategoryTO categoryTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateCategoryData(categoryTO);
	}

	@Override
	public String saveorUpdateMsgDetailsData(MOBILE_APP_RequestBody requestBody) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateMsgDetailsData(requestBody);
	}

}
