package com.sixdee.magik.services.util;

import java.util.Random;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sixdee.magik.services.model.PasswordMgmnt;

/**
 * @author Basil Jose
 * @version 1.0.0.0
 * @since Jul 26, 2018 : 11:37:02 AM
 */

@Component
public class PasswordCore {
	
	@Autowired
	SystemProperties systemProperties;
	
	public String generatePassword(PasswordMgmnt pas) {
		String password = RandomStringUtils.randomNumeric(5);

		if (pas != null) {
			if (pas.getType() == 1) {
				// alphabets
				if (isOn(pas.getCapsReq())) {
					if (isOn(pas.getSpCharReq())) {
						password = (RandomStringUtils.randomAlphabetic(1).toUpperCase()
								+ RandomStringUtils.randomAlphabetic(pas.getLength() - 2).toLowerCase())
								+ getSpecialChara();

					} else {
						password = (RandomStringUtils.randomAlphabetic(1).toUpperCase()
								+ RandomStringUtils.randomAlphabetic(pas.getLength() - 1).toLowerCase());
					}
				} else {
					if (isOn(pas.getSpCharReq())) {
						password = RandomStringUtils.randomAlphabetic(pas.getLength() - 1).toLowerCase()
								+ getSpecialChara();
					} else {
						password = RandomStringUtils.randomAlphabetic(pas.getLength()).toLowerCase();
					}
				}
			} else if (pas.getType() == 2) {
				// numeric
				password = RandomStringUtils.randomNumeric(pas.getLength());

			} else {
				// alphanumberic
				if (isOn(pas.getCapsReq())) {
					if (isOn(pas.getSpCharReq())) {
						password = (RandomStringUtils.randomAlphanumeric(1).toUpperCase()
								+ RandomStringUtils.randomAlphanumeric(pas.getLength() - 3).toLowerCase())
								+ getSpecialChara() + RandomStringUtils.randomNumeric(1);
					} else {
						password = (RandomStringUtils.randomAlphanumeric(1).toUpperCase()
								+ RandomStringUtils.randomAlphanumeric(pas.getLength() - 2).toLowerCase())
								+ RandomStringUtils.randomNumeric(1);
					}
				} else {
					if (isOn(pas.getSpCharReq())) {
						password = RandomStringUtils.randomAlphanumeric(pas.getLength() - 2).toLowerCase()
								+ getSpecialChara() + RandomStringUtils.randomNumeric(1);

					} else {
						password = RandomStringUtils.randomAlphanumeric(pas.getLength() - 1).toLowerCase()
								+ RandomStringUtils.randomNumeric(1);
					}
				}
			}
		}
		return password;
	}

	public boolean validateRule(PasswordMgmnt pas, String password) {
		if (pas != null) {

			if (pas.getLength() > password.length()) {
				return false;
			}

			if (pas.getType() == 1) {
				// alphabets
				if (!isAlphabets(password)) {
					return false;
				}
				if (isOn(pas.getCapsReq()) && !isContainsUpperCase(password)) {
					return false;
				}
				if (isOn(pas.getSpCharReq()) && !isContainsSpecialCharacter(password)) {
					return false;
				}
				/*if(isOn(pas.getAvoidConsec()) && hasConcecutiveAlphabets(password)) {
					return false;
				}*/
				/*if(isOn(pas.getAvoidSameChar()) && hasCharRepeated(password)) {
					return false;
				}*/
			} else if (pas.getType() == 2) {
				// numeric
				if(!StringUtils.isNumeric(password))
					return false;
				
				/*if(isOn(pas.getAvoidConsec()) && hasConcecutiveNumbers(password))
					return false;*/
				
				/*if(isOn(pas.getAvoidSameChar()) && hasCharRepeated(password))
					return false;*/

			} else {
				// alphanumberic
				if (!isAlphaNumeric(password)) {
					return false;
				}
				if (isOn(pas.getCapsReq()) && !isContainsUpperCase(password)) {
					return false;
				}
				if (isOn(pas.getSpCharReq()) && !isContainsSpecialCharacter(password)) {
					return false;
				}
				/*if(isOn(pas.getAvoidConsec()) && (hasConcecutiveAlphabets(password) || hasConcecutiveNumbers(password))) {
					return false;
				}*/
				/*if(isOn(pas.getAvoidSameChar()) && hasCharRepeated(password)) {
					return false;
				}*/

				/*
				 * if (isOn(pas.getThreeConsChars()) && !isThreeOrMoreCharacters(password)) {
				 * return false; } if (isOn(pas.getThreeConsDigitsLetters()) &&
				 * !isThreeConsLettersOrDisgits(password)) { return false; }
				 */

			}
		}
		return true;
	}

	private static boolean isOn(int value) {
		return (value == 1);
	}

	private static char getSpecialChara() {
		try {
			String CHAR_LIST = "!#$%&()*+@_{}";
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(CHAR_LIST.length());
			return CHAR_LIST.charAt(randomInt);
		} catch (Exception e) {
		}
		return '@';
	}

	public static boolean isAlphaNumeric(String input) {
		boolean gotnumber = false;
		boolean gotaplha = false;
		int length = input.length();
		for (int i = 0; i < length; i++) {
			char chr = input.charAt(i);
			if (!gotnumber && isInteger(String.valueOf(chr))) {
				gotnumber = true;
			}
			if (!gotaplha && isAlphabets(String.valueOf(chr))) {
				gotaplha = true;
			}
			if (gotaplha && gotnumber) {
				return true;
			}
		}
		return false;
	}

	public static boolean isInteger(String input) {
		try {
			String pattern = "-?[0-9]+";
			if (!isNull(input)) {
				if (Pattern.compile(pattern).matcher(input.trim()).matches()) {
					return true;
				}
			}
		} catch (Exception ex) {
			return false;
		}
		return false;
	}

	public static boolean isAlphabets(String input) {
		String pattern = "^[a-zA-Z]*$";
		if (input.matches(pattern)) {
			return true;
		}
		return false;
	}

	public static boolean isNull(Object obj) {
		return (obj == null || obj.toString().length() == 0 || obj.toString().equals("")
				|| obj.toString().trim().equals(""));
	}

	public static boolean isContainsUpperCase(String input) {
		return (input.matches(".*[A-Z].*"));
	}

	public static boolean isContainsSpecialCharacter(String input) {
		String special = "?=.*[@#$%^&+=!_]";
		String pattern = ".*[" + Pattern.quote(special) + "].*";
		return input.matches(pattern);
	}

	// for checking three or more than three same characters, for example, AAA.
	/*
	 * public static boolean isThreeOrMoreCharacters(String input) { String regex =
	 * "([a-zA-Z])\\1{2}"; return input.matches(regex); }
	 */

	/*
	 * //for Avoid using three or more than three consecutive letters or digits, for
	 * example, ABC or 123 public static boolean isThreeConsLettersOrDisgits(String
	 * input) { String regExp =
	 * "(abc|bcd|cde|def|efg|fgh|ghi|hij|ijk|jkl|klm|lmn|mno|nop|opq|pqr|qrs|rst|stu|tuv|uvw|vwx|wxy|xyz|012|123|234|345|456|567|678|789)";
	 * return input.matches(regExp); }
	 */

	/*
	 * public static boolean isConcecutiveNumbers(String input) {
	 * System.out.println(input); return (input.matches("/\\d{3}$/")); }
	 */
	
	public boolean hasConcecutiveAlphabets(String input) {
		String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
		input = input.toLowerCase();
//		System.out.println(input);
		for(int i=0; i<input.length(); i++) {
			for(int j=0; j<ALPHABETS.length(); j++) {
//				System.out.println("CHECKING - " + input.charAt(i) + ", " + SMALL.charAt(j));
				if(input.charAt(i) == ALPHABETS.charAt(j)) {
					int inc = 1;
					boolean flag = false;
					while(inc < systemProperties.getConsecutiveCharsLength()) {
						if((i + inc) < input.length() && (j + inc) < ALPHABETS.length()) {
//							System.out.println("-------------------------- " + input.charAt(i+inc) + ", " + SMALL.charAt(j+inc));
							if(input.charAt(i+inc) != ALPHABETS.charAt(j+inc)) {
								flag = false;
								break;
							}else
								flag = true;
						}else {
							flag = false;
							break;
						}
						inc++;
					}
					if(flag == true)
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasConcecutiveNumbers(String input) {
		String NUMS = "0123456789";
		input = input.toLowerCase();
		for(int i=0; i<input.length(); i++) {
			for(int j=0; j<NUMS.length(); j++) {
				System.out.println("Checking "+input.charAt(i) + ", " +NUMS.charAt(j));
				if(input.charAt(i) == NUMS.charAt(j)) {
					int inc = 1;
					boolean flag = false;
					while(inc < systemProperties.getConsecutiveCharsLength()) {
						if((i + inc) < input.length() && (j + inc) < NUMS.length()) {
							if(input.charAt(i+inc) != NUMS.charAt(j+inc)) {
								flag = false;
								break;
							}else
								flag = true;
						}else {
							flag = false;
							break;
						}
						inc++;
					}
					if(flag == true)
						return true;
				}
			}
		}
		return false;
	}
	
	public boolean hasCharRepeated(String input) {
		input = input.toLowerCase();
		for(int i=0; i <input.length(); i++) {
			int counter = 0;
			for(int j=0; j < input.length(); j++) {
				if(input.charAt(i) == input.charAt(j)) {
					counter++;
				}
				if(counter >= systemProperties.getSameCharLength())
					return true;
			}
		}
		return false;
	}

	/*
	 * public static void main(String[] args) { String inp = "AB2345DEF";
	 * System.out.println(); if (isConcecutiveAlphabets(inp)) {
	 * System.out.println(inp + " is Concec"); } else { System.out.println(inp +
	 * " is Not Concec"); } inp = "3425667"; if (isConcecutiveNumbers(inp)) {
	 * System.out.println(inp + " is Concec"); } else { System.out.println(inp +
	 * " is Not Concec"); } inp = "ABDB444"; if (isCharRepeated(inp)) {
	 * System.out.println(inp + " REPEATED"); } else { System.out.println(inp +
	 * " NOT REPEATED"); } }
	 */
	 
}




