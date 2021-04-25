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
@Table(name = "RE_GROUP_SUBGROUP_MAPING")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class SubGroupMappingTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SubGroupMappingTO")
	@TableGenerator(name = "SubGroupMappingTO", allocationSize = 1)

	@Column(name = "GROUP_ID")
	private int groupId;

	@Column(name = "SUB_GROUP_ID")
	private int subGroupId;

	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "UPDATE_DATE")
	private Date updateDate;

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

	@Override
	public String toString() {
		return "SubGroupMappingTO [groupId=" + groupId + ", subGroupId=" + subGroupId + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}

}
