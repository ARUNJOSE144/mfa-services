package com.sixdee.magik.services.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class USSDResponse {

	
	public List<GetCampaigns> campaigns;

	public String keyword;
	private String statusCode;
	private String status;
	public List<GetSMSMenusTO> menus;
	public List<GetSMSLangMessages> messages;
	public List<GetLanguages> languages;


	public List<GetSMSMenusTO> getMenus() {
		return menus;
	}

	public void setMenus(List<GetSMSMenusTO> menus) {
		this.menus = menus;
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

	public List<GetCampaigns> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(List<GetCampaigns> campaigns) {
		this.campaigns = campaigns;
	}

	public List<GetSMSLangMessages> getMessages() {
		return messages;
	}

	public void setMessages(List<GetSMSLangMessages> messages) {
		this.messages = messages;
	}
	
	public List<GetLanguages> getLanguages() {
		return languages;
	}

	public void setLanguages(List<GetLanguages> languages) {
		this.languages = languages;
	}

}
