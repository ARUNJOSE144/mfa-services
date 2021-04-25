package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.SkypeCampaignScreenDAO;
import com.sixdee.magik.services.model.MessageLanguagesTO;
import com.sixdee.magik.services.model.RequestBodyTO;
import com.sixdee.magik.services.model.SkypeDetailsTO;
import com.sixdee.magik.services.service.SkypeCampaignScreenService;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@Service
@Transactional
public class SkypeCampaignScreenServiceImpl implements SkypeCampaignScreenService {
	
	@Autowired
	SkypeCampaignScreenDAO skypeDAO;

	@Override
	public List<MessageLanguagesTO> getLanguages() {
		// TODO Auto-generated method stub
		return skypeDAO.getLanguages();
	}

	@Override
	public List<SkypeDetailsTO> getDetailsData(int userId) {
		// TODO Auto-generated method stub
		return skypeDAO.getDetailsData(userId);
	}

	@Override
	public SkypeDetailsTO getSelectedRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return skypeDAO.getSelectedRecord(msgAutoId);
	}

	@Override
	public String deleteRecord(int msgAutoId) {
		// TODO Auto-generated method stub
		return skypeDAO.deleteRecord(msgAutoId);
	}

	@Override
	public String saveorUpdateRecord(RequestBodyTO requestBodyTO) {
		// TODO Auto-generated method stub
		return skypeDAO.saveorUpdateRecord(requestBodyTO);
	}

}
