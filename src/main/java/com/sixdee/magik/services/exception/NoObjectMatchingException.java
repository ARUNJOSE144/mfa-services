/**
 * SixDEE Telecom Solutions Pvt. Ltd.
 * Copyright 2017
 * All Rights Reserved.
 */
package com.sixdee.magik.services.exception;

/**
 * @author Afthab
 * @version 1.0.0.0
 * @since 23-Oct-2017 : 1:08:10 PM
 */
public class NoObjectMatchingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoObjectMatchingException(String message) {
		super(message);
	}

}
