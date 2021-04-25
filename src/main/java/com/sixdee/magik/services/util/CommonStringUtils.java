package com.sixdee.magik.services.util;

public class CommonStringUtils {
	
	
	public static Boolean roleMaster_COnstraintException = false;
	
	 public static String afterStringUtils(String value, String a) {
	        // Returns a substring containing all characters after a string.
	        int posA = value.lastIndexOf(a);
	        if (posA == -1) {
	            return "";
	        }
	        int adjustedPosA = posA + a.length();
	        if (adjustedPosA >= value.length()) {
	            return "";
	        }
	        return value.substring(adjustedPosA);
	    }
	 
	 
	 public static String beforeStringUtils(String value, String a) {
	        // Return substring containing all characters before a string.
	        int posA = value.indexOf(a);
	        if (posA == -1) {
	            return "";
	        }
	        return value.substring(0, posA);
	    }	 
	

}
