package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_MOBILE_APP_TEMPLATE_DETAILS")
public class MOBILE_APP_TemplateDtlsTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "MOBILE_APP_TemplateDtlsTO")
	@TableGenerator(name = "MOBILE_APP_TemplateDtlsTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@OneToOne
	@JoinColumn(name="LANGUAGE_ID")
	private Language langagueTO;
	
	@Column(name = "MESSAGE_CONTENT")
	private String messageContent;
	 
	@ManyToOne
	@JoinColumn(name="MESSAGE_ID")
	@JsonIgnore
	private MOBILE_APP_TemplateTO mobileAppTemplateTO = new MOBILE_APP_TemplateTO();
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "TITLE")
	private String title;
	
	@Column(name = "USSD_SENDER_ID")
	private String ussdSenderId;
	
	@Column(name = "APP_API_LINK")
	private String appApiLink;
	
	@Column(name = "WEB_API_LINK")
	private String webApiLink;
	
	@Column(name = "TYPE")
	private String messageType;
	
	@Column(name = "DEEP_LINK")
	public String deepLink;
	
	@Column(name = "CAMPAIGN_TYPE")
	public String campaignType;
	
	@Column(name = "PRIORITY")
	public String priority;
	
	/*@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)*/
	@Column(name = "START_DATE")
	public String startDate;
	
	/*@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)*/
	@Column(name = "END_DATE")
	public String endDate;

	public int getAutoId() {
		return autoId;
	}

	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}

	public Language getLangagueTO() {
		return langagueTO;
	}

	public void setLangagueTO(Language langagueTO) {
		this.langagueTO = langagueTO;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public MOBILE_APP_TemplateTO getMobileAppTemplateTO() {
		return mobileAppTemplateTO;
	}

	public void setMobileAppTemplateTO(MOBILE_APP_TemplateTO mobileAppTemplateTO) {
		this.mobileAppTemplateTO = mobileAppTemplateTO;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
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
