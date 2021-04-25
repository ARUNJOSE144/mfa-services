package com.sixdee.magik.services.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "USSD_MENU_DETAILS")
@JsonInclude(Include.NON_NULL)
public class GetSMSMenusTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="system-uuid") //, generator = "GetSMSMenusTO"
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	@TableGenerator(name = "GetSMSMenusTO", allocationSize = 1)
	@Column(name = "MENU_ID")
	public String menuId;

	@Column(name = "MENU_NAME")
	public String menuName;

	@Column(name = "CAMP_ID")
	public String campId;
	
	@Column(name = "CREATE_USER")
	public String createUser;

	
	@CreationTimestamp
	@Column(name="CREATE_DATE",nullable = false, updatable = false)
	private Date createdDate;
	
	@UpdateTimestamp
	@Column(name="MODIFIED_DATE")
	private Date modifiedDate;
	
	
	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	
	
	

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public GetSMSMenusTO() {
		super();
	}

	public GetSMSMenusTO(String menuName, String campId, String createUser) {
		super();
		this.menuName = menuName;
		this.campId = campId;
		this.createUser = createUser;
	}

	@Override
	public String toString() {
		return "GetSMSMenusTO [menuId=" + menuId + ", menuName=" + menuName + ", campId=" + campId + "]";
	}

}
