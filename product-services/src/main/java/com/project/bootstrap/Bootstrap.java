package com.project.bootstrap;

import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.model.Category;
import com.project.model.Product;
import com.project.repository.CategoryRepository;
import com.project.repository.ProductRepository;

/**
 * Created by jt on 9/24/17.
 */
@Component
public class Bootstrap implements CommandLineRunner{

	private CategoryRepository categoryRespository;
    private ProductRepository productRespository;
    

    public Bootstrap(CategoryRepository categoryRespository, ProductRepository productRespository) {
    	this.categoryRespository = categoryRespository;
		this.productRespository = productRespository;
	}

	@Override
    public void run(String... args) throws Exception {
		fillCategories();
    	fillProducts();
    }

	private void fillCategories() throws NoSuchAlgorithmException {
		categoryRespository.save(Category.builder().description("Camera").build());
		categoryRespository.save(Category.builder().description("Phones").build());
		categoryRespository.save(Category.builder().description("Electronics").build());
		categoryRespository.save(Category.builder().description("Toys").build());

        System.out.println("Categories Data Loaded = " + categoryRespository.count() );
	}
	
	private void fillProducts() throws NoSuchAlgorithmException {
		
		Category camera = categoryRespository.findByDescription("Camera");
		productRespository.save(Product.builder().category(camera).description("Sony").price(new BigDecimal(300)).build());
		productRespository.save(Product.builder().category(camera).description("Canon").price(new BigDecimal(550)).build());
		
		Category phones = categoryRespository.findByDescription("Phones");
		productRespository.save(Product.builder().category(phones).description("Sumsung Note 9").price(new BigDecimal(1500)).build());
		productRespository.save(Product.builder().category(phones).description("IPhone X").price(new BigDecimal(2000)).build());
		productRespository.save(Product.builder().category(phones).description("OnePlus 6T").price(new BigDecimal(800)).build());
		
		Category electronics = categoryRespository.findByDescription("Electronics");
		productRespository.save(Product.builder().category(electronics).description("Television").price(new BigDecimal(1500)).build());
		productRespository.save(Product.builder().category(electronics).description("Washing Machine").price(new BigDecimal(2000)).build());
		productRespository.save(Product.builder().category(electronics).description("Receiver").price(new BigDecimal(800)).build());
		
		Category toys = categoryRespository.findByDescription("Toys");
		productRespository.save(Product.builder().category(toys).description("Baby Doll").price(new BigDecimal(1500)).build());
		productRespository.save(Product.builder().category(toys).description("Cay Toy").price(new BigDecimal(2000)).build());

		System.out.println("Products Data Loaded = " + productRespository.count() );

	}

}
