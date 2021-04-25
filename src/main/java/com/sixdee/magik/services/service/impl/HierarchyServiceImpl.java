
package com.sixdee.magik.services.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.HierarchyDao;
import com.sixdee.magik.services.model.ChannelTypeHeirarchy;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.ModuleMaster;
import com.sixdee.magik.services.service.HierarchyService;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

@Service
@Transactional
public class HierarchyServiceImpl implements HierarchyService {

	@Autowired
	HierarchyDao hierarchyDao;

	@Override
	public ChannelTypeHeirarchy viewHierarchy() {
		return hierarchyDao.viewHierarchy();
	}

	@Override
	public ChannelTypeHeirarchy createHierarchyNode(ChannelTypeHeirarchy channelTypeHeirarchy) {
		return hierarchyDao.createHierarchyNode(channelTypeHeirarchy);
	}

	@Override
	public ChannelTypeHeirarchy deleteHierarchyNode(ChannelTypeHeirarchy channelTypeHeirarchy) {
		return hierarchyDao.deleteHierarchyNode(channelTypeHeirarchy);
	}

	@Override
	public DesignationHierarchy viewDesignationHierarchy(DesignationHierarchy designationHierarchy) {
		return hierarchyDao.viewDesignationHierarchy(designationHierarchy);
	}

	@Override
	public DesignationHierarchy createDesignationHierarchyNode(DesignationHierarchy designationHierarchy) {
		return hierarchyDao.createDesignationHierarchyNode(designationHierarchy);
	}

	@Override
	public DesignationHierarchy deleteDesignationHierarchyNode(DesignationHierarchy designationHierarchy) {
		return hierarchyDao.deleteDesignationHierarchyNode(designationHierarchy);
	}

}
