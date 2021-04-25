package com.sixdee.magik.services.model;

import java.util.Date;

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
@Table(name = "RE_BUSINESS_KPIS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class BussinessKpiTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "BussinessKpiTO")
	@TableGenerator(name = "BussinessKpiTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PROFILE_NAME")
	private String profileName;

	@Column(name = "GROUP_ID")
	private int groupId;

	@Column(name = "SUB_GROUP_ID")
	private int subGroupId;

	@Column(name = "DATA_TYPE")
	private int dataType;

	@Column(name = "SELECT_VALUE")
	private String selectValue;

	@Column(name = "CREATED_BY")
	private int createdBy;

	@Column(name = "CREATED_DATE")
	private Date createDate;
	@Column(name = "LAST_UPDATED_DATE")
	private Date updateDate;

	@Column(name = "FORMULA_ID")
	private int formulaId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getSubGroupId() {
		return subGroupId;
	}

	public void setSubGroupId(int subGroupId) {
		this.subGroupId = subGroupId;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public String getSelectValue() {
		return selectValue;
	}

	public void setSelectValue(String selectValue) {
		this.selectValue = selectValue;
	}

	public int getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getFormulaId() {
		return formulaId;
	}

	public void setFormulaId(int formulaId) {
		this.formulaId = formulaId;
	}

	@Override
	public String toString() {
		return "BussinessKpiTO [id=" + id + ", name=" + name + ", profileName=" + profileName + ", groupId=" + groupId
				+ ", subGroupId=" + subGroupId + ", dataType=" + dataType + ", selectValue=" + selectValue
				+ ", createdBy=" + createdBy + ", createDate=" + createDate + ", updateDate=" + updateDate
				+ ", formulaId=" + formulaId + "]";
	}

}
