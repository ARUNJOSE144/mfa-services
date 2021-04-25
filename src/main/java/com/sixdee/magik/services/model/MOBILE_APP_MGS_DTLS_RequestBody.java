package com.sixdee.magik.services.model;

import java.util.Date;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */
 
public class MOBILE_APP_MGS_DTLS_RequestBody {
	
	private String msgDtlsId;
    private String languageAutoId;
	private String message;
	private String title;
	private String ussdSenderId;
	private String appApiLink;
	private String webApiLink;
	private String messageType;
	public String deepLink;
	public String campaignType;
	public String priority;
	public String startDate;
	public String endDate;
	
	
	
	public String getMsgDtlsId() {
		return msgDtlsId;
	}
	public void setMsgDtlsId(String msgDtlsId) {
		this.msgDtlsId = msgDtlsId;
	}
	public String getLanguageAutoId() {
		return languageAutoId;
	}
	public void setLanguageAutoId(String languageAutoId) {
		this.languageAutoId = languageAutoId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUssdSenderId() {
		return ussdSenderId;
	}
	public void setUssdSenderId(String ussdSenderId) {
		this.ussdSenderId = ussdSenderId;
	}
	public String getAppApiLink() {
		return appApiLink;
	}
	public void setAppApiLink(String appApiLink) {
		this.appApiLink = appApiLink;
	}
	public String getWebApiLink() {
		return webApiLink;
	}
	public void setWebApiLink(String webApiLink) {
		this.webApiLink = webApiLink;
	}
	
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getDeepLink() {
		return deepLink;
	}
	public void setDeepLink(String deepLink) {
		this.deepLink = deepLink;
	}
	public String getCampaignType() {
		return campaignType;
	}
	public void setCampaignType(String campaignType) {
		this.campaignType = campaignType;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	

}
