package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.SMSCampaignScreenDAO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.SMSDetailsCategoryTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateDtlsTO;
import com.sixdee.magik.services.model.SMSDetailsTemplateTO;
import com.sixdee.magik.services.model.SMSLanguagesTO;
import com.sixdee.magik.services.service.SMSCampaignScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */


@Service
@Transactional
public class SMSCampaignScreenServiceImpl implements SMSCampaignScreenService {
	
	@Autowired
	SMSCampaignScreenDAO daoLayer;

	@Override
	public List<SMSLanguagesTO> getLanguages() {
		// TODO Auto-generated method stub
		return daoLayer.getLanguages();
	}

	@Override
	public String saveorUpdateCategoryData(SMSDetailsCategoryTO categoryTO) {
		// TODO Auto-generated method stub
		return daoLayer.saveorUpdateCategoryData(categoryTO);
	}

	@Override
	public List<SMSDetailsCategoryTO> getCategoriesList(int userId) {
		return daoLayer.getCategoriesList(userId);
	}

	@Override
	public List<SMSDetailsTemplateTO> getTemplateRecord(int userId, int categoryId) {
		// TODO Auto-generated method stub
		return daoLayer.getTemplateRecord(userId, categoryId);
	}

	@Override
	public List<SMSDetailsTemplateTO> getSelectedTemplate_Detials(int menuId) {
		// TODO Auto-generated method stub
		return daoLayer.getSelectedTemplate_Detials(menuId);
	}

	@Override
	public String saveorUpdateSMSData(RequestBodyTO requestBodyTO) {
		return daoLayer.saveorUpdateSMSData(requestBodyTO);
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

}
