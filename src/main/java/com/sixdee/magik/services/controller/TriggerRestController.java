package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.TriggerTO;
import com.sixdee.magik.services.service.TriggerService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */
@RestController
public class TriggerRestController {

	static final Logger logger = Logger.getLogger(TriggerRestController.class);
	@Autowired
	TriggerService triggerService;

	@GetMapping(MagikServicePath.LOAD_TRIGGER_DATA)
	public @ResponseBody RestInfo<TriggerTO> loadTriggerData(HttpServletRequest httpServletRequest) {

		logger.info("================== Trigger Rest Controllar API Start =====================");
		logger.info("Class : TriggerRestController | Method : loadTriggerData");
		RestInfo<TriggerTO> info = new RestInfo<TriggerTO>();
		try {
			info.setData(triggerService.loadTriggerData());
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TriggerRestController  |  Method : loadTriggerData " + e);
			e.printStackTrace();
		}
		logger.info("================== Trigger Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.SAVE_TRIGGER)
	public @ResponseBody RestInfo<String> saveTrigger(HttpServletRequest httpServletRequest,
			@RequestBody TriggerTO triggerTO) {

		logger.info("================== TriggerRestController  API Start =====================");
		logger.info("Class : TriggerRestController | Method : saveTrigger");

		RestInfo<String> info = new RestInfo<String>();

		try {
			triggerService.saveTrigger(triggerTO);
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : TriggerRestController  |  Method : saveTrigger " + e);
			e.printStackTrace();
		}

		logger.info("================== Trigger Rest Controllar API End =====================");

		return info;
	}

	@GetMapping(MagikServicePath.GET_TRIGGER_LIST)
	public @ResponseBody RestListInfo<TriggerTO> getTriggerList(HttpServletRequest httpServletRequest) {
		
		logger.info("================== Trigger Rest Controllar API Start =====================");
		logger.info("Class : TriggerRestController | Method : getTriggerList");
		
		TriggerTO triggerTO=new TriggerTO();
		
		if(httpServletRequest.getParameter("name")!=null && !httpServletRequest.getParameter("name").trim().equalsIgnoreCase(""))
			triggerTO.setName(httpServletRequest.getParameter("name"));
		

		RestListInfo<TriggerTO> info = new RestListInfo<TriggerTO>();

		try {
			info.setData(triggerService.getTriggerList(triggerTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TriggerRestController  |  Method : getTriggerList " + e);
			e.printStackTrace();
		}

		logger.info("================== TriggerRestController Rest Controllar API End =====================");
		return info;
	}

	@GetMapping(MagikServicePath.GET_TRIGGER)
	public @ResponseBody RestInfo<TriggerTO> getTrigger(HttpServletRequest httpServletRequest) {

		logger.info("================== Trigger Rest Controllar API Start =====================");
		logger.info("Class : TriggerRestController | Method : getTrigger");
		
		
		TriggerTO triggerTO=new TriggerTO();
		if(httpServletRequest.getParameter("triggerId")!=null && !httpServletRequest.getParameter("triggerId").trim().equalsIgnoreCase(""))
			triggerTO.setId(Integer.parseInt(httpServletRequest.getParameter("triggerId")));

		RestInfo<TriggerTO> info = new RestInfo<TriggerTO>();

		try {
			info.setData(triggerService.getTrigger(triggerTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TriggerRestController  |  Method : getTrigger " + e);
			e.printStackTrace();
		}

		logger.info("================== TriggerRestController Rest Controllar API End =====================");
		return info;
	}

	@PostMapping(MagikServicePath.DELETE_TRIGGER)
	public @ResponseBody RestInfo<String> deleteTrigger(HttpServletRequest httpServletRequest) {

		logger.info("================== Trigger Rest Controllar API Start =====================");
		logger.info("Class : TriggerRestController | Method : deleteTrigger");
		TriggerTO triggerTO=new TriggerTO();
		if(httpServletRequest.getParameter("triggerId")!=null && !httpServletRequest.getParameter("triggerId").trim().equalsIgnoreCase(""))
			triggerTO.setId(Integer.parseInt(httpServletRequest.getParameter("triggerId")));
		// funTo.setCreateDate(new Date()+"");

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(triggerService.deleteTrigger(triggerTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : TriggerRestController  |  Method : deleteTrigger " + e);
			e.printStackTrace();
		}

		logger.info("================== TriggerRestController Rest Controllar API End =====================");
		return info;
	}

}
