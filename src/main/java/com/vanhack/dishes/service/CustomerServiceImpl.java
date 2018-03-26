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
import com.vanhack.dishes.utils.Passwords;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired CustomerRepository customerRepository;
	@Autowired CustomerLoginEventService customerLoginEventService;
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public Customer save(CustomerRequest request) {
		LOGGER.info("Begin method save for customer with email {}", request.getEmail());
		
		Customer customer = CustomerBuilder.New(request).build();
		Customer persistedCustomer = customerRepository.save(customer);
		
		LOGGER.info("Finish method save for customer with email: {}", persistedCustomer.getEmail());
		return persistedCustomer;
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public String auth(CustomerRequest request) throws Exception {
		LOGGER.info("Begin method auth for customer with email {}", request.getEmail());
		
		Customer customer = customerRepository.findByEmail(request.getEmail());
		
		String token = null;
		if(Passwords.matches(request.getPassword(), customer.getPassword())) {
			StringBuffer sb = new StringBuffer();
			sb.append(customer.getEmail());
			sb.append(customer.getPassword());
			token = Base64.getEncoder().encodeToString(sb.toString().getBytes());
			
			customerLoginEventService.logoutAll(customer);
			
			CustomerLoginEvent event = new CustomerLoginEvent(customer, token);
			customerLoginEventService.save(event);
		}else {
			throw new Exception("E-mail or password incorrect");
		}
		
		LOGGER.info("Finish method auth for customer with email: {}", request.getEmail());
		return token;
	}
}
