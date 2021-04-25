package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "MOBILE_APP_MENU_MSG_DETAILS")
public class MobileAppMenuMsgDetailsTO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@TableGenerator(name = "MobileAppMenuMsgDetailsTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;
		
	@Column(name = "MENU_ID")
	private int menuId;
	
	@Column(name = "LANGUAGE_ID")
    private String langId;
		
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "USER_ID")
	private int userId;
	
	/*@CreationTimestamp
	@Column(name = "CREATE_TIME",nullable = false, updatable = false)
	private Date createTime;*/
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "USSD_SENDER_ID")
	private String ussdSenderId;
	
	@Column(name = "APP_API_LINK")
	private String appApiLink;
	
	@Column(name = "WEB_API_LINK")
	private String webApiLink;
	
	@Column(name = "MSG_TYPE")
	private int messageType;
	
	@Column(name = "DEEP_LINK")
	public String deepLink;
	
	@Column(name = "CAMPAIGN_TYPE")
	public String campaignType;
	
	@Column(name = "PRIORITY")
	public String priority;
	
	@Column(name = "START_DATE")
	public String startDate;
	
	@Column(name = "END_DATE")
	public String endDate;



	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
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


	public int getMenuId() {
		return menuId;
	}


	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
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




	public String getLangId() {
		return langId;
	}


	public void setLangId(String langId) {
		this.langId = langId;
	}


	public int getMessageType() {
		return messageType;
	}


	public void setMessageType(int messageType) {
		this.messageType = messageType;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	/*public MobileAppMenusTO getMenuDetails() {
		return menuDetails;
	}


	public void setMenuDetails(MobileAppMenusTO menuDetails) {
		this.menuDetails = menuDetails;
	}*/


	

	

	

}
