package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.services.OrderService;

@RestController
public class OrderController {

	@Autowired
	private final OrderService orderService;
	
	@Autowired
    RestTemplate restTemplate;
	
	public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;
    }
	
	@RequestMapping(value = "/orders/createOrder/{cartId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> create(@PathVariable("cartId") Long cartId) {
		
		boolean saved = orderService.createOrder(cartId);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
}
