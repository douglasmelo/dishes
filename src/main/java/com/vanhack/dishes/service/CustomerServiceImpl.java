package com.vanhack.dishes.service;


import java.time.LocalDateTime;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanhack.dishes.builder.CustomerBuilder;
import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.CustomerLoginEvent;
import com.vanhack.dishes.model.request.CustomerRequest;
import com.vanhack.dishes.repository.CustomerRepository;
import com.vanhack.dishes.security.TokenAuthenticationService;
import com.vanhack.dishes.utils.Passwords;

@Service
public class CustomerServiceImpl implements CustomerService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	@Autowired Environment env;
	@Autowired CustomerRepository customerRepository;
	@Autowired CustomerLoginEventService customerLoginEventService;
	@Autowired TokenAuthenticationService tokenAuthenticationService;
	
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
			LocalDateTime validAt = LocalDateTime.now();
			validAt = validAt.plusDays(TokenAuthenticationService.EXPIRATION_TIME_IN_DAYS);
			String jwt = tokenAuthenticationService.getTokenAuthentication(customer.getEmail(), validAt);
			token = TokenAuthenticationService.TOKEN_PREFIX + StringUtils.SPACE + jwt;
			customerLoginEventService.logoutAll(customer);
			
			CustomerLoginEvent event = new CustomerLoginEvent(customer, token, validAt);
			customerLoginEventService.save(event);
		}else {
			throw new Exception("E-mail or password incorrect");
		}
		
		LOGGER.info("Finish method auth for customer with email: {}", request.getEmail());
		return token;
	}
}
