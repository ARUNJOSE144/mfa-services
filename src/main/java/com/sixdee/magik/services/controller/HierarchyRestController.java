package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.ChannelTypeHeirarchy;
import com.sixdee.magik.services.model.DesignationHierarchy;
import com.sixdee.magik.services.model.ModuleMaster;
import com.sixdee.magik.services.service.HierarchyService;
import com.sixdee.magik.services.service.RoleService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author arun.jose
 * @Date : Augest, 2010
 *
 */

@RestController
public class HierarchyRestController {

	static final Logger logger = Logger.getLogger(HierarchyRestController.class);

	@Autowired
	HierarchyService hierarchyService;

	@GetMapping(MagikServicePath.VIEW_HIERARCHY)
	public @ResponseBody RestInfo<ChannelTypeHeirarchy> viewHierarchy(HttpServletRequest httpServletRequest) {

		logger.info("Class : HierarchyRestController | Method : viewHierarchy");

		RestInfo<ChannelTypeHeirarchy> list = new RestInfo<ChannelTypeHeirarchy>();
		try {
			list.setData(hierarchyService.viewHierarchy());
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : HierarchyRestController  |  Method : viewHierarchy " + e);
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping(MagikServicePath.CREATE_HIERARCHY_NODE)
	public @ResponseBody RestInfo<ChannelTypeHeirarchy> createHierarchyNode(HttpServletRequest httpServletRequest,
			@RequestBody ChannelTypeHeirarchy channelTypeHeirarchy) {

		logger.info("Class : HierarchyRestController | Method : createHierarchyNode");

		RestInfo<ChannelTypeHeirarchy> list = new RestInfo<ChannelTypeHeirarchy>();
		try {
			list.setData(hierarchyService.createHierarchyNode(channelTypeHeirarchy));
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : HierarchyRestController  |  Method : createHierarchyNode " + e);
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping(MagikServicePath.DELETE_HIERARCHY_NODE)
	public @ResponseBody RestInfo<ChannelTypeHeirarchy> deleteHierarchyNode(HttpServletRequest httpServletRequest,
			@RequestBody ChannelTypeHeirarchy channelTypeHeirarchy) {

		logger.info("Class : HierarchyRestController | Method : deleteHierarchyNode");

		RestInfo<ChannelTypeHeirarchy> list = new RestInfo<ChannelTypeHeirarchy>();
		try {
			list.setData(hierarchyService.deleteHierarchyNode(channelTypeHeirarchy));
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : HierarchyRestController  |  Method : deleteHierarchyNode " + e);
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping(MagikServicePath.VIEW_DESTINATION_HIERARCHY)
	public @ResponseBody RestInfo<DesignationHierarchy> viewDesignationHierarchy(HttpServletRequest httpServletRequest,
			@RequestBody DesignationHierarchy designationHierarchy) {

		logger.info("Class : HierarchyRestController | Method : viewHierarchy");

		RestInfo<DesignationHierarchy> list = new RestInfo<DesignationHierarchy>();
		try {
			list.setData(hierarchyService.viewDesignationHierarchy(designationHierarchy));
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : HierarchyRestController  |  Method : viewHierarchy " + e);
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping(MagikServicePath.CREATE_DESIGNATION_HIERARCHY_NODE)
	public @ResponseBody RestInfo<DesignationHierarchy> createDesignationHierarchyNode(HttpServletRequest httpServletRequest,
			@RequestBody DesignationHierarchy designationHierarchy) {

		logger.info("Class : HierarchyRestController | Method : createHierarchyNode");

		RestInfo<DesignationHierarchy> list = new RestInfo<DesignationHierarchy>();
		try {
			list.setData(hierarchyService.createDesignationHierarchyNode(designationHierarchy));
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : HierarchyRestController  |  Method : createHierarchyNode " + e);
			e.printStackTrace();
		}
		return list;
	}

	@PostMapping(MagikServicePath.DELETE_DESIGNATION_HIERARCHY_NODE)
	public @ResponseBody RestInfo<DesignationHierarchy> deleteDesignationHierarchyNode(HttpServletRequest httpServletRequest,
			@RequestBody DesignationHierarchy designationHierarchy) {

		logger.info("Class : HierarchyRestController | Method : deleteHierarchyNode");

		RestInfo<DesignationHierarchy> list = new RestInfo<DesignationHierarchy>();
		try {
			list.setData(hierarchyService.deleteDesignationHierarchyNode(designationHierarchy));
			list.setOperationCode(0);
		} catch (Exception e) {
			System.out.println("in catch rule");
			list.setOperationCode(1);
			ExceptionUtil.handle(e, list);
			logger.error("Class : HierarchyRestController  |  Method : deleteHierarchyNode " + e);
			e.printStackTrace();
		}
		return list;
	}

}
