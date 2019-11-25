package com.project.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.model.Cart;
import com.project.model.CartProduct;
import com.project.model.Order;
import com.project.model.OrderProduct;
import com.project.model.Status;
import com.project.repository.OrderProductRepository;
import com.project.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
    OrderRepository orderRepository;
	
	@Autowired
	OrderProductRepository orderProductRepository;

	@Autowired
    RestTemplate restTemplate;
	
    public OrderService(OrderRepository orderRepository, OrderProductRepository orderProductRepository, RestTemplate restTemplate) {
		this.orderRepository = orderRepository;
		this.orderProductRepository = orderProductRepository;
		this.restTemplate = restTemplate;
	}

	public List<Order> getOrderByCustomerId(Long customerId)  {
		return orderRepository.findByCustomerId(customerId);
	}
    
    public boolean createOrder(Long cartId) {
    	
    	Cart cart = restTemplate.getForObject("http://cart-services/cart/"+cartId, Cart.class);

    	Order order = Order.builder().cartId(
				cart.getId()).customerId(cart.getCustomerId()).customerUserName(cart.getCustomerUserName()).
				ordered(new Date()).status(Status.UNDER_REVIEW).build();
    	
    	List<OrderProduct> orderProductList = new ArrayList<OrderProduct>();
    	
    	Integer totals = 0;
    	for(CartProduct e : cart.getCartProducts()) {
    		totals += e.getQuantity() * e.getProductPrice().intValue();
    		
    		orderProductList.add(OrderProduct.builder().order(order).productId(e.getProductId()).
				productDescription(e.getProductDescription()).productPrice(e.getProductPrice()).quantity(e.getQuantity()).build());
    	}
    	
    	order.setTotal(new BigDecimal(totals));
    	order.setOrderProducts(orderProductList);
    	Order savedOrder = orderRepository.save(order);
    	
    	return savedOrder !=null;
	}
}
