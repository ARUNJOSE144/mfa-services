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
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

@Entity
@Table(name = "RE_SUB_GROUPS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubGroupTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SubGroupTO")
	@TableGenerator(name = "SubGroupTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATE_DATE")
	private String createDate;

	@Column(name = "UPDATE_DATE")
	private String updateDate;

	@Transient
	private int groupId;

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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
