package com.sixdee.magik.services.model;

import java.io.Serializable;


public class ChannelMessageTO implements Serializable {

	private String messageId;
	private String english;
	private String spanish;
	private String arabic;
	private String messageName;
	private String channelType;

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getEnglish() {
		return english;
	}

	public void setEnglish(String english) {
		this.english = english;
	}

	public String getSpanish() {
		return spanish;
	}

	public void setSpanish(String spanish) {
		this.spanish = spanish;
	}

	public String getArabic() {
		return arabic;
	}

	public void setArabic(String arabic) {
		this.arabic = arabic;
	}

	
	public String getMessageName() {
		return messageName;
	}

	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

	@Override
	public String toString() {
		return "ChannelMessageTO [messageId=" + messageId + ", english=" + english + ", spanish=" + spanish
				+ ", arabic=" + arabic + "]";
	}

}
