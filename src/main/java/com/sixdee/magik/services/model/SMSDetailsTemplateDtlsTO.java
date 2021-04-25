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

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_SMS_TEMPLATE_DETAILS")
public class SMSDetailsTemplateDtlsTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SMSDetailsTemplateDtlsTO")
	@TableGenerator(name = "SMSDetailsTemplateDtlsTO", allocationSize = 1)
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
	private SMSDetailsTemplateTO smsDetailsTemplateTO = new SMSDetailsTemplateTO();
	
	@CreationTimestamp
	@Column(name="CREATED_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;

	
	
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

	public SMSDetailsTemplateTO getSmsDetailsTemplateTO() {
		return smsDetailsTemplateTO;
	}

	public void setSmsDetailsTemplateTO(SMSDetailsTemplateTO smsDetailsTemplateTO) {
		this.smsDetailsTemplateTO = smsDetailsTemplateTO;
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
	

}
