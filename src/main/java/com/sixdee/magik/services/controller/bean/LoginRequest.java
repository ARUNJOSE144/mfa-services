package com.sixdee.magik.services.controller.bean;

import javax.validation.constraints.NotNull;

public class LoginRequest {

	@NotNull(message = "username can't null or Empty!")
	private String username;

	@NotNull(message = "password can't null  or Empty!")
	private String password;
	
	private String isMultiSession;

	/** ------------------------------------. **/

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

	public String getIsMultiSession() {
		return isMultiSession;
	}

	public void setIsMultiSession(String isMultiSession) {
		this.isMultiSession = isMultiSession;
	}

	@Override
	public String toString() {
		return "EntityBasicInfo [username=" + username + ", password=" + password + ", isMultiSession="+isMultiSession+"]";
	}

}
