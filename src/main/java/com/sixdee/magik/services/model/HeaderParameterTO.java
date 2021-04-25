package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "RE_ACTION_HEADER_PARAMETERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class HeaderParameterTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "HeaderParameterTO")
	@TableGenerator(name = "HeaderParameterTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "ACTION_ID")
	private int actionId;

	@Column(name = "PARAMETER_NAME")
	private String parameterName;

	@Column(name = "PARAMETER_VALUE")
	private int parameterValue;

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

	public String getParameterName() {
		return parameterName;
	}

	public void setParameterName(String parameterName) {
		this.parameterName = parameterName;
	}

	public int getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(int parameterValue) {
		this.parameterValue = parameterValue;
	}

	@Override
	public String toString() {
		return "HeaderParameterTO [id=" + id + ", actionId=" + actionId + ", parameterName=" + parameterName
				+ ", parameterValue=" + parameterValue + ", getId()=" + getId() + ", getActionId()=" + getActionId()
				+ ", getParameterName()=" + getParameterName() + ", getParameterValue()=" + getParameterValue()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}

}
