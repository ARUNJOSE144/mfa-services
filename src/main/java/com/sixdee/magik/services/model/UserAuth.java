package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jan 15, 2018 : 7:14:18 PM
 */

@Entity
@Table(name = "MFS_USER_AUTH")
public class UserAuth implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_NAME")
	private String userName;

	@Column(name = "CHANNEL_ID", nullable = false)
	private int channelId;

	@Column(name = "CHANNEL_NAME", nullable = false)
	private String channelName;

	@Column(name = "AUTH_METHOD", nullable = false)
	private short authReq;

	/** -----------------------------------------------------. **/

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public short getAuthReq() {
		return authReq;
	}

	public void setAuthReq(short authReq) {
		this.authReq = authReq;
	}

}