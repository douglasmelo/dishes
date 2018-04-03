package com.vanhack.dishes.model;

import com.vanhack.dishes.exception.CustomerNotFoundException;
import com.vanhack.dishes.exception.OrderNotFoundException;
import com.vanhack.dishes.exception.ProductNameAlreadyExistsException;
import com.vanhack.dishes.exception.ProductNotFoundException;

public enum ResponseCode {
	
	INTERNAL_ERROR("internal.error", Exception.class),
	PRODUCT_NAME_ALREADY_EXIST("product.name.already.exists", ProductNameAlreadyExistsException.class),
	ORDER_NOT_FOUND("order.not.found", OrderNotFoundException.class),
	PRODUCT_NOT_FOUND("product.not.found", ProductNotFoundException.class),
	CUSTOMER_NOT_FOUND("customer.not.found", CustomerNotFoundException.class);

	private String key;
	private Class<? extends Exception> exception;

	private ResponseCode(String key, Class<? extends Exception> exception) {
		this.key = key;
		this.exception = exception;
	}
	
	public String getKey() {
		return key;
	}

	public Class<? extends Exception> getException() {
		return exception;
	}

	public static ResponseCode getByKey(String key) {
		for (ResponseCode responseCode : ResponseCode.values()) {
			if (responseCode.getKey().equals(key)) {
				return responseCode;
			}
		}
		
		return ResponseCode.INTERNAL_ERROR;
	}
}
