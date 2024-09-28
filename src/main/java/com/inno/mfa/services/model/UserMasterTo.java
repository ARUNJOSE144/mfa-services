package com.inno.mfa.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

@Entity
@Table(name = "USER_MASTER")
public class UserMasterTo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	private int userId;

	@ManyToOne
	@JoinColumn(name = "ROLE_ID", insertable = false, updatable = false)
	private RolesTo role;

	@Column(name = "USER_NAME", nullable = false)
	private String username;

	@Column(name = "PASSWORD", nullable = false)
	private String password = "admin123";

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "EMAIL_ID", nullable = false)
	private String emailId;

	@Column(name = "CONTACT_NUMBER", nullable = false)
	private String contactNumber;

	@Column(name = "WRONG_PWD_ATTEMPT", nullable = false)
	private int wrongPasswordAttempts;

	@Column(name = "STATUS", nullable = false)
	private int status;

	@Column(name = "ROLE_ID", nullable = false)
	private int roleId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public RolesTo getRole() {
		return role;
	}

	public void setRole(RolesTo role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public int getWrongPasswordAttempts() {
		return wrongPasswordAttempts;
	}

	public void setWrongPasswordAttempts(int wrongPasswordAttempts) {
		this.wrongPasswordAttempts = wrongPasswordAttempts;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

}