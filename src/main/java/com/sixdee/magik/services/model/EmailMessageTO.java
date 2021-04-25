package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "EMAIL_MESSAGE_DETAILS")
public class EmailMessageTO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EmailMessageTO")
	@TableGenerator(name = "EmailMessageTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "LANGUAGE_ID")
    private String languageId;
	
	@Column(name = "SUBJECT")
	private String subject;
	
	
	@Column(name = "MESSAGE")
	private String message;
	
	@Column(name = "CREATE_USER")
	private int createUser;
	
	@CreationTimestamp
	@Column(name = "CREATE_TIME",nullable = false, updatable = false)
	private Date createTime;
	
	@Column(name = "FILE_PATH")
	private String filePath;
	
	
	@Column(name = "FILE_NAME")
	private String fileName;

	@ManyToOne
	@JoinColumn(name = "MENU_ID")
	private EmailMasterTO menuDetails;

//	public int getMenuId() {
//		return menuId;
//	}
//
//
//	public void setMenuId(int menuId) {
//		this.menuId = menuId;
//	}


	public String getLanguageId() {
		return languageId;
	}


	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public int getCreateUser() {
		return createUser;
	}


	public void setCreateUser(int createUser) {
		this.createUser = createUser;
	}



	public Date getCreateTime() {
		return createTime;
	}


	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getFilePath() {
		return filePath;
	}


	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public EmailMasterTO getMenuDetails() {
		return menuDetails;
	}


	public void setMenuDetails(EmailMasterTO menuDetails) {
		this.menuDetails = menuDetails;
	}


	@Override
	public String toString() {
		return "EmailMasterTO [languageId=" + languageId +", subject=" + subject + ", message=" + message 
				+ ", createUser="+createUser+", createTime"+createTime+", filePath="+filePath+", fileName="+fileName+"]";
	}

}
