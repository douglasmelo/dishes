package com.vanhack.dishes.service;

import com.vanhack.dishes.exception.CustomerNotFoundException;
import com.vanhack.dishes.exception.OrderNotFoundException;
import com.vanhack.dishes.model.Order;
import com.vanhack.dishes.model.request.OrderRequest;

public interface OrderService {

	void save(OrderRequest request) throws CustomerNotFoundException;

	void cancel(String orderId) throws OrderNotFoundException;

	Order inquire(String orderId) throws OrderNotFoundException;

}
