package com.sixdee.magik.services.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_WHATSAPP_DETAILS")
public class WhatsAppMessageDetailsTO {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "WhatsAppMessageDetailsTO")
	@TableGenerator(name = "WhatsAppMessageDetailsTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@OneToOne
	@JoinColumn(name="LANGUAGE_ID")
	private MessageLanguagesTO langagueTO;
	
	@Column(name = "MESSAGE_CONTENT")
	private String messageContent;
	
	
	@Column(name = "UPLOAD_FILE_PATH")
	private String uploadedFilePath;
	
	@Column(name = "STATUS")
	private String status;

	 
	@ManyToOne
	@JoinColumn(name="MESSAGE_ID")
	@JsonIgnore
	private WhatsAppDetailsTO whatsAppDetailsTO = new WhatsAppDetailsTO();
	
	
	
	@Transient
	private  List<AttachedFileTransientTO> attachedDocs;


	public int getAutoId() {
		return autoId;
	}


	public void setAutoId(int autoId) {
		this.autoId = autoId;
	}


	public MessageLanguagesTO getLangagueTO() {
		return langagueTO;
	}


	public void setLangagueTO(MessageLanguagesTO langagueTO) {
		this.langagueTO = langagueTO;
	}


	public String getMessageContent() {
		return messageContent;
	}


	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}


	public String getUploadedFilePath() {
		return uploadedFilePath;
	}


	public void setUploadedFilePath(String uploadedFilePath) {
		this.uploadedFilePath = uploadedFilePath;
	}


	public WhatsAppDetailsTO getWhatsAppDetailsTO() {
		return whatsAppDetailsTO;
	}


	public void setWhatsAppDetailsTO(WhatsAppDetailsTO whatsAppDetailsTO) {
		this.whatsAppDetailsTO = whatsAppDetailsTO;
	}


	public List<AttachedFileTransientTO> getAttachedDocs() {
		return attachedDocs;
	}


	public void setAttachedDocs(List<AttachedFileTransientTO> attachedDocs) {
		this.attachedDocs = attachedDocs;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
}
