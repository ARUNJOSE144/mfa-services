package com.inno.mfa.services.util;

public class Util {

	public static boolean validate(String val) {
		if (val != null && !val.equalsIgnoreCase("undefined") && !val.equalsIgnoreCase(""))
			return true;
		else
			return false;
	}

}
