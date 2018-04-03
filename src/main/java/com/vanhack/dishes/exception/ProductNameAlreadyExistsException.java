package com.vanhack.dishes.exception;

public class ProductNameAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -7836945579221008172L;

	public ProductNameAlreadyExistsException() {
		super();
	}

	public ProductNameAlreadyExistsException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductNameAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductNameAlreadyExistsException(String message) {
		super(message);
	}

	public ProductNameAlreadyExistsException(Throwable cause) {
		super(cause);
	}
}
