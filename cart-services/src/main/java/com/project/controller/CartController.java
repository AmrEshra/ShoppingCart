package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.project.exception.CartNotFoundException;
import com.project.exception.ProductNotFoundException;
import com.project.model.Cart;
import com.project.model.Product;
import com.project.services.CartService;

@RestController
public class CartController {

	@Autowired
	private final CartService cartService;
	
	@Autowired
    RestTemplate restTemplate;
	
	public CartController(CartService cartService, RestTemplate restTemplate) {
        this.cartService = cartService;
        this.restTemplate = restTemplate;
    }
	
	@GetMapping("/cart/{id}")
	public ResponseEntity<Cart> getCartById(@PathVariable("id") Long id) throws ProductNotFoundException, CartNotFoundException  {
		Cart cart = cartService.getCartById(id);
		return new ResponseEntity<Cart> (cart, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/carts/AddCart/{customerId}", method = RequestMethod.POST)
	public ResponseEntity<Boolean> create(@PathVariable("customerId") Long customerId) {
		boolean saved = false;
		
		String customerUserName = restTemplate.getForObject("http://customer-services/customer/"+customerId, String.class);
        
		saved = cartService.createCart(Cart.builder().customerId(customerId).customerUserName(customerUserName).build());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@RequestMapping(value = "/carts/AddProducts", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addProductToCart
		(@RequestParam("cartId") Long cartId, @RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity) throws CartNotFoundException {
		
		boolean saved = false;
		
		Product product = restTemplate.getForObject("http://product-services/product/"+productId, Product.class);
		
		saved = cartService.addProductToCart(cartId, productId, product.getDescription(), product.getPrice(), quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

}
