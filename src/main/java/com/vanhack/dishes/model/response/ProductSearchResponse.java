package com.vanhack.dishes.model.response;

import java.util.Collection;

import com.vanhack.dishes.model.Product;

public class ProductSearchResponse extends Response{

	private Long total;
	
	private Collection<Product> products;

	public Long getTotal() {
		return total;
	}

	public ProductSearchResponse withTotal(Long total) {
		this.total = total;
		return this;
	}

	public Collection<Product> getProducts() {
		return products;
	}

	public ProductSearchResponse withProducts(Collection<Product> products) {
		this.products = products;
		return this;
	}
}
