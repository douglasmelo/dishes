package com.vanhack.dishes.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
//	
//	@ExceptionHandler(value = InvalidRequestParameterException.class)
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleException(InvalidRequestParameterException exception, HttpServletRequest request) {
//		LOGGER.error(exception.getMessage(), exception);
//		Collection<ResponseError> errors = new HashSet<>();
//		BindingResult result = exception.getBindingResult();
//		for (ObjectError error : result.getAllErrors()) {
//			ResponseCode responseCode = ResponseCode.getByKey(error.getCode());
//			Locale locale = getLocale(request);
//			ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
//			errors.add(responseError);
//		}
//		
//		Collection<ResponseError> sortedErrors = errors.stream()
//				.sorted((e1, e2) -> e1.getError().name().compareTo(e2.getError().name()))
//				.collect(Collectors.toList());
//		
//		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, sortedErrors);
//		LOGGER.info("Response: {}", response);
//		return new ResponseEntity<ResponseDetail>(response, HttpStatus.BAD_REQUEST);
//	}
//	
//	@ExceptionHandler(value = ConstraintViolationException.class)
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleException(ConstraintViolationException exception, HttpServletRequest request) {
//		LOGGER.error(exception.getMessage());
//		Collection<ResponseError> errors = new HashSet<>();
//		for (ConstraintViolation<?> error : exception.getConstraintViolations()) {
//			ResponseCode responseCode = ResponseCode.getByKey(error.getMessage());
//			Locale locale = getLocale(request);
//			ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
//			errors.add(responseError);
//		}
//		
//		Collection<ResponseError> sortedErrors = errors.stream()
//				.sorted((e1, e2) -> e1.getError().name().compareTo(e2.getError().name()))
//				.collect(Collectors.toList());
//		
//		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, sortedErrors);
//		LOGGER.info("Response: {}", response);
//		return new ResponseEntity<ResponseDetail>(response, HttpStatus.BAD_REQUEST);
//	}
//	
//	@ExceptionHandler(value = MethodArgumentNotValidException.class)
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleException(MethodArgumentNotValidException exception, HttpServletRequest request) {
//		LOGGER.error(exception.getMessage(), exception);
//		Collection<ResponseError> errors = new HashSet<>();
//		for (ObjectError error : exception.getBindingResult().getAllErrors()) {
//			ResponseCode responseCode = ResponseCode.getByKey(error.getDefaultMessage());
//			Locale locale = getLocale(request);
//			ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
//			errors.add(responseError);
//		}
//		
//		Collection<ResponseError> sortedErrors = errors.stream()
//				.sorted((e1, e2) -> e1.getError().name().compareTo(e2.getError().name()))
//				.collect(Collectors.toList());
//		
//		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, sortedErrors);
//		LOGGER.info("Response: {}", response);
//		return new ResponseEntity<ResponseDetail>(response, HttpStatus.BAD_REQUEST);
//	}
//	
//	@ExceptionHandler(value = MissingServletRequestParameterException.class)
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleException(MissingServletRequestParameterException exception, HttpServletRequest request) {
//		LOGGER.error(exception.getMessage(), exception);
//		
//		ResponseCode responseCode = ResponseCode.getByKey(INVALID + exception.getParameterName());
//		Locale locale = getLocale(request);
//		ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
//		
//		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, responseError);
//		LOGGER.info("Response: {}", response);
//		return new ResponseEntity<ResponseDetail>(response, HttpStatus.BAD_REQUEST);
//	}
//	
//	public ResponseEntity<ResponseDetail> handleErros(BindingResult result, Locale locale) {
//		Collection<ObjectError> allErrors = result.getAllErrors();
//		Collection<ResponseError> errors = new HashSet<>();
//		for (ObjectError error : allErrors) {
//			String key = error.getDefaultMessage();
//			ResponseCode responseCode = ResponseCode.getByKey(key);
//			ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
//			errors.add(responseError );
//		}
//		
//		Collection<ResponseError> sortedErrors = errors.stream()
//				.sorted((e1, e2) -> e1.getError().name().compareTo(e2.getError().name()))
//				.collect(Collectors.toList());
//		
//		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, sortedErrors);
//		LOGGER.info("Response handleErros: {}", response);
//		return new ResponseEntity<ResponseDetail>(response, HttpStatus.BAD_REQUEST);
//	}
//	
	public ResponseEntity<ResponseDetail> internalError(Exception exception, Locale locale) {
		LOGGER.error(exception.getMessage(), exception);
		ResponseError responseError = responseErrorHelper.create(ResponseCode.INTERNAL_ERROR, locale, null);
		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, responseError);
		LOGGER.info("Response defaultError: {}", response);
		return new ResponseEntity<ResponseDetail>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}
//
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleError(ResponseCode responseCode, String method, Locale locale) {
//		return handleError(responseCode, method, HttpStatus.BAD_REQUEST, locale);
//	}
//	
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleError(Exception exception, ResponseCode responseCode, String method, Locale locale) {
//		return handleError(exception, responseCode, method, HttpStatus.BAD_REQUEST, locale);
//	}
//
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleError(Exception exception, ResponseCode responseCode, String method, HttpStatus httpStatus, Locale locale) {
//		LOGGER.error(exception.getMessage());
//		return handleError(responseCode, method, httpStatus, locale);
//	}
//	
//	public BindingResult getBindingResult(Class<?> clazz) {
//		DataBinder binder = new DataBinder(clazz);
//		BindingResult result = binder.getBindingResult();
//		return result;
//	}
//
//	private ResponseEntity<ResponseDetail> handleError(ResponseCode responseCode, String method, HttpStatus httpStatus, Locale locale) {
//		ResponseError responseError = responseErrorHelper.create(responseCode, locale, null);
//		ResponseDetail response = new ResponseDetail(ResponseStatus.ERROR, responseError);
//		LOGGER.info("{} Response: {}", method, response);
//		return new ResponseEntity<ResponseDetail>(response, httpStatus);
//	}
//
//	@ExceptionHandler(value = Exception.class)
//	@ResponseBody
//	public ResponseEntity<ResponseDetail> handleException(Exception exception, Locale locale) {
//		return internalError(exception, locale);
//	}
//
//	private Locale getLocale(HttpServletRequest request) {
//		String language = request.getHeader(ACCEPT_LANGUAGE);
//		return Locales.getLocale(language);
//	}
}
