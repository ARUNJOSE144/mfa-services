/**
 * 
 */
package com.inno.mfa.services.exception;

/**
 * @author Arun Jose
 * @Date : March, 2021
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
