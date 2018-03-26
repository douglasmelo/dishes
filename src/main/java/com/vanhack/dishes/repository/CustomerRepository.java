package com.vanhack.dishes.repository;

import org.springframework.data.repository.CrudRepository;

import com.vanhack.dishes.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

	Customer findByUuid(String uuid);
	Customer findByEmail(String email);
}
