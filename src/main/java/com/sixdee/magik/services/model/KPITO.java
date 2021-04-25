package com.sixdee.magik.services.model;

import java.util.Date;

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
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "RE_BUSINESS_KPIS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class KPITO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "KPITO")
	@TableGenerator(name = "KPITO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "Name")
	private String name;

	@Column(name = "GROUP_ID")
	private int groupId;

	@Column(name = "SUB_GROUP_ID")
	private int subGroupId;

	@Column(name = "DATA_TYPE")
	private int dataTypeId;

	@Column(name = "CREATED_BY")
	private String cretedBy;

	@Column(name = "CREATED_DATE")
	private Date cretedDate;

	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;

	@Column(name = "PROFILE_NAME")
	private String profileName;

	@Column(name = "SELECT_VALUE")
	private String values;

	@Column(name = "FORMULA_ID")
	private int formulaId;

	@Transient
	private String groupName;

	@Transient
	private String subGroupName;

	@Transient
	private String dataType;

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getDataTypeId() {
		return dataTypeId;
	}

	public void setDataTypeId(int dataTypeId) {
		this.dataTypeId = dataTypeId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getCretedBy() {
		return cretedBy;
	}

	public void setCretedBy(String cretedBy) {
		this.cretedBy = cretedBy;
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

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public int getFormulaId() {
		return formulaId;
	}

	public Date getCretedDate() {
		return cretedDate;
	}

	public void setCretedDate(Date cretedDate) {
		this.cretedDate = cretedDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	public void setFormulaId(int formulaId) {
		this.formulaId = formulaId;
	}

}
