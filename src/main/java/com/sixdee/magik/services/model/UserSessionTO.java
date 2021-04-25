package com.sixdee.magik.services.model;

/**
 * @author ramesh.cheerla
 * @Date : October, 2018
 *
 */

public class UserSessionTO {

	private int userId;
	private String userName;
	private String sessionId;

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

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

}
