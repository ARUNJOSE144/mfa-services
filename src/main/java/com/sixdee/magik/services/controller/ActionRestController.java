/**
 * 
 */
package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.dao.impl.CacheDaoImpl;
import com.sixdee.magik.services.model.ActionDefaultParamsTO;
import com.sixdee.magik.services.model.ActionHeaderParameterTo;
import com.sixdee.magik.services.model.ActionParameterTO;
import com.sixdee.magik.services.model.ActionTO;
import com.sixdee.magik.services.model.ActionsDTO;
import com.sixdee.magik.services.model.AuthenticationTO;
import com.sixdee.magik.services.service.ActionService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Vinay Kariyappa
 *
 *         Nov 23, 2018
 */
@RestController
public class ActionRestController {

	static final Logger logger = Logger.getLogger(ActionRestController.class);

	@Autowired
	ActionService actionService;

	/*
	 * Get All Actions Groups
	 */
	@GetMapping("GetAllActionGroups")
	public @ResponseBody RestListInfo<ActionsDTO> getAllActionGroups(HttpServletRequest httpServletRequest) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubGroups");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();

		try {
			info.setData(actionService.getAllActionGroups());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubGroups " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Get Sub Actions
	 */
	@GetMapping("GetSubActions")
	public @ResponseBody RestListInfo<ActionsDTO> getSubActions(HttpServletRequest httpServletRequest) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int groupId = Integer.parseInt(httpServletRequest.getParameter("groupId"));
		try {
			info.setData(actionService.getSubActions(groupId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Add Header Parameter
	 */
	@PostMapping("/CreateOnlyActions")
	public @ResponseBody RestListInfo<ActionsDTO> createOnlyActions(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : addHeaderParameter");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		try {
			actionService.createOnlyActions(actionsDTO);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			// ExceptionUtil.handle(e, info);
			info.setMessage(e.getMessage());
			logger.error("Class : ActionRestController  |  Method : addHeaderParameter " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Add Header Parameter
	 */
	@PostMapping("/addHeaderParameter")
	public @ResponseBody RestListInfo<ActionsDTO> addHeaderParameter(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : addHeaderParameter");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		try {
			actionService.addHeaderParameter(actionsDTO);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			// ExceptionUtil.handle(e, info);
			info.setMessage(e.getMessage());
			logger.error("Class : ActionRestController  |  Method : addHeaderParameter " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Add Normal Parameter
	 */
	@PostMapping("addNormalParameter")
	public @ResponseBody RestListInfo<ActionsDTO> addNormalParameter(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : addNormalParameter");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		try {
			actionService.addNormalParameter(actionsDTO);
		} catch (Exception e) {
			info.setOperationCode(1);
			// ExceptionUtil.handle(e, info);
			info.setMessage(e.getMessage());
			logger.error("Class : ActionRestController  |  Method : addNormalParameter " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Get GetHeaderParameter
	 */
	@GetMapping("GetHeaderParameter")
	public @ResponseBody RestListInfo<ActionsDTO> getHeaderParameter(HttpServletRequest httpServletRequest) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			info.setData(actionService.getHeaderParameter(actionId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Get GetHeaderParameter
	 */
	@GetMapping("SubGroup/GetHeaderParameter")
	public @ResponseBody RestListInfo<ActionsDTO> getSubGroupHeaderParameter(HttpServletRequest httpServletRequest) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubGroupHeaderParameter");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			info.setData(actionService.getSubGroupHeaderParameter(actionId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Get Sub Actions
	 */
	@GetMapping("SubGroup/GetNormalParameter")
	public @ResponseBody RestListInfo<ActionsDTO> getSubGroupNormalParameter(HttpServletRequest httpServletRequest) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			info.setData(actionService.getSubGroupNormalParameter(actionId));
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	/*
	 * Get Sub Actions
	 */
	@GetMapping("GetNormalParameter")
	public @ResponseBody RestListInfo<ActionsDTO> getNormalParameter(HttpServletRequest httpServletRequest) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			info.setData(actionService.getNormalParameter(actionId));
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_ACTION_LIST)
	public @ResponseBody RestListInfo<ActionTO> getActionsList(HttpServletRequest httpServletRequest) {

		logger.info("================== Action Rest Controllar API Start =====================");
		logger.info("Class : Action | Method : Action");

		RestListInfo<ActionTO> info = new RestListInfo<ActionTO>();
		try {
			info.setData(actionService.getActionsList());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : Action  |  Method : authLoginCredentails " + e);
			e.printStackTrace();
		}
		logger.info("================== Action Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_ACTION_PARAMETER_LIST)
	public @ResponseBody RestListInfo<ActionParameterTO> getActionParameterList(HttpServletRequest httpServletRequest) {

		logger.info("================== Action Rest Controllar API Start =====================");
		logger.info("Class : Action | Method : Action");

		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));

		RestListInfo<ActionParameterTO> info = new RestListInfo<ActionParameterTO>();
		try {
			info.setData(actionService.getActionParameterList(actionId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : Action  |  Method : authLoginCredentails " + e);
			e.printStackTrace();
		}
		logger.info("================== Action Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_ACTION_HEADER_PARAMETER_LIST)
	public @ResponseBody RestListInfo<ActionHeaderParameterTo> getActionHeaderParameterList(
			HttpServletRequest httpServletRequest) {

		logger.info("================== Action Rest Controllar API Start =====================");
		logger.info("Class : Action | Method : Action");

		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));

		RestListInfo<ActionHeaderParameterTo> info = new RestListInfo<ActionHeaderParameterTo>();
		try {
			info.setData(actionService.getActionHeaderParameterList(actionId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : Action  |  Method : authLoginCredentails " + e);
			e.printStackTrace();
		}
		logger.info("================== Action Rest Controllar API End =====================");

		return info;
	}

	@PostMapping("UpdateHeaderParameter")
	public @ResponseBody RestListInfo<ActionsDTO> updateHeaderParameter(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			actionService.updateHeaderParameter(actionsDTO);
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@PostMapping("UpdateNormalParameter")
	public @ResponseBody RestListInfo<ActionsDTO> updateNormalParameter(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			actionService.updateNormalParameter(actionsDTO);
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@PostMapping("DeleteNormalParameter")
	public @ResponseBody RestListInfo<ActionsDTO> deleteNormalParameter(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			actionService.deleteNormalParameter(actionsDTO);
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@PostMapping("DeleteHeaderParameter")
	public @ResponseBody RestListInfo<ActionsDTO> deleteHeaderParameter(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : getSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		int actionId = Integer.parseInt(httpServletRequest.getParameter("actionId"));
		try {
			actionService.deleteHeaderParameter(actionsDTO);
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@PostMapping("editSubActions")
	public @ResponseBody RestListInfo<ActionsDTO> editSubActions(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : editSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		try {
			actionService.editSubActions(actionsDTO);
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@PostMapping("deleteSubActions")
	public @ResponseBody RestListInfo<ActionsDTO> deleteSubActions(HttpServletRequest httpServletRequest,
			@RequestBody ActionsDTO actionsDTO) {

		logger.info("================== Action API Start =====================");
		logger.info("Class : ActionRestController | Method : deleteSubActions");

		RestListInfo<ActionsDTO> info = new RestListInfo<ActionsDTO>();
		try {
			actionService.deleteSubActions(actionsDTO);
		} catch (Exception e) {
			// ExceptionUtil.handle(e, info);
			// info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : ActionRestController  |  Method : getSubActions " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_ACTION_DEFAULT_PARAMETER_LIST)
	public @ResponseBody RestListInfo<ActionDefaultParamsTO> getActionsDefaultParamList(
			HttpServletRequest httpServletRequest) {

		logger.info("================== Action Rest Controllar API Start =====================");
		logger.info("Class : Action | Method : Action");
		int groupId;
		groupId = Integer.parseInt(httpServletRequest.getParameter("groupId"));

		RestListInfo<ActionDefaultParamsTO> info = new RestListInfo<ActionDefaultParamsTO>();
		try {
			info.setData(actionService.getActionsDefaultParamList(groupId));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : Action  |  Method : authLoginCredentails " + e);
			e.printStackTrace();
		}
		logger.info("================== Action Rest Controllar API End =====================");

		return info;
	}
}
