package com.project.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartProduct {

	private Long productId;
	private String productDescription;
	private BigDecimal productPrice;
	private Integer quantity;
	
}
