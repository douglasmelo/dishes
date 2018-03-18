package com.vanhack.dishes.model.request;

import java.io.Serializable;

import com.vanhack.dishes.utils.ToStringBuilder;

public class Request implements Serializable {

	private static final long serialVersionUID = -561371366882335724L;

	@Override
	public String toString() {
		return ToStringBuilder.toString(this);
	}
}
