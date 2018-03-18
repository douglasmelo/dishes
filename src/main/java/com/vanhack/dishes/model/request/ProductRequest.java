package com.vanhack.dishes.model.request;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vanhack.dishes.model.Product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ProductRequest extends Product {

	private static final long serialVersionUID = -6766810823845297076L;
	
	@NotNull(message = "invalid.product.storeId", groups = {Save.class})
	@ApiModelProperty(required = true, example = "STO-be8fc1cf-c725-4d4f-ab94-5943a6e763da", position = 1)
	@JsonProperty
	private Long storeId;
	
	@NotNull(message = "invalid.product.name", groups = {Save.class})
	@ApiModelProperty(required = true, example = "water", position = 2)
	@JsonProperty
	private String name;
	
	@NotNull(message = "invalid.product.description", groups = {Save.class})
	@ApiModelProperty(required = true, example = "Mineral walter", position = 3)
	@JsonProperty
	private String description;
	
	@NotNull(message = "invalid.product.price", groups = {Save.class})
	@ApiModelProperty(required = true, example = "2.49", position = 4)
	@JsonProperty
	private BigDecimal price;
	
	public Long getStoreId() {
		return storeId;
	}

	public void setStoreId(Long storeId) {
		this.storeId = storeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public interface Save { }

}
