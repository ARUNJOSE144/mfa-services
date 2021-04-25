package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name="TELEGRAM_CHAT_DETAILS")
@DynamicUpdate(true)
@JsonInclude(Include.NON_NULL)
public class TelegramChatDetailsDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TelegramBotConfig")
	@TableGenerator(name = "TelegramBotConfig")
	@Column(name = "ID")
	private int id;
	
	@Column(name="BOT_NAME")
	private String botName;
	
	@Column(name="BOT_ID")
	private String botId;
	
	@Column(name="CHAT_ID")
	private String chatId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="INCOMING_MESSAGE")
	private String incomingMessage;
	
	@Column(name="RESPONSE")
	private String response;
	
	@Column(name="CREATE_DATE")
	private String createDate;
	
	@Column(name="MESSAGE_ID")
	private int messageId;
	
	@Transient
	private String status;
	
	@Transient
	private String statusDesc;

	public TelegramChatDetailsDTO() {}
	
	
	public TelegramChatDetailsDTO(String botName, String botId, String chatId, String firstName, String lastName,
			String incomingMessage, String response, int messageId) {
		super();
		this.botName = botName;
		this.botId = botId;
		this.chatId = chatId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.incomingMessage = incomingMessage;
		this.response = response;
		this.messageId = messageId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	public String getBotId() {
		return botId;
	}

	public void setBotId(String botId) {
		this.botId = botId;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getIncomingMessage() {
		return incomingMessage;
	}

	public void setIncomingMessage(String incomingMessage) {
		this.incomingMessage = incomingMessage;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	@Override
	public String toString() {
		return "TelegramChatDetailsDTO [id=" + id + ", botName=" + botName + ", botId=" + botId + ", chatId=" + chatId
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", incomingMessage=" + incomingMessage
				+ ", response=" + response + ", createDate=" + createDate + ", messageId=" + messageId + ", status="
				+ status + ", statusDesc=" + statusDesc + "]";
	}

	
	
}
