package com.sixdee.magik.services.model;

import java.util.List;

public class TelegramBotCommandModel {

	private List<TelegramBotCommands> commands;

	public List<TelegramBotCommands> getCommands() {
		return commands;
	}

	public void setCommands(List<TelegramBotCommands> commands) {
		this.commands = commands;
	}

	@Override
	public String toString() {
		return "TelegramBotCommandModel [commands=" + commands + "]";
	}
	
	
	
}
