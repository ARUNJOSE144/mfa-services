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
 * @author arun.jose
 * @Date : December, 2018
 *
 */

@Entity
@Table(name = "RE_ACTION_INFO")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class ActionTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ActionTO")
	@TableGenerator(name = "ActionTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "ACTION_TYPE")
	private String type;

	@Column(name = "GROUP_ID")
	private int groupId;
	
	@Transient
	private String groupName;
	

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	


}
