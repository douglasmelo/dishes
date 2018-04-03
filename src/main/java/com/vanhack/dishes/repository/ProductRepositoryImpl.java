package com.vanhack.dishes.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import com.vanhack.dishes.exception.ProductIntegrityViolationException;
import com.vanhack.dishes.exception.ProductNameAlreadyExistsException;
import com.vanhack.dishes.model.Product;

@Component
public class ProductRepositoryImpl {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductRepositoryImpl.class);

	@Autowired ProductRepository productRepository;
	
	public Product saveProduct(Product product) throws ProductNameAlreadyExistsException, ProductIntegrityViolationException {
		try {
			LOGGER.debug("Begin persist product {}", product);
			Product persistedProduct = this.productRepository.save(product);
			LOGGER.debug("Finish persist product {}", product);
			return persistedProduct;
		} catch(DataIntegrityViolationException e) {
			if(e.getMessage().contains("UK_product_name")) {
				throw new ProductNameAlreadyExistsException();
			}
			throw new ProductIntegrityViolationException();
		}
	}
}
