package com.vanhack.dishes.service;

import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.CustomerLoginEvent;

public interface CustomerLoginEventService {

	void logoutAll(Customer customer);

	void logout(CustomerLoginEvent event);

	CustomerLoginEvent save(CustomerLoginEvent event);

}
