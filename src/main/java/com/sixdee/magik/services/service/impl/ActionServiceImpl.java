/**
 * 
 */
package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.ActionDao;
import com.sixdee.magik.services.model.ActionDefaultParamsTO;
import com.sixdee.magik.services.model.ActionHeaderParameterTo;
import com.sixdee.magik.services.model.ActionParameterTO;
import com.sixdee.magik.services.model.ActionTO;
import com.sixdee.magik.services.model.ActionsDTO;
import com.sixdee.magik.services.service.ActionService;

/**
 * @author Vinay Kariyappa
 *
 * Nov 23, 2018
 */
@Service
@Transactional
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionDao actionDao;
	
	@Override
	public List<ActionsDTO> getAllActionGroups() throws Exception {
		return actionDao.getAllActionGroups();
	}

	
	@Override
	public List<ActionsDTO> getSubActions(int groupId) throws Exception {
		return actionDao.getSubActions(groupId);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#addHeaderParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void addHeaderParameter(ActionsDTO actionsDTO) throws Exception {
		actionDao.addHeaderParameter(actionsDTO);
		
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#addNormalParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void addNormalParameter(ActionsDTO actionsDTO) throws Exception {
		actionDao.addNormalParameter(actionsDTO);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#getHeaderParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public List<ActionsDTO> getHeaderParameter(int actionId) throws Exception {
		return actionDao.getHeaderParameter(actionId);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#getNormalParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public List<ActionsDTO> getNormalParameter(int actionId) throws Exception {
		return actionDao.getNormalParameter(actionId);
	}


	@Override
	public List<ActionTO> getActionsList() {
		// TODO Auto-generated method stub
		return actionDao.getActionsList();
	}


	@Override
	public List<ActionParameterTO> getActionParameterList(int actionId) {
		// TODO Auto-generated method stub
		return actionDao.getActionParameterList(actionId);
	}


	@Override
	public List<ActionHeaderParameterTo> getActionHeaderParameterList(int actionId) {
		// TODO Auto-generated method stub
		return actionDao.getActionHeaderParameterList(actionId);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#createOnlyActions(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void createOnlyActions(ActionsDTO actionsDTO) throws Exception {
		// TODO Auto-generated method stub
		actionDao.createOnlyActions(actionsDTO);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#updateHeaderParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void updateHeaderParameter(ActionsDTO actionsDTO) throws Exception {
		actionDao.updateHeaderParameter(actionsDTO);
		
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#updateNormalParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void updateNormalParameter(ActionsDTO actionsDTO) throws Exception {
		// TODO Auto-generated method stub
		actionDao.updateNormalParameter(actionsDTO);
		
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#deleteNormalParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void deleteNormalParameter(ActionsDTO actionsDTO) throws Exception {
		// TODO Auto-generated method stub
		actionDao.deleteNormalParameter(actionsDTO);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#deleteHeaderParameter(com.sixdee.magik.services.model.ActionsDTO)
	 */
	@Override
	public void deleteHeaderParameter(ActionsDTO actionsDTO) throws Exception {
		actionDao.deleteHeaderParameter(actionsDTO);
		
	}

	public void editSubActions(ActionsDTO actionsDTO) throws Exception {
		actionDao.editSubActions(actionsDTO);
	}


	@Override
	public void deleteSubActions(ActionsDTO actionsDTO) throws Exception {
		actionDao.deleteSubActions(actionsDTO);
	}


	@Override
	public List<ActionsDTO> getSubGroupHeaderParameter(int actionId) throws Exception {
		return actionDao.getSubGroupHeaderParameter(actionId);
	}


	@Override
	public List<ActionsDTO> getSubGroupNormalParameter(int actionId) throws Exception {
		return actionDao.getSubGroupNormalParameter(actionId);
	}


	/* (non-Javadoc)
	 * @see com.sixdee.magik.services.service.ActionService#getActionsDefaultParamList()
	 */
	@Override
	public List<ActionDefaultParamsTO> getActionsDefaultParamList(int groupId) {
		// TODO Auto-generated method stub
		return actionDao.getActionsDefaultParamList(groupId);
	}
	
	

}
