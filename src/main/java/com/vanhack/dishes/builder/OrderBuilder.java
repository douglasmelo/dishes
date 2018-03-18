package com.vanhack.dishes.builder;

import com.vanhack.dishes.model.Order;
import com.vanhack.dishes.model.OrderStatus;
import com.vanhack.dishes.model.request.OrderRequest;

public class OrderBuilder {
  
	private OrderRequest orderRequest;
	
	private OrderBuilder(OrderRequest orderRequest) {
		this.orderRequest = orderRequest;
	}
	
	public static OrderBuilder New(OrderRequest orderRequest) {
		return new OrderBuilder(orderRequest);
	}
	
	public Order build() {
		Order order = new Order();
		
		order.setContact(orderRequest.getContact());
//		order.setCustomer(orderRequest.getCustomerUuid());
		order.setDeliveryAddress(orderRequest.getDeliveryAddress());
//		order.setItems(orderRequest.getOrderItems());
		order.setStoreId(orderRequest.getStoreId());
		order.setTotal(orderRequest.getTotal());
		order.setStatus(OrderStatus.PENDING);
		
		return order;
	}
	
}
