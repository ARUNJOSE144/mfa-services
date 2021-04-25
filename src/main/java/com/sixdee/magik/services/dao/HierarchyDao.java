package com.sixdee.magik.services.dao;

import com.sixdee.magik.services.model.ChannelTypeHeirarchy;
import com.sixdee.magik.services.model.DesignationHierarchy;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

public interface HierarchyDao {

	ChannelTypeHeirarchy viewHierarchy();

	ChannelTypeHeirarchy createHierarchyNode(ChannelTypeHeirarchy channelTypeHeirarchy);

	ChannelTypeHeirarchy deleteHierarchyNode(ChannelTypeHeirarchy channelTypeHeirarchy);

	DesignationHierarchy viewDesignationHierarchy(DesignationHierarchy designationHierarchy);

	DesignationHierarchy createDesignationHierarchyNode(DesignationHierarchy designationHierarchy);

	DesignationHierarchy deleteDesignationHierarchyNode(DesignationHierarchy designationHierarchy);

}
