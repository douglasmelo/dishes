package com.vanhack.dishes.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrderItemRequest extends Request {

	private static final long serialVersionUID = 3804917520260451148L;
	
	@NotNull(message = "invalid.orderItem.product.id", groups = {Save.class})
	@ApiModelProperty(required = false, position = 1)
	@JsonProperty
	private String productId;

	@NotNull(message = "invalid.orderItem.price", groups = {Save.class})
	@ApiModelProperty(required = true, example = "10.48", position = 2)
	@JsonProperty
	private BigDecimal price;
	
	@NotNull(message = "invalid.orderItem.quantity", groups = {Save.class})
	@ApiModelProperty(required = true, example = "2", position = 3)
	@JsonProperty
	private Integer quantity;
	
	@NotNull(message = "invalid.orderItem.total", groups = {Save.class})
	@ApiModelProperty(required = true, example = "2", position = 4)
	@JsonProperty
	private BigDecimal total;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public interface Save { }
}
