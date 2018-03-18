package com.vanhack.dishes.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanhack.dishes.utils.ToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ResponseError {

	@ApiModelProperty(position = 0)
	@JsonProperty("error")
	private ResponseCode error;
	
	@ApiModelProperty(position = 1)
	@JsonProperty("message")
	private String message;
	
	public ResponseError(ResponseCode error, String message) {
		this.error = error;
		this.message = message;
	}

	public ResponseCode getError() {
		return error;
	}

	public void setError(ResponseCode error) {
		this.error = error;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((error == null) ? 0 : error.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseError other = (ResponseError) obj;
		if (error != other.error)
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.toString(this);
	}
}