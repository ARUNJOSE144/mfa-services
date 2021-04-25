
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.GroupDao;
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GroupTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.model.SubGroupTO;
import com.sixdee.magik.services.service.GroupService;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

	@Autowired
	GroupDao groupDao;

	@Override
	public void addGroup(GroupTO to) {
		groupDao.addGroup(to);
	}

	@Override
	public List<GroupTO> getGroups() {
		return groupDao.getGroups();
	}

	@Override
	public void addSubGroup(SubGroupTO to) {
		groupDao.addSubGroup(to);
	}

	@Override
	public List<SubGroupTO> getSubGroups(int groupId) {
		return groupDao.getSubGroups(groupId);
	}

	@Override
	public Integer addGenericGroups(GenericGroupDTO genericGroupDTO) throws Exception{
		// TODO Auto-generated method stub
		return groupDao.addGenericGroups(genericGroupDTO);
	}

	@Override
	public List<SubGroupTO> getAllSubGroups() {
		// TODO Auto-generated method stub
		return groupDao.getAllSubGroups();
	}

	@Override
	public void editGroups(GenericGroupDTO genericGroupDTO) throws Exception {
		 groupDao.editGroups(genericGroupDTO);
	}

	@Override
	public void editSubGroups(GenericGroupDTO genericGroupDTO) throws Exception {
		groupDao.editSubGroups(genericGroupDTO);
	}

	@Override
	public void deleteGroups(GenericGroupDTO genericGroupDTO) throws Exception {
		groupDao.deleteGroups(genericGroupDTO);
	}

	@Override
	public void deleteSubGroups(GenericGroupDTO genericGroupDTO) throws Exception {
		groupDao.deleteSubGroups(genericGroupDTO);
	}

	@Override
	public List<KPITO> getVariableForGroups(int groupId, int subGroupId) {
		return groupDao.getVariableForGroups(groupId,subGroupId);
	}

}
