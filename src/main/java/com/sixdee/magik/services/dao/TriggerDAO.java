package com.sixdee.magik.services.dao;

import java.util.List;

import com.sixdee.magik.services.model.TriggerTO;

/**
 * @author Rajesh
 * @Date : September, 2018
 *
 */
public interface TriggerDAO {

	public TriggerTO loadTriggerData();
	
	public void saveTrigger(TriggerTO triggerTO);
	
	public List<TriggerTO> getTriggerList(TriggerTO triggerTO);

	public TriggerTO getTrigger(TriggerTO triggerTO);

	public String deleteTrigger(TriggerTO triggerTO);
}
