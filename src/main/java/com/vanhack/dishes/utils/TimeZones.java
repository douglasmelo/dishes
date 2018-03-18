package com.vanhack.dishes.utils;

import java.time.ZoneId;
import java.util.TimeZone;

public class TimeZones {

	private static final String AMERICA_SAO_PAULO = "America/Sao_Paulo";

	public static void setDefault() {
		TimeZone.setDefault(TimeZone.getTimeZone(AMERICA_SAO_PAULO));
	}
	
	public static ZoneId getDefault() {
		return ZoneId.of(AMERICA_SAO_PAULO);
	}
}
