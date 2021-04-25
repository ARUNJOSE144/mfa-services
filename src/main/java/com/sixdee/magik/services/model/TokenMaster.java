/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jul 4, 2018 : 6:28:06 PM
 */

@Entity
@Table(name = "MFS_TOKEN_MASTER")
public class TokenMaster {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "TOKEN_ID", updatable = false, nullable = false)
	private int tokenId;

	@Column(name = "TOKEN")
	private String token;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "SESSION_ID")
	private String sessionId;

	@Column(name = "SESSION_NAME")
	private String sessionName;

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	@Override
	public String toString() {
		return "TokenMaster [tokenId=" + tokenId + ", token=" + token + ", userId=" + userId + ", sessionId="
				+ sessionId + ", sessionName=" + sessionName + "]";
	}

}
