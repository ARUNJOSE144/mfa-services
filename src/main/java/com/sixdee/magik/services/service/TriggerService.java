package com.sixdee.magik.services.service;

import java.util.List;

import com.sixdee.magik.services.model.TriggerTO;

/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */
public interface TriggerService {

	public TriggerTO loadTriggerData();
	public void saveTrigger(TriggerTO triggerTO);
	public List<TriggerTO> getTriggerList(TriggerTO triggerTO);
	public TriggerTO getTrigger(TriggerTO triggerTO);
	public String deleteTrigger(TriggerTO triggerTO);
}
