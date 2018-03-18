package com.vanhack.dishes.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.CustomerLoginEvent;

public interface CustomerLoginEventRepository extends CrudRepository<CustomerLoginEvent, Long> {

	Collection<CustomerLoginEvent> findByCustomerAndLoggedTrue(Customer customer);
}
