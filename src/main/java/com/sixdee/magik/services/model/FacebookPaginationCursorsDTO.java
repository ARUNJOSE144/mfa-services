package com.sixdee.magik.services.model;

public class FacebookPaginationCursorsDTO {

	private String before;
	private String after;
	
	public String getBefore() {
		return before;
	}
	public void setBefore(String before) {
		this.before = before;
	}
	public String getAfter() {
		return after;
	}
	public void setAfter(String after) {
		this.after = after;
	}
	@Override
	public String toString() {
		return "FacebookPaginationCursorsDTO [before=" + before + ", after=" + after + "]";
	}
	
	
}
