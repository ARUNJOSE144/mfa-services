package com.sixdee.magik.services.controller;

import java.util.List;

import com.sixdee.magik.services.model.ChannelMessageTO;

public interface CampaignMessagesChannelService {
	
	public List<ChannelMessageTO> getChannelSearch(String channel, int userId);

}
