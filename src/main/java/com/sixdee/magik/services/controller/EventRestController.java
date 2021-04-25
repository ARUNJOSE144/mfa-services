package com.sixdee.magik.services.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sixdee.magik.services.model.EventTO;
import com.sixdee.magik.services.model.FunctionMetricsTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.model.TriggerTO;
import com.sixdee.magik.services.service.EventService;
import com.sixdee.magik.services.util.ExceptionUtil;
import com.sixdee.magik.services.util.MagikServicePath;
import com.sixdee.magik.services.util.RestInfo;
import com.sixdee.magik.services.util.RestListInfo;

/**
 * 
 * @author sudarshan.indla
 * @Date : November, 2018
 *
 */
@RestController
public class EventRestController {

	
	static final Logger logger = Logger.getLogger(EventRestController.class);
	

	@Autowired
	EventService  eventService;
	
	
	
	@PostMapping(MagikServicePath.SAVE_EVENT)
	public @ResponseBody RestInfo<String> saveEvent(HttpServletRequest httpServletRequest,
			@RequestBody EventTO eventTO) {

		logger.info("================== Event Rest Controller  API Start =====================");
		logger.info("Class : EventRestController | Method : saveEvent");

		System.out.println("in save event");
		RestInfo<String> info = new RestInfo<String>();
		try {
			System.out.println("in try");
			eventService.saveEvent(eventTO);	
			info.setOperationCode(0);
		} catch (Exception e) {
			info.setOperationCode(1);
			ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : saveEvent " + e);
			e.printStackTrace();
		}

		logger.info("================== Event Rest Controllar API End =====================");

		return info;
	}
	
	@GetMapping(MagikServicePath.LOAD_EVENT_DATA)
	public @ResponseBody RestInfo<EventTO> loadEventData(HttpServletRequest httpServletRequest) {
		logger.info("================== Event Rest Controllar API Start =====================");
		logger.info("Class : EventRestController | Method : loadEventData");
		RestInfo<EventTO> info = new RestInfo<EventTO>();
		String eventId =  httpServletRequest.getParameter("eventId");
		System.out.println("eventid>>>>>"+eventId);
		try {
			info.setData(eventService.getEvent(Integer.parseInt(eventId)));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : loadTriggerData " + e);
			e.printStackTrace();
		}
		logger.info("================== Event Rest Controllar API End =====================");
		return info;
	}
	
	@GetMapping(MagikServicePath.GET_EVENTS)
	public @ResponseBody RestListInfo<EventTO> getAllEvents(HttpServletRequest httpServletRequest) {

		logger.info("================== Event Rest Controllar API Start =====================");
		logger.info("Class : EventRestController | Method : getAllEvents");
		EventTO eventTO = new EventTO();
		if(httpServletRequest.getParameter("userId")!=null && !httpServletRequest.getParameter("userId").trim().equalsIgnoreCase(""))
			eventTO.setUserId(Integer.parseInt(httpServletRequest.getParameter("userId")));
		if(httpServletRequest.getParameter("name")!=null && !httpServletRequest.getParameter("name").trim().equalsIgnoreCase(""))
			eventTO.setName(httpServletRequest.getParameter("name"));

		RestListInfo<EventTO> info = new RestListInfo<EventTO>();

		try {
			info.setData(eventService.getAllEvents(eventTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : getAllEvents " + e);
			e.printStackTrace();
		}

		logger.info("================== EventRestController Rest Controllar API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.DELETE_EVENT)
	public @ResponseBody RestInfo<String> deleteEvent(HttpServletRequest httpServletRequest) {

		logger.info("================== Event Rest Controllar API Start =====================");
		logger.info("Class : EventRestController | Method : deleteEvent");
		EventTO eventTO=new EventTO();
		if(httpServletRequest.getParameter("eventId")!=null && !httpServletRequest.getParameter("eventId").trim().equalsIgnoreCase(""))
			eventTO.setId(Integer.parseInt(httpServletRequest.getParameter("eventId")));
		// funTo.setCreateDate(new Date()+"");

		RestInfo<String> info = new RestInfo<String>();

		try {
			info.setData(eventService.deleteEvent(eventTO));
		} catch (Exception e) {
			ExceptionUtil.handle(e, info);
			logger.error("Class : EventRestController  |  Method : deleteEvent " + e);
			e.printStackTrace();
		}

		logger.info("================== Event Rest Controllar API End =====================");
		return info;
	}
	
	@PostMapping(MagikServicePath.GET_EVENTS_WITH_PAGINATION)
	public @ResponseBody RestInfo<PaginationDTO<EventTO>> getEventsWithPagination(HttpServletRequest httpServletRequest, @RequestBody PaginationDTO<EventTO> paginationDTO) {
		logger.info("Class : Events Rest Controller | method : getEventsWithPagination");
		
		RestInfo<PaginationDTO<EventTO>> resp = new RestInfo<PaginationDTO<EventTO>>();
		
		try {

			resp.setData(eventService.getEventsPagination(paginationDTO));
			resp.setOperationCode(0);
			resp.setMessage("Success");
		} catch (Exception e) {
			resp.setOperationCode(1);
			ExceptionUtil.handle(e, resp);
			logger.error("Class : RoleRestController  |  Method : getRoleList " + e);
			e.printStackTrace();
		}

		return resp;
		
	}
}
