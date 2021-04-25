/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.controller.bean;

/**
 * @author AfthabRajesh 1.0.0.0
 * @since 18-Sep-2020 : 7:09:41 PM
 */
public class CommonResponse {

	private String resultCode;
	private String responseMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

}
