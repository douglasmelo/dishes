package com.vanhack.dishes.exception;

public class ProductIntegrityViolationException extends Exception {

	private static final long serialVersionUID = -2176463631959789682L;

	public ProductIntegrityViolationException() {
		super();
	}

	public ProductIntegrityViolationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ProductIntegrityViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	public ProductIntegrityViolationException(String message) {
		super(message);
	}

	public ProductIntegrityViolationException(Throwable cause) {
		super(cause);
	}
	
}
