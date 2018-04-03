package com.vanhack.dishes.repository;

import org.springframework.data.repository.CrudRepository;

import com.vanhack.dishes.model.CustomerLoginEvent;

public interface CustomerLoginEventRepository extends CrudRepository<CustomerLoginEvent, Long> {

	CustomerLoginEvent findByCustomerEmailAndLoggedTrue(String email);
	
}
