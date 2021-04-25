package com.sixdee.magik.services.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author arun.jose
 * @Date : December, 2018
 *
 */

@Entity
@Table(name = "RE_ACTION_HEADER_PARAMETERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActionHeaderParameterTo {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionHeaderParameterTo")
	@TableGenerator(name = "ActionHeaderParameterTo", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "ACTION_ID")
	private int actionId;

	@Column(name = "PARAMETER_NAME")
	private String paramName;

	@Column(name = "PARAMETER_VALUE")
	private String paramValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}
	
	

	

	

}
