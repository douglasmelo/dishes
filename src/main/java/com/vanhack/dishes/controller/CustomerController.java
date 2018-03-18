package com.vanhack.dishes.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseStatus;
import com.vanhack.dishes.model.request.CustomerRequest;
import com.vanhack.dishes.model.response.CustomerResponse;
import com.vanhack.dishes.service.CustomerService;
import com.vanhack.dishes.utils.Locales;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Customer" })
@RequestMapping("/api/v1/customer")
public class CustomerController extends BaseRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired CustomerService customerService;

	@ApiOperation(value = "Create Customer")
	@PostMapping(produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<? extends ResponseDetail> create(
			@RequestHeader(value = "Accept-Language", required = false) String language,
			@RequestBody @Validated({CustomerRequest.Save.class}) CustomerRequest request,
			BindingResult result) {
		
		String method = "Customer Saver";

		LOGGER.info("{} Request: {}", method, request.toString());

		Locale locale = Locales.getLocale(language);
		
		try {
			String token = customerService.save(request);
			CustomerResponse response = new CustomerResponse(ResponseStatus.SUCCESS, token);
			LOGGER.info("{} Response: {}", method, response.toString());
			return new ResponseEntity<CustomerResponse>(response, HttpStatus.CREATED);
		} catch(Exception e) {
			return internalError(e, locale);
		}
	}
}
