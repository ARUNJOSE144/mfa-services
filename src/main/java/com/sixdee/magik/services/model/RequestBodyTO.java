package com.sixdee.magik.services.model;

import java.util.List;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 * @category : Used in CampaignMessage: SMSTemplate,USSDTemplate,WhatsAPP,Twitter,Telegram,Facebook,Skype,WAPTemplate
 */


public class RequestBodyTO {
	
	private String messageAutoId;
	private String message;
	private String userId;
	private int categoryId;
	private List<LanguageTransientTO> languageList;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<LanguageTransientTO> getLanguageList() {
		return languageList;
	}
	public void setLanguageList(List<LanguageTransientTO> languageList) {
		this.languageList = languageList;
	}
	public String getMessageAutoId() {
		return messageAutoId;
	}
	public void setMessageAutoId(String messageAutoId) {
		this.messageAutoId = messageAutoId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
