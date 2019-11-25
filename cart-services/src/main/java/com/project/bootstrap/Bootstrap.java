package com.project.bootstrap;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.model.Cart;
import com.project.model.CartProduct;
import com.project.repository.CartProductRepository;
import com.project.repository.CartRepository;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

    private CartRepository cartRespository;
    private CartProductRepository cartProductRespository;

	public Bootstrap(CartRepository cartRespository, CartProductRepository cartProductRespository) {
		this.cartRespository = cartRespository;
		this.cartProductRespository = cartProductRespository;
	}

	@Override
    public void run(String... args) throws Exception {
    	fillCarts();
    }

	private void fillCarts() throws NoSuchAlgorithmException {
		
		Cart cart1 = Cart.builder().id(1L).customerId(1L).customerUserName("AmrEshra").build();
		cartRespository.save(cart1);
		cartProductRespository.save(CartProduct.builder().cart(cart1).productId(1L).productDescription("Camera").productPrice(new BigDecimal(300)).quantity(2).build());
		cartProductRespository.save(CartProduct.builder().cart(cart1).productId(6L).productDescription("Television").productPrice(new BigDecimal(1500)).quantity(1).build());
		cartProductRespository.save(CartProduct.builder().cart(cart1).productId(3L).productDescription("Sumsung Note 9").productPrice(new BigDecimal(1500)).quantity(6).build());
		cartProductRespository.save(CartProduct.builder().cart(cart1).productId(4L).productDescription("IPhone X").productPrice(new BigDecimal(2000)).quantity(1).build());
		
		Cart cart2 = Cart.builder().id(2L).customerId(2L).customerUserName("OlaMagdy").build();
		cartRespository.save(cart2);
		cartProductRespository.save(CartProduct.builder().cart(cart2).productId(2L).productDescription("Canon").productPrice(new BigDecimal(550)).quantity(1).build());
		cartProductRespository.save(CartProduct.builder().cart(cart2).productId(7L).productDescription("Washing Machine").productPrice(new BigDecimal(2000)).quantity(1).build());
		
		System.out.println("Carts Data Loaded = " + cartRespository.count() );
		System.out.println("Cart Products Data Loaded = " + cartProductRespository.count() );
	}

}
