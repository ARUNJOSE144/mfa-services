/**
 * 
 */
package com.sixdee.magik.services.model;

import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Vinay Kariyappa
 *
 *         Nov 23, 2018
 */
@JsonInclude(Include.NON_NULL)
public class ActionsDTO {

	private Integer id;
	private String name;
	private String actionType;
	private String paramName;
	private String paramValue;
	private Integer actionId;
	private Integer groupId;
	private String displayName;
	private String visibility;
	private String mandatory;
	private String dataType;
	private String mode;
	private String normalParamName;
	private String normalParamValue;

	private String auditRoleName;
	private String auditUserName;

	public String getNormalParamName() {
		return normalParamName;
	}

	public void setNormalParamName(String normalParamName) {
		this.normalParamName = normalParamName;
	}

	public String getNormalParamValue() {
		return normalParamValue;
	}

	public void setNormalParamValue(String normalParamValue) {
		this.normalParamValue = normalParamValue;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getActionId() {
		return actionId;
	}

	public void setActionId(Integer actionId) {
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

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuditRoleName() {
		return auditRoleName;
	}

	public void setAuditRoleName(String auditRoleName) {
		this.auditRoleName = auditRoleName;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	@Override
	public String toString() {
		return "ActionsDTO [id=" + id + ", name=" + name + ", actionType=" + actionType + ", paramName=" + paramName
				+ ", paramValue=" + paramValue + ", actionId=" + actionId + ", groupId=" + groupId + ", displayName="
				+ displayName + ", visibility=" + visibility + ", mandatory=" + mandatory + ", dataType=" + dataType
				+ ", mode=" + mode + ", normalParamName=" + normalParamName + ", normalParamValue=" + normalParamValue
				+ ", auditRoleName=" + auditRoleName + ", auditUserName=" + auditUserName + "]";
	}

}
