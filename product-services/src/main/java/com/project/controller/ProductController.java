package com.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.exception.ProductNotFoundException;
import com.project.model.Product;
import com.project.services.ProductService;

@RestController
public class ProductController {

	@Autowired
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
        this.productService = productService;
    }
	
	@GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> productList = productService.getAllProducts();
		HttpHeaders responseHeaders = new HttpHeaders();
        return new ResponseEntity<>(productList, responseHeaders, HttpStatus.OK);
    }
	
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException  {
		Product product = productService.getProductById(id);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
	}

	@GetMapping("/products/description/{description}")
	public ResponseEntity<Product> getProductByDescription(@PathVariable("description") String description) throws ProductNotFoundException  {
		Product product = productService.getProductByDescription(description);
		return new ResponseEntity<Product> (product, HttpStatus.OK);
	}

	@GetMapping("/products/category/{category}")
	public ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) throws ProductNotFoundException  {
		List<Product> products = productService.getProductByCategory(category);
		return new ResponseEntity<List<Product>> (products, HttpStatus.OK);
	}
}
