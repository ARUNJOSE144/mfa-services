package com.sixdee.magik.services.model;

import java.util.List;

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
@Table(name="TELEGRAM_BROADCAST_DETAILS")
@DynamicUpdate(true)
@JsonInclude(Include.NON_NULL)
public class TelegramSendMessageDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TelegramSendMessageDTO")
	@TableGenerator(name = "TelegramSendMessageDTO")
	@Column(name = "ID")
	private int id;
	
	@Column(name = "BOT_ID")
	private int botId;
	
	@Column(name = "BOT_NAME")
	private String botName;
	
	@Column(name = "CHAT_ID")
	private String chatId;

	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "MEDIA_TYPE")
	private String mediaType;
	
	@Column(name = "FILE_ID")
	private String fileId;
	
	@Column(name = "STATUS")
	private String status;
	
	@Transient
	private String statusDesc;
	
	@Transient
	private List<TelegramSendMessageDTO> broadcastList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBotId() {
		return botId;
	}

	public void setBotId(int botId) {
		this.botId = botId;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
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

	public List<TelegramSendMessageDTO> getBroadcastList() {
		return broadcastList;
	}

	public void setBroadcastList(List<TelegramSendMessageDTO> broadcastList) {
		this.broadcastList = broadcastList;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	@Override
	public String toString() {
		return "TelegramSendMessageDTO [id=" + id + ", botId=" + botId + ", botName=" + botName + ", chatId=" + chatId
				+ ", message=" + message + ", mediaType=" + mediaType + ", fileId=" + fileId + ", status=" + status
				+ ", statusDesc=" + statusDesc + ", broadcastList=" + broadcastList + "]";
	}

	

	
	

}
