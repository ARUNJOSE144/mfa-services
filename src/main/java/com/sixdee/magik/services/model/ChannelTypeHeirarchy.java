/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Rajesh
 *
 */
@Entity
@Table(name = "MFS_CHANNEL_TYPE_HIERARCHY")
public class ChannelTypeHeirarchy implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHANNEL_TYPE")
	private int channelType;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String desc;

	@Column(name = "PARENT_ID")
	private Integer parentId;

	@Column(name = "TYPE")
	private int type;

	@Column(name = "MULTIPLE_LOC_FLAG")
	private int multLoc;

	@Transient
	private List<ChannelTypeHeirarchy> children;

	public int getChannelType() {
		return channelType;
	}

	public void setChannelType(int channelType) {
		this.channelType = channelType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMultLoc() {
		return multLoc;
	}

	public void setMultLoc(int multLoc) {
		this.multLoc = multLoc;
	}

	public List<ChannelTypeHeirarchy> getChildren() {
		return children;
	}

	public void setChildren(List<ChannelTypeHeirarchy> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "ChannelTypeHeirarchy [channelType=" + channelType + ", name=" + name + ", desc=" + desc + ", parentId="
				+ parentId + ", type=" + type + ", multLoc=" + multLoc + "]";
	}

}