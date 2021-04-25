package com.sixdee.magik.services.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "EMAIL_MENU_DETAILS")
@JsonInclude(Include.NON_NULL)
public class EmailMenuTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@TableGenerator(name = "EmailMenuTO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "EmailMenuTO")
	@Column(name = "MENU_ID")
	private int menuId;
	
	@Column(name = "MENU_NAME")
	private String menuName;
	
	@Column(name = "CREATE_USER")
	private String createUser;
	
	
	@Column(name = "CREATED_TIME")
	private String createdTime;
	
	@Transient
	private List<EmailMenuTO> emailMenuList;
	
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



	public String getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}



	public List<EmailMenuTO> getEmailMenuList() {
		return emailMenuList;
	}



	public void setEmailMenuList(List<EmailMenuTO> emailMenuList) {
		this.emailMenuList = emailMenuList;
	}



	@Override
	public String toString() {
		return "EmailMenuTO [menuId=" + menuId + ", menuName=" + menuName + ", createUser=" + createUser + ", createdTime=" + createdTime +"]";
	}

}
