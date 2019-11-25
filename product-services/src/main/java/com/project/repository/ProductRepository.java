package com.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
	Product findByDescription(String description);
	
	@Query("SELECT p FROM Product AS p WHERE p.category.description = :category")
	List<Product> findByCategoryDescription(@Param("category") String category);
}
