/**
 * 
 */
package com.sixdee.magik.services.model;

import javax.persistence.Transient;

/**
 * @author Vinay Kariyappa
 *
 *         Nov 20, 2018
 */
public class GenericGroupDTO {

	private Integer Id;
	private Integer groupId;
	private Integer subGroupId;
	private String groupName;
	private String subGroupName;
	private String englishName;
	private String createdBy;
	private String createdDate;
	private String lastUpdatedDate;
	private String dataType;
	private Integer dataTypeId;
	private String blSubGroupName;
	private Integer blGroupId;
	private Integer blSubGroupId;
	private String mode;
	private String selectValue;

	private String auditRoleName;
	private String auditUserName;

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(Integer subGroupId) {
		this.subGroupId = subGroupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getSubGroupName() {
		return subGroupName;
	}

	public void setSubGroupName(String subGroupName) {
		this.subGroupName = subGroupName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(String lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(Integer dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getBlSubGroupName() {
		return blSubGroupName;
	}

	public void setBlSubGroupName(String blSubGroupName) {
		this.blSubGroupName = blSubGroupName;
	}

	public Integer getBlGroupId() {
		return blGroupId;
	}

	public void setBlGroupId(Integer blGroupId) {
		this.blGroupId = blGroupId;
	}

	public Integer getBlSubGroupId() {
		return blSubGroupId;
	}

	public void setBlSubGroupId(Integer blSubGroupId) {
		this.blSubGroupId = blSubGroupId;
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
		return "GenericGroupDTO [Id=" + Id + ", groupId=" + groupId + ", subGroupId=" + subGroupId + ", groupName="
				+ groupName + ", subGroupName=" + subGroupName + ", englishName=" + englishName + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", lastUpdatedDate=" + lastUpdatedDate + ", dataType="
				+ dataType + ", dataTypeId=" + dataTypeId + ", blSubGroupName=" + blSubGroupName + ", blGroupId="
				+ blGroupId + ", blSubGroupId=" + blSubGroupId + ", mode=" + mode + ", selectValue=" + selectValue
				+ ", auditRoleName=" + auditRoleName + ", auditUserName=" + auditUserName + "]";
	}

}
