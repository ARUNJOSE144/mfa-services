package com.sixdee.magik.services.model;

import java.util.List;



public class USSDRequest {
	
	private String Keyword;
	private String userId;
	public String campId;
	public String menuName;
	public String menuId;
	public String name;
	public String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<GetSMSMessages> messages;
	
	
	public List<GetSMSMessages> getMessages() {
		return messages;
	}
	public void setMessages(List<GetSMSMessages> messages) {
		this.messages = messages;
	}
	public String getKeyword() {
		return Keyword;
	}
	public void setKeyword(String keyword) {
		Keyword = keyword;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getCampId() {
		return campId;
	}
	public void setCampId(String campId) {
		this.campId = campId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
