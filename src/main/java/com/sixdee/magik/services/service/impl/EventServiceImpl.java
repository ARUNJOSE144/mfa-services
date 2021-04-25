package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sixdee.magik.services.dao.EventDao;
import com.sixdee.magik.services.model.EventTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;
import com.sixdee.magik.services.service.EventService;


@Service
public class EventServiceImpl implements EventService {

	
	@Autowired
	EventDao eventDao;
	
	@Override
	public void saveEvent(EventTO eventTO) {
		System.out.println("save service");
		eventDao.saveEvent(eventTO);
	}

	@Override
	public EventTO getEvent(int eventId) {
		// TODO Auto-generated method stub
		return eventDao.getEvent(eventId);
	}

	@Override
	public List<EventTO> getAllEvents(EventTO  eventTO) {
		// TODO Auto-generated method stub
		return eventDao.getAllEvents(eventTO);
	}
	
	@Override
	public PaginationDTO<EventTO> getEventsPagination(PaginationDTO<EventTO> paginationDTO) {
		return eventDao.getEventsPagination(paginationDTO);
	}
	
	@Override
	public String deleteEvent(EventTO  eventTO) {
		// TODO Auto-generated method stub
		return eventDao.deleteEvent(eventTO);
	}

}
