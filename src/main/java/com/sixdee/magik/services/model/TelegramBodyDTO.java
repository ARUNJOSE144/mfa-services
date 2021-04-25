package com.sixdee.magik.services.model;

public class TelegramBodyDTO {

	private String update_id;
	
	private TelegramMessageDTO message;

	public String getUpdate_id() {
		return update_id;
	}

	public void setUpdate_id(String update_id) {
		this.update_id = update_id;
	}

	public TelegramMessageDTO getMessage() {
		return message;
	}

	public void setMessage(TelegramMessageDTO message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TelegramBodyDTO [update_id=" + update_id + ", message=" + message + "]";
	}
	
	
}
