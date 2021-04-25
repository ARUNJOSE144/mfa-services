package com.sixdee.magik.services.model;

public class FacebookDataDTO {

	private String users;
	private String estimate_ready;

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getEstimate_ready() {
		return estimate_ready;
	}

	public void setEstimate_ready(String estimate_ready) {
		this.estimate_ready = estimate_ready;
	}

	@Override
	public String toString() {
		return "[users=" + users + ", estimate_ready=" + estimate_ready + "]";
	}

}
