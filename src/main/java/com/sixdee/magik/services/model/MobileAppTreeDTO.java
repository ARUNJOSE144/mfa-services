package com.sixdee.magik.services.model;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
@Component
public class MobileAppTreeDTO {

	public List<MobileAppCategoryTO> category;

	public String keyword;
	private String statusCode;
	private String status;
	public List<MobileAppMenusTO> menus;
	public List<MobileAppLangMessagesTO> messages;
	public List<MobileAppLanguagesTO> languages;
	public List<MobileAppLangMessagesTO> inboundMessages;
	public String userId;
	public String campId;
	public String menuName;
	public String menuId;
	public String url;
	public String campName;
	
	public List<MobileAppCategoryTO> getCategory() {
		return category;
	}

	public void setCategory(List<MobileAppCategoryTO> category) {
		this.category = category;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<MobileAppMenusTO> getMenus() {
		return menus;
	}

	public void setMenus(List<MobileAppMenusTO> menus) {
		this.menus = menus;
	}

	public List<MobileAppLangMessagesTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MobileAppLangMessagesTO> messages) {
		this.messages = messages;
	}

	public List<MobileAppLanguagesTO> getLanguages() {
		return languages;
	}

	public void setLanguages(List<MobileAppLanguagesTO> languages) {
		this.languages = languages;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}
	public List<MobileAppLangMessagesTO> getInboundMessages() {
		return inboundMessages;
	}

	public void setInboundMessages(List<MobileAppLangMessagesTO> inboundMessages) {
		this.inboundMessages = inboundMessages;
	}


	@Override
	public String toString() {
		return "MobileAppTreeDTO [category=" + category + ", keyword=" + keyword + ", statusCode=" + statusCode
				+ ", status=" + status + ", menus=" + menus + ", messages=" + messages + ", languages=" + languages
				+ ", userId=" + userId + ", campId=" + campId + ", menuName=" + menuName + ", menuId=" + menuId
				+ ", url=" + url + ", campName=" + campName + "]";
	}

}
