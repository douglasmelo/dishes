package com.vanhack.dishes.builder;

import com.vanhack.dishes.model.Product;
import com.vanhack.dishes.model.request.ProductRequest;

public class ProductBuilder {
  
	private ProductRequest productRequest;
	
	private ProductBuilder(ProductRequest productRequest) {
		this.productRequest = productRequest;
	}
	
	public static ProductBuilder New(ProductRequest productRequest) {
		return new ProductBuilder(productRequest);
	}
	
	public Product build() {
		Product product = new Product();
		
		product.setDescription(productRequest.getDescription());
		product.setName(productRequest.getName());
		product.setPrice(productRequest.getPrice());
		product.setStoreId(productRequest.getStoreId());
		
		return product;
	}
	
}
