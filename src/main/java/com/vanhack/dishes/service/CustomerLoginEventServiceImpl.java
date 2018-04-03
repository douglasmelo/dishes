package com.vanhack.dishes.service;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.CustomerLoginEvent;
import com.vanhack.dishes.repository.CustomerLoginEventRepository;

@Service
public class CustomerLoginEventServiceImpl implements CustomerLoginEventService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerLoginEventServiceImpl.class);
	
	@Autowired CustomerLoginEventRepository customerLoginEventRepository;

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void logoutAll(Customer customer) {
		CustomerLoginEvent customerLoginEvent = customerLoginEventRepository.findByCustomerEmailAndLoggedTrue(customer.getEmail());
		logout(customerLoginEvent);
	}
	
	@Override
	@Transactional(rollbackFor = Throwable.class)
	public void logout(CustomerLoginEvent event) {
		LOGGER.info("Customer to log out by customer: {}", event.getId());
		event.setLogged(Boolean.FALSE);
		event.setLogOutDate(LocalDateTime.now());
		customerLoginEventRepository.save(event);
	}

	@Override
	@Transactional(rollbackFor = Throwable.class)
	public CustomerLoginEvent save(CustomerLoginEvent event) {
		return customerLoginEventRepository.save(event);
	}
}
