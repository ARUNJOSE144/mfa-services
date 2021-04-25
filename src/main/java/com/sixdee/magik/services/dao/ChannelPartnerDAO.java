package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.ChannelPartnerDetails;
import com.sixdee.magik.services.model.DesignationHierarchyDetails;

/**
 * @author ramesh.cheerla
 * @Date : January, 2019
 *
 */

public interface ChannelPartnerDAO {

	public List<ChannelPartnerDetails> getChannelPartners();
	
	public List<DesignationHierarchyDetails> getDesignationsByChannelPartner(long channelType);
}
