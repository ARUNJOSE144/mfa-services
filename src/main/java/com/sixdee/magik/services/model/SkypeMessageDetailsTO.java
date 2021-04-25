package com.sixdee.magik.services.model;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ABHIRAM MACHIRAJU
 * @Date : December, 2020
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CMP_MSG_SKYPE_DETAILS")
public class SkypeMessageDetailsTO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SkypeMessageDetailsTO")
	@TableGenerator(name = "SkypeMessageDetailsTO", allocationSize = 1)
	@Column(name = "ID")
	private int autoId;
	
	@OneToOne
	@JoinColumn(name="LANGUAGE_ID")
	private MessageLanguagesTO langagueTO;
	
	@Column(name = "MESSAGE_CONTENT")
	private String messageContent;
	
	
	@Column(name = "STATUS")
	private String status;
	
	/*@Transient
	private  List<AttachedFileTransientTO> attachedDocs;*/
	 
	@ManyToOne
	@JoinColumn(name="MESSAGE_ID")
	@JsonIgnore
	private SkypeDetailsTO skypeDetailsTO = new SkypeDetailsTO();

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public SkypeDetailsTO getSkypeDetailsTO() {
		return skypeDetailsTO;
	}

	public void setSkypeDetailsTO(SkypeDetailsTO skypeDetailsTO) {
		this.skypeDetailsTO = skypeDetailsTO;
	}
	
	

	
}
