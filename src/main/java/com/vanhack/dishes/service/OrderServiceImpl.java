package com.vanhack.dishes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vanhack.dishes.builder.OrderBuilder;
import com.vanhack.dishes.model.Order;
import com.vanhack.dishes.model.OrderStatus;
import com.vanhack.dishes.model.request.OrderRequest;
import com.vanhack.dishes.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService{

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Autowired OrderRepository orderRepository;

	@Override
	@Transactional(noRollbackFor = Throwable.class)
	public void save(OrderRequest request) {

		LOGGER.info("Begin method save for order {}", request);

		//TODO: Send to RabbitMQ
		Order order = OrderBuilder.New(request).build();
		Order persistedOrder = orderRepository.save(order);

		LOGGER.info("Finish method save for order {}", persistedOrder);
	}

	@Override
	@Transactional(noRollbackFor = Throwable.class)
	public void cancel(Long orderId) {
		LOGGER.info("Begin method cancel for order {}", orderId);
		
		Order order = orderRepository.findOne(orderId);
		
		order.setStatus(OrderStatus.CANCELED);
		
		orderRepository.save(order);
		
		LOGGER.info("Finish method cancel for order {}", orderId);
		
	}

}
