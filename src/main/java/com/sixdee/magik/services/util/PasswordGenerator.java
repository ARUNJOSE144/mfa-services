package com.sixdee.magik.services.util;

import java.security.SecureRandom;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class PasswordGenerator {

	private static SecureRandom random = new SecureRandom();

	/** different dictionaries used */
	private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
	private static final String NUMERIC = "0123456789";
	private static final String SPECIAL_CHARS = "!@#$%^&*_=+-/";

	/**
	 * Method will generate random string based on the parameters
	 * 
	 * @param len the length of the random string
	 * @param dic the dictionary used to generate the password
	 * @return the random password
	 */

	public String generatePassword() {
		int len = 6;
		String dic = ALPHA_CAPS + ALPHA + SPECIAL_CHARS + NUMERIC;
		String result = "";
		for (int i = 0; i < len; i++) {
			int index = random.nextInt(dic.length());
			result += dic.charAt(index);
		}
		return result;
	}

}
