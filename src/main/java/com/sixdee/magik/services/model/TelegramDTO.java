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
@Table(name="TELEGRAM_COMMANDS_CONFIG")
@DynamicUpdate(true)
@JsonInclude(Include.NON_NULL)
public class TelegramDTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "TelegramDTO")
	@TableGenerator(name = "TelegramDTO")
	@Column(name = "ID")
	private int id;

	@Column(name="BOT_ID")
	private int botId;
	
	@Column(name = "COMMAND")
	private String command;

	@Column(name = "RESPONSE_TYPE")
	private String responseType;
	
	@Column(name="STATUS")
	private String commandStatus;
	
	@Column(name="MESSAGE")
	private String message;
	
	@Column(name="FILE_ID")
	private String fileId;
	
	@Column(name="PARENT_ID")
	private int parentId;
	
	@Column(name="CHILDREN")
	private int children;
	
	@Transient
	private String status;
	@Transient
	private String statusDesc;
	@Transient
	private List<TelegramDTO> commandsList;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}


	public String getCommandStatus() {
		return commandStatus;
	}

	public void setCommandStatus(String commandStatus) {
		this.commandStatus = commandStatus;
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

	public List<TelegramDTO> getCommandsList() {
		return commandsList;
	}

	public void setCommandsList(List<TelegramDTO> commandsList) {
		this.commandsList = commandsList;
	}

	public int getBotId() {
		return botId;
	}

	public void setBotId(int botId) {
		this.botId = botId;
	}

	public String getResponseType() {
		return responseType;
	}

	public void setResponseType(String responseType) {
		this.responseType = responseType;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public int getChildren() {
		return children;
	}

	public void setChildren(int children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TelegramDTO [id=" + id + ", botId=" + botId + ", command=" + command + ", responseType=" + responseType
				+ ", commandStatus=" + commandStatus + ", message=" + message + ", fileId=" + fileId + ", parentId="
				+ parentId + ", children=" + children + ", status=" + status + ", statusDesc=" + statusDesc
				+ ", commandsList=" + commandsList + "]";
	}



}
