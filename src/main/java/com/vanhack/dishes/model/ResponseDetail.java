package com.vanhack.dishes.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanhack.dishes.utils.ToStringBuilder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
@JsonInclude(Include.NON_NULL)
public class ResponseDetail {

	@ApiModelProperty(required = true, position = 0)
	@JsonProperty("status")
	private ResponseStatus responseStatus;

	@ApiModelProperty(position = 1)
	@JsonProperty("errors")
	private Collection<ResponseError> errors;

	public ResponseDetail() {
		this.loadStatusError();
	}

	public ResponseDetail(ResponseStatus responseStatus) {
		this.responseStatus = responseStatus;
	}

	public ResponseDetail(ResponseStatus responseStatus, Collection<ResponseError> errors) {
		setErrors(errors);
		this.loadStatusError();
	}

	public ResponseDetail(ResponseStatus responseStatus, ResponseError error) {
		Collection<ResponseError> errors = new ArrayList<>(1);
		errors.add(error);
		setErrors(errors);
		this.loadStatusError();
	}

	public ResponseStatus getResponseStatus() {
		return responseStatus;
	}

	public Collection<ResponseError> getErrors() {
		return errors;
	}

	public void setErrors(Collection<ResponseError> errors) {
		this.errors = errors;
	}

	public void loadStatusError() {

		this.responseStatus = ResponseStatus.SUCCESS;

		if(Objects.nonNull(errors)) {
			this.responseStatus = ResponseStatus.ERROR;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((errors == null) ? 0 : errors.hashCode());
		result = prime * result + ((responseStatus == null) ? 0 : responseStatus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ResponseDetail other = (ResponseDetail) obj;
		if (errors == null) {
			if (other.errors != null) {
				return false;
			}
		} else if (!errors.equals(other.errors)) {
			return false;
		}
		if (responseStatus != other.responseStatus) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return ToStringBuilder.toString(this);
	}
}
