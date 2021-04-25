package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.TwitterCampaignScreenDAO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.TwitterDetailsTO;
import com.sixdee.magik.services.service.TwitterCampaignScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@Service
@Transactional
public class TwitterCampaignScreenServiceImpl implements TwitterCampaignScreenService {
	
	@Autowired
	TwitterCampaignScreenDAO twitterDAO;

	@Override
	public List<MessageLanguagesTO> getLanguages() {
		// TODO Auto-generated method stub
		return twitterDAO.getLanguages();
	}

	@Override
	public List<TwitterDetailsTO> getDetailsData(int userId) {
		// TODO Auto-generated method stub
		return twitterDAO.getDetailsData(userId);
	}

	@Override
	public TwitterDetailsTO getSelectedRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return twitterDAO.getSelectedRecord(msgAutoId);
	}

	@Override
	public String deleteRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return twitterDAO.deleteRecord(msgAutoId);
	}

	@Override
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO) {
		// TODO Auto-generated method stub
		return twitterDAO.saveorUpdateRecord(requestBodyTO);
	}

}
