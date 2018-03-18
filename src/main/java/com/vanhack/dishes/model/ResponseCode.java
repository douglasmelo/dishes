package com.vanhack.dishes.model;


public enum ResponseCode {
	
	INTERNAL_ERROR("internal.error", Exception.class);

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
