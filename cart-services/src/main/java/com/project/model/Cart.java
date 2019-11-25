package com.project.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "carts")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "customer_id", nullable = false)
	private Long customerId;
	
	@Column(name = "customer_username", nullable = false)
	private String customerUserName;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cart")
	private List<CartProduct> cartProducts = new ArrayList<CartProduct>();
	
	@Builder
	public static Cart of(Long id, Long customerId, String customerUserName) {
		Cart cart = new Cart();
		
		cart.setId(id);
		cart.setCustomerId(customerId);
		cart.setCustomerUserName(customerUserName);
		return cart;
	}
}
