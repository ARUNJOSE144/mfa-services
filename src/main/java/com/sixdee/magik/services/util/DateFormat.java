package com.sixdee.magik.services.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ramesh.cheerla
 * @Date : September, 2018
 *
 */

public class DateFormat {

	// only date in string
	public String strDate(String date) {
		String[] strDate = date.split(" ");
		return strDate[0];
	}

	// Date and Time
	public String strDateTime(String date) {
		return date.replace(".0", "");
	}

	public Date strToDate(String strDate) {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		Date date = null;

		try {
			date = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		System.out.println("==========================   " + date);

		return date;
	}
	
	public String taskProfileDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = dateFormat.format(date);
		System.out.println("Converted String: " + strDate);
		
		return strDate;
	}

	/*public static void main(String args[]) {
		Date date = Calendar.getInstance().getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-M-dd");
		String strDate = dateFormat.format(date);
		System.out.println("Converted String: " + strDate);

	}*/

}
