package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "EMAIL_MENU_DETAILS")
@JsonInclude(Include.NON_NULL)
public class EmailMasterTO implements Serializable {
 
	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "EmailMasterTO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EmailMasterTO")
	@Column(name = "MENU_ID")
	private int menuId;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "CREATE_USER")
	private String createUser;
	
	@CreationTimestamp
	@Column(name = "CREATED_TIME",nullable = false, updatable = false)
	private Date createdTime;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;


	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menuDetails", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<EmailFileTO> fileDetails;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "menuDetails", cascade=CascadeType.ALL, orphanRemoval = true)
	private List<EmailMessageTO> messageDetailsList;
	
	@Transient
	private List<EmailMasterTO> emailMenuList;
	
	public int getMenuId() {
		return menuId;
	}



	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}



	public String getMenuName() {
		return menuName;
	}



	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}



	public String getCreateUser() {
		return createUser;
	}



	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}



	public Date getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}



	public List<EmailMessageTO> getMessageDetails() {
		return messageDetailsList;
	}



	public void setMessageDetails(List<EmailMessageTO> messageDetailsList) {
		this.messageDetailsList = messageDetailsList;
	}



	public List<EmailMasterTO> getEmailMenuList() {
		return emailMenuList;
	}



	public void setEmailMenuList(List<EmailMasterTO> emailMenuList) {
		this.emailMenuList = emailMenuList;
	}



	public List<EmailFileTO> getFileDetails() {
		return fileDetails;
	}



	public void setFileDetails(List<EmailFileTO> fileDetails) {
		this.fileDetails = fileDetails;
	}



	public Date getModifiedDate() {
		return modifiedDate;
	}



	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}



	@Override
	public String toString() {
		return "EmailMasterTO [menuId=" + menuId + ", menuName=" + menuName + ", createUser=" + createUser + ", createdTime=" + createdTime +"]";
	}

}
