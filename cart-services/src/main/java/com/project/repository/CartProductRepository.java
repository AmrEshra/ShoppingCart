package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.CartProduct;

@Repository
public interface CartProductRepository extends JpaRepository<CartProduct, Long>{
	
}
