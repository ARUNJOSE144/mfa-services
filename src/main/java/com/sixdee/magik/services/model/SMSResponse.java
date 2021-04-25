package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@JsonInclude(Include.NON_NULL)
public class SMSResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4568604164460031551L;

	public List<SmsCategoryTO> category;

	public String keyword;
	private String statusCode;
	private String status;
	public List<SMSMenusTO> menus;
	public List<SMSLangMessagesTO> messages;
	public List<SMSLanguagesTO> languages;
	
	
	



	public List<SmsCategoryTO> getCategory() {
		return category;
	}

	public void setCategory(List<SmsCategoryTO> category) {
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




	public List<SMSMenusTO> getMenus() {
		return menus;
	}




	public void setMenus(List<SMSMenusTO> menus) {
		this.menus = menus;
	}




	public List<SMSLangMessagesTO> getMessages() {
		return messages;
	}




	public void setMessages(List<SMSLangMessagesTO> messages) {
		this.messages = messages;
	}




	public List<SMSLanguagesTO> getLanguages() {
		return languages;
	}




	public void setLanguages(List<SMSLanguagesTO> languages) {
		this.languages = languages;
	}




	@Override
	public String toString() {
		return "SMSResponse [category=" + category + ", keyword="
				+ keyword + "]";
	}
	
}
