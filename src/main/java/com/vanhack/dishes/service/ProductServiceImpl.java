package com.vanhack.dishes.service;

import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanhack.dishes.builder.ProductBuilder;
import com.vanhack.dishes.exception.ProductIntegrityViolationException;
import com.vanhack.dishes.exception.ProductNameAlreadyExistsException;
import com.vanhack.dishes.model.Product;
import com.vanhack.dishes.model.request.ProductRequest;
import com.vanhack.dishes.model.response.ProductSearchResponse;
import com.vanhack.dishes.model.specification.ProductSpecification;
import com.vanhack.dishes.repository.ProductRepository;
import com.vanhack.dishes.repository.ProductRepositoryImpl;

@Service
public class ProductServiceImpl  implements ProductService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired ProductRepository productRepository;
	@Autowired ProductRepositoryImpl productRepositoryImpl;
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Product save(ProductRequest request) throws ProductNameAlreadyExistsException, ProductIntegrityViolationException {
		
		LOGGER.info("Begin method save for product {}", request);
		
		Product product = ProductBuilder.New(request).build();
		Product persistedProduct = productRepositoryImpl.saveProduct(product);
		
		LOGGER.info("Finish method save for product {}", persistedProduct);
		
		return persistedProduct;
	}

	@Override
	public ProductSearchResponse getProducts(ProductRequest request) {
		LOGGER.info("Begin method get product {}", request);
		
		Specifications<Product> specifications = where(ProductSpecification.withStoreId(request.getStoreId()))
				.and(ProductSpecification.withName(request.getName()))
				.and(ProductSpecification.withDescription(request.getDescription()))
				.and(ProductSpecification.withPrice(request.getPrice()));
				
		Long total = productRepository.count(specifications);
		
		List<Product> products = productRepository.findAll(specifications);
		
		LOGGER.info("Finish method get product {}", request);
		
		return new ProductSearchResponse()
				.withTotal(total)
				.withProducts(products);
			
	}
}
