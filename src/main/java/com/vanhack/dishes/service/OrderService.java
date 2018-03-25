package com.vanhack.dishes.service;

import com.vanhack.dishes.model.request.OrderRequest;

public interface OrderService {

	void save(OrderRequest request);

	void cancel(String orderId);

}
