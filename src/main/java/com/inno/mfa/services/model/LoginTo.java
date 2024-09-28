package com.inno.mfa.services.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */

public class LoginTo implements Serializable {

	private static final long serialVersionUID = 1L;
	public String resultCode;
	public String responseMsg;
	public String token;
	public String refreshToken;
	public String tokenExpiry;
	public String username;
	public String msisdn;
	public int userId;
	public String fullName;
	public List<Integer> privilages;
	public String emailId;
	public String password;
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getResponseMsg() {
		return responseMsg;
	}
	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getTokenExpiry() {
		return tokenExpiry;
	}
	public void setTokenExpiry(String tokenExpiry) {
		this.tokenExpiry = tokenExpiry;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<Integer> getPrivilages() {
		return privilages;
	}
	public void setPrivilages(List<Integer> privilages) {
		this.privilages = privilages;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	
}