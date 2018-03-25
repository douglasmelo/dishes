package com.vanhack.dishes.model.response;

import java.math.BigDecimal;

import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseStatus;

public class ProductResponse extends ResponseDetail {
	
	private String productId;
	
	private Long storeId;
	
	private String name;
	
	private String description;
	
	private BigDecimal price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

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

	public ProductResponse() {
		super();
	}
	
	public ProductResponse(ResponseStatus responseStatus) {
		super(responseStatus);
	}
}
