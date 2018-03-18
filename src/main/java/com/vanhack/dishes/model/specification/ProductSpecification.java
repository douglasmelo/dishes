package com.vanhack.dishes.model.specification;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.vanhack.dishes.model.Product;

public class ProductSpecification {

	public static Specification<Product> withStoreId(Long storeId) {
		if (Objects.isNull(storeId)) {
			return null;
		}
		return (root, query, cb) -> cb.equal(root.get(Product.STORE_ID), storeId);
	}

	public static Specification<Product> withName(String name) {
		if (Objects.isNull(name)) {
			return null;
		}
		return (root, query, cb) -> cb.equal(root.get(Product.NAME), name);
	}

	public static Specification<Product> withDescription(String description) {
		if (Objects.isNull(description)) {
			return null;
		}
		return (root, query, cb) -> cb.equal(root.get(Product.DESCRIPTION), description);
	}

	public static Specification<Product> withPrice(BigDecimal price) {
		if (Objects.isNull(price)) {
			return null;
		}
		return (root, query, cb) -> cb.equal(root.get(Product.PRICE), price);
	}
}
