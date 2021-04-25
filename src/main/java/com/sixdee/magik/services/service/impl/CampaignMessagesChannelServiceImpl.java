package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.controller.CampaignMessagesChannelService;
import com.sixdee.magik.services.dao.CampaignMessagesChannelDAO;
import com.sixdee.magik.services.model.ChannelMessageTO;

@Service
@Transactional
public class CampaignMessagesChannelServiceImpl implements CampaignMessagesChannelService {

	@Autowired CampaignMessagesChannelDAO daoLayer;
	
	@Override
	public List<ChannelMessageTO> getChannelSearch(String channel, int userId) {
		// TODO Auto-generated method stub
		return daoLayer.getChannelSearch(channel, userId);
	}

}
