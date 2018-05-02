package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class FormUtil {
	public static String getArozonaCurrentDateAndTime() {
		Date currentDate = Calendar.getInstance().getTime();

		SimpleDateFormat dateAndTimeFomater = new SimpleDateFormat("MM-dd-yyyy hh:mm aaa");
		dateAndTimeFomater.setTimeZone(TimeZone.getTimeZone("US/Arizona"));
		return dateAndTimeFomater.format(currentDate);
	}

}
