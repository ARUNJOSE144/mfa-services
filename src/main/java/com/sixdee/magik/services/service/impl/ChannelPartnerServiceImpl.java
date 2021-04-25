
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ChannelPartnerDAO;
import com.sixdee.magik.services.model.ChannelPartnerDetails;
import com.sixdee.magik.services.model.DesignationHierarchyDetails;
import com.sixdee.magik.services.service.ChannelPartnerService;

/**
 * @author ramesh.cheerla
 * @Date : January, 2019
 *
 */

@Service
@Transactional
public class ChannelPartnerServiceImpl implements ChannelPartnerService {

	@Autowired
	ChannelPartnerDAO channelPartnerDAO;
	
	@Override
	public List<ChannelPartnerDetails> getChannelPartners() {
		// TODO Auto-generated method stub
		return channelPartnerDAO.getChannelPartners();
	}

	@Override
	public List<DesignationHierarchyDetails> getDesignationsByChannelPartner(long channelType) {
		// TODO Auto-generated method stub
		return channelPartnerDAO.getDesignationsByChannelPartner(channelType);
	}

	
}
