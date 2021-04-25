package com.sixdee.magik.services.util;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class ValidationException extends Exception {

	private static final long serialVersionUID = 5184844668528363799L;
	private String message;
	private int errorCode;

	public ValidationException(String message, int errorCode) {
		this.message = message;
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
