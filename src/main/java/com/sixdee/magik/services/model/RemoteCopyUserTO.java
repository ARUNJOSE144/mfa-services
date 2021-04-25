package com.sixdee.magik.services.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

/**
 * @author Nakhil Kurian
 * @Date : January, 2021
 */

@Entity
@Table(name = "REMOTE_COPY")
public class RemoteCopyUserTO {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "RemoteCopyUserTO")
	@TableGenerator(name = "RemoteCopyUserTO", allocationSize = 1)

	@Column(name = "ID")
	private int id;

	@Column(name = "COPY_TOKEN_ID")
	private String copyTokenId;

	@Column(name = "PASTE_TOKEN_ID")
	private String pasteTokenId;

	@Column(name = "COPY_TOKEN_NAME")
	private String copyTokenName;

	@Column(name = "PASTE_TOKEN_NAME")
	private String pasteTokenName;

	@Column(name = "JSON")
	private String json;

	@Column(name = "TOKEN")
	private String token;

	@Column(name = "USERID")
	private String userID;

	@Column(name = "COMMENTS")
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCopyTokenId() {
		return copyTokenId;
	}

	public void setCopyTokenId(String copyTokenId) {
		this.copyTokenId = copyTokenId;
	}

	public String getPasteTokenId() {
		return pasteTokenId;
	}

	public void setPasteTokenId(String pasteTokenId) {
		this.pasteTokenId = pasteTokenId;
	}

	public String getCopyTokenName() {
		return copyTokenName;
	}

	public void setCopyTokenName(String copyTokenName) {
		this.copyTokenName = copyTokenName;
	}

	public String getPasteTokenName() {
		return pasteTokenName;
	}

	public void setPasteTokenName(String pasteTokenName) {
		this.pasteTokenName = pasteTokenName;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "RemoteCopyUserTO [id=" + id + ", copyTokenId=" + copyTokenId + ", pasteTokenId=" + pasteTokenId
				+ ", copyTokenName=" + copyTokenName + ", pasteTokenName=" + pasteTokenName + ", json=" + json
				+ ", token=" + token + ", userID=" + userID + ", comment=" + comment + "]";
	}

}
