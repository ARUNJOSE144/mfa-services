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
@Table(name = "EMAIL_MESSAGE_FILE_DETAILS")
public class EmailFileTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "EmailFileTO")
	@TableGenerator(name = "EmailFileTO", allocationSize = 1)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "LANGUAGE_ID")
    private String languageId;
	
	@Column(name = "CREATE_USER")
	private int createUser;
	
	@CreationTimestamp
	@Column(name = "CREATE_TIME",nullable = false, updatable = false)
	private Date createTime;
	
	@Column(name = "ENCODED_FILE")
	private String encodedFile;
	
	
	@Column(name = "FILE_NAME")
	private String fileName;
	
	@Column(name = "ORDER_ID")
	private String orderId;

	@ManyToOne
	@JoinColumn(name = "MENU_ID")
	private EmailMasterTO menuDetails;

	public String getLanguageId() {
		return languageId;
	}


	public void setLanguageId(String languageId) {
		this.languageId = languageId;
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


	public String getEncodedFile() {
		return encodedFile;
	}


	public void setEncodedFile(String encodedFile) {
		this.encodedFile = encodedFile;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
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


	/*
	 * @Override public String toString() { return "EmailMasterTO [languageId=" +
	 * languageId +", subject=" + subject + ", message=" + message +
	 * ", createUser="+createUser+", createTime"+createTime+", filePath="
	 * +filePath+", fileName="+fileName+"]"; }
	 */

}
