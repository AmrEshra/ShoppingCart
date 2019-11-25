package com.project.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.ProductNotFoundException;
import com.project.model.Product;
import com.project.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

    public Product getProductById(Long id) throws ProductNotFoundException  {
		return productRepository.findById(id).get();
	}

    public Product getProductByDescription(String description) throws ProductNotFoundException  {
		return productRepository.findByDescription(description);
	}
    
    public List<Product> getProductByCategory(String description) throws ProductNotFoundException  {
		return productRepository.findByCategoryDescription(description);
	}
    
}
