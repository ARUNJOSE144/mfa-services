package com.sixdee.magik.services.model;

import java.io.Serializable;

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
 * @Date : January, 2019
 *
 */
@Entity
@Table(name = "RE_RULEENGINE_URL")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UrlConfigTO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "UrlConfigTO")
	@TableGenerator(name = "UrlConfigTO", allocationSize = 1)
	@Column(name = "ID")
	private int Id;

	@Column(name = "RULENGINE_NAME")
	private String name;

	@Column(name = "RULENGINE_URL")
	private String url;

	@Column(name = "NODE_ID")
	private String nodeId;

	@Column(name = "USER_ID")
	private int userId;

	@Column(name = "IS_ONLINE")
	private String onlineTrigger;

	@Column(name = "CREATE_DATE")
	private String createDate;

	@Column(name = "IP")
	private String ip;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Transient
	private String auditRoleName;

	@Transient
	private String auditUserName;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNodeId() {
		return nodeId;
	}

	public void setNodeId(String nodeId) {
		this.nodeId = nodeId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOnlineTrigger() {
		return onlineTrigger;
	}

	public void setOnlineTrigger(String onlineTrigger) {
		this.onlineTrigger = onlineTrigger;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
		return "UrlConfigTO [Id=" + Id + ", name=" + name + ", url=" + url + ", nodeId=" + nodeId + ", userId=" + userId
				+ ", onlineTrigger=" + onlineTrigger + ", createDate=" + createDate + ", ip=" + ip + ", userName="
				+ userName + ", password=" + password + ", auditRoleName=" + auditRoleName + ", auditUserName="
				+ auditUserName + "]";
	}

}
