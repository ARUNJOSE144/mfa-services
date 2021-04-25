package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ChannelMessageTO;

public interface CampaignMessagesChannelDAO {
	
	public List<ChannelMessageTO> getChannelSearch(String channel, int userId);
	
}
