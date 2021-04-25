package com.sixdee.magik.services.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the MENU_DETAILS database table.
 * 
 */
@JsonInclude(Include.NON_NULL)
@Entity
@Table(name = "MENU_DETAILS")
public class MenuDetailDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MENU_ID")
	private int menuId;

	@Column(name = "CHANGE_DATE")
	private Timestamp changeDate;

	@Column(name = "CHANGE_USER")
	private String changeUser;

	@Column(name = "CREATE_DATE")
	private Timestamp createDate;

	@Column(name = "CREATE_USER")
	private String createUser;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IS_BOOTSTRAP")
	private int isBootstrap;

	@Column(name = "MENU_NAME")
	private String menuName;

	@Column(name = "MENU_ORDER")
	private int menuOrder;

	@Column(name = "URL")
	private String url;

	@Column(name = "PARENT_ID")
	private int parentId;

	@Column(name = "ICON")
	private String iconName;

	@Column(name = "SHORTCUT")
	private boolean shortcut;
	
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "MODULE_ID")
	private int moduleId;

	/*
	 * @Column(name="CHILD_EXIST") private boolean childExist;
	 */

	@Transient
	private List<String> subMenuFnList = null;

	public List<String> getSubMenuFnList() {
		return subMenuFnList;
	}

	public void setSubMenuFnList(List<String> subMenuFnList) {
		this.subMenuFnList = subMenuFnList;
	}

	public MenuDetailDTO() {
	}

	public int getMenuId() {
		return this.menuId;
	}

	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}

	public Timestamp getChangeDate() {
		return this.changeDate;
	}

	public void setChangeDate(Timestamp changeDate) {
		this.changeDate = changeDate;
	}

	public String getChangeUser() {
		return this.changeUser;
	}

	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}

	public Timestamp getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getIsBootstrap() {
		return this.isBootstrap;
	}

	public void setIsBootstrap(int isBootstrap) {
		this.isBootstrap = isBootstrap;
	}

	public String getMenuName() {
		return this.menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public int getMenuOrder() {
		return this.menuOrder;
	}

	public void setMenuOrder(int menuOrder) {
		this.menuOrder = menuOrder;
	}

	public void setIconName(String iconName) {
		this.iconName = iconName;
	}

	public String getIconName() {
		return iconName;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public boolean isShortcut() {
		return shortcut;
	}

	public void setShortcut(boolean shortcut) {
		this.shortcut = shortcut;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getModuleId() {
		return moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}
	
	

	/*
	 * public boolean isChildExist() { return childExist; }
	 * 
	 * public void setChildExist(boolean childExist) { this.childExist = childExist;
	 * }
	 */

}