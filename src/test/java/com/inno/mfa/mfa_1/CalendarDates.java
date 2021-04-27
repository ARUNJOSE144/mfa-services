package com.inno.mfa.mfa_1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CalendarDates {

	public static void main(String[] args) {

		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 100);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));

		Date monthFirstDay = calendar.getTime();
		System.out.println("Month First Date >>>>>>>>>>> : " + monthFirstDay);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println("Date Format >>>>>>>> : " + sdf.format(calendar.getTime()));

		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));

		Date monthLastDay = calendar.getTime();
		System.out.println("Month Last Date  >>>>>>>>>>> : " + monthLastDay);
		System.out.println("Date Format >>>>>>>> : " + sdf.format(calendar.getTime()));

	}
}
