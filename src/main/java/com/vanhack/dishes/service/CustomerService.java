package com.vanhack.dishes.service;

import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.request.CustomerRequest;

public interface CustomerService {

	Customer save(CustomerRequest request);

	String auth(CustomerRequest request) throws Exception;

}
