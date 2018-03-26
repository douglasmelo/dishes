package com.vanhack.dishes.model.response;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanhack.dishes.model.Order;
import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderInquireResponse extends ResponseDetail {
	
	@ApiModelProperty(position = 0)
	@JsonProperty
	private String uuid;
	
	@ApiModelProperty(position = 1)
	@JsonProperty
	private String status;
	
	@ApiModelProperty(position = 2)
	@JsonProperty
	private String deliveryAddress;

	@ApiModelProperty(position = 3)
	@JsonProperty
	private String contact;
	
	@ApiModelProperty(position = 4)
	@JsonProperty
	private BigDecimal total;

	public OrderInquireResponse(ResponseStatus responseStatus, Order order) {
		super(responseStatus);
		this.uuid = order.getUuid();
		this.status = order.getStatus().name();
		this.deliveryAddress = order.getDeliveryAddress();
		this.contact = order.getContact();
		this.total = order.getTotal();
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	
}
