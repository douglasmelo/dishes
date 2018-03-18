package com.vanhack.dishes.utils;

import java.util.Locale;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

public class Locales {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(Locales.class);

	private static final String UNDERSCORE = "_";

	public static Locale getLocale(String language) {
		try {
			if(Objects.nonNull(language) && language.contains(UNDERSCORE)) {
				String[] splitedLanguage = language.split(UNDERSCORE);
				return new Locale(splitedLanguage[0], splitedLanguage[1]);
			}
			return brazil();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return LocaleContextHolder.getLocale();
		}
	}
	
	public static Locale getDefault() {
		return new Locale("pt", "BR");
	}

	public static Locale brazil() {
		return getDefault();
	}
}