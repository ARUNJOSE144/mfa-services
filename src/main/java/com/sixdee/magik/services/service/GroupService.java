package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GroupTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.model.SubGroupTO;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public interface GroupService {

	// Add Group
	public void addGroup(GroupTO to);

	// Get Groups
	public List<GroupTO> getGroups();

	// Add Sub Group
	public void addSubGroup(SubGroupTO to);

	// Get Sub Groups
	public List<SubGroupTO> getSubGroups(int groupId);
	
	public Integer addGenericGroups(GenericGroupDTO genericGroupDTO) throws Exception;
	
	public void editGroups(GenericGroupDTO genericGroupDTO) throws Exception;
	
	public void editSubGroups(GenericGroupDTO genericGroupDTO) throws Exception;

	public List<SubGroupTO> getAllSubGroups();
	
	public void deleteGroups(GenericGroupDTO genericGroupDTO) throws Exception;
	
	public void deleteSubGroups(GenericGroupDTO genericGroupDTO) throws Exception;
	
	public List<KPITO> getVariableForGroups(int groupId, int subGroupId);

}
