package com.vanhack.dishes.model.response;

import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseStatus;

public class CustomerResponse extends ResponseDetail {

	private String token;
	
	public CustomerResponse() {
		super();
	}
	
	public CustomerResponse(ResponseStatus responseStatus, String token) {
		super(responseStatus);
		this.token = token;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
