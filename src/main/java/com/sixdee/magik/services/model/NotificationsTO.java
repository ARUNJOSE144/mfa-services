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
@Table(name = "NOTIFICATIONS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class NotificationsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "NotificationsTO")
	@TableGenerator(name = "NotificationsTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "NOTIFICATION")
	private String notification;

	@Column(name = "CREATE_DATE")
	private String createDate;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "UPDATED_BY")
	private String updateBy;

	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "ROLE_ID")
	private String roleID;
	
	@Column(name = "ROLE_NAME")
	private String roleName;
	
	@Column(name = "DAY_TYPE")
	private String dayType;

	@Column(name = "VIEW_ALL_NOTIFICATIONS")
	private String viewallNotifications;
	

	@Transient
	private String startDate;

	@Transient
	private String endDate;
	
	

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getNotification() {
		return notification;
	}

	public void setNotification(String notification) {
		this.notification = notification;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

	public String getDayType() {
		return dayType;
	}

	public void setDayType(String dayType) {
		this.dayType = dayType;
	}

	
	public String getViewallNotifications() {
		return viewallNotifications;
	}

	public void setViewallNotifications(String viewallNotifications) {
		this.viewallNotifications = viewallNotifications;
	}

	@Override
	public String toString() {
		return "NotificationsTO [Id=" + Id + ", subject=" + subject + ", notification=" + notification + ", createDate="
				+ createDate + ", userId=" + userId + ", updateBy=" + updateBy + ", status=" + status + ", roleID="
				+ roleID + ", roleName=" + roleName + ", dayType=" + dayType + ", viewallNotifications="
				+ viewallNotifications + "]";
	}

	

}
