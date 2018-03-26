package com.vanhack.dishes.controller;

import java.util.Locale;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanhack.dishes.model.Order;
import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseStatus;
import com.vanhack.dishes.model.request.OrderRequest;
import com.vanhack.dishes.model.response.OrderInquireResponse;
import com.vanhack.dishes.service.OrderService;
import com.vanhack.dishes.utils.Locales;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Order" })
@RequestMapping("/api/v1/order")
public class OrderController extends BaseRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired private OrderService orderService;

	@ApiOperation(value = "Create Order")
	@org.springframework.web.bind.annotation.ResponseStatus(value = HttpStatus.CREATED)
	@PostMapping(produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<? extends ResponseDetail> create(
			@RequestHeader(value = "Accept-Language", required = false) String language,
			@RequestBody @Validated({OrderRequest.Save.class}) OrderRequest request,
			BindingResult result) throws Exception {

		String method = "Order Saver";

		LOGGER.info("{} Request: {}", method, request.toString());

		Locale locale = Locales.getLocale(language);
		
		try {
			orderService.save(request);
			return new ResponseEntity<ResponseDetail>(new ResponseDetail(ResponseStatus.SUCCESS), HttpStatus.CREATED);
		}catch (Exception e) {
			return internalError(e, locale);
		}
	}
	
	@ApiOperation(value = "Cancel Order")
	@PutMapping(value = "/{orderId}/cancel", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<? extends ResponseDetail> cancel(
			@RequestHeader(value = "Accept-Language", required = false) String language,
			@NotNull(message = "invalid.order.id") @NotEmpty(message = "invalid.order.id") @PathVariable(value = "orderId") String orderId){

		String method = "Cancel oder";

		LOGGER.info("{} Request: {}", method, orderId);

		Locale locale = Locales.getLocale(language);
		
		try {
			orderService.cancel(orderId);
			return new ResponseEntity<ResponseDetail>(new ResponseDetail(ResponseStatus.SUCCESS), HttpStatus.OK);
		}catch (Exception e) {
			return internalError(e, locale);
		}
	}
	
	@ApiOperation(value = "Status Order")
	@GetMapping(value = "/{orderId}/status", produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<? extends ResponseDetail> status(
			@RequestHeader(value = "Accept-Language", required = false) String language,
			@NotNull(message = "invalid.order.id") @NotEmpty(message = "invalid.order.id") @PathVariable(value = "orderId") String orderId){
		
		String method = "Status oder";
		
		LOGGER.info("{} Request: {}", method, orderId);
		
		Locale locale = Locales.getLocale(language);
		
		try {
			Order order = orderService.inquire(orderId);
			OrderInquireResponse response = new OrderInquireResponse(ResponseStatus.SUCCESS, order);
			return new ResponseEntity<ResponseDetail>(response, HttpStatus.OK);
		}catch (Exception e) {
			return internalError(e, locale);
		}
	}
}
