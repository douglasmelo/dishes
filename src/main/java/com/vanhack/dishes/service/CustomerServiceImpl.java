package com.vanhack.dishes.service;


import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanhack.dishes.builder.CustomerBuilder;
import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.CustomerLoginEvent;
import com.vanhack.dishes.model.request.CustomerRequest;
import com.vanhack.dishes.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired CustomerRepository customerRepository;
	@Autowired CustomerLoginEventService customerLoginEventService;
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String save(CustomerRequest request) {
		LOGGER.info("Begin method save for customer with email {}", request.getEmail());
		
		Customer customer = CustomerBuilder.New(request).build();
		Customer persistedCustomer = customerRepository.save(customer);
		
		StringBuffer sb = new StringBuffer();
		sb.append(customer.getEmail());
		sb.append(customer.getPassword());
		String token = Base64.getEncoder().encodeToString(sb.toString().getBytes());
		
		customerLoginEventService.logoutAll(persistedCustomer);
		
		CustomerLoginEvent event = new CustomerLoginEvent(persistedCustomer, token);
		customerLoginEventService.save(event);
		
		LOGGER.info("Finish method save for customer with email: {}", persistedCustomer.getEmail());
		
		return token;
	}
}
