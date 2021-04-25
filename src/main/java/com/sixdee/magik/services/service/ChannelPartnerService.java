package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ChannelPartnerDetails;
import com.sixdee.magik.services.model.DesignationHierarchyDetails;

/**
 * @author Rajesh
 * @Date : Sep, 2020
 *
 */

public interface ChannelPartnerService {


	public List<ChannelPartnerDetails> getChannelPartners();
	
	public List<DesignationHierarchyDetails> getDesignationsByChannelPartner(long channelType);
}
