package com.project.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

	private Long id;
	private Long customerId;
	private String customerUserName;
	private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
}
