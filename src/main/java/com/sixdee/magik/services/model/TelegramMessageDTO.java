package com.sixdee.magik.services.model;

import java.util.List;

public class TelegramMessageDTO {
	
	private int message_id;
	
	private String date;
	
	private String text;
	
	private TelegramFromDTO from;
	
	private TelegramChatDTO chat;
	
	private List<TelegramPaginationDTO> entities;

	public int getMessage_id() {
		return message_id;
	}

	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TelegramFromDTO getFrom() {
		return from;
	}

	public void setFrom(TelegramFromDTO from) {
		this.from = from;
	}

	public TelegramChatDTO getChat() {
		return chat;
	}

	public void setChat(TelegramChatDTO chat) {
		this.chat = chat;
	}

	public List<TelegramPaginationDTO> getEntities() {
		return entities;
	}

	public void setEntities(List<TelegramPaginationDTO> entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		return "TelegramMessageDTO [message_id=" + message_id + ", date=" + date + ", text=" + text + ", from=" + from
				+ ", chat=" + chat + ", entities=" + entities + "]";
	}
	
	
	

}
