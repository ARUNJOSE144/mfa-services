package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.FacebookCampaignScreenDAO;
import com.sixdee.magik.services.model.FacebookDetailsTO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.service.FacebookCampaignScreenService;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@Service
@Transactional
public class FacebookCampaignScreenServiceImpl implements FacebookCampaignScreenService {
	
	@Autowired
	FacebookCampaignScreenDAO facebookDAO;

	@Override
	public List<MessageLanguagesTO> getLanguages() {
		// TODO Auto-generated method stub
		return facebookDAO.getLanguages();
	}

	@Override
	public List<FacebookDetailsTO> getDetailsData(int userId) {
		// TODO Auto-generated method stub
		return facebookDAO.getDetailsData(userId);
	}

	@Override
	public FacebookDetailsTO getSelectedRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return facebookDAO.getSelectedRecord(msgAutoId);
	}

	@Override
	public String deleteRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return facebookDAO.deleteRecord(msgAutoId);
	}

	@Override
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO) {
		// TODO Auto-generated method stub
		return facebookDAO.saveorUpdateRecord(requestBodyTO);
	}

}
