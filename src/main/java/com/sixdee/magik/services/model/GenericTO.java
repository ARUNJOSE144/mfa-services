package com.sixdee.magik.services.model;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class GenericTO {

	Integer id;
	String respCode;
	String respMessage;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMessage() {
		return respMessage;
	}

	public void setRespMessage(String respMessage) {
		this.respMessage = respMessage;
	}

}
