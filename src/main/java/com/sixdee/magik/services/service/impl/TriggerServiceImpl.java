package com.sixdee.magik.services.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sixdee.magik.services.dao.TriggerDAO;
import com.sixdee.magik.services.model.TriggerTO;
import com.sixdee.magik.services.service.TriggerService;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Service
@Transactional
public class TriggerServiceImpl implements TriggerService {
	
	@Autowired
	TriggerDAO triggerDAO;

	@Override
	public TriggerTO loadTriggerData() {
		// TODO Auto-generated method stub
		return triggerDAO.loadTriggerData();
	}

	@Override
	public void saveTrigger(TriggerTO triggerTO) {
		triggerDAO.saveTrigger(triggerTO);
		
	}

	@Override
	public List<TriggerTO> getTriggerList(TriggerTO triggerTO) {
		// TODO Auto-generated method stub
		return triggerDAO.getTriggerList(triggerTO);
	}

	@Override
	public TriggerTO getTrigger(TriggerTO triggerTO) {
		// TODO Auto-generated method stub
		return triggerDAO.getTrigger(triggerTO);
	}

	@Override
	public String deleteTrigger(TriggerTO triggerTO) {
		// TODO Auto-generated method stub
		return triggerDAO.deleteTrigger(triggerTO);
	}


}
