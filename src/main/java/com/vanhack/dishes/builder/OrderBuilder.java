package com.vanhack.dishes.builder;

import static java.util.Objects.isNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.vanhack.dishes.exception.CustomerNotFoundException;
import com.vanhack.dishes.exception.ProductNotFoundException;
import com.vanhack.dishes.model.Customer;
import com.vanhack.dishes.model.Order;
import com.vanhack.dishes.model.OrderItem;
import com.vanhack.dishes.model.OrderStatus;
import com.vanhack.dishes.model.Product;
import com.vanhack.dishes.model.request.OrderItemRequest;
import com.vanhack.dishes.model.request.OrderRequest;
import com.vanhack.dishes.repository.CustomerRepository;
import com.vanhack.dishes.repository.ProductRepository;

public class OrderBuilder {
  
	private CustomerRepository customerRepository;
	private ProductRepository productRepository;
	private OrderRequest orderRequest;
	
	private OrderBuilder(OrderRequest orderRequest, CustomerRepository customerRepository, ProductRepository productRepository) {
		this.productRepository = productRepository;
		this.customerRepository = customerRepository;
		this.orderRequest = orderRequest;
	}
	
	public static OrderBuilder New(OrderRequest orderRequest, CustomerRepository customerRepository, ProductRepository productRepository) {
		return new OrderBuilder(orderRequest, customerRepository, productRepository);
	}
	
	public Order build() throws CustomerNotFoundException {
		Order order = new Order();
		
		order.setContact(orderRequest.getContact());
		order.setCustomer(getCustomer());
		order.setDeliveryAddress(orderRequest.getDeliveryAddress());
		order.setStoreId(orderRequest.getStoreId());
		order.setTotal(orderRequest.getTotal());
		order.setStatus(OrderStatus.PENDING);
		order.setItems(buildOrderItems(orderRequest.getOrderItems(), order));
		
		return order;
	}

	private Collection<OrderItem> buildOrderItems(List<OrderItemRequest> orderItems, Order order) {
		return Optional.ofNullable(orderItems)
			.map(items -> items.stream()
					.map(itemRequest -> {
						OrderItem item = new OrderItem();
						item.setProduct(buildProduct(itemRequest.getProductId()));
						item.setPrice(itemRequest.getPrice());
						item.setQuantity(itemRequest.getQuantity());
						//TODO: Total could be calculated on backend
//						item.setTotal(itemRequest.getPrice().multiply(new BigDecimal(itemRequest.getQuantity())));
						item.setTotal(itemRequest.getTotal());
						item.setOrder(order);
						
						return item;
					}).collect(Collectors.toList()))
			.orElse(new ArrayList<>(0));
	}

	private Product buildProduct(String productId) throws ProductNotFoundException {
		Product product = productRepository.findByUuid(productId);
		if(isNull(product)) {
			throw new ProductNotFoundException();
		}
		return product;
	}

	private Customer getCustomer() throws CustomerNotFoundException {
		Customer customer = customerRepository.findByUuid(orderRequest.getCustomerId());
		if(isNull(customer)){
			throw new CustomerNotFoundException();
		}
		return customer;
	}
	
}
