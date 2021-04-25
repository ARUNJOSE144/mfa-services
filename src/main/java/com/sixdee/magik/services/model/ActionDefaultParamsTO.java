package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author Aru Jose
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "RE_ACTION_DEFAULT_PARAMETERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActionDefaultParamsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionDefaultParamsTO")
	@TableGenerator(name = "ActionDefaultParamsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "PARAMETER_NAME")
	private String paramName;

	@Column(name = "DISPLAY_NAME")
	private String displayName;

	@Column(name = "PARAMETER_VALUE")
	private String selectValue;

	@Column(name = "DATA_TYPE")
	private String dataType;

	@Column(name = "GROUP_ID")
	private int groupId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public String toString() {
		return "ActionDefaultParamsTO [id=" + id + ", paramName=" + paramName + ", displayName=" + displayName
				+ ", selectValue=" + selectValue + ", dataType=" + dataType + ", groupId=" + groupId + "]";
	}

}
