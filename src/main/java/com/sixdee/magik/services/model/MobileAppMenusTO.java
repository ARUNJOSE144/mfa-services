package com.sixdee.magik.services.model;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "MOBILE_APP_MENU_DETAILS")
public class MobileAppMenusTO {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@TableGenerator(name = "MobileAppMenusTO", allocationSize = 1)
	
	@Column(name = "MENU_ID")
	public int menuId;
	
	@Column(name = "MENU_NAME")
	public String menuName;
	
	@Column(name = "CAMP_ID")
	public String campId;
	
	@Column(name = "CREATE_USER")
	public String userId;
	
	@Transient
	private String statusCode;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "menuDetails", cascade=CascadeType.ALL, orphanRemoval = true)
	@Transient
	private List<MobileAppMenuMsgDetailsTO> messages;
	
	//@OneToMany(fetch = FetchType.LAZY, mappedBy = "menuDetails", cascade=CascadeType.ALL, orphanRemoval = true)
	@Transient
	private List<MobileAppMenuMsgDetailsTO> inboundMessages;


	

	public List<MobileAppMenuMsgDetailsTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MobileAppMenuMsgDetailsTO> messages) {
		this.messages = messages;
	}

	public List<MobileAppMenuMsgDetailsTO> getInboundMessages() {
		return inboundMessages;
	}

	public void setInboundMessages(List<MobileAppMenuMsgDetailsTO> inboundMessages) {
		this.inboundMessages = inboundMessages;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

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
