package com.vanhack.dishes.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vanhack.dishes.model.ResponseDetail;
import com.vanhack.dishes.model.ResponseStatus;
import com.vanhack.dishes.model.request.ProductRequest;
import com.vanhack.dishes.model.response.ProductListResponse;
import com.vanhack.dishes.model.response.ProductResponse;
import com.vanhack.dishes.model.response.ProductSearchResponse;
import com.vanhack.dishes.service.ProductService;
import com.vanhack.dishes.utils.Locales;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Product" })
@RequestMapping("/api/v1/product")
public class ProductController extends BaseRestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Autowired ProductService productService;

	@ApiOperation(value = "Create Product")
	@PostMapping(produces = APPLICATION_JSON, consumes = APPLICATION_JSON)
	public ResponseEntity<? extends ResponseDetail> create(
			@RequestHeader(value = "Accept-Language", required = false) String language,
			@RequestBody @Validated({ProductRequest.Save.class}) ProductRequest request,
			BindingResult result) {
		
		String method = "Product Saver";

		LOGGER.info("{} Request: {}", method, request.toString());

		Locale locale = Locales.getLocale(language);
		
		try {
			productService.save(request);
			ProductResponse response = new ProductResponse(ResponseStatus.SUCCESS);
			LOGGER.info("{} Response: {}", method, response.toString());
			return new ResponseEntity<ProductResponse>(response, HttpStatus.CREATED);
		} catch(Exception e) {
			return internalError(e, locale);
		}
	}
	
	@GetMapping(produces = APPLICATION_JSON)
	public ResponseEntity<? extends ResponseDetail> getProducts(
			@RequestHeader(value = "Accept-Language", required = false) String language,
			@RequestParam(value="storeId", required = false) Long storeId,
			@RequestParam(value="name", required = false) String name,
			@RequestParam(value="description", required = false) String description,
			@RequestParam(value="price", required = false) BigDecimal price
			){
		String method = "Get Products";

		LOGGER.info("{} by storeId: {}, name: {}, description: {}, price: {}", 
				method, storeId, name, description, price);

		Locale locale = Locales.getLocale(language);
		
		try {
			ProductRequest request = new ProductRequest();
			request.setStoreId(storeId);
			request.setName(name);
			request.setDescription(description);
			request.setPrice(price);
			
			ProductSearchResponse productSearchResponse = productService.getProducts(request);
			Collection<ProductResponse> productResponseList = new ArrayList<>();
			productSearchResponse.getProducts().forEach(product -> productResponseList.add(product.getProductResponse()));
			ProductListResponse response = new ProductListResponse(productSearchResponse.getTotal(), productResponseList);
			
			LOGGER.info("{} Response: {}", method, response.toString());
			return new ResponseEntity<ProductListResponse>(response, HttpStatus.OK);
		} catch(Exception e) {
			return internalError(e, locale);
		}
	}
}
