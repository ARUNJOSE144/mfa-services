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
@Table(name="TELEGRAM_BOT_CONFIG")
@DynamicUpdate(true)
@JsonInclude(Include.NON_NULL)
public class TelegramBotConfig {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TelegramBotConfig")
	@TableGenerator(name = "TelegramBotConfig")
	@Column(name = "ID")
	private int id;
	
	@Column(name="BOT_NAME")
	private String botName;
	
	@Column(name="TELEGRAM_BOT_ID")
	private String botId;
	
	@Column(name="BOT_KEY")
	private String botKey;
	
	@Column(name="LOCKED")
	private int locked;
	
	@Transient
	private String status;
	
	@Transient
	private String statusDesc;
	
	@Transient
	private List<TelegramBotConfig> botList;
	

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

	public String getBotKey() {
		return botKey;
	}

	public void setBotKey(String botKey) {
		this.botKey = botKey;
	}

	public int getLocked() {
		return locked;
	}

	public void setLocked(int locked) {
		this.locked = locked;
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

	public List<TelegramBotConfig> getBotList() {
		return botList;
	}

	public void setBotList(List<TelegramBotConfig> botList) {
		this.botList = botList;
	}

	@Override
	public String toString() {
		return "TelegramBotConfig [id=" + id + ", botName=" + botName + ", botId=" + botId + ", botKey=" + botKey
				+ ", locked=" + locked + ", status=" + status + ", statusDesc=" + statusDesc + ", botList=" + botList
				+ "]";
	}

	
}
