package com.vanhack.dishes.utils;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import com.vanhack.dishes.model.ResponseCode;
import com.vanhack.dishes.model.ResponseError;

@Component
public class ResponseErrorHelper {

	private MessageSource messageSource;

	@Autowired
	public ResponseErrorHelper(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public ResponseError create(ResponseCode responseCode, Locale locale, Object[] args) {
		String message = getMessage(responseCode.getKey(), locale, args);
		return new ResponseError(responseCode, message);
	}

	public ResponseError create(String messageKey, Locale locale, Object[] args) {
		String message = "";
		if (StringUtils.isNoneEmpty(messageKey)) {
			message = getMessage(messageKey, locale, args);
		}
		return new ResponseError(ResponseCode.INTERNAL_ERROR, message);
	}

	public String getMessage(String messageKey, Locale locale, Object[] args) {
		String message = messageSource.getMessage(messageKey, args, locale);
		if (messageKey.equals(message)) {
			message = messageSource.getMessage(messageKey, args, Locales.getDefault());
		}
		return message;
	}
}
