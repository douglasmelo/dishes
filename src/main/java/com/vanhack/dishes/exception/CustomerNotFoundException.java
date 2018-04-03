package com.vanhack.dishes.exception;

public class CustomerNotFoundException extends Exception {
	
	private static final long serialVersionUID = -4025365575379964329L;

	public CustomerNotFoundException() {
		super();
	}

	public CustomerNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerNotFoundException(String message) {
		super(message);
	}

	public CustomerNotFoundException(Throwable cause) {
		super(cause);
	}

}
