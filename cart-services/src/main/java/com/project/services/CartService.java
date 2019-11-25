package com.project.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.exception.CartNotFoundException;
import com.project.model.Cart;
import com.project.model.CartProduct;
import com.project.repository.CartProductRepository;
import com.project.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
    CartRepository cartRepository;
	
	@Autowired
	CartProductRepository cartProductRepository;

    public CartService(CartRepository cartRepository, CartProductRepository cartProductRepository) {
		this.cartRepository = cartRepository;
		this.cartProductRepository = cartProductRepository;
	}

	public List<Cart> getCartByCustomerId(Long customerId) throws CartNotFoundException  {
		return cartRepository.findByCustomerId(customerId);
	}
    
    public Cart getCartById(Long id) throws CartNotFoundException  {
		return cartRepository.findById(id).get();
	}

    public boolean createCart(Cart cart) {
    	Cart saved = cartRepository.save(cart);
    	return saved !=null;
	}
    
    public boolean addProductToCart(Long cartId, Long productId, String productDescription, BigDecimal productPrice, Integer quantity) throws CartNotFoundException {
    	
    	Cart cart = getCartById(cartId);
    	CartProduct saved =  cartProductRepository.save(CartProduct.builder().cart(cart).productId(productId).
    				productDescription(productDescription).productPrice(productPrice).quantity(quantity).build());
    	return saved !=null;
	}
    
}
