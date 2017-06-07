package com.movbooking.util;

import java.util.Calendar;
public class DateStringToCalendar {
	
	//dateString:2016-05-20
	public static Calendar dateStringToCalendar(String dateString) {
		String[] comp = dateString.trim().split("-");
		if (comp.length != 3) {
			return null;
		}
		int year = Integer.parseInt(comp[0]);
		int month = Integer.parseInt(comp[1])-1;
		int day = Integer.parseInt(comp[2]);
		
		Calendar cale = Calendar.getInstance();
		cale.set(year, month, day, 0, 0, 0);
		cale.set(Calendar.MILLISECOND, 0);
		return cale;
	}
	
	//timeString:2016-05-26 10:25
	public static Calendar timeStringToCalendar(String timeString) {
		String[] comp = timeString.trim().split(" ");
		if (comp.length != 2) {
			return null;
		}
		Calendar cale = dateStringToCalendar(comp[0]);
		Integer hours = Integer.parseInt(comp[1].split(":")[0]);
		Integer min = Integer.parseInt(comp[1].split(":")[1]);
		cale.set(Calendar.HOUR_OF_DAY, hours);
		cale.set(Calendar.MINUTE, min);
		return cale;
	}
}
