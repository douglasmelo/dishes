package com.vanhack.dishes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.vanhack.dishes.utils.Locales;

@Configuration
public class I18nConfiguration {
	
	private static final String ENCODING = "UTF-8";
	private static final String BASE_PATH = "i18n/messages";

	@Bean
	public LocaleResolver localeResolver() {
		SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locales.getDefault());
		return sessionLocaleResolver;
	}

	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource source = new ResourceBundleMessageSource();
		source.setBasenames(BASE_PATH); 
		source.setUseCodeAsDefaultMessage(true);
		source.setDefaultEncoding(ENCODING);
		return source;
	}
}
