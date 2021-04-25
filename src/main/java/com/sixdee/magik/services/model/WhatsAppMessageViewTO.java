/*package com.sixdee.magik.services.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

*//**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 * 
 * 
 * Used for Rule Engine --Segment -- CampaignMessage Screens
 * 
 *    NotificationsDaoImpl : getChannelNotifications method
 *//*


@SuppressWarnings("serial")
@Entity
@Table(name = "RULE_CMP_WHATSAPP_VIEW")
public class WhatsAppMessageViewTO implements Serializable {
	
	@Id
	@Column(name = "ID")
	private String id;
	
	@Column(name = "MSG_ID")
	private int messageId;
	
	@Column(name = "MESSAGE_NAME")
	private String messageName;
	
	@Column(name = "CREATED_BY")
	private int createdUserId;
	
	@Column(name = "LANGUAGE_ID")
	private int langId;

	@Column(name = "MESSAGE_CONTENT")
	private String messageContent;

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public int getCreatedUserId() {
		return createdUserId;
	}

	public void setCreatedUserId(int createdUserId) {
		this.createdUserId = createdUserId;
	}

	public int getLangId() {
		return langId;
	}

	public void setLangId(int langId) {
		this.langId = langId;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
*/