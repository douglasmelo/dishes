package com.vanhack.dishes.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vanhack.dishes.model.ResponseCode;
import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseError;
import com.vanhack.dishes.model.ResponseStatus;
import com.vanhack.dishes.utils.ResponseErrorHelper;

@Component
public class BaseRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(BaseRestController.class);
	
	public static final String APPLICATION_JSON = MediaType.APPLICATION_JSON_UTF8_VALUE;
	
	@Autowired private ResponseErrorHelper responseErrorHelper;

	public ResponseEntity<ResponseDetail> internalError(Exception exception, Locale locale) {
		LOGGER.error(exception.getMessage(), exception);
		ResponseError responseError = responseErrorHelper.create(ResponseCode.INTERNAL_ERROR, locale, null);
		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, responseError);
		LOGGER.info("Response defaultError: {}", response);
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ResponseBody
	public ResponseEntity<ResponseDetail> handleError(Exception exception, ResponseCode responseCode, String method, HttpStatus httpStatus, Locale locale) {
		LOGGER.error(exception.getMessage());
		return handleError(responseCode, method, httpStatus, locale);
	}
	
	private ResponseEntity<ResponseDetail> handleError(ResponseCode responseCode, String method, HttpStatus httpStatus, Locale locale) {
		ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, responseError);
		LOGGER.info("{} Response: {}", method, response);
		return new ResponseEntity<ResponseDetail>(response, httpStatus);
	}
	
}
