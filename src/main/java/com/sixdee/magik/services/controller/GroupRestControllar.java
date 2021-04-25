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
import com.sixdee.magik.services.model.GenericGroupDTO;
import com.sixdee.magik.services.model.GenericTO;
import com.sixdee.magik.services.model.GroupTO;
import com.sixdee.magik.services.model.KPITO;
import com.sixdee.magik.services.model.SubGroupTO;
import com.sixdee.magik.services.service.GroupService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@RestController
public class GroupRestControllar {

	static final Logger logger = Logger.getLogger(GroupRestControllar.class);

	@Autowired
	GroupService groupService;

	/*
	 * Add Group
	 */
	@PostMapping(MagikServicePath.ADD_GROUP)
	public @ResponseBody RestInfo<GenericTO> addGroup(HttpServletRequest httpServletRequest, @RequestBody GroupTO to) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : addGroup");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			groupService.addGroup(to);
			info.setMessage("Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage("Exception on adding Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	/*
	 * Get Groups
	 */
	@GetMapping(MagikServicePath.GET_GROUPS)
	public @ResponseBody RestListInfo<GroupTO> getGroups(HttpServletRequest httpServletRequest) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : getGroups");
	
		RestListInfo<GroupTO> info = new RestListInfo<GroupTO>();
		
		try {
			info.setData(groupService.getGroups());
			//info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GroupRestControllar  |  Method : getGroups " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	/*
	 * Add Sub Group
	 */
	@PostMapping(MagikServicePath.ADD_SUBGROUP)
	public @ResponseBody RestInfo<GenericTO> addSubGroup(HttpServletRequest httpServletRequest, @RequestBody SubGroupTO to) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : addSubGroup");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			groupService.addSubGroup(to);
			info.setMessage("Sub Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage("Exception on adding Sub Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addSubGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	/*
	 * Get Sub Groups
	 */
	@GetMapping(MagikServicePath.GET_SUBGROUPS)
	public @ResponseBody RestListInfo<SubGroupTO> getSubGroups(HttpServletRequest httpServletRequest) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : getSubGroups");

		int groupId = Integer.parseInt(httpServletRequest.getParameter("groupId"));

		RestListInfo<SubGroupTO> info = new RestListInfo<SubGroupTO>();
		
		try {
			info.setData(groupService.getSubGroups(groupId));
			//info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GroupRestControllar  |  Method : getSubGroups " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	@PostMapping("/addGenericGroups")
	public @ResponseBody RestInfo<GenericTO> addGenericGroups(HttpServletRequest httpServletRequest, @RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : addGroup");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			GenericTO genericTO = new GenericTO();
			Integer id = groupService.addGenericGroups(genericGroupDTO);
			genericTO.setId(id);
			//info.setMessage("Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Exception on adding Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	
	@GetMapping(MagikServicePath.GET_ALL_SUBGROUPS)
	public @ResponseBody RestListInfo<SubGroupTO> getAllSubGroups(HttpServletRequest httpServletRequest) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : getSubGroups");

		//int groupId = Integer.parseInt(httpServletRequest.getParameter("groupId"));

		RestListInfo<SubGroupTO> info = new RestListInfo<SubGroupTO>();
		
		try {
			info.setData(groupService.getAllSubGroups());
			//info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GroupRestControllar  |  Method : getSubGroups " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	@PostMapping("/editProfileGroups")
	public @ResponseBody RestInfo<GenericTO> editGroups(HttpServletRequest httpServletRequest, @RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : editGroups");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			groupService.editGroups(genericGroupDTO);
			//info.setMessage("Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Exception on adding Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	@PostMapping("/editProfileSubGroups")
	public @ResponseBody RestInfo<GenericTO> editSubGroups(HttpServletRequest httpServletRequest, @RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : editSubGroups");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			groupService.editSubGroups(genericGroupDTO);
			//info.setMessage("Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Exception on adding Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	@PostMapping("/deleteProfileGroups")
	public @ResponseBody RestInfo<GenericTO> deleteGroups(HttpServletRequest httpServletRequest, @RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : editGroups");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			groupService.deleteGroups(genericGroupDTO);
			//info.setMessage("Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Exception on adding Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	@PostMapping("/deleteProfileSubGroups")
	public @ResponseBody RestInfo<GenericTO> deleteSubGroups(HttpServletRequest httpServletRequest, @RequestBody GenericGroupDTO genericGroupDTO) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : editSubGroups");

		RestInfo<GenericTO> info = new RestInfo<GenericTO>();
		
		try {
			groupService.deleteSubGroups(genericGroupDTO);
			//info.setMessage("Group "+CacheDaoImpl.messages.get(7));
		} catch (Exception e) {
			//ExceptionUtil.handle(e, info);
			info.setOperationCode(1);
			info.setMessage("Exception on adding Group. Please try agin.");
			logger.error("Class : GroupRestControllar  |  Method : addGroup " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
	
	@GetMapping("/getVariableForGroup")
	public @ResponseBody RestListInfo<KPITO> getVariableForGroups(HttpServletRequest httpServletRequest) {

		logger.info("================== Group API Start =====================");
		logger.info("Class : GroupRestControllar | Method : getVariableForGroups");

		int groupId = Integer.parseInt(httpServletRequest.getParameter("groupId"));
		int subGroupId = Integer.parseInt(httpServletRequest.getParameter("subGroupId"));

		RestListInfo<KPITO> info = new RestListInfo<KPITO>();
		
		try {
			info.setData(groupService.getVariableForGroups(groupId,subGroupId));
			//info.setMessage(CacheDaoImpl.messages.get(9));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			info.setMessage(CacheDaoImpl.messages.get(10));
			logger.error("Class : GroupRestControllar  |  Method : getSubGroups " + e);
			e.printStackTrace();
		}

		logger.info("================== Group API End =====================");

		return info;
	}
}
