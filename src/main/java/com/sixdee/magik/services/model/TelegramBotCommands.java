package com.sixdee.magik.services.model;

public class TelegramBotCommands {

	private String command;
	
	private String description;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "TelegramBotCommands [command=" + command + ", description=" + description + "]";
	}
	
	
}
