package com.sixdee.magik.services.model;

import java.util.List;

public class SMSRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3289442503372662616L;
	
	public String keyword;
	public String userId;
	public String campId;
	public String menuName;
	public String menuId;
	public List<SMSMessagesTO> messages;
	public String url;
	public String campName;
	
	
	
	

	public String getCampName() {
		return campName;
	}


	public void setCampName(String campName) {
		this.campName = campName;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public List<SMSMessagesTO> getMessages() {
		return messages;
	}


	public void setMessages(List<SMSMessagesTO> messages) {
		this.messages = messages;
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getCampId() {
		return campId;
	}


	public void setCampId(String campId) {
		this.campId = campId;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	@Override
	public String toString() {
		return "GetUserRequest [keyword=" + keyword + ", userId=" + userId + "]";
	}
	
	

}
