package com.vanhack.dishes.model.response;

import java.util.Collection;

import com.vanhack.dishes.model.ResponseDetail;

public class ProductListResponse extends ResponseDetail {
	
	private Long total;
	private Collection<ProductResponse> products;

	public ProductListResponse(Long total, Collection<ProductResponse> products) {
		this.total = total;
		this.products = products;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Collection<ProductResponse> getProducts() {
		return products;
	}

	public void setProducts(Collection<ProductResponse> products) {
		this.products = products;
	}

}
