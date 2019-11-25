package com.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.model.OrderProduct;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long>{
	
}
