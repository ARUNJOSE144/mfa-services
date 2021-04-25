package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "RE_EVENT_INFO")
public class EventTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EventTO")
	@TableGenerator(name = "EventTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "GROUP_ID")
	private int groupId;
	
	
	@Column(name = "JSON")
	private String eventJson;
	
	@Column(name = "USER_ID")
	private int userId;
	
	@Column(name = "CREATE_DATE",updatable=false)
	private Date createDate;
	
	@Column(name = "LAST_UPDATED_DATE")
	private Date lastUpdatedDate;
	
	@Transient 
	private String createDateUI;
	
	@Transient 
	private String eventGroupName;

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

	public String getEventJson() {
		return eventJson;
	}

	public void setEventJson(String eventJson) {
		this.eventJson = eventJson;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	
	

	public String getCreateDateUI() {
		return createDateUI;
	}

	public void setCreateDateUI(String createDateUI) {
		this.createDateUI = createDateUI;
	}
	
	

	public String getEventGroupName() {
		return eventGroupName;
	}

	public void setEventGroupName(String eventGroupName) {
		this.eventGroupName = eventGroupName;
	}

	@Override
	public String toString() {
		return "EventTO [Id=" + Id + ", name=" + name + ", groupId=" + groupId + ", eventJson=" + eventJson
				+ ", userId=" + userId + ", createDate=" + createDate + ", lastUpdatedDate=" + lastUpdatedDate + "]";
	}

}
