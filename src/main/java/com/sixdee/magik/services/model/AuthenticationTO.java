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
@Table(name = "AUTHENTICATION")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AuthenticationTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AuthenticationTO")
	@TableGenerator(name = "AuthenticationTO", allocationSize = 1)
	@Column(name = "ID")
	private int userId;

	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "NO_OF_ATTEMPTS")
	private int noOfAttemps;

	@Transient
	private String mailId;
	
	@Transient
	private String currentPassword;
	
	@Transient
	private String StatusCode;

	public String getStatusCode() {
		return StatusCode;
	}

	public void setStatusCode(String statusCode) {
		StatusCode = statusCode;
	}

	public int getNoOfAttemps() {
		return noOfAttemps;
	}

	public void setNoOfAttemps(int noOfAttemps) {
		this.noOfAttemps = noOfAttemps;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public String getMailId() {
		return mailId;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public void setMailId(String mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "AuthenticationTO [userId=" + userId + ", userName=" + userName + ", password=" + password
				+ ", noOfAttemps=" + noOfAttemps + ", mailId=" + mailId + ", currentPassword=" + currentPassword
				+ ", StatusCode=" + StatusCode + "]";
	}

	
}
