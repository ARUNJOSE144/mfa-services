package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SMS_MENU_DETAILS")
public class SMSMenusTO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "SMSMenusTO")
	@TableGenerator(name = "SMSMenusTO", allocationSize = 1)
	
	@Column(name = "MENU_ID")
	public int menuId;
	
	@Column(name = "MENU_NAME")
	public String menuName;
	
	@Column(name = "CAMP_ID")
	public String campId;
	
	@Column(name = "CREATE_USER")
	public String userId;

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

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
