/**
 * 
 */
package com.sixdee.magik.services.exception;

/**
 * @author STS-117
 *
 */
public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
