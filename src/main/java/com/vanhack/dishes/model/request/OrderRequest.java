package com.vanhack.dishes.model.request;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderRequest extends Request {

	private static final long serialVersionUID = -2393928978333703266L;
	
	@NotNull(message = "invalid.order.customerUuid", groups = {Save.class})
	@ApiModelProperty(required = true, example = "CUS-be8fc1cf-c725-4d4f-ab94-5943a6e763da", position = 1)
	@JsonProperty
	private String customerId;
	 
	@NotNull(message = "invalid.order.deliveryAddress", groups = {Save.class})
	@ApiModelProperty(required = true, example = "2991 Pembina Hwy, Winnipeg, Manitoba R3T 2H5, Canada", position = 2)
	@JsonProperty
	private String deliveryAddress;
	
	@NotNull(message = "invalid.order.contact", groups = {Save.class})
	@ApiModelProperty(required = true, example = "Doug", position = 3)
	@JsonProperty
	private String contact;
	
	@NotNull(message = "invalid.order.storeId", groups = {Save.class})
	@ApiModelProperty(required = true, example = "1", position = 4)
	@JsonProperty
	private Long storeId;
	
	@ApiModelProperty(required = false, position = 5)
	@Valid
	@JsonProperty
	private List<OrderItemRequest> orderItems;
	
	@NotNull(message = "invalid.order.total", groups = {Save.class})
	@DecimalMin(value = "0.01", message = "invalid.order.total")
	@ApiModelProperty(required = false, example= "1.43", position = 6)
	@JsonProperty
	private BigDecimal total;
	
	public OrderRequest() {
		
	}
	
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public List<OrderItemRequest> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemRequest> orderItems) {
		this.orderItems = orderItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public interface Save{ }
	
}
