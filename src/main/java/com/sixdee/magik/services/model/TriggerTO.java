package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
 * @author Rajesh
 * @Date : November, 2018
 *
 */

@Entity
@Table(name = "RE_TRIGGERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TriggerTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TriggerGroupTO")
	@TableGenerator(name = "TriggerGroupTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;
	
	@Column(name = "Name")
	private String name;
	
	@Column(name = "GROUP_ID")
	private int groupId;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "JSON")
	private String triggerJson;
	
	@Column(name = "CREATE_DATE",updatable=false)
	private Date createDate;
	
	@Column(name = "MAIN_TRIGGER_ID")
	private int mappedTriggerId;
	
	@Transient
	private List<TriggerGroupTO> triggerGroupsList;
	
	@Transient
	private List<TriggerInfoTO> triggerInfoList;
	
	
	@Transient
	private String  createDateUI;
	
	@Transient
	private String  triggerGroupName;
	
	@Transient
	private String  triggerInfoGroupName;
	
	

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

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTriggerJson() {
		return triggerJson;
	}

	public void setTriggerJson(String triggerJson) {
		this.triggerJson = triggerJson;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public int getMappedTriggerId() {
		return mappedTriggerId;
	}

	public void setMappedTriggerId(int mappedTriggerId) {
		this.mappedTriggerId = mappedTriggerId;
	}

	public List<TriggerGroupTO> getTriggerGroupsList() {
		return triggerGroupsList;
	}

	public void setTriggerGroupsList(List<TriggerGroupTO> triggerGroupsList) {
		this.triggerGroupsList = triggerGroupsList;
	}

	public List<TriggerInfoTO> getTriggerInfoList() {
		return triggerInfoList;
	}

	public void setTriggerInfoList(List<TriggerInfoTO> triggerInfoList) {
		this.triggerInfoList = triggerInfoList;
	}

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}

	public String getTriggerGroupName() {
		return triggerGroupName;
	}

	public void setTriggerGroupName(String triggerGroupName) {
		this.triggerGroupName = triggerGroupName;
	}

	public String getTriggerInfoGroupName() {
		return triggerInfoGroupName;
	}

	public void setTriggerInfoGroupName(String triggerInfoGroupName) {
		this.triggerInfoGroupName = triggerInfoGroupName;
	}


	
	
	
}
