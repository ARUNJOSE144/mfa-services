package com.sixdee.magik.services.model;

import java.util.List;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : January, 2021
 */
 
public class MOBILE_APP_RequestBody {
	
	public String messageId;
	public String messageName;
	public String categoryAutoId;
	public String userId;
	private List<MOBILE_APP_MGS_DTLS_RequestBody> outBoundmessages;
	private List<MOBILE_APP_MGS_DTLS_RequestBody> inboundMessages;
	
	
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public String getMessageName() {
		return messageName;
	}
	public void setMessageName(String messageName) {
		this.messageName = messageName;
	}
	public String getCategoryAutoId() {
		return categoryAutoId;
	}
	public void setCategoryAutoId(String categoryAutoId) {
		this.categoryAutoId = categoryAutoId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public List<MOBILE_APP_MGS_DTLS_RequestBody> getOutBoundmessages() {
		return outBoundmessages;
	}
	public void setOutBoundmessages(List<MOBILE_APP_MGS_DTLS_RequestBody> outBoundmessages) {
		this.outBoundmessages = outBoundmessages;
	}
	public List<MOBILE_APP_MGS_DTLS_RequestBody> getInboundMessages() {
		return inboundMessages;
	}
	public void setInboundMessages(List<MOBILE_APP_MGS_DTLS_RequestBody> inboundMessages) {
		this.inboundMessages = inboundMessages;
	}
	

}
