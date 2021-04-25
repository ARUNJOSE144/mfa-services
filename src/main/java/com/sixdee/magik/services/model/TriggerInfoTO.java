package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Entity
@Table(name = "TRIGGER_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TriggerInfoTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TriggerInfoTO")
	@TableGenerator(name = "TriggerInfoTO", allocationSize = 1)
	@Column(name = "TRIGGER_ID")
	private int triggerId;
	
	@Column(name = "TRIGGER_NAME")
	private String triggerName;

	public int getTriggerId() {
		return triggerId;
	}

	public void setTriggerId(int triggerId) {
		this.triggerId = triggerId;
	}

	public String getTriggerName() {
		return triggerName;
	}

	public void setTriggerName(String triggerName) {
		this.triggerName = triggerName;
	}
	
	



	
}
