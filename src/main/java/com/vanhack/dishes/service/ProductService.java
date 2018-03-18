package com.vanhack.dishes.service;

import com.vanhack.dishes.model.Product;
import com.vanhack.dishes.model.request.ProductRequest;
import com.vanhack.dishes.model.response.ProductSearchResponse;

public interface ProductService {

	Product save(ProductRequest request);

	ProductSearchResponse getProducts(ProductRequest request);

}
