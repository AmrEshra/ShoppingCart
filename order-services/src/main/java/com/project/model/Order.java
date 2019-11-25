package com.project.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name= "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "cart_id", nullable = false)
	private Long cartId;
	
	@Column(name = "customer_id", nullable = false)
	private Long customerId;
	
	@Column(name = "customer_username", nullable = false)
	private String customerUserName;
	
	@Column(name = "ordered", nullable = false)
	private Date ordered;
	
	@Column(name = "status", nullable = false)
	@Enumerated(value = EnumType.STRING)
	private Status status;
	
	@Column(name = "total", nullable = false)
	private BigDecimal total;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
	private List<OrderProduct> orderProducts = new ArrayList<OrderProduct>();
	
	@Builder
	public static Order of(Long id, Long cartId, Long customerId, String customerUserName, Date ordered, Status status, BigDecimal total) {
		Order order = new Order();
		
		order.setId(id);
		order.setCartId(cartId);
		order.setCustomerId(customerId);
		order.setCustomerUserName(customerUserName);
		order.setStatus(status);
		order.setOrdered(ordered);
		order.setTotal(total);
		return order;
	}
}
