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
 * @author Nakhil Kurian
 * @Date : Aprill, 2020
 *
 */
@Entity
@Table(name = "RE_ACTION_PARAMETERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NormalParameterTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "NormalParameterTO")
	@TableGenerator(name = "NormalParameterTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "ACTION_ID")
	private int actionId;

	@Column(name = "PARAMETER_NAME")
	private String parameterName;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "PARAMETER_VALUE")
	private String parameterValue;

	@Column(name = "DATA_TYPE")
	private String dataType;

	@Column(name = "VISIBILITY")
	private String visibility;

	@Column(name = "MANDATORY")
	private String mandatory;

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

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getParameterValue() {
		return parameterValue;
	}

	public void setParameterValue(String parameterValue) {
		this.parameterValue = parameterValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getMandatory() {
		return mandatory;
	}

	public void setMandatory(String mandatory) {
		this.mandatory = mandatory;
	}

	@Override
	public String toString() {
		return "NormalParameterTO [id=" + id + ", actionId=" + actionId + ", parameterName=" + parameterName
				+ ", displayName=" + displayName + ", parameterValue=" + parameterValue + ", dataType=" + dataType
				+ ", visibility=" + visibility + ", mandatory=" + mandatory + "]";
	}

}
