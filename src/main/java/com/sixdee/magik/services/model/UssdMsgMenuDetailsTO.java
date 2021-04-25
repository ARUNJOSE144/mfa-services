package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "USSD_MENU_MSG_DETAILS")
public class UssdMsgMenuDetailsTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "UssdMsgMenuDetailsTO")
	@TableGenerator(name = "UssdMsgMenuDetailsTO", allocationSize = 1)
	@Column(name = "ID")
	public String id;

	@Column(name = "MENU_ID")
	public String menu_id;

	@Column(name = "LANGUAGE_ID")
	public String language_id;

	@Column(name = "MESSAGE")
	public String message;

	@Column(name = "USER_ID")
	public String user_id;

	@Column(name = "MSG_COUNT")
	public String msg_count;

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getLanguage_id() {
		return language_id;
	}

	public void setLanguage_id(String language_id) {
		this.language_id = language_id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String mesage) {
		this.message = mesage;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getMsg_count() {
		return msg_count;
	}

	public void setMsg_count(String msg_count) {
		this.msg_count = msg_count;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	

	public UssdMsgMenuDetailsTO() {
		super();
	}

	public UssdMsgMenuDetailsTO(String id, String language_id, String message, String user_id) {
		super();
		this.id = id;
		this.language_id = language_id;
		this.message = message;
		this.user_id = user_id;
	}
	
	/*
	 * public UssdMsgMenuDetailsTO(String menu_id, String language_id, String
	 * message, String user_id) { super(); this.menu_id = menu_id; this.language_id
	 * = language_id; this.message = message; this.user_id = user_id; }
	 */

	@Override
	public String toString() {
		return "UssdMsgMenuDetailsTO [id=" + id + ", menu_id=" + menu_id + ", language_id=" + language_id + ", message="
				+ message + ", user_id=" + user_id + ", msg_count=" + msg_count + "]";
	}

}
