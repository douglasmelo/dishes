package com.vanhack.dishes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Dates {

//	private static final String INITIAL_TIMESTAMP = "T00:00:00";
//	private static final String FINAL_TIMESTAMP = "T23:59:59";
//	private static final int FIRST_DAY_OF_MONTH = 1;

	public static String getFormattedDate(LocalDateTime localDateTime, String pattern) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		return localDateTime.format(formatter);
	}
	
}
