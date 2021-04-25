/**
 * 
 */
package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.ActionDefaultParamsTO;
import com.sixdee.magik.services.model.ActionHeaderParameterTo;
import com.sixdee.magik.services.model.ActionParameterTO;
import com.sixdee.magik.services.model.ActionTO;
import com.sixdee.magik.services.model.ActionsDTO;

/**
 * @author Vinay Kariyappa
 *
 * Nov 23, 2018
 */
public interface ActionService {

	public List<ActionsDTO> getAllActionGroups() throws Exception;
	
	public List<ActionsDTO> getSubActions(int groupId) throws Exception;
	
	public void addHeaderParameter(ActionsDTO actionsDTO) throws Exception;
	
	public void createOnlyActions(ActionsDTO actionsDTO) throws Exception;
	
	public void addNormalParameter(ActionsDTO actionsDTO) throws Exception;
	
	public List<ActionsDTO> getHeaderParameter(int actionId) throws Exception;
	
	public List<ActionsDTO> getNormalParameter(int actionId) throws Exception;

	public List<ActionTO> getActionsList();

	public List<ActionParameterTO> getActionParameterList(int actionId);

	public List<ActionHeaderParameterTo> getActionHeaderParameterList(int actionId);
	
	public void updateHeaderParameter(ActionsDTO actionsDTO) throws Exception;
	
	public void updateNormalParameter(ActionsDTO actionsDTO) throws Exception;
	
	public void deleteNormalParameter(ActionsDTO actionsDTO) throws Exception;
	
	public void deleteHeaderParameter(ActionsDTO actionsDTO) throws Exception;
	
	public void editSubActions(ActionsDTO actionsDTO) throws Exception;
	
	public void deleteSubActions(ActionsDTO actionsDTO) throws Exception;
	
	public List<ActionsDTO> getSubGroupHeaderParameter(int actionId) throws Exception;
	
	public List<ActionsDTO> getSubGroupNormalParameter(int actionId) throws Exception;

	public List<ActionDefaultParamsTO> getActionsDefaultParamList(int groupId);
		
}
