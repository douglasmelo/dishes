package com.vanhack.dishes.builder;

import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.request.CustomerRequest;

public class CustomerBuilder {
  
	private CustomerRequest customerRequest;
	
	private CustomerBuilder(CustomerRequest customerRequest) {
		this.customerRequest = customerRequest;
	}
	
	public static CustomerBuilder New(CustomerRequest customerRequest) {
		return new CustomerBuilder(customerRequest);
	}
	
	public Customer build() {
		Customer customer = new Customer();
		
		customer.setAddress(customerRequest.getAddress());
		customer.setEmail(customerRequest.getEmail());
		customer.setName(customerRequest.getName());
		customer.setPassword(customerRequest.getPassword());
		
		return customer;
	}
	
}
