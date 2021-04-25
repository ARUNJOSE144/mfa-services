package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.EventTO;
import com.sixdee.magik.services.model.PaginationDTO;
import com.sixdee.magik.services.model.RoleMaster;

public interface EventService {

	
	public void  saveEvent(EventTO  eventTO);
	
	public EventTO getEvent(int eventId);
	
	public List<EventTO> getAllEvents(EventTO  eventTO);
	
	public String deleteEvent(EventTO  eventTO);

	public PaginationDTO<EventTO> getEventsPagination(PaginationDTO<EventTO> paginationDTO);
	
	
}
