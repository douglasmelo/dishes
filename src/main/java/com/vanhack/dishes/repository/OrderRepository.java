package com.vanhack.dishes.repository;

import org.springframework.data.repository.CrudRepository;

import com.vanhack.dishes.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

	Order findByUuid(String uuid);
}
