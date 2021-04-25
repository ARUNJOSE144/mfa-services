package com.sixdee.magik.services.util;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class RestInfo<T> {
	private int operationCode;
	private String message;
	private T data;

	public RestInfo() {
	}

	public int getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(int operationCode) {
		this.operationCode = operationCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RestInfo(int operationCode, String message, T data) {
		super();
		this.operationCode = operationCode;
		this.message = message;
		this.data = data;
	}
}
