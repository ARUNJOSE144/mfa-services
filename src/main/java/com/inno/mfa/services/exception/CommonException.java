package com.inno.mfa.services.exception;

/**
 * @author Arun Jose
 * @Date : March, 2021
 */
public class CommonException extends RuntimeException {

	private static final long serialVersionUID = -857968536935667808L;
	private String statusCode;

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * Constructs an InvalidParameterException with no detail message. A detail
	 * message is a String that describes this particular exception.
	 */
	public CommonException() {
		super();
	}

	/**
	 * Constructs an InvalidParameterException with the specified detail message. A
	 * detail message is a String that describes this particular exception.
	 *
	 * @param msg the detail message.
	 */
	public CommonException(String msg) {
		super(msg);
	}

	public CommonException(String msg, int statusCode) {
		super(msg);
		this.statusCode = String.valueOf(statusCode);
	}

	public CommonException(String msg, String statusCode) {
		super(msg);
		this.statusCode = (statusCode);
	}

}
